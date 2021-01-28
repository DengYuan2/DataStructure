package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * todo 在stack包原来PolandNotation的基础上自己实现了小数功能
 */
public class PolandNotation {
    public static void main(String[] args) {
        //计算器完整版
        String s = "1.5+((5-1)*7.5)-5.5";
        //1、将中缀表达式转为对应的list,便于遍历
        List<String> infixList = toInfixExpressionList(s);
        System.out.println("中缀表达式对应的list" + infixList);
        //2、将中缀表达式转为后缀表达式
        List<String> suffixExpressionList = parseSuffixExpressionList(infixList);
        System.out.println("后缀表达式对应的list" + suffixExpressionList);
        //3、用后缀表达式运算
        double res = calculate(suffixExpressionList);
        System.out.println("结果为" + res);
    }

    //转成后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();
        for (String item : ls) {
            //todo 主要修改地方1：
            if (item.matches("\\d+\\.\\d+") || item.matches("\\d+")) { //若是数字,前一个是小数的意思
                list.add(item);
            } else if (item.equals("(")) { //若是左括号
                stack.push(item);
            } else if (item.equals(")")) { //若是右括号
                while (!stack.peek().equals("(")) {
                    list.add(stack.pop());
                }
                stack.pop(); //！！！丢弃左括号
            } else { //若是运算符
                while (stack.size() != 0 && Operation.getValue(stack.peek()) >= Operation.getValue(item)) {
                    list.add(stack.pop());
                }
                stack.push(item);
            }
        }
        while (stack.size() != 0) list.add(stack.pop());
        return list;
    }

    //将中缀表达式转为对应的list,便于遍历【此处给定的表达式未用空格隔开】
    //老师的写法：
    public static List<String> toInfixExpressionList(String s) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0; //指针，用于遍历中缀表达式
        String str = ""; //用于多位数的拼接
        char ch = ' '; //存放每个遍历的字符
        do {
            ch = s.charAt(i);
            //todo 主要修改地方2.1：
            if ((ch < 48 || ch > 57) && ch != 46) { //若不是数字；按照ASCII,数字0-9应当在48-57之间
                list.add("" + ch);
                i++;
            } else { //若是数字，要考虑多位数
                str = ""; //先清空str
                //此处直接检查后面是不是数字，如果是数字，则拼接
                //todo 主要修改地方2.2：
                while ((i < s.length() && (ch = s.charAt(i)) >= 46 && (ch = s.charAt(i)) <= 57 && (ch = s.charAt(i)) != 47)) { //小数点为46
                    str += ch;
                    i++;
                }
                list.add(str);
            }

        } while (i < s.length());
        return list;
    }


    //完成对逆波兰表达式的计算
    public static double calculate(List<String> ls) {//todo 主要修改地方3：int型改为double
        Stack<Double> stack = new Stack<>();
        double num1 = 0;
        double num2 = 0;
        double res = 0;
        String str = "";
        for (int i = 0; i < ls.size(); i++) {
            str = ls.get(i);
            if (isOper(str)) { //若是运算符
                num1 = stack.pop();
                num2 = stack.pop();
                res = cal(num1, num2, str);
                stack.push(res);
            } else { //若是数字
                stack.push(Double.parseDouble(str));

            }
        }
        return stack.pop();
    }

    public static boolean isOper(String oper) {
        return oper.equals("/") || oper.equals("*") || oper.equals("-") || oper.equals("+");
    }

    public static double cal(double num1, double num2, String oper) {
        double res = 0;
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
}

//编写一个类，返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int sub = 1;
    private static int MUL = 1;
    private static int DIV = 1;

    //返回对应的优先级数字
    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = DIV;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}
