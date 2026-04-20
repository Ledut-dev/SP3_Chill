import util.TextUI;

import java.util.ArrayList;

public class SearchEngine {
    /*
    Jeg foreslår, at vi rykker denne ArrayList hertil, da SearchEngine naturligvis skal bruge adgangen,
    mens MediaLibrary nok alligevel vil gå gennem SearchEngine hver gang.
    Eventuelt kan man kombinere MediaLibrary og SearchEngine.
     */
    TextUI textUI = new TextUI();


    // Bruger skriver en filmtitel. Hvis den matcher tages brugeren til MediaOptions
    public void displayTitleSearch(){
        String input = textUI.promptText("Enter title:");
        for (Media media : allMedia){
            if (input.equalsIgnoreCase(media.title)){
                textUI.displayMsg("You have chosen " + media.title);
                // MediaOptions.displayMediaOptions;
            } else {
                textUI.displayMsg("Title not found.");
                displaySearchMenu();
            }
        }
    }

    // Beder brugeren om at vælge en category fra listen og returnerer alle titler med denne category.
    public ArrayList<Media> displayCategorySearch(){
        int categorySelection = selectFromList("Select a category:", Category.values())-1;

        ArrayList<Media> filteredList = new ArrayList<>();
        for (Media media : allMedia){
            for (Category category : media.categories){
                if (Category.values()[categorySelection].equals(category)){
                    filteredList.add(media);
                }
            }
        }
        return filteredList;
    }

    // Tager en filtreret liste og beder bruger vælge en film derfra. Brugeren tages herefter til MediaOptions.
    public void displayFilteredList(ArrayList<Media> filteredList){
        int mediaSelection = selectFromList("Select a title:", filteredList)-1;
        textUI.displayMsg("You have chosen " + filteredList.get(mediaSelection).title);
        // MediaOptions.displayMediaOptions;
    }

    private int selectFromList(String message, ArrayList<Media> list){
        for (int i = 0; i < list.size(); i++){
            message += "\n" + (i+1) + ". " + list.get(i).toString();
        }
        return textUI.promptNumeric(message);
    }

    private int selectFromList(String message, Category[] list){
        for (int i = 0; i < list.length; i++){
            message += "\n" + (i+1) + ". " + list[i].toString();
        }
        return textUI.promptNumeric(message);
    }
}
