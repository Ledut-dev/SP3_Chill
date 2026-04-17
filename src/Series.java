import java.util.ArrayList;

public class Series extends Media{

    protected String releasePeriod;
    protected String[] seasons;

    public Series(String title, String releasePeriod, ArrayList<Category> categories, double rating, String[] seasons){
        super(title, categories, rating);
        this.releasePeriod = releasePeriod;
        this.seasons = seasons;
    }
}
