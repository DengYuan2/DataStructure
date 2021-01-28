package stack;

import java.util.Stack;

public class Calculator {
    /**
     * 综合计算器【中缀表达式】
     * 7*2*2-5+1-5+3-4
     * 计算思路：【中缀表达式】
     * 1、建立两个栈，一个数栈，一个符号栈；通过一个index值（索引）来遍历表达式
     * 2、如果是数组，直接入数栈
     * 3、如果是符号，分情况如下
     * 3.1 若符号栈为空，直接入栈
     * 3.2 若符号栈中有符号，比较符号
     * 3.2.1 若当前操作符的优先级小于或等于栈中的操作符，就从数栈中pop出两个数，从符号栈中pop出一个符号，进行运算，将得到的结果压入数栈，将当前操作符压入符号栈
     * 3.2.2 若当前操作符的优先级大于栈中的操作符，直接压入符号栈
     * 4、当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号，并运行
     * 5、最后，数栈中只剩下一个数字，即表达式的结果
     *
     * 前缀表达式（波兰表达式） 后缀表达式（逆波兰表达式）
     */
    //todo 注意：本来该程序对7*2*2-4/2+7不行啊，还是有问题的，不过被我修改好啦，在46加了while循环

    public static void main(String[] args) {
//        String expression="32+2*6-2";
        String expression="7*2*2-4/2+7";//若用该式子，结果还是错的，该程序还是有漏洞
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index=0;//用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';//将每次扫描得到的char保存到ch
        String keepNum="";//用于拼接
        int peek=0;
        //扫描expression
        while (true){
            //注意：此处都是个位数
            ch=expression.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)){ //如果是运算符
                if (!operStack.isEmpty()){ //符号栈不为空
                    peek = operStack.peek();
                    if (operStack.priority(ch)<=operStack.priority(peek)){ //当前运算符小或等于
                        while (operStack.priority(ch)<=operStack.priority(peek)){
                            num1=numStack.pop();
                            num2=numStack.pop();
                            oper=operStack.pop();
                            res=operStack.cal(num1,num2,oper);
                            numStack.push(res);
                            if (!operStack.isEmpty()){
                                peek=operStack.peek();
                            }else {
                                break;
                            }
                        }
                        operStack.push(ch);
                    }else{ //当前运算符大
                        operStack.push(ch);
                    }
                }else { //符号栈为空
                    operStack.push(ch);
                }
            }
            else { //如果是数字[注意:由于ascii,若此时为1,可ch为'1',直接实际相差48]
                //问题：这只是针对个位数
//                numStack.push(ch-48);
                //分析思路：
                //1. 当处理多位数时，不能发现一个数就立即入栈
                //2. 需要向expression的index后再看一位，直到是符号才停止
                //3. 需要定义一个字符串变量进行拼接
                //todo 下面是对多位数的巧妙处理!!! 判断下一个字符是否为数字，是就继续扫描，否则入栈
                keepNum+=ch;

                //注意：如果ch已经是最后一位，直接入栈
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {//todo 判断下一个字符是否为数字，是就继续扫描，否则入栈
                    if(numStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //todo 重要!!! 清空keepNum
                        keepNum="";
                    }
                }
            }

            //让index+1，并判断是否扫描完毕
            index++;
            if (index>=expression.length()) break;
        }

        while (true){
            if (operStack.isEmpty()) break;
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //最后结果
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}

//
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组，模拟栈
    private int top = -1;//栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
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

    //返回栈顶的值
    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        return stack[top];

    }

    //返回运算符的优先级，自己决定，优先级用数字表示，数字越大，优先级越高
    //注：char和int可以混用
    public int priority(int ope) { //假定目前表达式中只有+ - * /
        if (ope == '*' || ope == '/') return 1;
        else if (ope == '+' || ope == '-') return 0;
        else return -1;
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;

        }
        return res;
    }
}