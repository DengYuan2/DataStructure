package stack;

import java.util.Scanner;
import java.util.Stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
//        //测试
        ArrayStack stack = new ArrayStack(4);
//        String key = "";
//        boolean loop = true;//控制是否退出菜单
//        Scanner scanner = new Scanner(System.in);
//        while (loop) {
//            System.out.println("-----菜单-----");
//            System.out.println("--加入数据-add");
//            System.out.println("--删除数据-del");
//            System.out.println("--所有数据-list");
//            System.out.println("--退出菜单-exit");
//            System.out.println("输入你的操作：");
//            String next = scanner.next();
//            char order = next.toLowerCase().charAt(0);
//            switch (order) {
//                case 'a':
//                    System.out.println("请输入要添加的数据：");
//                    int value = scanner.nextInt();
//                    stack.push(value);
//                    break;
//                case 'd':
//                    try {
//                        System.out.println("出栈的数据为：" + stack.pop());
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                    }
//                    break;
//                case 'l':
//                    stack.showStack();
//                    break;
//                case 'e':
//                    System.out.println("确定退出吗？y/n");
//                    if (scanner.next().toLowerCase().charAt(0) == 'y') loop = false;
//                    scanner.close();
//                    break;
//                default:
//                    break;
//            }
//
//        }
//        System.out.println("退出程序啦~~");

        System.out.println("计算器测试");
//        String equation = "7*2*2-4/2+7-2";//3+2*6-2
        String equation="3+2*6-2";
        stack.calculator(equation);

    }
}

class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组，模拟栈
    private int top = -1;//栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) throw new RuntimeException("栈空了呀，你还要我怎样~~");
        return stack[top--];
    }

    //遍历栈,从栈顶开始显示数据
    public void showStack() {
        if (isEmpty()) {
            System.out.println("栈是空的，没啥子好看的啦~~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 综合计算器
     * 7*2*2-5+1-5+3-4
     * 计算思路：
     * 1、建立两个栈，一个数栈，一个符号栈；通过一个index值（索引）来遍历表达式
     * 2、如果是数字，直接入数栈
     * 3、如果是符号，分情况如下
     * 3.1 若符号栈为空，直接入栈
     * 3.2 若符号栈中有符号，比较符号
     * 3.2.1 若当前操作符的优先级小于或等于栈中的操作符，就从数栈中pop出两个数，从符号栈中pop出一个符号，进行运算，将得到的结果压入数栈，将当前操作符压入符号栈
     * 3.2.2 若当前操作符的优先级大于栈中的操作符，直接压入符号栈
     * 4、当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
     * 5、最后，数栈中只剩下一个数字，即表达式的结果
     */

    //自己的写法：本来是错误的，若用7*2*2-4/2+7代入，结果是错的;但后来在141行加了while循环，就改对啦
    //我是直接用自带的stack
    //老师的写法在新建的一个类中，名为Calculator，对于7*2*2-4/2+7是错的
    //除了没有考虑多位数问题，我的写法也是可以的
    public void calculator(String equation) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<String> markStack = new Stack<String>();
        Integer num1 = 0;
        Integer num2 = 0;
        int res = 0;
        for (int i = 0; i < equation.length(); i++) {
            String str = equation.substring(i, i + 1); //默认数字全是个位数了
            if (isNum(str))
                numStack.push(Integer.valueOf(str));
            else {
                if (markStack.isEmpty())
                    markStack.push(str);
                else {
                    String oldStr = markStack.peek();
                    if (compareSign(str, oldStr))  //当前操作符大
                        markStack.push(str);
                    else {//当前操作符小或等于
                        //!!!todo 之所以错误是因为3.2.1中要一直检查（并不是只检查一次）markStack中的第一个是否优先级小于等于当前的操作符，否则一直要把第一个弹出做运算，直到当前运算符大于第一个
                        while (!compareSign(str,oldStr)){
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            res = cal(num1, num2, markStack.pop());
                            numStack.push(res);
                            if (!markStack.isEmpty()){ //当前符号栈中有操作符时
                                oldStr=markStack.peek();
                            }else { //当前符号栈中没有操作符时，即栈空时，不用再比较了
                                break;
                            }
                        }
                        markStack.push(str);
                    }
                }
            }

        }
        while (numStack.size() > 1) {//!markStack.isEmpty()
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = cal(num1, num2, markStack.pop());
            numStack.push(res);
        }
        res = numStack.pop();
        System.out.println(res);
    }
    public boolean isNum(String str) {
        if (str.equals("0") || str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4") || str.equals("5") || str.equals("6") || str.equals("7") || str.equals("8") || str.equals("9"))
            return true;
        return false;
    }

    public boolean compareSign(String newSign, String oldSign) {
        int newPro = 0;
        int oldPro = 0;
        if (oldSign=="") return true;
        if (newSign.equals("*")  || newSign.equals("/")) newPro = 1;
        if (oldSign.equals("*") || oldSign.equals("/")) oldPro = 1;
        if (newPro <= oldPro) {
            return false;
        } else return true;
    }

    public int cal(int num1, int num2, String sign) {
        if (sign.equals("*")) return num1 * num2;
        else if (sign.equals("/")) return num2 / num1;
        else if (sign.equals("+")) return num1 + num2;
        else return num2 - num1;
    }
}

