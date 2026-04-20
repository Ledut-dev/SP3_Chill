import util.FileIO;
import util.TextUI;

import java.util.ArrayList;

public class PickMedia {

    private MediaLibrary mediaLibrary;
    private User currentUser;
    private Menu menu;
    private ArrayList<User> users;
    TextUI ui;

    public PickMedia() {
        this.mediaLibrary = new MediaLibrary();
        users = new ArrayList<User>();
    }


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

    void createExistingUser (String userName){ //hvad gør vi med existing users arraylister?
        String [] attribute =getUserData(userName);

        ArrayList <String> wantToWatch = new ArrayList<>();
        String line = attribute[2];
        String [] titles= line.split(",");
        for (String s: titles){
            wantToWatch.add(s.trim());
        }

        ArrayList <String> watched = new ArrayList<>();
        String line2 = attribute[3];
        String [] titles2= line2.split(",");
        for (String t: titles2){
            watched.add(t.trim());
            }

        User u = new User(attribute[0].trim(),attribute[1].trim(), wantToWatch,watched);
        currentUser=u;
        users.add(u);
    }


    void promptCreateNewUser(){
        String userName=ui.promptText("Input username");
        String passWord=ui.promptText("input password");

        createNewUser(userName,passWord);

    }

    void createNewUser (String name, String password){
        User u=new User(name, password);
        users.add(u);
    }



    public String printCurrentUser() {
        return "currentUser=" + currentUser.getUserName() +
                '}' +
                "password=" + currentUser.getPassWord() +
                '}'+
                "want to watch=" + currentUser.printWantToWatch() +
                '}'+
                "watched=" + currentUser.printWatched() +
                '}';

    }
}
