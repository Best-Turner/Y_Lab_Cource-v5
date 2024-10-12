package io.ylab.app.command.impl;

import io.ylab.app.command.Command;

public class ProgramCloseCommand implements Command {
    @Override
    public void execute() {
        System.out.print("Программа завершает работу");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.exit(0);
    }
}
