package pl.pollub.designPatterns1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskSummariser {
    private static TaskSummariser INSTANCE = new TaskSummariser();

    private Connection databaseConnection = null;

    private Map<Task,Date> completedTasks = new HashMap<>();

    private NotifierFactory notifierFactory = new NotifierFactory();
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
            notifier = notifierFactory.getNotifier(user.getChosenNotifier());
            notifier.notify(task);
        }));
    }
}
