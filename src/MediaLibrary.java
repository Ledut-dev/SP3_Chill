import util.FileIO;

import java.sql.Array;
import java.util.ArrayList;

public class MediaLibrary {

    private ArrayList<Media> allMedia;

    public MediaLibrary(){
        this.allMedia = new ArrayList<>();
        createMedia();
    }

    public void createMedia(){
        //Parsing movie data and creating movie objects
        ArrayList<String> savedMovies = FileIO.readData("data/movies.csv");
        for (String s : savedMovies){
            String[] mediaData = s.split(";");

            String title = mediaData[0].trim();
            int releaseYear = Integer.parseInt(mediaData[1].trim());

            //Create ArrayList of categories, and then add each category found in data file as a Category enum
            ArrayList<Category> categories = new ArrayList<>();
            for (String category : mediaData[2].split(",")){
                categories.add(Category.valueOf(category.toLowerCase().trim()));
            }

            double rating = Double.parseDouble(mediaData[3].trim());

            allMedia.add(new Movie(title, releaseYear, categories, rating));
        }


        //Parsing series.csv data and creating series.csv objects
        //Potentially should have an "episode" object, where choosing a series.csv possibly prompts the user to pick an episode.
        //Currently, Series acts exactly as a Movie, except with more fields
        ArrayList<String> savedSeries = FileIO.readData("data/series.csv");
        for (String s : savedSeries){
            String[] mediaData = s.split(";");

            String title = mediaData[0].trim();
            String releasePeriod = mediaData[1].trim();

            //Create ArrayList of categories, and then add each category found in data file as a Category enum
            ArrayList<Category> categories = new ArrayList<>();
            for (String category : mediaData[2].split(",")){
                categories.add(Category.valueOf(category.toLowerCase().trim()));
            }

            double rating = Double.parseDouble(mediaData[3].trim());
            //Splitting seasons into array as they are separated with a "," in our CSV file.
            String[] seasons = mediaData[4].split(",");

            allMedia.add(new Series(title, releasePeriod, categories, rating, seasons));
        }
    }

    public void displayArray(ArrayList<Media> list){
        for (Media m : list){
            //We cannot name this class "System" as it's already a part of Java.lang
            //System.out.println(m.toString());

            //This would work if we don't want to rename.
            java.lang.System.out.println(m.toString());
        }
    }

    public ArrayList<Media> getAllMedia() {
        return this.allMedia;
    }
}
