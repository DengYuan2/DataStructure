package stack;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转后缀表达式：
 * 思路:
 * 1、初始化两个栈：运算符栈s1和存储中间结果的栈s2
 * 2、从左至右扫描中缀表达式
 * 3、遇到数字时，将其压入s2
 * 4、遇到运算符时，比较其与s1栈顶运算符的优先级：
 * 4.1 若s1为空，或栈顶运算符为“(”,直接将该运算符压入s1
 * 4.2 若优先级比栈顶运算符高，直接将该运算符压入s1
 * 4.3 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到4.1，与s1中新的栈顶运算符比较
 * 5、遇到括号时：
 * 5.1 若是“(”,直接压入s1
 * 5.2 若是“）”，则依次弹出s1栈顶运算符，并压入s2,直到左括号为止，此时将这一对括号丢弃
 * 6、重复步骤2-5，直到表达式的最右边
 * 7、将s1中剩余的运算符依次弹出并压入s2
 * 8、依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 * todo 我写的在ToSuffixExpre类中
 */
public class PolandNotation {
    public static void main(String[] args) {
        //计算器完整版
        String s ="1+((2+3)*4)-5";
        //1、将中缀表达式转为对应的list,便于遍历
        List<String> infixList = toInfixExpressionList(s);
        System.out.println("中缀表达式对应的list"+infixList);
        //2、将中缀表达式转为后缀表达式
        List<String> suffixExpressionList = parseSuffixExpressionList(infixList);
        System.out.println("后缀表达式对应的list"+suffixExpressionList);
        //3、用后缀表达式运算
        int res = calculate(suffixExpressionList);
        System.out.printf("expression=%d",res);
/*
        //给定后缀表达式，进行运算
        //先定义一个逆波兰表达式,为了方便，用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 -";//（30+4*5-6
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";//4*5-8+60+8/2=76
        String suffixExpression = "7 2 * 2 * 4 2 / - 7 +";//7*2*2-4/2+7 结果是正确的！
        //7 2 * 2 * 4 2 / - 7 +
        //思路：
        //1. 将表达式放到ArrayList中【这样就不用一个个用index扫描了】
        //2. 将list传递给一个方法，遍历list,配合栈完成计算
//        System.out.println(getListString(suffixExpression));
        System.out.printf("后缀表达式%s计算的结果是：%d", suffixExpression, calculate(getListString(suffixExpression)));
        */
    }

    //转成后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();
        for (String item:ls) {
            if (item.matches("\\d+")){ //若是数字
                list.add(item);
            }else if (item.equals("(")){ //若是左括号
                stack.push(item);
            }else if (item.equals(")")){ //若是右括号
                while (!stack.peek().equals("(")){
                    list.add(stack.pop());
                }
                stack.pop(); //！！！丢弃左括号
            }else { //若是运算符 todo 三种可能直接写在了一起！！牛！！而我用的是递归
                while (stack.size()!=0&&Operation.getValue(stack.peek())>= Operation.getValue(item)){
                    list.add(stack.pop());
                }
                stack.push(item);
            }
        }
        while (stack.size()!=0) list.add(stack.pop());
        return list;
    }

    //将中缀表达式转为对应的list,便于遍历【此处给定的表达式未用空格隔开】
    //老师的写法：
    public static List<String> toInfixExpressionList(String s){
        ArrayList<String> list = new ArrayList<>();
        int i = 0; //指针，用于遍历中缀表达式
        String str=""; //用于多位数的拼接
        char ch=' '; //存放每个遍历的字符
        do {
            ch=s.charAt(i);
            if (ch<48||ch>57){ //若不是数字；按照ASCII,数字0-9应当在48-57之间
                list.add(""+ch);
                i++;
            }else { //若是数字，要考虑多位数
                str=""; //先清空str
                //todo 此处直接检查后面是不是数字，如果是数字，则不断直接拼接(与Calculator类中的处理方式不一样，但也很巧妙)
                               while (i<s.length()&&(ch=s.charAt(i))>=48&&(ch=s.charAt(i))<=57){
                    str+=ch;
                    i++;
                }
                list.add(str);
            }

        }while (i<s.length());
        return list;
    }

    //将中缀表达式转为对应的list,便于遍历【此处给定的表达式未用空格隔开】
    //我的写法;与TV相比，我对运算符和括号的处理过于冗余，而老师直接用char来区分是否为数字，与另外两种区分开来，更为简单
    public static List<String> toInfixExpression(String s) {
        String str = "";
        String temp = "";//对多位数的拼接
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            str = s.substring(i, i + 1);
            if (isOper(str)||str.equals("(")||str.equals(")")){ //运算符或括号
                list.add(str);
            }else { //数字
                temp += str;
                if (i==s.length()-1) { //当走到最后一个，直接加入
                    list.add(temp);
                } else {
                    if (isOper(s.substring(i + 1, i + 2))||isBracket(s.substring(i+1,i+2))) {
                        list.add(temp);
                        temp = "";
                    }
                }
            }

        }
        return list;
    }


    //依次将逆波兰表达式的数据和运算符放入到ArrayList中【此处给定的表达式用空格隔开了】
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    /**
     * 后缀表达式运算：
     * 思路:
     * 从左向右扫描表达式，遇到数字时，将数字压入数栈；
     * 遇到运算符时，弹出数栈的两个数，用运算符对它们进行运算，将结果压入数栈。
     * 重复上述操作直到表达式最右端
     */
    //完成对逆波兰表达式的计算
    public static int calculate(List<String> ls) {
        Stack<Integer> stack = new Stack<>();
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        String str = "";
        for (int i = 0; i < ls.size(); i++) {
            str = ls.get(i);
            if (isOper(str)) { //若是运算符
                num1 = stack.pop();
                num2 = stack.pop();
                res = cal(num1, num2, str);
                stack.push(res);
            } else { //若是数字
                stack.push(Integer.parseInt(str));

            }
        }
        //老师的写法：
//        for (String item:ls) {
//            if (item.matches("\\d+")){//用正则表达式来取出数（可以为多位数）
//                stack.push(Integer.parseInt(item));
//            }else { //若为运算符
//                //。。。
//            }
//        }
        return stack.pop();
    }

    public static boolean isOper(String oper) {
        return oper.equals("/") || oper.equals("*") || oper.equals("-") || oper.equals("+");
    }

    public static int cal(int num1, int num2, String oper) {
        int res = 0;
        switch (oper) {
            case "+":
                res = num2 + num1;
                break;
            case "-":
                res = num2 - num1;
                break;
            case "*":
                res = num2 * num1;
                break;
            case "/":
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //判别是否为括号
    public static boolean isBracket(String brac){
        return brac.equals("(")||brac.equals(")");
    }

}

//编写一个类，返回一个运算符对应的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=1;
    private static int DIV=1;
    //返回对应的优先级数字
    public static int getValue(String operation){
        int res=0;
        switch (operation){
            case "+":
                res= ADD;
                break;
            case "-":
                res= SUB;
                break;
            case "*":
                res= MUL;
                break;
            case "/":
                res= DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}
