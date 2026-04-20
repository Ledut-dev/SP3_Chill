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

    public String displaySearchMenu(User currentUser){
        String input = ui.promptText("""
                How do you want to search?
                1. Search by category
                2. Search by title
                3. Back to Main Menu""");
        switch (input){
            case "1":
                String categorySelection = selectFromList("Select a category:", Category.values());
                return categorySelection;
            case "2":
                String titleSelection = ui.promptText("Enter title:");
                return titleSelection;
            case "3":
                mainMenu(currentUser);
                break;
            default:
                ui.displayMsg("Please pick a valid option (numbers 1-3)");
                displaySearchMenu(currentUser);
                break;
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


    private String selectFromList(String message, ArrayList<Media> list){
        for (int i = 1; i <= list.size(); i++){
            message += "\n" + (i) + ". " + list.get(i).toString();
        }
        return ui.promptText(message);
    }

    private String selectFromList(String message, Category[] list){
        for (int i = 1; i <= list.length; i++){
            message += "\n" + (i) + ". " + list[i].toString();
        }
        return ui.promptText(message);
    }


}
