package io.ylab.service;

import io.ylab.model.User;

import java.util.Optional;

public class AutenticationService {
    private final UserService service;
    private User currentUser;
    private boolean isAuthentication;

    public AutenticationService(UserService service) {
        this.service = service;
    }

    public boolean authenticate(String email, String password) {
        Optional<User> userByEmail = service.findByEmail(email);
        if (userByEmail.isPresent() && userByEmail.get().getPassword().equals(password)) {
            currentUser = userByEmail.get();
            isAuthentication = true;
            return true;
        }
        return false;
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void logOut() {
        isAuthentication = false;
        currentUser = null;
    }

    public boolean isAuthentication() {
        return isAuthentication;
    }
}
