package pl.pollub.designPatterns1.Task.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.pollub.designPatterns1.Comms.CommsChannels;
import pl.pollub.designPatterns1.Task.Model.Task;
import pl.pollub.designPatterns1.User.User;
import pl.pollub.designPatterns1.User.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TaskSummariserTest {

    private TaskSummariser taskSummariser = TaskSummariser.getInstance();

    @InjectMocks
    private TaskService taskService;

    @Mock
    private UserService userService;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void TaskSummariserChoosesRightNotifier(){
        //given
        User userWithNoNotifier = new User.Builder(1).build();

        User userWithSmsNotifier = new User.Builder(2)
                                            .withNotifier(CommsChannels.SMS).build();

        User userWithPushNotifier = new User.Builder(3)
                                            .withNotifier(CommsChannels.PUSH).build();

        Mockito.when(userService.getUserById(1)).thenReturn(userWithNoNotifier);
        Mockito.when(userService.getUserById(2)).thenReturn(userWithSmsNotifier);
        Mockito.when(userService.getUserById(3)).thenReturn(userWithPushNotifier);

        Task task = taskService.createTaskForUser(1,2,3);

        taskSummariser.completeTask(task);

        //test moved to AbstractCommsFactoryTest
        //TODO after implementing some logic in Notifier classes
    }

}