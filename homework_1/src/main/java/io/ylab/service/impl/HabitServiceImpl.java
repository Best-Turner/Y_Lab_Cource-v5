package io.ylab.service.impl;

import io.ylab.exception.AccessException;
import io.ylab.model.Habit;
import io.ylab.repository.HabitRepository;
import io.ylab.service.HabitService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class HabitServiceImpl implements HabitService {

    private final HabitRepository repository;

    public HabitServiceImpl(HabitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Habit save(Habit habit) {
        int id = getRandomId();
        LocalDate creationDate = LocalDate.now();
        habit.setId(id);
        habit.setCreationDate(creationDate);
        habit.setStatus(Habit.StatusHabit.NEW);
        return repository.save(habit);
    }

    @Override
    public Optional<Habit> getHabitById(int id) {
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
    public Habit update(int id, Habit updatedHabit, int currentUserId) throws AccessException {
        Habit habitById = repository.findById(id);
        if (habitById.getIdOwner() != currentUserId) {
            throw new AccessException("У вас нет привычки с ID = " + id);
        }
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

    private int getRandomId() {
        Random random = new Random();
        return random.nextInt(100);
    }
}
