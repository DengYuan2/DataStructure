package algorithm.suppliments.stack;

/**
 * @author DengYuan2
 * @create 2021-01-27 8:23
 */
public interface MyStack<Item> extends Iterable<Item> {
    MyStack<Item> push(Item item);

    Item pop() throws Exception;

    boolean isEmpty();

    int size();
}
