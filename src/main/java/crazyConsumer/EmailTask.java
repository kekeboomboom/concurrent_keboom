package crazyConsumer;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public class EmailTask extends CrazyTask{

    public EmailTask(String taskName, int threadNum) {
        this.taskName = taskName;
        // default thread num
        this.threadNum = threadNum;
        this.isTerminated = false;
    }

    @Override
    void process(Message message) {
        // do something
        System.out.println(Thread.currentThread().getName() +"  process  "+ message.toString());
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "EmailTask{" +
                "taskName='" + taskName + '\'' +
                ", threadNum=" + threadNum +
                ", isTerminated=" + isTerminated +
                '}';
    }
}
