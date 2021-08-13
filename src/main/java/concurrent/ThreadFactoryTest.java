package concurrent;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author keboom
 * @date 2021/8/13
 */
public class ThreadFactoryTest {

    public static void main(String[] args) {
//        ThreadUtil.execute(() -> System.out.println(" hello"));
//        System.out.println("mian hello");

        new ThreadPoolExecutor(5,10,50, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    }
}
