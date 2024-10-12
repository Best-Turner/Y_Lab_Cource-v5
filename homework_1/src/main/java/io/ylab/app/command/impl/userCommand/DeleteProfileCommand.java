package io.ylab.app.command.impl.userCommand;

import io.ylab.app.command.Command;
import io.ylab.service.AutenticationService;
import io.ylab.service.UserService;

public class DeleteProfileCommand implements Command {

    private final AutenticationService authentication;
    private final UserService userService;

    public DeleteProfileCommand(AutenticationService authentication, UserService userService) {
        this.authentication = authentication;
        this.userService = userService;
    }

    @Override
    public void execute() {
        userService.deleteById(authentication.getCurrentUser().getId());
        authentication.logOut();
        System.out.println("Вы удалили свой профиль");
    }
}
