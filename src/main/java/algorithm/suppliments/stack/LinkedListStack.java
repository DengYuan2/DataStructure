package algorithm.suppliments.stack;

import java.util.Iterator;

/**
 * @author DengYuan2
 * @create 2021-01-27 8:42
 */
public class LinkedListStack<Item> implements MyStack<Item> {

    private class Node{
        Item item;
        Node next;
    }

    private Node top=null;

    private int N=0;

    @Override
    public MyStack<Item> push(Item item) {
        Node node = new Node();
        node.item=item;
        node.next=top;
        top=node;
        N++;
        return this;
    }

    @Override
    public Item pop() throws Exception {
        if (isEmpty()){
            throw new Exception("the stack is empty!");
        }
        Item item = top.item;
        top=top.next;
        N--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return N==0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node cur = top;
            @Override
            public boolean hasNext() {
                return cur!=null;
            }

            @Override
            public Item next() {
                Item item = cur.item;
                cur=cur.next;
                return item;
            }
        };
    }
}
