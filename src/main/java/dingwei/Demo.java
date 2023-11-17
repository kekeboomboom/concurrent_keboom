package dingwei;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/15
 * {@code @description: https://learn.lianglianglee.com/%e4%b8%93%e6%a0%8f/%e4%b8%ad%e9%97%b4%e4%bb%b6%e6%a0%b8%e5%bf%83%e6%8a%80%e6%9c%af%e4%b8%8e%e5%ae%9e%e6%88%98/05%20%20%e5%a4%9a%e7%ba%bf%e7%a8%8b%ef%bc%9a%e5%a4%9a%e7%ba%bf%e7%a8%8b%e7%bc%96%e7%a8%8b%e6%9c%89%e5%93%aa%e4%ba%9b%e5%b8%b8%e8%a7%81%e7%9a%84%e8%ae%be%e8%ae%a1%e6%a8%a1%e5%bc%8f%ef%bc%9f.md}
 */
public class Demo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), new ThreadFactory() {
            private AtomicInteger threadNum = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("pull-service-" + threadNum.incrementAndGet());
                return t;
            }
        });

        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }


        executor.shutdown();
    }
}
