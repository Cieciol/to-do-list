package pl.pollub.designPatterns1.Comms;

import pl.pollub.designPatterns1.Notifier.EmailNotifier;
import pl.pollub.designPatterns1.Notifier.Notifier;
import pl.pollub.designPatterns1.Reminder.EmailReminder;
import pl.pollub.designPatterns1.Reminder.Reminder;

public class EmailCommsFactory extends AbstractCommsFactory {
    @Override
    public Notifier createNotifier() {
        return new EmailNotifier();
    }

    @Override
    public Reminder createReminder() {
        return new EmailReminder();
    }
}
