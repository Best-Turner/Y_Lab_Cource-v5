package io.ylab.app.command.impl.habitCommand;

import io.ylab.app.command.Command;
import io.ylab.exception.AccessException;
import io.ylab.model.Habit;
import io.ylab.service.AutenticationService;
import io.ylab.service.HabitService;

import java.util.Optional;
import java.util.Scanner;

public class ChangeStatusHabitCommand implements Command {

    private final AutenticationService autenticationService;
    private final HabitService habitService;
    private Scanner scanner;

    public ChangeStatusHabitCommand(AutenticationService autenticationService, HabitService habitService) {
        this.autenticationService = autenticationService;
        this.habitService = habitService;
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        int habitIdToUpdate = getHabitIdToUpdate();
        Optional<Habit> habitById = habitService.getHabitById(habitIdToUpdate);
        if (habitById.isPresent() && habitById.get().getIdOwner() == autenticationService.getCurrentUser().getId()) {
            Habit.StatusHabit newStatus = Habit.StatusHabit.valueOf(getNewHabitStatus());
            Habit habit = habitService.changeStatus(habitIdToUpdate, newStatus);
            System.out.println(habit);
        } else {
            try {
                throw new AccessException("У вас нет такой привычки с ID = " + habitIdToUpdate);
            } catch (AccessException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getHabitIdToUpdate() throws IllegalArgumentException {
        System.out.println("Введите ID привычки, статус которой хотите изменить");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Вы ввели не числовое значение. Не надо так!!");
        }
    }

    private String getNewHabitStatus() {
        System.out.println("Выберете новый статус");
        Habit.StatusHabit[] values = Habit.StatusHabit.values();
        int count = 1;
        for (Habit.StatusHabit status : values) {
            System.out.println(count++ + " - " + status);
        }
        int i = scanner.nextInt();
        return values[i - 1].toString();
    }
}
