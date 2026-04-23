import util.TextUI;

import java.util.ArrayList;

public class SearchEngine {
    private MediaLibrary mediaLibrary;

    TextUI textUI = new TextUI();


    public SearchEngine(){
        this.mediaLibrary = new MediaLibrary();
    }

    // Bruger skriver en filmtitel. Hvis den matcher tages brugeren til MediaOptions
    //TODO - Alt kommunikation og tekst til bruger skal gerne ske via "Menu", derfor skal denne tage String titel som argument.
    //End result -
    public Media filterByTitle(String searchArgument){
        //Brugervalg skal håndteres i Menu.
//        String input = textUI.promptText("Enter title:");
        for (Media media : mediaLibrary.getAllMedia()){
            if (searchArgument.equalsIgnoreCase(media.title)){
                return media;
            } else {
                textUI.displayMsg("Title not found.");
            }
        }
        return null;
    }

    // Tager et valg af category fra Menu (som String) og returnerer alle titler med denne category.
    public ArrayList<Media> filterByCategory(String categorySelectionAsString){
        int categorySelection = Integer.parseInt(categorySelectionAsString);
        ArrayList<Media> filteredList = new ArrayList<>();
        for (Media media : mediaLibrary.getAllMedia()){
            for (Category category : media.categories){
                if (Category.values()[categorySelection-1].equals(category)){
                    filteredList.add(media);
                }
            }
        }
        return filteredList;
    }



    public MediaLibrary getMediaLibrary(){
        return mediaLibrary;
    }

}
