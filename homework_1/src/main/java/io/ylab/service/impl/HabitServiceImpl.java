package io.ylab.service.impl;

import io.ylab.model.Habit;
import io.ylab.repository.HabitRepository;
import io.ylab.service.HabitService;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class HabitServiceImpl implements HabitService {

    private final HabitRepository repository;

    public HabitServiceImpl(HabitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Habit save(Habit habit) {
        int id = Integer.valueOf(UUID.randomUUID().toString().substring(0, 5));
        LocalDate creationDate = LocalDate.now();
        habit.setId(id);
        habit.setCreationDate(creationDate);
        habit.setStatus(Habit.StatusHabit.NEW);
        return repository.save(habit);
    }

    @Override
    public Optional<Habit> getById(int id) {
        return Optional.of(repository.findById(id));
    }

    @Override
    public Iterator<Habit> getHabitsByUserId(int userId) {
        List<Habit> all = repository.findAll();
        return all
                .stream()
                .filter(habit -> habit.getIdOwner() == userId)
                .collect(Collectors.toList()).iterator();
    }

    @Override
    public Habit update(int id, Habit updatedHabit) {
        Habit habitById = repository.findById(id);
        habitById.setTitle(updatedHabit.getTitle());
        habitById.setDescription(updatedHabit.getDescription());
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }

    @Override
    public Habit changeStatus(int habitId, Habit.StatusHabit statusHabit) {
        Habit habitById = repository.findById(habitId);
        habitById.setStatus(statusHabit);
        return repository.findById(habitId);
    }
}
