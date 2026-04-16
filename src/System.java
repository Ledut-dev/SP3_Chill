import util.TextUI;

import java.util.ArrayList;

public class System {
    private User currentUser;
    private MediaLibrary mediaLibrary;
    private Menu menu;
    private ArrayList<User> allUsers;
    TextUI ui;

    void createExistingUser (String data){
        String [] values= data.split(",");
        User u = new User(values[0].trim(),values[1].trim());

        if (values.length>2){ //hvis der er mere end username og password gemt på en bruger
            // skal disse tildeles hver deres arrayList watched eller wantToWatch. eller vi kan splitte på et andet symbol også
            //først skabe to string arrays...

        }

        allUsers.add(u);
    }

    void createAllUsers(ArrayList<String> data){ //igen hvad gør vi med arraylister?
        for (String s:data){
            String [] values= s.split(",");
            User u= new User(values[0].trim(), values[1].trim());
            allUsers.add(u);
        }

    }

    void createNewUser(){
        String userName=ui.promptText("Input username");
        String passWord=ui.promptText("input password");

        User u= new User(userName,passWord);
        allUsers.add(u);

    }

    void createNewUser2 (String name, String password){
        User u=new User(name, password);
        allUsers.add(u);
    }




}

//class System {
//- User currentUser
//- MediaLibrary mediaLibrary
//- Menu menu
//- ArrayList<User> allUsers
//}