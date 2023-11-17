package crazyConsumer;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public class TaskPool {

    ConcurrentHashMap<String, CrazyTask> taskMap;

    public void addTask(CrazyTask task) {
        taskMap.put(task.taskName, task);
    }

    public void removeTask(CrazyTask task) {
        taskMap.remove(task.taskName);
    }

    public CrazyTask getTask(String taskName) {
        return taskMap.get(taskName);
    }

}
