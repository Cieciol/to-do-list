package pl.pollub.designPatterns1.Notifier;

import org.apache.log4j.Logger;
import pl.pollub.designPatterns1.Task.Model.Task;

public class NullNotifier implements Notifier {
    @Override
    public void notify(Task task) {
        Logger logger = Logger.getLogger(NullNotifier.class);
        logger.info("used notify from nullNotifier");
    }
}
