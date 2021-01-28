package algorithm.suppliments.queue;

/**
 * @author DengYuan2
 * @create 2021-01-27 8:58
 */
public interface MyQueue<Item> extends Iterable<Item> {
    int size();

    boolean isEmpty();

    MyQueue<Item> add(Item item);

    Item remove() throws Exception;
}
