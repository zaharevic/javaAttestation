package ru.gb.raffle.view;
import ru.gb.raffle.model.Toy;
import ru.gb.raffle.presenter.Presenter;
import java.util.Scanner;

public class ConsoleUI implements View{
    private static final String INPUT_ERROR = "You entered an incorrect value";
    private Scanner scanner;
    private Presenter presenter;
    private boolean work;
    private MainMenu menu;

    public ConsoleUI(){
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        work = true;
        menu = new MainMenu(this);
    }

    @Override
    public void start() {
        hello();
        while (work){
            printMenu();
            execute();
        }
    }

    private void inputError(){
        System.err.println(INPUT_ERROR);
    }

    private void hello(){
        System.out.println("Welcome!");
    }

    private void printMenu(){
        System.out.println(menu.menu());
    }

    private void execute(){
        String line = scanner.nextLine();
        if (checkTextForInt(line)){
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)){
                menu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text){
        if (text.matches("[0-9]+")){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    private boolean checkCommand(int numCommand){
        if (numCommand < (menu.getSize()+1)){
            return true;
        } else {
            inputError();
            return false;
        }
    }

    public void addNewToy(){
        boolean nameFlag = true;
        boolean probabilityFlag= true;
        boolean countFlag = true;
        long probability = -1;
        long count = -1;
        String name = null;

        while(nameFlag) {
            System.out.println("Enter toy name: ");
            name = scanner.nextLine();
            if(!name.isEmpty()){
                nameFlag = false;
            }
            else{
                inputError();
            }
        }

        while (probabilityFlag){
            System.out.printf("Enter probability of winning in perсents (Max probability equal %d): \n", presenter.getMaxProbability());
            String tempProb = scanner.nextLine();
            if(isDigit(tempProb)){
                probability = Long.parseLong(tempProb);
                if(probability > presenter.getMaxProbability() || probability < 1){
                    inputError();
                } else {
                    probabilityFlag = false;
                }
            } else {
                inputError();
            }
        }

        while(countFlag){
            System.out.println("Enter count: ");
            String tempCount = scanner.nextLine();
            if(isDigit(tempCount)){
                count = Long.parseLong(tempCount);
                if (count < 1){
                    inputError();
                }else {
                    countFlag = false;
                }
            }else {
                inputError();
            }
        }

        if(presenter.addNewToy(name, probability, count)){
            System.out.println("Toy added sucsessfuly!");
        }else {
            inputError();
            System.err.println("Try again!");
        }
    }

    private boolean isDigit(String tempProb) {
        try{
            Long.parseLong(tempProb);
            return true;
        }catch (NumberFormatException e){
            inputError();
            return false;
        }
    }

    private void printList(){
        presenter.printList();
    }

    public void deleteToy(){
        boolean deleteFlag = true;

        while (deleteFlag){
            printList();
            System.out.println("Enter toy ID: ");
            String strID = scanner.nextLine();
            if(isDigit(strID)){
                long id = Long.parseLong(strID);
                if(presenter.deleteToy(id)){
                    System.out.println("Deleted sucessfully!");
                    deleteFlag = false;
                }
                else {
                    inputError();
                }
            }else {
                inputError();
            }
        }
    }

    public void startRaffle(){
        long quantity = 0;
        boolean raffleFlag = true;

        while (raffleFlag){
            System.out.println("Enter count of winers: ");
            String tempQuantity = scanner.nextLine();
            if(isDigit(tempQuantity)){
                quantity = Long.parseLong(tempQuantity);
                if(quantity > presenter.getAllCount()){
                    inputError();
                }else {
                    raffleFlag = false;
                }
            }else{
                inputError();
            }
        }

        presenter.startRaffle(quantity);
    }

    public void editToy(){
        boolean nameFlag = true;
        boolean probabilityFlag= true;
        boolean countFlag = true;
        boolean idFlag = true;
        long probability = -1;
        long count = -1;
        long id = -1;
        String name = null;

        while (idFlag){
            printList();
            System.out.println("Enter toy ID:");
            String strID = scanner.nextLine();
            if(isDigit(strID)){
                id = Long.parseLong(strID);
                if(presenter.getById(id) != null){
                    idFlag = false;
                }
                else {
                    inputError();
                }
            }else {
                inputError();
            }
        }

        Toy toy = presenter.getById(id);

        while(nameFlag) {
            System.out.println("Enter toy name: ");
            name = scanner.nextLine();
            if(!name.isEmpty()){
                nameFlag = false;
            }
            else{
                inputError();
            }
        }

        while (probabilityFlag){
            System.out.printf("Enter probability of winning in perсents (Max probability equal %d): \n", presenter.getMaxProbability() + toy.getProbabilityOfWinning());
            String tempProb = scanner.nextLine();
            if(isDigit(tempProb)){
                probability = Long.parseLong(tempProb);
                if(probability > presenter.getMaxProbability() + toy.getProbabilityOfWinning() || probability < 1){
                    inputError();
                } else {
                    probabilityFlag = false;
                }
            } else {
                inputError();
            }
        }

        while(countFlag){
            System.out.println("Enter count: ");
            String tempCount = scanner.nextLine();
            if(isDigit(tempCount)){
                count = Long.parseLong(tempCount);
                if (count < 1){
                    inputError();
                }else {
                    countFlag = false;
                }
            }else {
                inputError();
            }
        }

        if(presenter.editToy(toy, name, probability, count)){
            System.out.println("Toy edit sucsessfuly!");
        }else {
            inputError();
            System.err.println("Try again!");
        }
    }

    private void goodbye(){
        System.out.println("Goodbye!");
    }

    public void stop(){
        goodbye();
        work = false;
    }
}
