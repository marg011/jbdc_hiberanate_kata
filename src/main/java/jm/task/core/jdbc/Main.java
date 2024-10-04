package jm.task.core.jdbc;

import java.util.ArrayList;
import java.util.List;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.*;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        List<User> usersSave = new ArrayList<>();
        usersSave.add(new User("Mike", "White", (byte) 30));
        usersSave.add(new User("Steve", "Ewebs", (byte) 12));
        usersSave.add(new User("Ron", "Potter", (byte) 55));
        usersSave.add(new User("Harry", "Potter", (byte) 18));


        for (User user : usersSave) {
            userService.saveUser(user.getName(), user.getLastName(), (byte) user.getAge());
            System.out.println("User с именем — " + user.getName() + " добавлен в базу данных");
        }

        List<User> usersGet = userService.getAllUsers();
        for (User user : usersGet) {
            System.out.println(user);
        }

//        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}