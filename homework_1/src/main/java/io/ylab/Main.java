package io.ylab;

import io.ylab.app.HabitManagerApp;
import io.ylab.repository.HabitRepository;
import io.ylab.repository.UserRepository;
import io.ylab.repository.impl.HabitRepositoryImpl;
import io.ylab.repository.impl.UserRepositoryImpl;
import io.ylab.service.AutenticationService;
import io.ylab.service.HabitService;
import io.ylab.service.UserService;
import io.ylab.service.impl.HabitServiceImpl;
import io.ylab.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        HabitRepository habitRepository = HabitRepositoryImpl.getInstance();
        HabitService habitService = new HabitServiceImpl(habitRepository);
        UserRepository userRepository = UserRepositoryImpl.getInstance();

        UserService service = new UserServiceImpl(userRepository, habitService);

        AutenticationService authenticationService = new AutenticationService(service);
        HabitManagerApp app = new HabitManagerApp(authenticationService, service, habitService);
        app.run();

    }
}