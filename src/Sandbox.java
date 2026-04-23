import util.FileIO;

import java.util.ArrayList;

public class Sandbox {



    // UPs = username & password.
    // Anvendes til at sammenligne brugerinput med eksisterende burgeres brugernavn og password.
    public ArrayList<String> getExistingUPs() {
        ArrayList<String> UPs = new ArrayList<>();
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData) {
            String[] attributes = line.split(";");
            String username = attributes[0].trim();
            String password = attributes[1].trim();
            UPs.add(username + ", " + password);
        }
        return UPs;
    }

    // Tager et brugernavn og returnere data fra CSV-filen.
    // Anvendes til at oprette en instance af User, når en bruger logger ind.
    public String[] getUserData(String username) {
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData) {
            String[] attributes = line.split(";");
            if (username.equals(attributes[0].trim())) {
                //Inden return skal denne laves om til ArrayList<String> da User tager ArrayList.
                return line.split(";");
            }
        }
        return null;
    }

//    public String printCurrentUser() {
//        return "currentUser=" + currentUser.getUsername() +
//                '}' +
//                "password=" + currentUser.getPassword() +
//                '}' +
//                "want to watch=" + currentUser.printWantToWatch() +
//                '}' +
//                "watched=" + currentUser.printWatched() +
//                '}';
//    }

//    //Do we keep ArrayList<String> vs media?
//    public void displayList(ArrayList<Media> list){
//        for (Media m : list){
//            ui.displayMsg(m.toString());
//        }
//    }

    // Tager en filtreret liste og beder bruger vælge en film derfra. Brugeren tages herefter til MediaOptions.
    public void displayFilteredList(ArrayList<Media> filteredList){
//        int mediaSelection = selectFromList("Select a title:", filteredList)-1;
//        textUI.displayMsg("You have chosen " + filteredList.get(mediaSelection).title);
        // MediaOptions.displayMediaOptions;
    }
}
