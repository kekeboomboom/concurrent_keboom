package crazyConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/20
 * {@code @description:}
 */
public class SimpleConsumer {

    private String topic;
    private String tag;

    public SimpleConsumer(String topic, String tag) {
        this.topic = topic;
        this.tag = tag;
    }

    public List<Message> receive() {
        ArrayList<Message> list = new ArrayList<>();
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            list.add(new Message(topic + "-" + tag + "-" + i + "-" + currentTimeMillis));
        }
        System.out.println("receive message: " + list.toString());
        return list;
    }

    public void ack(Message message) {
        System.out.println("ack message: " + message);
    }
}
