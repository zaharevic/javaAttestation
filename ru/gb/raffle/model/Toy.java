package ru.gb.raffle.model;

public class Toy implements Comparable<Toy>{
    private long id;
    private String name;
    private long
            probabilityOfWinning;
    private long count;

    public Toy(long id, String name, long probabilityOfWinning, long count){
        this.id = id;
        this.name = name;
        this.probabilityOfWinning = probabilityOfWinning;
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getProbabilityOfWinning() {
        return probabilityOfWinning;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProbabilityOfWinning(long probabilityOfWinning) {
        this.probabilityOfWinning = probabilityOfWinning;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return  false;
        }
        return name.equals(((Toy) o).name);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ");
        sb.append(getId());
        sb.append(", name: ");
        sb.append(getName());
        sb.append(", count: ");
        sb.append(getCount());
        sb.append(", probability: ");
        sb.append(getProbabilityOfWinning());
        return sb.toString();
    }

    @Override
    public int compareTo(Toy o) {
        return Long.compare(this.probabilityOfWinning, o.probabilityOfWinning);
    }
}
