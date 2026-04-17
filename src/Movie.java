import java.util.ArrayList;
import java.util.Objects;

public class Movie extends Media{


    protected int releaseYear;

    public Movie(String title, int releaseYear, ArrayList<Category> categories, double rating){
        super(title, categories, rating);
        this.releaseYear = releaseYear;
    }
}
