

import java.util.ArrayList;

public class User {

    private String userName;
    private String passWord;
    private ArrayList <String> wantToWatch;
    private ArrayList <String> watched;

    public User (String userName, String passWord){
        this.userName=userName;
        this.passWord=passWord;
        wantToWatch=new ArrayList<>();
        watched= new ArrayList<>();
    }

    public User (String userName, String passWord, ArrayList<String> wantToWatch, ArrayList<String> watched){
        this.userName=userName;
        this.passWord=passWord;
        this.wantToWatch=wantToWatch;
        this.watched=watched;
    }


    public void addMediaToList(Media m, ArrayList<Media> list){
        list.add(m);
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return passWord;
    }

    public ArrayList<String> getWantToWatch() {
        return wantToWatch;
    }

    public ArrayList<String> getWatched() {
        return watched;
    }
}
