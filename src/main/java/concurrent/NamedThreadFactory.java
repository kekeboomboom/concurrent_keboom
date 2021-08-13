package concurrent;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 根据ThreadPoolExecutor的默认线程工厂模仿而来
 * @author keboom
 * @date 2021/8/13
 */
public class NamedThreadFactory implements ThreadFactory {


    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    NamedThreadFactory(String threadFactoryName, String threadNamePrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = threadFactoryName +
                poolNumber.getAndIncrement() +
                "-" + threadNamePrefix + "-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

    public static void main(String[] args) {
        NamedThreadFactory factory = new NamedThreadFactory("测试业务线程工厂","测试线程");
        factory.newThread(() -> System.out.println(Thread.currentThread().getName())).start();
        factory.newThread(() -> System.out.println(Thread.currentThread().getName())).start();

        // hutool
        ThreadFactory hutool = ThreadFactoryBuilder.create().setNamePrefix("灰猫工厂-thread-").build();
        hutool.newThread(() -> System.out.println(Thread.currentThread().getName())).start();
        hutool.newThread(() -> System.out.println(Thread.currentThread().getName())).start();

        // guava
        ThreadFactory guava = new com.google.common.util.concurrent.ThreadFactoryBuilder().setNameFormat("白猫工厂-thread" + "-%d").build();
        guava.newThread(() -> System.out.println(Thread.currentThread().getName())).start();
        guava.newThread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
