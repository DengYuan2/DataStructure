package stack;

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
 * todo 问题：s2完全可以不用栈，用一个list啊。我用list写该程序,老师也觉得没必要且麻烦，使用的也是list
 * todo TV在PolandNotation类中
 * todo 我直接用空格隔开了数据，按理说应该是一连串字符；TV中将字符串自己区分成了list,用的toInfixExpressionList（）方法
 */
public class ToSuffixExpre {
    public static void main(String[] args) {
        //
        String expression = "1 + ( ( 2 + 3 ) * 4 ) - 5";//1, 2, 3, +, 4, *, +, 5, -
//        String expression = "7 * 2 * 2 - 4 / 2 + 7";//7 2 * 2 * 4 2 / - 7 +
        List<String> stringList = getStringList(expression);
        System.out.println(stringList);
        List<String> transfer = transfer(stringList);
        System.out.println(transfer);
    }


    public static List<String> getStringList(String expression) {
        String[] split = expression.split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String item : split) {
            list.add(item);
        }
        return list;
    }


    public static List<String> transfer(List<String> ls) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();
        for (String item:ls) {
            if (item.matches("\\d+")){ //若是数字
                list.add(item);
            }else if (isOper(item)){ //若是运算符
               operActions(item,list,stack);

            }else if (item.equals("(")){ //若是左括号
                stack.push(item);
            }else { //若是右括号
                String temp="";
                while (true){
                    temp=stack.pop();
                    if (temp.equals("(")){
                        break;
                    }
                    list.add(temp);
                }
            }
        }
        while (stack.size()>0){
            list.add(stack.pop());
        }
        return list;
    }

    public static boolean isOper(String oper) {
        return oper.equals("/") || oper.equals("*") || oper.equals("-") || oper.equals("+");
    }

    public static boolean getPriority(String sign1,String sign2){
        int pro1=0;
        int pro2=0;
        if (sign1.equals("*")||sign1.equals("/")) pro1=1;
        if (sign2.equals("*")||sign2.equals("/")) pro2=1;
        return pro1>pro2;
    }

    //对第四步：
    public static void operActions(String item,List<String> list,Stack<String> stack){
        if (stack.isEmpty() || stack.peek().equals("(")){
            stack.push(item);
        }else if (getPriority(item,stack.peek())){//若优先级高
            stack.push(item);
        }else {
            list.add(stack.pop());
            operActions(item,list,stack);
        }
    }


}
