import util.FileIO;
import util.TextUI;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Service {

    private MediaLibrary mediaLibrary;
    private User currentUser;
    private ArrayList<User> brugere = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    TextUI ui = new TextUI();

    public Service() {
        loadBrugere();
    }

    // Indlæs alle brugere fra CSV ved opstart
    private void loadBrugere() {
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData) {
            String[] data = line.split(";");
            if (data.length >= 2) {
                String navn = data[0].trim();
                String pass = data[1].trim();
                ArrayList<String> want = new ArrayList<>();
                ArrayList<String> watched = new ArrayList<>();
                if (data.length >= 4) {
                    if (!data[2].trim().isEmpty())
                        for (String s : data[2].split(",")) want.add(s.trim());
                    if (!data[3].trim().isEmpty())
                        for (String s : data[3].split(",")) watched.add(s.trim());
                }
                brugere.add(new User(navn, pass, want, watched));
            }
        }
    }

    // Returnerer brugernavn + adgangskode for alle brugere
    public ArrayList<String> getExistingUPs() {
        ArrayList<String> UPs = new ArrayList<>();
        for (User u : brugere) {
            UPs.add(u.getUserName() + ", " + u.getPassWord());
        }
        return UPs;
    }

    // Returnerer rådata for en specifik bruger fra CSV
    public String[] getUserData(String username) {
        ArrayList<String> userData = FileIO.readData("data/userData.csv");
        for (String line : userData) {
            String[] attributes = line.split(";");
            if (username.equals(attributes[0].trim())) {
                return line.split(";");
            }
        }
        return null;
    }

    // Opretter en User-instans fra CSV-data og sætter den som currentUser
    void createExistingUser(String userName) {
        String[] attribute = getUserData(userName);
        if (attribute == null) return;

        ArrayList<String> wantToWatch = new ArrayList<>();
        if (attribute.length > 2 && !attribute[2].trim().isEmpty()) {
            for (String s : attribute[2].split(",")) wantToWatch.add(s.trim());
        }

        ArrayList<String> watched = new ArrayList<>();
        if (attribute.length > 3 && !attribute[3].trim().isEmpty()) {
            for (String t : attribute[3].split(",")) watched.add(t.trim());
        }

        User u = new User(attribute[0].trim(), attribute[1].trim(), wantToWatch, watched);
        currentUser = u;
        users.add(u);
    }

    void promptCreateNewUser() {
        String userName = ui.promptText("Input username");
        String passWord = ui.promptText("Input password");
        createNewUser(userName, passWord);
    }

    void createNewUser(String name, String password) {
        User u = new User(name, password);
        users.add(u);
    }

    public String printCurrentUser() {
        return "currentUser=" + currentUser.getUserName() + "}" +
                "password=" + currentUser.getPassWord() + "}" +
                "want to watch=" + currentUser.printWantToWatch() + "}" +
                "watched=" + currentUser.printWatched() + "}";
    }

    // ---- LOGIN PROMPT ----
    public void loginPrompt() {
        System.out.println("\nSTREAMINGCHILL");

        while (true) {
            System.out.print("\nHar du allerede en bruger? (ja/nej): ");
            String svar = scanner.nextLine().trim().toLowerCase();

            if (svar.equals("ja")) {
                login();
                break;
            } else if (svar.equals("nej")) {
                opretBruger();
                break;
            } else {
                System.out.println("Skriv 'ja' eller 'nej'.");
            }
        }
    }

    // ---- LOGIN ----
    public void login() {
        while (true) {
            System.out.print("\nBrugernavn: ");
            String navn = scanner.nextLine();
            System.out.print("Adgangskode: ");
            String pass = scanner.nextLine();

            User matchedUser = null;
            for (User u : brugere) {
                if (u.getUserName().equals(navn) && u.getPassWord().equals(pass)) {
                    matchedUser = u;
                    break;
                }
            }

            if (matchedUser != null) {
                // SUCCESS
                currentUser = matchedUser;
                System.out.println("\nVELKOMMEN " + navn.toUpperCase() + "!");
                System.out.println("Du er nu logget ind.");
                return;
            } else {
                // UNSUCCESSFUL
                System.out.println("\nLOGIN FEJLEDE! Forkert brugernavn eller adgangskode.");

                // RAINY DAY: bruger har ikke en konto
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

    // ---- OPRET BRUGER ----
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
            String bekraeft = scanner.nextLine();

            if (!pass.equals(bekraeft)) {
                System.out.println("Adgangskoder matcher ikke!");
                continue;
            }

            User nyBruger = new User(navn, pass, new ArrayList<>(), new ArrayList<>());
            brugere.add(nyBruger);

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

    public ArrayList<User> getBrugere() {
        return brugere;
    }
}
