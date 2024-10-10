package io.ylab.app.command.impl;

import io.ylab.app.command.Command;
import io.ylab.service.UserService;

public class DeleteUserCommand implements Command {

    private final UserService service;

    public DeleteUserCommand(UserService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
