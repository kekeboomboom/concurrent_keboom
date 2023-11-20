package crazyConsumer;

import java.util.ArrayList;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public class Main {

    /**
     * 一种多线程消费场景。比如我们有一个消费队列，里面有各种消息，我们需要尽快的消费他们，不同的消息对应不同的业务
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        // 比方说我们这个有rocketmq不同主题的consumer
        /*
        List<MessageView> messageViewList = null;
        try {
            messageViewList = simpleConsumer.receive(10, Duration.ofSeconds(30));
            messageViewList.forEach(messageView -> {
                System.out.println(messageView);
                //消费处理完成后，需要主动调用ACK提交消费结果。
                try {
                    simpleConsumer.ack(messageView);
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            });
        } catch (ClientException e) {
            //如果遇到系统流控等原因造成拉取失败，需要重新发起获取消息请求。
            e.printStackTrace();
        }

         */

        // 想要实现多线程消费消息，我们希望有一个任务，此任务能够不停的拉取消息，然后创建子线程池去消费消息。
        // 停止任务后，需要将任务中的消息消费完后，再关闭任务。

        ArrayList<CrazyTask> tasks = new ArrayList<>();
        tasks.add(new PhoneTask("phoneTask", 2));
        tasks.add(new EmailTask("emailTask", 3));

        for (CrazyTask task : tasks) {
            new Thread(() -> {
                task.doExecute(new SimpleConsumer("topic"+task.getTaskName().charAt(0), "tagA"));
            }).start();
        }

        // task running
        Thread.sleep(150);

        // task terminated
        tasks.forEach(CrazyTask::terminate);
    }
}
