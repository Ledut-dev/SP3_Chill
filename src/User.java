import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList <Media> wantToWatch; // media liste?
    private ArrayList <Media> watched; // media liste?

    public User (String username, String password){
        this.username=username;
        this.password=password;
        wantToWatch=new ArrayList<>();
        watched= new ArrayList<>();
    }

    public User (String username, String password, ArrayList<Media> wantToWatch, ArrayList<Media> watched){
        this.username=username;
        this.password=password;
        this.wantToWatch=wantToWatch;
        this.watched=watched;
    }

    public void addMediaToList(Media m, ArrayList<Media> list){
        list.add(m);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Media> getWantToWatch(){
        return this.wantToWatch;
    }

    public ArrayList<Media> getWatched(){
        return this.wantToWatch;
    }

    public String printWantToWatch() {
        String output="";
        for (Media m:wantToWatch){
            output+=(m.title+", ");
        }return output;
    }

    public String printWatched() {
        String output="";
        for (Media m:watched){
            output+=(m.title+", ");
        }return output;
    }





}
