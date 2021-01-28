package linkedList;

import java.util.Stack;

/**
 * 该类之所以在这里是为了演示栈基本的使用，因为面试题四的方法之一是用的栈
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("Jack");
        stack.add("Tom");
        stack.add("Smith");
        //出栈
        while (stack.size()>0)
            System.out.println(stack.pop());

    }
}
