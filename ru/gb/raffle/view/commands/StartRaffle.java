package ru.gb.raffle.view.commands;

import ru.gb.raffle.view.ConsoleUI;

public class StartRaffle extends Command{
    public StartRaffle(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Start raffle";
    }

    @Override
    public void execute() {
        consoleUI.startRaffle();
    }
}
