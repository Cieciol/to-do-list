package pl.pollub.designPatterns1.Task.Model;

import pl.pollub.designPatterns1.User.User;

import java.util.List;

public class Tasks {

    public static Task createTaskWithoutContributors(int id, User owner){
        return new Task(id,owner,null);
    }
    public static Task createTaskWithContibutors(int id, User owner, List<User> contributors){
        return new Task(id,owner,contributors);
    }


}
