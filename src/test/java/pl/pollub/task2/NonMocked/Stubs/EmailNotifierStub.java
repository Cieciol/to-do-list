package pl.pollub.task2.NonMocked.Stubs;

import pl.pollub.task2.EmailNotifier;
import pl.pollub.task2.Task;
import pl.pollub.task2.User;

import java.util.*;
import java.util.stream.Collectors;

public class EmailNotifierStub extends EmailNotifier {

    private Map<Integer, Collection<String>> sentEmails = new HashMap<>();
    @Override
    public void notify(Task task) {
        List<String> emails = task.getContributors().stream().map(User::getEmail).collect(Collectors.toList());
        emails.add(task.getTaskOwner().getEmail());
        sentEmails.put(task.getId(),emails);
    }
    public HashSet<String> getSentEmails(int taskId){
        return ((HashSet<String>) sentEmails.get(taskId));
    }
}
