package algorithm.suppliments.stack;

import java.util.Iterator;

/**
 * 栈-数组实现
 * @author DengYuan2
 * @create 2021-01-27 8:26
 */
public class ArrayStack<Item> implements MyStack<Item> {

    private Item[] a = (Item[]) new Object[1];

    /**
     * 元素數量
     */
    private int N = 0;


    @Override
    public MyStack<Item> push(Item item) {
        check();
        a[N++]=item;
        return this;
    }

    public void check() {
        if (N >= a.length) {
            resize(a.length * 2);
        } else if (N > 0 && N <= a.length / 4) {
            resize(a.length / 2);
        }
    }

    public void resize(int size) {
        Item[] tmp = (Item[]) new Object[size];
        for (int i = 0; i < a.length; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    @Override
    public Item pop() throws Exception {
        if (isEmpty()){
            throw new Exception("栈空！");
        }
        Item item = a[--N];
        check();
        //避免对象游离
        a[N]=null;
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
            private int i=N;
            @Override
            public boolean hasNext() {
                return i>0;
            }

            @Override
            public Item next() {
                return a[--i];
            }
        };
    }

}
