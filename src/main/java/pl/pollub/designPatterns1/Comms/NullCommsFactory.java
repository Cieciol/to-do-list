package pl.pollub.designPatterns1.Comms;

import pl.pollub.designPatterns1.Notifier.Notifier;
import pl.pollub.designPatterns1.Notifier.NullNotifier;
import pl.pollub.designPatterns1.Reminder.NullReminder;
import pl.pollub.designPatterns1.Reminder.Reminder;

public class NullCommsFactory extends AbstractCommsFactory{
    @Override
    public Notifier createNotifier() {
        return new NullNotifier();
    }

    @Override
    public Reminder createReminder() {
        return new NullReminder();
    }
}
