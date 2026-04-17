import util.TextUI;

import java.util.ArrayList;

public class System {
    private User currentUser;
    private MediaLibrary mediaLibrary;
    private Menu menu;
    private ArrayList<User> allUsers;
    TextUI ui;

    void createExistingUser (String userName){ //hvad gør vi med existing users arraylister?
        String [] attribute =getUserData(userName);
        User u = new User(attribute[0].trim(),attribute[1].trim());
        allUsers.add(u);
        }


    void createAllUsers(ArrayList<String> data){ //igen hvad gør vi med arraylister?
        for (String s:data){
            String [] values= s.split(",");
            User u= new User(values[0].trim(), values[1].trim());
            allUsers.add(u);
        }

    }

    void promptCreateNewUser(){
        String userName=ui.promptText("Input username");
        String passWord=ui.promptText("input password");

        User u= new User(userName,passWord);
        allUsers.add(u);

    }

    void createNewUser (String name, String password){
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