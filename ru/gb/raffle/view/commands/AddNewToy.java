package ru.gb.raffle.view.commands;

import ru.gb.raffle.view.ConsoleUI;

public class AddNewToy extends Command{
    public AddNewToy(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Add new toy";
    }

    @Override
    public void execute() {
        consoleUI.addNewToy();
    }
}
