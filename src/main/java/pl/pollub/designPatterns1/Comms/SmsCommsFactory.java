package pl.pollub.designPatterns1.Comms;

import pl.pollub.designPatterns1.Notifier.Notifier;
import pl.pollub.designPatterns1.Notifier.SMSNotifier;
import pl.pollub.designPatterns1.Reminder.Reminder;
import pl.pollub.designPatterns1.Reminder.SmsReminder;

public class SmsCommsFactory extends AbstractCommsFactory {
    @Override
    public Notifier createNotifier() {
        return new SMSNotifier();
    }

    @Override
    public Reminder createReminder() {
        return new SmsReminder();
    }
}
