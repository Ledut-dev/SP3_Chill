import util.TextUI;

public class Menu {

    TextUI ui = new TextUI();


    public void mainMenu(User u){ //mere lir, men skal vi vente til vi ved om vi har tid?

        ui.displayMsg("Welcome "+u.getUserName());
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




}
