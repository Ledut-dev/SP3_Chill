import util.TextUI;
import java.util.ArrayList;

public class Menu {

    TextUI ui = new TextUI();
    User currentUser;
    public Menu(){

    }

    public String printLoginPrompt(){
        ui.displayMsg("INSERT SERVICE NAME HERE");
        String userInput = ui.promptText("""
                Select an option:
                
                1. Login
                
                2. Create new user""");
        return userInput;
    }

    public String printMainMenu(){ //mere lir, men skal vi vente til vi ved om vi har tid?

        String userInput = ui.promptText("Welcome "+currentUser.getUsername()+"""
       
       1. Search media
       
       2. Show saved lists
       
       3. Show all available movies
       
       4. Show all available series
       
       5. Quit""");

        return userInput;
    }

    public String printSearchMenu(){
        String input = ui.promptText("""
                How do you want to search?
                
                1. Search by category
                
                2. Search by title
                
                3. Back to Main Menu""");

        return input;
    }

    public String printListMenu(){
        String userInput = ui.promptText("""
                Which list would you like to view?
                
                 1. Want to watch
                
                 2. Watched
                
                 3. Back to Main Menu""");

        return userInput;
    }

    public String printMediaMenu(){
        String userInput = ui.promptText("""
                What would you like to do?
                
                1. Play Media
                
                2. Save to Want to Watch
                
                3. Remove from Want to
                
                4. Back to Main Menu""");
        return userInput;
    }

    public String printQuitMenu(){
        String userInput = ui.promptText("""
                Are you sure you want to quit?
                
                1. Quit
                
                2. Return to Main Menu""");
        return userInput;
    }

    //Do we keep ArrayList<String> vs media?
    public void displayList(ArrayList<Media> list){
        for (Media m : list){
            ui.displayMsg(m.toString());
        }
    }

    public String selectFromList(String message, ArrayList<Media> list){
        for (int i = 1; i <= list.size(); i++){
            message += "\n" + (i) + ". " + list.get(i-1).toString();
        }
        return ui.promptText(message);
    }

    public String selectFromList(String message, Category[] list){
        for (int i = 1; i <= list.length; i++){
            message += "\n" + (i) + ". " + list[i-1].toString();
        }
        return ui.promptText(message);
    }

    public void setCurrentUser(User u){
        this.currentUser = u;
    }

}
