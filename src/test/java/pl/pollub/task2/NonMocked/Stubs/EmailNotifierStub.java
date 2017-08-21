package pl.pollub.task2.NonMocked.Stubs;

import pl.pollub.task2.EmailNotifier;

import java.util.*;

public class EmailNotifierStub implements EmailNotifier {

    private Map<Integer, Collection<String>> sentEmails = new HashMap<>();
    @Override
    public void notify(int taskId, Collection<String> emails) {
        sentEmails.put(taskId,emails);
    }
    public HashSet<String> getSentEmails(int taskId){
        return ((HashSet<String>) sentEmails.get(taskId));
    }
}
