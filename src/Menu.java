import util.TextUI;

import java.util.ArrayList;

public class Menu {

    TextUI ui = new TextUI();
    public Menu(){

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
