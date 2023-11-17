package crazyConsumer;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public abstract class CrazyTask {

    String taskName;
    int threadNum;
    volatile boolean isTerminated;

    abstract void process();

    void doExecute(List<Message> messages) {
        // messages 分割成 threadNum 份，每个线程处理一份
        // 每个线程处理的时候，调用 process 方法
        Lists.partition(messages, threadNum).forEach(messageList -> {
            CrazyTaskUtil.getOrInitExecutor(taskName, threadNum).execute(this::process);
        });
        if (isTerminated) {
            CrazyTaskUtil.shutdown(taskName);
        }
    }

    void terminate() {
        isTerminated = true;
    }
}
