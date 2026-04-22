import util.FileIO;
import util.TextUI;

import java.io.File;
import java.util.ArrayList;

public class Service {

    private SearchEngine searchEngine;
    private User currentUser;
    private Menu menu;
    private ArrayList<User> users;
    TextUI ui = new TextUI();

    public Service() {
        this.menu = new Menu();
        this.searchEngine = new SearchEngine();
        users = new ArrayList<User>();
        createAllUsers();
    }


    public void startMenuLoop() {
        loginPrompt();
        mainMenuOptions();
    }

    public void loginPrompt() {

        switch (menu.printLoginPrompt()) {
            case "1":
                login();
                break;
            case "2":
                String username = ui.promptText("Enter username:");
                String password = ui.promptText("Enter password:");
                createNewUser(username, password);
                break;
            default:
                ui.displayMsg("Invalid option, please try again.");
                loginPrompt();
        }
    }

    // ---- LOGIN ----
    public void login() {
        String username = ui.promptText("Enter username:").trim();
        String password = ui.promptText("Enter password:").trim();

        //Get UPS, separate data, then compare to see if userlogin is correct.
        for (User u : users) {
            if (username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())) {
                currentUser = u;
                menu.setCurrentUser(u);
                return;
            }
        }
        // UNSUCCESSFUL
        System.out.println("\nLogin failed, try again?");
        loginPrompt();
    }

    public void mainMenuOptions() {

        switch (menu.printMainMenu()) {
            case "1":
                searchMenuOptions();
                break;
            case "2":
                listMenuOptions();
                break;
            case "3":
                for (Media m : searchEngine.getMediaLibrary().getAllMovies()) {
                    ui.displayMsg(m.toString());
                    //vil gerne bruge ui.displaylist men har ikke en liste kun med titler? maaske ligemeget
                }
                break;
            case "4":
                for (Media s : searchEngine.getMediaLibrary().getAllSeries()) {
                    ui.displayMsg(s.toString());
                }
                break;
            case "5":
                quitMenuOptions();
                break;
            default:
                ui.displayMsg("choose an option 1, 2, 3, 4 or 5.");
                mainMenuOptions();
        }
    }

    public void searchMenuOptions() {
        switch (menu.printSearchMenu()) {
            case "1":
                String categorySelection = menu.selectFromList("Select a category:", Category.values());
                ArrayList<Media> filteredMediaByCategory = searchEngine.filterByCategory(categorySelection);
                int mediaChoice = Integer.parseInt(menu.selectFromList("Select a Media:", filteredMediaByCategory));
                currentUser.setCurrentMedia(filteredMediaByCategory.get(mediaChoice - 1));
                mediaMenuOptions();
                break;
            case "2":
                String titleSelection = ui.promptText("Enter title:");
                Media filteredMediaByTitle = searchEngine.filterByTitle(titleSelection);
                currentUser.setCurrentMedia(filteredMediaByTitle);
                ui.displayMsg("Found " + filteredMediaByTitle.title);
                mediaMenuOptions();
                break;
            case "3":
                mainMenuOptions();
                break;
            default:
                ui.displayMsg("Please pick a valid option (numbers 1-3)");
                searchMenuOptions();
                break;
        }
    }

    private void listMenuOptions() {
        switch (menu.printListMenu()) {
            case "1":
                menu.displayList(currentUser.getWantToWatch());
                break;
            case "2":
                menu.displayList(currentUser.getWatched());
                break;
            case "3":
                mainMenuOptions();
            default:
                ui.displayMsg("Select an option: 1, 2, or 3.");
                listMenuOptions();
        }
    }

    public void mediaMenuOptions() {
        switch (menu.printMediaMenu()) {
            case "1":
                currentUser.addMediaToWatched();
                currentUser.getCurrentMedia().play();
                mediaMenuOptions();
                break;
            case "2":
                currentUser.addMediaToWantToWatch();
                mediaMenuOptions();
                break;
            case "3":
                currentUser.removeFromWantToWatch();
                mediaMenuOptions();
                break;
            case "4":
                mainMenuOptions();
                break;
            default:
                ui.displayMsg("Please pick a valid option (numbers 1-4)");
                mediaMenuOptions();
        }
    }

    public void quitMenuOptions() {
        switch (menu.printQuitMenu()) {
            case "1":
                FileIO.saveData(userDataString(), "data/userData.csv");
                break;
            case "2":
                mainMenuOptions();
                break;
        }
    }


    // UPs = username & password.
    // Anvendes til at sammenligne brugerinput med eksisterende burgeres brugernavn og password.
    public ArrayList<String> getExistingUPs() {
        ArrayList<String> UPs = new ArrayList<>();
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData) {
            String[] attributes = line.split(";");
            String username = attributes[0].trim();
            String password = attributes[1].trim();
            UPs.add(username + ", " + password);
        }
        return UPs;
    }

    // Tager et brugernavn og returnere data fra CSV-filen.
    // Anvendes til at oprette en instance af User, når en bruger logger ind.
    public String[] getUserData(String username) {
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData) {
            String[] attributes = line.split(";");
            if (username.equals(attributes[0].trim())) {
                //Inden return skal denne laves om til ArrayList<String> da User tager ArrayList.
                return line.split(";");
            }
        }
        return null;
    }

    //Seemingly not needed when swapping to creating all users as objects on program load.
//    //Creating a user object by matching a username to datafile, as well as converting titles of media to media objects.
//    void createExistingUser(String userName) {
//        String[] attribute = getUserData(userName);
//
//        ArrayList<Media> wantToWatch = new ArrayList<>();
//        String line = attribute[2];
//        String[] titles = line.split(",");
//        //Use saved String ArrayList to create Media objects for the users wantToWatch list
//        for (String s : titles) {
//            for (Media m : searchEngine.getMediaLibrary().getAllMedia())
//                if (s.equalsIgnoreCase(m.title))
//                    wantToWatch.add(m);
//        }
//
//        ArrayList<Media> watched = new ArrayList<>();
//        String line2 = attribute[3];
//        String[] titles2 = line2.split(",");
//        for (String s : titles2) {
//            for (Media m : searchEngine.getMediaLibrary().getAllMedia())
//                if (s.equalsIgnoreCase(m.title))
//                    watched.add(m);
//
//            User u = new User(attribute[0].trim(), attribute[1].trim(), wantToWatch, watched);
//            currentUser = u;
//            menu.setCurrentUser(u);
//            users.add(u);
//        }
//    }

    //Called when a user doesn't login in, and instead creates an entirely new user.
    void createNewUser(String name, String password) {
        User u = new User(name, password);
        currentUser = u;
        menu.setCurrentUser(u);
        users.add(u);
    }

    void createAllUsers() {
        ArrayList<String> userData = FileIO.readData("data/userData.csv");

        for (String s : userData) {

            ArrayList<Media> wantToWatch = new ArrayList<>();
            ArrayList<Media> watched = new ArrayList<>();

            String[] line = s.split(";");

            String username = line[0].trim();
            String password = line[1].trim();


            String wtw = line[2].trim();
            String[] movies = wtw.split(",");
            //Use saved String ArrayList to create Media objects for the users wantToWatch list
            for (String movie : movies) {
                for (Media m : searchEngine.getMediaLibrary().getAllMedia())
                    if (movie.equalsIgnoreCase(m.title))
                        wantToWatch.add(m);
            }

            String w = line[3].trim();
            String[] series = w.split(",");
            for (String serie : series) {
                for (Media t : searchEngine.getMediaLibrary().getAllMedia())
                    if (serie.equalsIgnoreCase(t.title))
                        watched.add(t);
            }
            User u = new User(username, password, wantToWatch, watched);
            users.add(u);
        }
    }


    public String printCurrentUser() {
        return "currentUser=" + currentUser.getUsername() +
                '}' +
                "password=" + currentUser.getPassword() +
                '}' +
                "want to watch=" + currentUser.printWantToWatch() +
                '}' +
                "watched=" + currentUser.printWatched() +
                '}';
    }


    //gem alle linjer fra CSV
    //lav en lang String af et user-objekt
    //Stringjoiner?

    public ArrayList<String> userDataString(){
        ArrayList <String> allUsersData=new ArrayList<>();
        for(User u:users) {
            String userdata = u.getUsername() + ";" + u.getPassword() + ";";
            if (!u.getWantToWatch().isEmpty()) {
                for (Media m : u.getWantToWatch()) {
                    if (u.getWantToWatch().getLast().title.equals(m.title)) {
                        userdata += m.title;
                    } else userdata += m.title + ",";
                }
            }
            else {
                userdata += "NoMedia";
                }

            userdata += ";";
            if (!u.getWatched().isEmpty()) {
                for (Media s : u.getWatched()) {
                    if (u.getWatched().getLast().title.equals(s.title)) {
                        userdata += s.title;
                    } else userdata += s.title + ",";
                }
            }
            else {
                userdata += "NoMedia";
            }
            userdata += ";";
            allUsersData.add(userdata);

        } return allUsersData;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }
}



