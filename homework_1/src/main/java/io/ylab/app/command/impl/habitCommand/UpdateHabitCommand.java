package io.ylab.app.command.impl.habitCommand;

import io.ylab.app.command.Command;
import io.ylab.exception.AccessException;
import io.ylab.model.Habit;
import io.ylab.service.AutenticationService;
import io.ylab.service.HabitService;

import java.util.Scanner;

public class UpdateHabitCommand implements Command {
    private final AutenticationService authenticationService;
    private final HabitService habitService;
    private Scanner scanner;

    public UpdateHabitCommand(AutenticationService authenticationService, HabitService habitService) {
        this.authenticationService = authenticationService;
        this.habitService = habitService;
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        int habitIdToUpdate = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                habitIdToUpdate = getHabitIdToUpdate();
                if (habitIdToUpdate == -1) {
                    System.out.println("Отмена операции.");
                    return;
                }

                Habit updatedHabit = new Habit();
                updatedHabit.setTitle(getInputTitle());
                updatedHabit.setDescription(getInputDescription());
                Habit update =
                        habitService.update(habitIdToUpdate, updatedHabit, authenticationService.getCurrentUser().getId());
                isValidInput = true;
                System.out.println("Изменение произошло успешно");
                System.out.println(update);
            } catch (IllegalArgumentException | AccessException e) {
                System.out.println(e.getMessage());
                System.out.println("Повторите попытку или введите -1 для отмены.");
            }
        }
    }

    private int getHabitIdToUpdate() throws IllegalArgumentException {
        System.out.println("Введите ID привычки, которую хотите изменить");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Вы ввели не числовое значение. Не надо так!!");
        }
    }

    private String getInputTitle() {
        return scanner.nextLine();
    }

    private String getInputDescription() {
        return scanner.nextLine();
    }

}
