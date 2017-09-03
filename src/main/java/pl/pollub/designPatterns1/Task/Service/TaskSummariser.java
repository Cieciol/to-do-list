package pl.pollub.designPatterns1.Task.Service;

import pl.pollub.designPatterns1.Comms.AbstractCommsFactory;
import pl.pollub.designPatterns1.Notifier.Notifier;
import pl.pollub.designPatterns1.Task.Model.Task;
import pl.pollub.designPatterns1.User.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskSummariser {
    private static TaskSummariser INSTANCE = new TaskSummariser();

    private Connection databaseConnection = null;

    @lombok.Getter
    private Map<Task,Date> completedTasks = new HashMap<>();

    private Notifier notifier;

    private TaskSummariser(){

    }

    public static TaskSummariser getInstance() {
        return INSTANCE;
    }

    public void completeTask(Task task){
        completedTasks.put(task,new Date());
        doNotifyingStuff(task);
    }

    private void doNotifyingStuff(Task task){
        ArrayList<User> notified = (ArrayList<User>) task.getContributors();
        notified.add(task.getTaskOwner());
        notified.forEach((user -> {
            notifier = AbstractCommsFactory.createFactory(user).createNotifier();
            notifier.notify(task);
        }));
    }

}
