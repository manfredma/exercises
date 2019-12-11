package manfred.multi.task;

import lombok.Getter;

import java.util.concurrent.Callable;

@Getter
public class TaskPair<V> {

    private String name;

    private Callable<V> callable;

    public TaskPair(String name, Callable<V> cal) {
        this.name = name;
        this.callable = cal;
    }

    public static <V> TaskPair<V> of(String taskName, Callable<V> cal) {
        return new TaskPair<V>(taskName, cal);
    }
}
