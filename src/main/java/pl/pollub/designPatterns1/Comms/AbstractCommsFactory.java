package pl.pollub.designPatterns1.Comms;

import pl.pollub.designPatterns1.Notifier.Notifier;
import pl.pollub.designPatterns1.Reminder.Reminder;
import pl.pollub.designPatterns1.User.User;

public abstract class AbstractCommsFactory {

    public abstract Notifier createNotifier();
    public abstract Reminder createReminder();

    private static SmsCommsFactory smsCommsFactory = new SmsCommsFactory();
    private static PushCommsFactory pushCommsFactory = new PushCommsFactory();
    private static EmailCommsFactory emailCommsFactory = new EmailCommsFactory();
    private static NullCommsFactory nullCommsFactory = new NullCommsFactory();

    public static AbstractCommsFactory createFactory(User user){
        switch (user.getChosenNotifier()){
            case SMS: return smsCommsFactory;
            case PUSH: return pushCommsFactory;
            case EMAIL:return emailCommsFactory;
            case NOT_CHOSEN: default: return nullCommsFactory;
        }
    }
}
