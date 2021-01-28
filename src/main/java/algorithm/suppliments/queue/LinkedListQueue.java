package algorithm.suppliments.queue;

import java.util.Iterator;

/**
 * @author DengYuan2
 * @create 2021-01-27 8:59
 */
public class LinkedListQueue<Item> implements MyQueue<Item> {
    private class Node{
        Item item;
        Node next;
    }

    public Node first;
    public Node last;
    public int N=0;
    @Override
    public int size() {
        return N;
    }

    @Override
    public boolean isEmpty() {
        return N==0;
    }

    @Override
    public MyQueue<Item> add(Item item) {
        Node node = new Node();
        node.item=item;
        node.next=null;
        if (isEmpty()){
            first=node;
            last=node;
        }else {
            last.next=node;
            last=node;
        }
        N++;
        return this;
    }

    @Override
    public Item remove() throws Exception {
        if (isEmpty()){
            throw new Exception("the queue is empty!");
        }
        Node node = first;
        first=first.next;
        N--;
        if (isEmpty()){
            last=null;
        }
        return node.item;
    }

    @Override
    public Iterator<Item> iterator() {

        return new Iterator<Item>() {
            Node cur = first;
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
