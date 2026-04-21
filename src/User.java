import java.util.ArrayList;

public class User {

    private String userName;
    private String passWord;
    private ArrayList <String> wantToWatch; // media liste?
    private ArrayList <String> watched; // media liste?

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

    //Users lists are still Strings, can either have the lists be media itself, or potentially save just the titles as strings,
    //and later match the titles to corresponding media object in MediaLibrary allMedia list.
    //That way it's not needed to save all media data to each user, but we can still create media objects when loading current user.
    public void addMediaToList(Media m, ArrayList<Media> list){
        list.add(m);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public ArrayList<String> getWantToWatch(){
        return this.wantToWatch;
    }

    public ArrayList<String> getWatched(){
        return this.wantToWatch;
    }

    public String printWantToWatch() {
        String output="";
        for (String s:wantToWatch){
            output+=(s+", ");
        }return output;
    }

    public String printWatched() {
        String output="";
        for (String s:watched){
            output+=(s+", ");
        }return output;
    }





}
