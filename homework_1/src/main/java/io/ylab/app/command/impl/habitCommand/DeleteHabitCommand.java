package io.ylab.app.command.impl.habitCommand;

import io.ylab.app.command.Command;
import io.ylab.exception.AccessException;
import io.ylab.exception.HabitNotFoundException;
import io.ylab.exception.UserNotFoundException;
import io.ylab.service.AutenticationService;
import io.ylab.service.UserService;

import java.util.Scanner;

public class DeleteHabitCommand implements Command {
    private final AutenticationService autenticationService;
    private final UserService userService;

    private Scanner scanner;

    public DeleteHabitCommand(AutenticationService autenticationService, UserService userService) {
        this.autenticationService = autenticationService;
        this.userService = userService;
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        try {
            int inputHabitId = getInputHabitId();
            int userId = autenticationService.getCurrentUser().getId();
            userService.deleteHabitById(userId, inputHabitId);
        } catch (NumberFormatException | UserNotFoundException | HabitNotFoundException | AccessException e) {
            System.out.println(e.getMessage());
            System.out.println("Повторите попытку");
        }
    }

    private int getInputHabitId() throws NumberFormatException {
        System.out.println("\nВведите ID привычки которую хотите удалить:\n");
        int command = -1;
        String inputCommand = scanner.nextLine();
        command = Integer.parseInt(inputCommand);
        return command;
    }
}
