package test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Rule;
import org.junit.Test;

import java.util.*;

public class Test1 {
    @Test
    public void test(){
        String equation="7*2*2-5";//+1-5+3-4
        for (int i = 0; i < equation.length(); i++) {
            String substring = equation.substring(i, i + 1);
            System.out.println(substring);
            if (substring.equals("2")) System.out.println("找到了"+i);
        }
//        for (int i = 0; i < equation.length(); i++) {
//            char c = equation.charAt(i);
//            System.out.print(c+"    ");
//            if (c=='1'){
//                System.out.println();
//                System.out.println(i);
//            }
//        }


    }

    @Test
    public void test2(){
        String expression="7*2*2-4/2+7";
        expression.split("");
    }

    @Test
    public void test3(){
        String s = "5.5";
        double aDouble = Double.parseDouble(s);
        System.out.println(aDouble);
    }

    @Test
    public void test4(){
        int oldCapacity = 15;
        int n = oldCapacity >> 1; //原来数字的0.5倍
        System.out.println(n);
    }

    @Test
     public void test5(){
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        HashSet<Integer> set = new HashSet<>();
        set.add(3);
        set.add(4);
        hashSet.addAll(set);
        System.out.println(hashSet);
    }

    @Test
    public void test6(){
        String str ="true1";
        boolean b = Boolean.parseBoolean(str); //boolean比较特殊，不是true则全是false；
        // 也不会像Integer.parseInt一样可能出现格式异常，如Integer.parseInt(12s3)

        System.out.println(b);

        String string="true";
        System.out.println(Boolean.parseBoolean(string));

        List li =new ArrayList<Integer>();
    }

    @Test
    public void test7(){
        String s ="10000000";
        int i = Integer.parseInt(s, 2);
        System.out.println(i);
        byte b= (byte) i;
        System.out.println(b);

    }

    @Test
    public void test8(){
        boolean flag=true;
        int temp = 6;
        if (flag) { //可能最后一个不需要高位补齐,虽然只是可能，但老师把这里理解成了就是，额。。。
            temp |= 256; //此处不管正负数都可以用的，主要是针对正数
        }
        String binaryString = Integer.toBinaryString(temp);
        if (flag || temp < 0) { //针对下面提出的第二个问题，将原来的if(flag)加了一个可能性
            System.out.println("if: "+binaryString.substring(binaryString.length() - 8));
        } else { //todo 这里还有两个老师没考虑的问题：第一个没解决呢，第二个根据网友的建议改了
            // 1、最后字节若以0开头，则没法使用此方法获得真实的二进制字符串；比如：最后一个是28,进入else后得到11100，但也有可能，它原来是0011100或00011100等，而这些情况被排除了
            //2、若最后字节是负数，则它是32位的，进入这个else后返回的数据过长了
            System.out.println("else: "+binaryString);
        }
    }

    @Test
    public void test9(){

        Set<String> set1 = new HashSet<>();
        set1.add("1");
        set1.add("2");
        set1.add("3");
        HashSet<String> set2 = new HashSet<>();
        set2.add("1");
        set2.add("4");
        set1.removeAll(set2);
        Iterator<String> iterator = set1.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }

    }
}
