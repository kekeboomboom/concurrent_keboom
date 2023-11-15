package concurrent;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 不做扩缩容了，栈固定大小为10
 * @param <E>
 */
public class MyStack<E> extends Stack {

     Object[] elementData;
     int cur = 0;

     public MyStack() {
          this.elementData = new Object[10];
          this.cur = 0;
     }


     public synchronized E pop() {
          E       obj;
          int     len = size();

          obj = (E) elementData[cur-1];

          elementData[cur-1]=null;
          cur--;

          return obj;
     }

     public synchronized E peek() {
          if (cur == 0)
               throw new EmptyStackException();
          return (E) elementAt(cur - 1);
     }


     public boolean empty() {
          return cur == 0;
     }

}
