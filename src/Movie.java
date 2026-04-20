import java.util.ArrayList;
import java.util.Objects;

public class Movie extends Media{


    protected int releaseYear;

    public Movie(String title, int releaseYear, ArrayList<Category> categories, double rating){
        super(title, categories, rating);
        this.releaseYear = releaseYear;
    }

    public void play(){
        System.out.println("Now playing: "+title+ "\nRelease year: "+releaseYear
             );
        //media tilføjes til users liste via Service


    }

    public String toString(){
        return "Title: "+title;
    }
}
