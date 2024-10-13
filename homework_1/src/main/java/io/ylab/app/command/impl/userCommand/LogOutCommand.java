package io.ylab.app.command.impl.userCommand;

import io.ylab.app.command.Command;
import io.ylab.service.AutenticationService;

public class LogOutCommand implements Command {
    private final AutenticationService autenticationService;
    public LogOutCommand(AutenticationService authentication) {
        this.autenticationService = authentication;
    }

    @Override
    public void execute() {
        autenticationService.logOut();
    }
}
