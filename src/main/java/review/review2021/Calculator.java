package review.review2021;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author DengYuan2
 * @create 2020-12-24 21:10
 */
public class Calculator {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
//        calculator.calculator("7*2*2-4/2+7");
//        String s = calculator.toSuffixExpre("7 * 2 * 2 - 4 / 2 + 7");
        String s = calculator.toSuffixExpre("1 + ( ( 2 + 3 ) * 4 ) - 5");
        System.out.println(s);
    }

    public String toSuffixExpre(String expression) {
        String[] split = expression.split(" ");
        Stack<String> operStack = new Stack<>();
        ArrayList<String> list = new ArrayList<>();
        String value = "";
        for (int i = 0; i < split.length; i++) {
            value = split[i];
            if (isNum(value)) {
                list.add(value);
            } else if (isLeft(value)) {
                operStack.push(value);
            } else if (isRight(value)) {
                while (!isMatch(operStack.peek(), value)) {
                    list.add(operStack.pop());
                }
                operStack.pop();
            } else {
                if (operStack.isEmpty()) {
                    operStack.push(value);
                } else {
                    while (true) {
                        if (operStack.isEmpty() || compareSign(value, operStack.peek())) {
                            operStack.push(value);
                            break;
                        }
                        list.add(operStack.pop());
                    }
                }
            }
        }
        while (!operStack.isEmpty()){
            list.add(operStack.pop());
        }
        return list.toString();
    }

    public boolean isLeft(String oper) {
        return oper.equals("(") || oper.equals("[") || oper.equals("{");
    }

    public boolean isRight(String oper) {
        return oper.equals(")") || oper.equals("]") || oper.equals("}");
    }

    public boolean isMatch(String first, String second) {
        return (first.equals("(") && second.equals(")")) || (first.equals("[") && second.equals("]")) || (first.equals("{") && second.equals("}"));
    }


    public void calculator(String equation) {
        //数字栈
        Stack<Integer> numStack = new Stack<>();
        //符号栈
        Stack<String> signStack = new Stack<>();
        for (int i = 0; i < equation.length(); i++) {
            String value = equation.substring(i, i + 1);
            if (isNum(value)) {
                numStack.push(Integer.valueOf(value));
            } else {
                while (true) {
                    if (signStack.isEmpty()) {
                        signStack.push(value);
                        break;
                    }

                    if (compareSign(value, signStack.peek())) {
                        signStack.push(value);
                        break;
                    } else {
                        String oldSign = signStack.pop();
                        Integer num1 = numStack.pop();
                        Integer num2 = numStack.pop();
                        int result = calc(num1, num2, oldSign);
                        numStack.push(result);
                    }
                }
            }
        }
        while (!signStack.isEmpty()) {
            Integer num1 = numStack.pop();
            Integer num2 = numStack.pop();
            String sign = signStack.pop();
            int calc = calc(num1, num2, sign);
            numStack.push(calc);
        }

        System.out.println(numStack.pop());

    }

    public boolean isNum(String str) {
        if (str.equals("0") || str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4") || str.equals("5") || str.equals("6") || str.equals("7") || str.equals("8") || str.equals("9"))
            return true;
        return false;
    }

    public boolean compareSign(String newSign, String oldSign) {
        int ns = 0;
        int os = 0;
        if (newSign.equals("/") || newSign.equals("*")) {
            ns = 1;
        }
        if (oldSign.equals("/") || oldSign.equals("*")) {
            os = 1;
        }
        if (oldSign.equals("(")||oldSign.equals("[")||oldSign.equals("{")){
            os=-1;
        }
        if (ns > os) {
            return true;
        } else {
            return false;
        }
    }

    public int calc(int num1, int num2, String sign) {
        int result = 0;
        switch (sign) {
            case "+":
                result = num2 + num1;
                break;
            case "-":
                result = num2 - num1;
                break;
            case "*":
                result = num2 * num1;
                break;
            case "/":
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

}
