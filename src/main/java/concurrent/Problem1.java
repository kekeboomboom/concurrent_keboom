package concurrent;

import javafx.concurrent.Task;

public class Problem1 implements Runnable{

    Task task;

    public Problem1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        while (true) {
            try {
                task.run();
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
