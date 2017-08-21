package pl.pollub.task2.NonMocked;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.pollub.task2.*;
import pl.pollub.task2.NonMocked.Stubs.EmailNotifierStub;
import pl.pollub.task2.NonMocked.Stubs.UserServiceSpike;

public class StubbedTaskServiceTest {

    private UserService userService = new UserServiceSpike();

    private EmailNotifier emailNotifier = new EmailNotifierStub();

    private TaskService taskService;

    @Before
    public void setUp() throws Exception {
        taskService = new TaskService(userService,emailNotifier);
    }

    @Test
    public void sendEmailToOwnerAndContributors() {
        Task task = taskService.createTaskForUser(1, 2, 3);

        taskService.completeTask(task.getId());


        HashSet<String> notified = ((EmailNotifierStub)emailNotifier).getSentEmails(task.getId());
        HashSet<String> expected = new HashSet<>(Arrays.asList("user1@wp.pl", "user2@wp.pl", "user3@wp.pl"));

       Assert.assertEquals(expected, notified);
    }

}