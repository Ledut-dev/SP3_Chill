import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList <Media> wantToWatch;
    private ArrayList <Media> watched;
    private Media currentMedia;

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

    public void addMediaToWatched(){
      this.watched.add(currentMedia);
    }

    public void addMediaToWantToWatch(){
        this.wantToWatch.add(currentMedia);
    }

    public void removeFromWantToWatch(){
        for (Media m : watched){
            if (currentMedia.title.equalsIgnoreCase(m.title)){
                wantToWatch.remove(m);
            }
        }
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
        return this.watched;
    }

    public Media getCurrentMedia(){
        return this.currentMedia;
    }

    public void setCurrentMedia(Media m){
        this.currentMedia = m;
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
