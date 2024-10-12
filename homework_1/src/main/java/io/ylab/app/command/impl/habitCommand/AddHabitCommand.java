package io.ylab.app.command.impl.habitCommand;

import io.ylab.app.command.Command;
import io.ylab.service.AutenticationService;
import io.ylab.service.HabitService;

public class AddHabitCommand implements Command {
    public AddHabitCommand(AutenticationService authentication, HabitService habitService) {
    }

    @Override
    public void execute() {
        System.out.println("Пока не поддерживатеся");
    }
}
