package pl.pollub.designPatterns1.Comms;

import pl.pollub.designPatterns1.Notifier.Notifier;
import pl.pollub.designPatterns1.Notifier.PushNotifier;
import pl.pollub.designPatterns1.Reminder.PushReminder;
import pl.pollub.designPatterns1.Reminder.Reminder;

public class PushCommsFactory extends AbstractCommsFactory {
    @Override
    public Notifier createNotifier() {
        return new PushNotifier();
    }

    @Override
    public Reminder createReminder() {
        return new PushReminder();
    }
}
