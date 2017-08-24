package pl.pollub.task2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;

    private final EmailNotifier emailNotifier;

    private final Map<Integer, Task> tasks = new HashMap<>();

    private final AtomicInteger counter = new AtomicInteger();

    private TaskSummariser taskSummariser = TaskSummariser.getInstance();

    public Task createTaskForUser(int userId, Integer... contributors){
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
