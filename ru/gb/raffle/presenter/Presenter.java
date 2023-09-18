package ru.gb.raffle.presenter;

import ru.gb.raffle.model.Service;
import ru.gb.raffle.model.Toy;
import ru.gb.raffle.view.View;

public class Presenter {
    private View view;
    private Service service;

    public Presenter(View view){
        this.view = view;
        service = new Service();
    }

    public boolean addNewToy(String name, long prob, long count){
        return service.addNewToy(name, prob, count);
    }

    public void printList(){
        service.printList();
    }

    public boolean deleteToy(long id){
        return service.deleteToy(id);
    }

    public void startRaffle(long quantity){
        service.startRaffle(quantity);
    }

    public long getMaxProbability(){return service.getMaxProbability();}

    public Toy getById(long id){
        return service.getByID(id);
    }

    public boolean editToy(Toy toy, String name, long prob, long count){
        return service.editToy(toy, name, prob, count);
    }

    public long getAllCount(){
        return service.getAllCount();
    }
}
