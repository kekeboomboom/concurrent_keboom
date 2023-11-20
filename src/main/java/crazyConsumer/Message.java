package crazyConsumer;

/**
 * {@code @author:} keboom
 * {@code @date:} 2023/11/17
 * {@code @description:}
 */
public class Message {

    private String messageBody;

    public Message(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageBody='" + messageBody + '\'' +
                '}';
    }
}
