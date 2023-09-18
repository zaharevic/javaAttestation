package ru.gb.raffle.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Service {
    private ArrayList<Toy> toys;
    private PriorityQueue<Toy> probability;
    private long objectID;

    public Service(){
        toys = new ArrayList<>();
        probability = new PriorityQueue<>();
        objectID = 0;
    }

    public boolean addNewToy(String name, long probability, long count){
        Toy tempToy = new Toy(-1, name, probability, count);
        if (!toys.contains(tempToy) || toys.size() == 0){
            Toy toy = new Toy(objectID++, name, probability, count);
            toys.add(toy);
            return true;
        }
        else{
            return false;
        }
    }

    public void printList(){
        StringBuilder sb = new StringBuilder();
        sb.append("List: \n");
        for(Toy toy: toys){
            sb.append(toy.toString());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public Toy getByID(long id){
        for(Toy toy: toys){
            if(toy.getId() == id){
                return toy;
            }
        }
        return null;
    }

    public boolean deleteToy(long id){
        Toy toy = getByID(id);
        if(toy != null){
            toys.remove(toy);
            return true;
        }
        else {
            return false;
        }
    }

    public void startRaffle(long quantity){
        Toy winingToy = null;
        StringBuilder sb = new StringBuilder();
        sb.append("Winners List: \n ***\n");


        for(int i = 0; i < quantity; i++){
           winingToy = raffle();
           sb.append("Toy number ");
           sb.append(i+1);
           sb.append(": ");
           sb.append(winingToy.getName());
           sb.append(", the nubmer of remaining of this item: ");
           sb.append(winingToy.getCount());
           sb.append("\n");
        }

        System.out.println(sb.toString());
        writeResulToFile(sb.toString());
    }

    private long getAllProb(){
        long res = 0;
        for(Toy toy: toys){
            if(toy.getCount() != 0) {
                res += toy.getProbabilityOfWinning();
            }
        }
        return res -1;
    }

    private Toy raffle(){
        int nowProbability = 0;
        Toy resultToy = null;
        long allProb = getAllProb();

        long randomRes = (long)(Math.random() * 99) + 1;
        for(Toy toy: toys){
            nowProbability += toy.getProbabilityOfWinning();
            if(nowProbability >= randomRes){
                resultToy = toy;
                toy.setCount(toy.getCount() - 1);
                if (toy.getCount() == 0) {
                    allProb -= toy.getProbabilityOfWinning();
                }
                return resultToy;
            }
        }
        return null;
    }

    private void writeResulToFile(String stringForWriting){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("toy_results.txt"))) {
            writer.write(stringForWriting);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getMaxProbability(){
        long maxProbability = 100;
        for(Toy toy: toys){
            maxProbability -= toy.getProbabilityOfWinning();
        }
        if(maxProbability >0){
            return maxProbability;
        }else {
            return 0;
        }
    }

    public boolean editToy(Toy toy,String name, long prob, long count){
        toy.setName(name);
        toy.setProbabilityOfWinning(prob);
        toy.setCount(count);
        return true;
    }

    public long getAllCount(){
        long allCount = 0;
        for(Toy toy: toys){
            allCount += toy.getCount();
        }
        return allCount;
    }
}
