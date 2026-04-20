import util.TextUI;
import java.util.ArrayList;

public class Menu {

    TextUI ui = new TextUI();
    public Menu(){

    }


    public void mainMenu(User currentUser){ //mere lir, men skal vi vente til vi ved om vi har tid?

        ui.displayMsg("Welcome "+currentUser.getUserName());
        ui.displayMsg("Menu");
        ui.displayMsg("1. Search media");
        ui.displayMsg("2. Show saved lists");
        ui.displayMsg("3. Show all available movies");
        ui.displayMsg("4. Show all available series");
        ui.displayMsg("5. Quit");

        mainMenuOptions();


    }

    public void mainMenuOptions(){ //vil vi hellere switche en string fordi det fucker mindre op?

        int userInput= ui.promptNumeric("input choice");
        switch(userInput){

            case 1: //method call search menu
                break;
            case 2: //method call show user u/current users lists
                break;
            case 3: // method call show all movie titles
                break;
            case 4: //method call show all series titles
                break;
            case 5: // quit and save data
                break;
            default:

        }

    }

    public void displayListMenu(User currentUser){
        String userInput = ui.promptText("""
                Which list would you like to view?
                
                 1. Want to watch
                
                 2. Watched
                
                 3. Back to Main Menu""");
        switch (userInput){
            case "1":
                displayList(currentUser.getWantToWatch());
                break;
            case "2":
                displayList(currentUser.getWatched());
        }
    }

    //Do we keep ArrayList<String> vs media?
    public void displayList(ArrayList<String> list){
        for (String s : list){
            s.toString();
        }
    }






}
