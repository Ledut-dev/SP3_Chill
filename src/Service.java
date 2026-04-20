import util.FileIO;
import util.TextUI;

import java.util.ArrayList;

public class Service {

    private MediaLibrary mediaLibrary;
    private User currentUser;
    private Menu menu;
    private ArrayList<User> users;
    TextUI ui;

    public Service() {
        this.menu = new Menu();
        users = new ArrayList<User>();
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

    void createExistingUser (String userName){ //hvad gør vi med existing users arraylister?
        String [] attribute =getUserData(userName);

        ArrayList <String> wantToWatch = new ArrayList<>();
        String line = attribute[2];
        String [] titles= line.split(",");
        for (String s: titles){
            wantToWatch.add(s.trim());
        }

        ArrayList <String> watched = new ArrayList<>();
        String line2 = attribute[3];
        String [] titles2= line2.split(",");
        for (String t: titles2){
            watched.add(t.trim());
            }

        User u = new User(attribute[0].trim(),attribute[1].trim(), wantToWatch,watched);
        currentUser=u;
        users.add(u);
    }


    void promptCreateNewUser(){
        String userName=ui.promptText("Input username");
        String passWord=ui.promptText("input password");

        createNewUser(userName,passWord);

    }

    void createNewUser (String name, String password){
        User u=new User(name, password);
        users.add(u);
    }



    public String printCurrentUser() {
        return "currentUser=" + currentUser.getUserName() +
                '}' +
                "password=" + currentUser.getPassWord() +
                '}'+
                "want to watch=" + currentUser.printWantToWatch() +
                '}'+
                "watched=" + currentUser.printWatched() +
                '}';

    }

    /// ////////////////////


    //LoginPrompt
        private final Scanner scanner = new Scanner(System.in);



        public void loginPrompt() {
            System.out.println("\nSTREAMINGCHILL");

            while (true) {
                System.out.print("\nHar du allerede en bruger? (ja/nej): ");
                String svar = scanner.nextLine().trim().toLowerCase();

                if (svar.equals("ja")) {
                    // UC1 #9 - Login method
                    LoginMethod loginMethod = new LoginMethod(brugere, scanner);
                    loginMethod.login();
                    currentUser = loginMethod.getCurrentUser();
                    break;
                } else if (svar.equals("nej")) {
                    // UC1 #9 - Rainy day: opret bruger
                    LoginMethod loginMethod = new LoginMethod(brugere, scanner);
                    loginMethod.opretBruger();
                    currentUser = loginMethod.getCurrentUser();
                    break;
                } else {
                    System.out.println("Skriv 'ja' eller 'nej'.");
                }
            }
        }

        public User getCurrentUser() {
            return currentUser;
        }


    public Service(ArrayList<User> brugere, Scanner scanner) {
        this.brugere = brugere;
        this.scanner = scanner;
    }

    public void login() {
        while (true) {
            System.out.print("\nBrugernavn: ");
            String navn = scanner.nextLine();
            System.out.print("Adgangskode: ");
            String pass = scanner.nextLine();

            User matchedUser = null;
            for (User u : brugere) {
                if (u.getUserName().equals(navn) && u.getPassword().equals(pass)) {
                    matchedUser = u;
                    break;
                }
            }

            if (matchedUser != null) {
                // SUCCESS: Send greetings message and create user object
                currentUser = matchedUser;
                System.out.println("\nVELKOMMEN " + navn.toUpperCase() + "!");
                System.out.println("Du er nu logget ind.");
                return;
            } else {
                // UNSUCCESSFUL: Send error message and prompt login again
                System.out.println("\nLOGIN FEJLEDE! Forkert brugernavn eller adgangskode.");

                // RAINY DAY: User realizes they don't have an account
                System.out.print("Har du ikke en bruger? (ja/nej): ");
                String svar = scanner.nextLine().trim().toLowerCase();
                if (svar.equals("ja")) {
                    opretBruger();
                    return;
                }
                System.out.println("Prøver igen...");
            }
        }
    }

    public void opretBruger() {
        while (true) {
            System.out.print("\nVælg brugernavn: ");
            String navn = scanner.nextLine();

            boolean taget = false;
            for (User u : brugere) {
                if (u.getUserName().equals(navn)) {
                    System.out.println("Brugernavn er allerede taget!");
                    taget = true;
                    break;
                }
            }
            if (taget) continue;

            System.out.print("Vælg adgangskode: ");
            String pass = scanner.nextLine();
            System.out.print("Bekræft adgangskode: ");
            String bekræft = scanner.nextLine();

            if (!pass.equals(bekræft)) {
                System.out.println("Adgangskoder matcher ikke!");
                continue;
            }

            User nyBruger = new User(navn, pass, new ArrayList<>(), new ArrayList<>());
            brugere.add(nyBruger);

            // Gem i CSV
            try (FileWriter writer = new FileWriter("data/userData.csv", true)) {
                writer.write(navn + ";" + pass + ";;\n");
            } catch (IOException e) {
                System.out.println("Fejl ved gemning af bruger.");
            }

            currentUser = nyBruger;
            System.out.println("\nBruger oprettet!");
            System.out.println("VELKOMMEN " + navn.toUpperCase() + "!");
            return;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}





    }

