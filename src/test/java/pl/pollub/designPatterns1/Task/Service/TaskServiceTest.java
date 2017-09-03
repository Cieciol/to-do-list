package pl.pollub.designPatterns1.Task.Service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import pl.pollub.task2.*;
import pl.pollub.task2.TaskService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {
    @Mock
    private UserService userService;

    @Mock
    private EmailNotifier emailNotifier;

    @InjectMocks
    private TaskService taskService;

    @Captor
    private ArgumentCaptor<List<String>> emailsCaptor;

    @Test
    public void sendEmailToOwnerAndContributors() {
        generateEmailsMock();
        Task task = taskService.createTaskForUser(1, 2, 3);

        taskService.completeTask(task.getId());
        captureNotifiedEmails(task);

        HashSet<String> notified = new HashSet<>(emailsCaptor.getValue());
        HashSet<String> expected = new HashSet<>(Arrays.asList("user1@wp.pl", "user2@wp.pl", "user3@wp.pl"));
        Assert.assertEquals(expected, notified);
    }
    private void generateEmailsMock() {
        Mockito.when(userService.getUserById(Mockito.anyInt())).then(invocationOnMock -> {
            Integer id = invocationOnMock.getArgumentAt(0, int.class);
            return new User(id, "user" + id + "@wp.pl");
        });
    }

    private void captureNotifiedEmails(Task task){
        Mockito.verify(emailNotifier).notify(Mockito.eq(task.getId()), emailsCaptor.capture());
    }

}