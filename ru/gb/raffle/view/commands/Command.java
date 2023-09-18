package ru.gb.raffle.view.commands;

import ru.gb.raffle.view.ConsoleUI;

public abstract class Command {
    String description;
    ConsoleUI consoleUI;

    public Command(ConsoleUI consoleUI){
        this.consoleUI = consoleUI;
    }

    public String getDescription() {
        return description;
    }

    public abstract void execute();
}