package crazyConsumer;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public class PhoneTask extends CrazyTask {

    public PhoneTask(String taskName, int threadNum) {
        this.taskName = taskName;
        // default thread num
        this.threadNum = threadNum;
        this.isTerminated = false;
    }

    @Override
    void process(Message message) {
        System.out.println(Thread.currentThread().getName() +"  process  "+ message.toString());
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "PhoneTask{" +
                "taskName='" + taskName + '\'' +
                ", threadNum=" + threadNum +
                ", isTerminated=" + isTerminated +
                '}';
    }
}
