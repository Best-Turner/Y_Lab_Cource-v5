package io.ylab.app.command.impl.habitCommand;

import io.ylab.app.command.Command;
import io.ylab.model.Habit;
import io.ylab.service.AutenticationService;
import io.ylab.service.UserService;

import java.util.Iterator;

public class ShowAllMyHabitCommand implements Command {

    private final AutenticationService authenticationService;
    private final UserService userService;

    public ShowAllMyHabitCommand(AutenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("Список ваших привычек");
        Iterator<Habit> habits = userService.getHabits(authenticationService.getCurrentUser().getId());
        while (habits.hasNext()) {
            System.out.println(habits.next());
        }
    }
}
