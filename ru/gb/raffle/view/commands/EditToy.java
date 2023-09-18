package ru.gb.raffle.view.commands;

import ru.gb.raffle.view.ConsoleUI;

public class EditToy extends Command{
    public EditToy(ConsoleUI consoleUI){
        super(consoleUI);
        description = "Edit toy";
    }

    @Override
    public void execute() {
        consoleUI.editToy();
    }
}
