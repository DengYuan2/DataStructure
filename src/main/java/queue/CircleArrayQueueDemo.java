package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        System.out.println("测试数组模拟环形队列");
         CircleArray queue = new CircleArray(4);//有效数据最大为3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop= true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.show();
                    break;
                case 'e':
                    scanner.close();//关闭scanner
                    loop =false;
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据");
                    int data = scanner.nextInt();
                    queue.addQueue(data);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据为%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序结束啦，完结撒花~~~");
    }
}

class CircleArray {
    int[] array;
    int front;
    int rear;
    int maxSize;

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        front = 0;//指向第一个元素，包含
        rear = 0;//指向最后一个元素后的位置，不包含
        array = new int[maxSize];
    }

    //判满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("不能再添加数据啦");
            return;
        } else {
            //直接加入数据
            array[rear] = value;
            //将rear后移，这里必须考虑取模
            rear = (rear + 1) % maxSize;
        }
    }

    //取出数据
    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("队列为空，没有数据可取...");
        //1. 把front对应的值保存到一个零时变量
        int data = array[front];
        //2. 将front后移，考虑取模
        front = (front + 1) % maxSize;
        return data;
    }

    //显示队列所有数据,老师的写法
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列是空的，没有数据~~~");
            return;
        }
        //思路：从front开始遍历，遍历多少个? -->需要用到有效数据的个数
        //我写的这个方法是错的啦，错在提前将 i=(i+1)%maxSize，则可能大部分都满足i < front+size()，则会一直循环；如maxSize=4,front=3,rear=0;此时size=1
//        for (int i = front; i < front+size(); i=(i+1)%maxSize) {
//            System.out.printf("array[%d]=%d\n",i,array[i]);
//        }
        //该方法正确
        for (int i = front; i < front+size(); i++) {
            System.out.printf("array[%d]=%d\n",i%maxSize,array[i%maxSize]);
        }
        //我的思路：从front出发，直到走到rear [该方法可行，但比较麻烦，不如上一种]
//        int s = front;//因为将front改变了，最后要复原，所以用一个临时变量保存一下
//        while (!isEmpty()) {
//            System.out.printf("array[%d]=%d\n",front,array[front]);
//            front = (front + 1) % maxSize;
//        }
//        front=s;
    }

    //显示队列所有数据，我的写法
    public void show(){
        if (isEmpty()){
            System.out.println("队列是空的");
            return;
        }
        int start = front;
        while (true){
            if (start==rear){
                break;
            }
            System.out.printf("array[%d]=%d\n",start,array[start]);
            start=(start+1)%maxSize;
        }
    }
    //当前队列有效数据个数
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    //显示队列第一个元素
    public int headQueue(){
        if (isEmpty()) throw new RuntimeException("队列是空的，没有数据~~~");
        return array[front];
    }


}

/**
 * 带头节点的链表
 * 1. 头节点：不存放具体数据，作用就是指向单链表头next
 * 2. 节点：数据 next节点
 * 3. 最后一个节点：数据 null
 */