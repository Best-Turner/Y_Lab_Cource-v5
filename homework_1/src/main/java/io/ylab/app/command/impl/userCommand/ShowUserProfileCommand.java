package io.ylab.app.command.impl.userCommand;

import io.ylab.app.command.Command;
import io.ylab.model.User;
import io.ylab.service.AutenticationService;
import io.ylab.service.UserService;

import java.util.Optional;

public class ShowUserProfileCommand implements Command {

    private final AutenticationService authenticationService;
    private final UserService userService;

    public ShowUserProfileCommand(AutenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public void execute() {
        Optional<User> byId = userService.findById(authenticationService.getCurrentUser().getId());
        System.out.println(byId.get());
    }
}
