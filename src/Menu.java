import util.TextUI;
import java.util.ArrayList;

public class Menu {

    TextUI ui = new TextUI();
    User currentUser;
    public Menu(){

    }

    public String printLoginPrompt(){
        ui.displayMsg("INSERT SERVICE NAME HERE");
        String userInput = ui.promptText("\nSelect an option:" + """
                
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
        switch (input){
            case "1":
                String categorySelection = selectFromList("Select a category:", Category.values());
                return categorySelection;
            case "2":
                String titleSelection = ui.promptText("Enter title:");
                return titleSelection;
            case "3":
                printMainMenu();
                break;
            default:
                ui.displayMsg("Please pick a valid option (numbers 1-3)");
                printSearchMenu();
                break;
        }
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

    //Do we keep ArrayList<String> vs media?
    public void displayList(ArrayList<Media> list){
        for (Media m : list){
            m.toString();
        }
    }

    private String selectFromList(String message, ArrayList<Media> list){
        for (int i = 1; i <= list.size(); i++){
            message += "\n" + (i) + ". " + list.get(i).toString();
        }
        return ui.promptText(message);
    }

    private String selectFromList(String message, Category[] list){
        for (int i = 1; i < list.length; i++){
            message += "\n" + (i) + ". " + list[i].toString();
        }
        return ui.promptText(message);
    }

    public void setCurrentUser(User u){
        this.currentUser = u;
    }

}
