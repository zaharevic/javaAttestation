package ru.gb.raffle.view.commands;

import ru.gb.raffle.view.ConsoleUI;

public class Stop extends Command{
    public Stop(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Stop work";
    }

    @Override
    public void execute() {
        consoleUI.stop();
    }
}
