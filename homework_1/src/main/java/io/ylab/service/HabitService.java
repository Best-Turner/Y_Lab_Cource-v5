package io.ylab.service;

import io.ylab.model.Habit;

import java.util.Iterator;
import java.util.Optional;

public interface HabitService {

    Habit save(Habit habit);

    Optional<Habit> getById(int id);

    Iterator<Habit> getHabitsByUserId(int userId);

    Habit update(int id, Habit updatedHabit);

    boolean deleteById(int id);

    Habit changeStatus(int habitId, Habit.StatusHabit statusHabit);

}
