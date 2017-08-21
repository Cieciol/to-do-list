package pl.pollub.task2.NonMocked.Stubs;

import pl.pollub.task2.User;
import pl.pollub.task2.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceSpike implements UserService {

    private List<User> users = new ArrayList<>();

    @Override
    public User getUserById(int id) {
        generateUsers(3);
        return users.get(id);
    }

    private void generateUsers(int amount){
        for (int i = 1; i <= amount; i++) {
            users.add(new User(i, "user" + i + "@wp.pl"));
        }

    }
}
