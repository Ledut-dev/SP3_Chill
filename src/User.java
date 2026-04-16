import java.util.ArrayList;

public class User {
    private String userName;
    private String passWord;
    private ArrayList <Media> wantToWatch;
    private ArrayList <Media> watched;

    public User (String userName, String passWord){
        this.userName=userName;
        this.passWord=passWord;
        wantToWatch=new ArrayList<>();
        watched= new ArrayList<>();
    }

    public User (String userName, String passWord, ArrayList<Media> wantToWatch, ArrayList<Media> watched){
        this.userName=userName;
        this.passWord=passWord;
        this.wantToWatch=wantToWatch;
        this.watched=watched;
    }


    public void addMediaToList(Media m, ArrayList<Media> list){
        list.add(m);
    }



}



//class User{
//- String username
//- String password
//- ArrayList<Media> wantToWatch
//- ArrayList<Media> watched
//+ void addMediaToList(Media, ArrayList<Media>)
//}