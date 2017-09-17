package pl.pollub

import pl.pollub.task.NewTask
import pl.pollub.task.Task
import pl.pollub.task.TaskList
import spock.lang.Specification

import java.util.function.Predicate
import java.util.regex.Pattern
import java.util.stream.Collectors

import static pl.pollub.TaskListSearchSpec.DatePredicates.*

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


    def "searches for dates in task content"() {
        expect:
        TaskList tasks = new TaskList()
        tasksContents.forEach({ task -> tasks.add new NewTask(task) })

        searchForDates(tasks.allTasks)*.id == results

        where:
        tasksContents                                                               || results
        ["zrób obiad w poniedziałek", "kup mleko", "posprzątaj w kuchni"]           || [1]
        ["kup chleb jutro", "kup mleko pojutrze", "posprzątaj w kuchni"]            || [1, 2]
        ["pójdź na egzanmin 21.03.1993", "14-02-2007", "posprzątaj w kuchni 12.02"] || [1, 2]


    }


    private List<Map<Task, Date>> searchForDates(List<Task> tasks) {
        List<Task> searchResults
        searchResults = tasks.stream()
                .filter(
                hasDatenames(days)
                        | hasDatenames(relatableDates)
                        | matchesRegex(straightDates))
                .collect(Collectors.toList())

        searchResults
    }

    private List<Task> search(List<Task> tasks, String query) {
        List<Task> searchResults

        searchResults = tasks.stream()
                .filter({ def task -> task.content.contains(query) })
                .collect(Collectors.toList())
        return searchResults
    }

    class DatePredicates {
        static String[] days = ["poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela"]
        static String[] relatableDates = ["jutro", "pojutrze", "wczoraj", "dzisiaj",]

        //some matcher from the net should accept dates in format dd-mm-yyyy or dd/mm/yyyy or dd.mm.yyyy
        static String straightDates = /(?:(?:(?:0?[13578]|1[02])(\\/|-|\.)31)\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\.)(?:29|30)\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})|(?:0?2(\\/|-|\.)29\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))|(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\.)(?:0?[1-9]|1\d|2[0-8])\4(?:(?:1[6-9]|[2-9]\d)?\d{2})/

        static Predicate<Task> hasDatenames(String[] dataNames) {
            {
                task -> dataNames.any { task.content.contains(it) }
            }
        }

        static Predicate<Task> matchesRegex(String regex) {
            Pattern pattern = Pattern.compile(regex)

            return { task -> pattern.matcher(task.content).find() }
        }
    }
}
