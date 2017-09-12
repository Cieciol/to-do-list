package pl.pollub

import pl.pollub.task.NewTask
import pl.pollub.task.Task
import pl.pollub.task.TaskList
import spock.lang.Specification

import java.util.stream.Collectors

class TaskListSearchSpec extends Specification {


    def "searches tasks"() {

        expect:
        TaskList tasks = new TaskList()
        tasksContents.forEach({ task -> tasks.add new NewTask(task) })
        search(tasks.allTasks, query)*.id == results

        where:

        tasksContents                                      | query   | results
        ["zrób obiad", "kup mleko", "posprzątaj w kuchni"] | "obiad" | [1]
        ["kup chleb", "kup mleko", "posprzątaj w kuchni"]  | "kup"   | [1, 2]
        ["kup chleb", "kup mleko", "posprzątaj w kuchni"]  | "ku"    | [1, 2, 3]


    }

    private List<Task> search(List<Task> tasks, String query) {
        List<Task> searchResults

        searchResults = tasks.stream()
                .filter({ def task -> task.content.contains(query) })
                .collect(Collectors.toList())
        return searchResults
    }
}
