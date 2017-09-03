package pl.pollub.designPatterns1.Task.Service;

import lombok.RequiredArgsConstructor;
import pl.pollub.designPatterns1.Task.Model.Task;
import pl.pollub.designPatterns1.User.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;

    private final Map<Integer, Task> tasks = new HashMap<>();

    private final AtomicInteger counter = new AtomicInteger();

    private TaskSummariser taskSummariser = TaskSummariser.getInstance();

    Task createTaskForUser(int userId, Integer... contributors){
        Task task = new Task(counter.incrementAndGet(),userService.getUserById(userId),
                             contributors != null ? Arrays.stream(contributors)
                                                            .map(userService::getUserById)
                                                            .collect(Collectors.toList()) : Collections.emptyList());
        tasks.put(task.getId(), task);
        return task;
    }

    public void completeTask(int taskId){
        Task task = tasks.get(taskId);
        taskSummariser.completeTask(task);
    }

}
