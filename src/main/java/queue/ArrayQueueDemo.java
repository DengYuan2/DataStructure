package queue;


import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
                    arrayQueue.showQueue();
                    break;
                case 'e':
                    scanner.close();//关闭scanner
                    loop =false;
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据");
                    int data = scanner.nextInt();
                    arrayQueue.addQueue(data);
                    break;
                case 'g':
                    try {
                        int result = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",result);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
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


//使用数组模拟队列
class ArrayQueue {
    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//用于存放数据的模拟队列

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头的前一个位置，并不包含
        rear = -1;//指向队列尾的位置，包含（即队列最后一个数据的位置）

    }

    //判断队列是否满,满则返回true
    public boolean isFull() {
        return rear + 1 == maxSize;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("已满，不能添加");
            return;
        } else arr[++rear] = data;

    }

    //获取队列数据,即数据出队列
    public int getQueue() {
        if (isEmpty())
            throw new RuntimeException("队列空，不能取数据");
        else
            return arr[++front];
    }

    //展示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        } else
            for (int i = 0; i < arr.length; i++)
                System.out.printf("arr[%d]=%d\n", i, arr[i]);

    }

    //显示队列的头数据，不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            throw new RuntimeException("队列为空，不能取数据~~");
        }
        return arr[front+1];

    }

}

/**
 * 问题分析与优化：
 * 1. 目前数组只能使用一次（即使为空，也可能判定为满），不能重复使用，没有充分的利用
 * 2. 将该数组使用算法，改进成一个环形队列：取模 %
 *      思路分析：1) front的含义变为：直接指向队列的第一个元素,初始值为0
 *               2) rear的含义变为：指向最后一个元素后面的位置。初始值为0。希望空出一个空间作为约定，即预留空间
 *               3) 判满条件：(rear+1)%maxSize=front
 *               4) 判空条件：rear==front
 *               5) 队列中有效的数据的个数为 (rear-front+maxSize)%maxSize
 */

