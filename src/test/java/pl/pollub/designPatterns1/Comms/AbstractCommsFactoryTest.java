package pl.pollub.designPatterns1.Comms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import pl.pollub.designPatterns1.Notifier.NullNotifier;
import pl.pollub.designPatterns1.Notifier.PushNotifier;
import pl.pollub.designPatterns1.Notifier.SMSNotifier;
import pl.pollub.designPatterns1.Task.Service.TaskService;
import pl.pollub.designPatterns1.User.User;
import pl.pollub.designPatterns1.User.UserService;

public class AbstractCommsFactoryTest {

    private AbstractCommsFactory abstractCommsFactory;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;

    @Before
    public void setUp() throws Exception {
        abstractCommsFactory = Mockito.mock(AbstractCommsFactory.class, Mockito.withSettings().defaultAnswer(InvocationOnMock::callRealMethod));
    }

    @Test
    public void factoryCreatesNotifierofRightClass() throws Exception {
        User userWithNoNotifier = new User.Builder(1).build();

        User userWithSmsNotifier = new User.Builder(2)
                .withNotifier(CommsChannels.SMS).build();

        User userWithPushNotifier = new User.Builder(3)
                .withNotifier(CommsChannels.PUSH).build();

        Assert.assertTrue(AbstractCommsFactory.createFactory(userWithNoNotifier).createNotifier().getClass() == NullNotifier.class);
        Assert.assertTrue(AbstractCommsFactory.createFactory(userWithPushNotifier).createNotifier().getClass() == PushNotifier.class);
        Assert.assertTrue(AbstractCommsFactory.createFactory(userWithSmsNotifier).createNotifier().getClass() == SMSNotifier.class);

    }

}