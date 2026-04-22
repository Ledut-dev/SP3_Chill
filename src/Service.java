import util.FileIO;
import util.TextUI;

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
    }


    public void startMenuLoop() {
        loginPrompt();
        mainMenuOptions();
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

        public void loginPrompt () {

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
        public void login () {
            String username = ui.promptText("Enter username:").trim();
            String password = ui.promptText("Enter password:").trim();

            //Get UPS, separate data, then compare to see if userlogin is correct.
            for (String s : getExistingUPs()) {
                String[] userData = s.split(",");
                String foundUsername = userData[0].trim();
                String foundPassword = userData[1].trim();
                if (username.equalsIgnoreCase(foundUsername) && password.equalsIgnoreCase(foundPassword)) {
                    createExistingUser(foundUsername);
                    return;
                }
            }
            // UNSUCCESSFUL
            System.out.println("\nLogin failed, try again?");
            loginPrompt();
        }

        public void mainMenuOptions(){

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
                case "5": // quit and save data
                    break;
                default:
                    ui.displayMsg("choose an option 1, 2, 3, 4 or 5.");
                    mainMenuOptions();
            }
        }

    private void searchMenuOptions() {
        menu.printSearchMenu();
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

    //Creating a user object by matching a username to datafile, as well as converting titles of media to media objects.
    void createExistingUser(String userName) {
        String[] attribute = getUserData(userName);

        ArrayList<Media> wantToWatch = new ArrayList<>();
        String line = attribute[2];
        String[] titles = line.split(",");
        //Use saved String ArrayList to create Media objects for the users wantToWatch list
        for (String s : titles) {
            for (Media m : searchEngine.getMediaLibrary().getAllMedia())
                if (s.equalsIgnoreCase(m.title))
                    wantToWatch.add(m);
        }

        ArrayList<Media> watched = new ArrayList<>();
        String line2 = attribute[3];
        String[] titles2 = line2.split(",");
        for (String s : titles2) {
            for (Media m : searchEngine.getMediaLibrary().getAllMedia())
                if (s.equalsIgnoreCase(m.title))
                    watched.add(m);

            User u = new User(attribute[0].trim(), attribute[1].trim(), wantToWatch, watched);
            currentUser = u;
            menu.setCurrentUser(u);
            users.add(u);
        }
    }

    //Called when a user doesn't login in, and instead creates an entirely new user.
    void createNewUser(String name, String password){
        User u = new User(name, password);
        menu.setCurrentUser(u);
        users.add(u);
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
}

