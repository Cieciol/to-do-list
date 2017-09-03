package pl.pollub.designPatterns1.Notifier;

import org.apache.log4j.Logger;
import pl.pollub.designPatterns1.Task.Model.Task;

public interface Notifier {

    default void notify(Task task){
        Logger logger = Logger.getLogger(getClass());
        logger.debug("notify function was used");
    }
}
