import java.util.ArrayList;

public class Series extends Media{

    protected String releasePeriod;
    protected String[] seasons;

    public Series(String title, String releasePeriod, ArrayList<Category> categories, double rating, String[] seasons){
        super(title, categories, rating);
        this.releasePeriod = releasePeriod;
        this.seasons = seasons;
    }
    public void play(){
       System.out.println("Now playing: "+title+ "\nRelease year: "+releasePeriod);
       //kunne være nice at skrive season + episode, kræver flere klasser
        //media tilføjes til users liste via Service


    }

    public String toString(){
        return "Title: "+title;
    }
}
