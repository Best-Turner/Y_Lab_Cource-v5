package io.ylab.repository.impl;

import io.ylab.model.Habit;
import io.ylab.repository.HabitRepository;

import java.util.ArrayList;
import java.util.List;

public class HabitRepositoryImpl implements HabitRepository {

    private static HabitRepositoryImpl instance;

    private final List<Habit> habitList;

    private HabitRepositoryImpl() {
        habitList = new ArrayList<>();
    }

    public static HabitRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new HabitRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Habit save(Habit habit) {
        habitList.add(habit);
        return habit;
    }

    @Override
    public Habit findById(Integer id) {
        return habitList.get(id);
    }

    @Override
    public List<Habit> findAll() {
        return habitList;
    }

    @Override
    public boolean deleteById(Integer id) {
        return habitList.removeIf(habit -> habit.getId() == id);
    }
}
