package ru.gb.raffle.view.commands;

import ru.gb.raffle.view.ConsoleUI;

public class DeleteToy extends Command{
    public DeleteToy(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Delete toy";
    }
    @Override
    public void execute() {
        consoleUI.deleteToy();
    }
}
