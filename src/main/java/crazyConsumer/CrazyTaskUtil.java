package crazyConsumer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public class CrazyTaskUtil {

    private static Map<String, ExecutorService> executors = new ConcurrentHashMap<>();

    public static ExecutorService getOrInitExecutor(String taskName, int threadNum) {
        ExecutorService executorService = executors.get(taskName);
        if (executorService == null) {
            synchronized (CrazyTaskUtil.class) {
                executorService = executors.get(taskName);
                if (executorService == null) {
                    executorService = initPool(taskName, threadNum);
                    executors.put(taskName, executorService);
                }
            }
        }
        return executorService;
    }

    private static ExecutorService initPool(String taskName, int threadNum) {
        // init pool
        return new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()
                , new ThreadFactoryBuilder().setNameFormat(taskName + "-%d").build());
    }

    public static void shutdown(String taskName) {
        ExecutorService executorService = executors.get(taskName);
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public static void shutdownAll() {
        executors.forEach((taskName, executorService) -> {
            executorService.shutdown();
        });
    }
}
