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
     * 因此我们希望一个任务池，我们如果有新的任务那么就提交到任务池中，任务池中有多个任务，每个任务有任务名，任务线程数，任务是否终止等属性。
     * 任务有启动方法，终止方法
     * 任务池子中的任务可以动态添加，任务可以动态删除。
     * @param args
     */
    public static void main(String[] args) {
        // this is entry point

        // 比方说我们这个有rocketmq不同主题的consumer
        /*
        BatchConsumer batchConsumer = ONSFactory.createBatchConsumer(consumerProperties);
        batchConsumer.subscribe(MqConfig.TOPIC, MqConfig.TAG, new BatchMessageListener() {

            @Override
            public Action consume(final List<Message> messages, ConsumeContext context) {
                TaskPool.getTask(MqConfig.TOPIC).doExecute(messages);
                // 批量消息处理。
                return Action.CommitMessage;
            }
        });
        //启动batchConsumer。
        batchConsumer.start();
        */

        TaskPool taskPool = new TaskPool();
        taskPool.addTask(new EmailTask());
        taskPool.addTask(new PhoneTask());

        // 比方说我们这个有rocketmq不同主题的consumer
        taskPool.getTask("emailTask").doExecute(new ArrayList<Message>());
        taskPool.getTask("phoneTask").doExecute(new ArrayList<Message>());


    }
}
