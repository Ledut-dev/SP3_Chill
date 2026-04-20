import java.util.ArrayList;

public abstract class Media {

    protected String title;
    protected ArrayList<Category> categories;
    protected double rating;

    public Media(String title, ArrayList<Category> categories, double rating){
        this.title = title;
        this.categories = categories;
        this.rating = rating;

    }

    public abstract void play();

    public abstract String toString();
}
