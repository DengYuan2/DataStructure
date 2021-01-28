package review.algorithm;

import java.util.Arrays;

//还是不会kmp算法
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD"; //ABCDABD
        String str3 = "abcac";
        int[] next = kmpNext(str3);
        int[] next2 = getNext(str3);
        System.out.println(Arrays.toString(next));
        System.out.println(Arrays.toString(next2));
//        int kmp = kmp(str1, str2, next);
//        System.out.println(kmp);

    }

    //自己写的，但是核心的获得next[]的方法还是没有掌握啊~~~
    public static int kmp(String str1,String str2,int[] next){
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        int i=0;
        int j=0;
        while (i<str1.length()&&j<str2.length()){
            if (arr1[i]!=arr2[j]){
                if (j==0){
                    i++;
                }else {
                    j=next[j-1];
                }
            }else {
                i++;
                j++;
            }
        }
        if (j==str2.length()){
            return i-j;
        }else {
            return -1;
        }
    }


    //复制老师的
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0; //若字符串长度为1，部分匹配值一定为0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];  //？？？why，我认为应当直接j=0，因为前缀必定要包括第一个字符，不等则重新开始;而且根本不用while循环，应该用if代替，不过该段语句不应和下方if语句互换
            } //当j=0退出循环时，即从头开始找
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;


        }
        return next;
    }

    //网上的写法
    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
