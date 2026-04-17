import util.FileIO;

import java.util.ArrayList;

import java.sql.Array;
import java.util.ArrayList;

public class System {

    private MediaLibrary mediaLibrary;

    public System(){
        this.mediaLibrary = new MediaLibrary();
    }

}


    // UPs = username & password.
    // Anvendes til at sammenligne brugerinput med eksisterende burgeres brugernavn og password.
    public ArrayList<String> getExistingUPs(){
        ArrayList<String> UPs = new ArrayList<>();
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData){
            String[] attributes = line.split(";");
            String username = attributes[0].trim();
            String password = attributes[1].trim();
            UPs.add(username + ", " + password);
        }
        return UPs;
    }

    // Tager et brugernavn og returnere data fra CSV-filen.
    // Anvendes til at oprette en instance af User, når en bruger logger ind.
    public String[] getUserData(String username){
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData){
            String[] attributes = line.split(";");
            if (username.equals(attributes[0].trim())){
                return line.split(",");
            }
        }
        return null;
    }
}