package crazyConsumer;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public abstract class CrazyTask {
    String taskName;
    int threadNum;
    volatile boolean isTerminated;
    // every partition data num.
    // for example: I receive 5 messages, partitionDataNum is 2, then i will partition 5 messages to 3 parts, 2,2,1
    int partitionDataCount = 2;

    abstract void process(Message message);

    void doExecute(SimpleConsumer consumer) {
        while (true) {
            // 此消费者每次主动拉取消息队列中消息
            List<Message> messages = consumer.receive();
            if (messages.isEmpty()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                continue;
            }
            // 获取处理此topic或者说处理此类型task的线程池
            ExecutorService executor = CrazyTaskUtil.getOrInitExecutor(taskName, threadNum);
            // 将消息分片，每个线程处理一部分消息
            List<List<Message>> partition = Lists.partition(messages, partitionDataCount);
            // 以消息分片数初始化CountDownLatch，每个线程处理完一片消息，countDown一次
            // 当countDownLatch为0时，说明所有消息都处理完了，countDownLatch.await();继续向下执行
            CountDownLatch countDownLatch = new CountDownLatch(partition.size());

            partition.forEach(messageList -> {
                executor.execute(() -> {
                    messageList.forEach(message -> {
                        process(message);
                        consumer.ack(message);
                    });
                    countDownLatch.countDown();
                });
            });
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (isTerminated) {
                break;
            }
        }
        // 释放线程池
        CrazyTaskUtil.shutdownThreadPool(taskName);
    }

    void terminate() {
        isTerminated = true;
        System.out.println();
        System.out.println(taskName + " shut down");
    }

    public String getTaskName() {
        return taskName;
    }
}
