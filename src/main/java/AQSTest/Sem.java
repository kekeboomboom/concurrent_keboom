package AQSTest;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Range;


import java.util.HashMap;
import java.util.concurrent.*;

public class Sem {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        String s = map.get(i);
                        if (s == null) {
                            map.put(i,Thread.currentThread().getName());
                        }
                    }
                }
            });

        }

    }
}
