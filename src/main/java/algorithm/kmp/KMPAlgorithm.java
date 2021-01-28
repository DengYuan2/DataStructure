package algorithm.kmp;

import java.util.Arrays;

//kmp算法 ？？？也许需要多看几遍视频，或者自己找资料了解
//如何更好地理解和掌握 KMP 算法? - 海纳的回答 - 知乎
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD"; //ABCDABD
        int[] next = kmpNext(str2);
        System.out.println("next= " + Arrays.toString(next));
        int res = kmpSearch(str1, str2, next);
        int resTV = kmpSearchTV(str1, str2, next);
        System.out.println("匹配到的位置为" + res);
        System.out.println("匹配到的位置为" + resTV);
    }

    /**
     * kmp算法：按照思路自己写的
     *
     * @param str1 源字符串
     * @param str2 子字符串
     * @param next 部分匹配值表
     * @return 找到则返回第一个匹配的位置，否则返回-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        int i = 0;
        int j = 0;
        int count = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
                count++;
            } else {
                if (count == 0) {
                    i++;
                    j = 0;
                } else {
                    i = i - next[j - 1]; //(i-count) + (count-next[j-1])
                    j = 0;
                    count = 0;
                }
            }
        }
        if (j > 0)
            return i - j;
        else
            return -1;

    }

    //kmp算法 老师写的,还是不明白它的核心点
    public static int kmpSearchTV(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //kmp算法核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1]; //
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length())
                return i - j + 1;
        }

        return -1;
    }

    //获取字符串的部分匹配值表,还未搞懂，后期可以再想一下
    //感觉跟老师说的前缀、后缀没啥关系，可以按照自己的想法理解；即跟前面的不一样则为0，出现一样的则加1,再看后面的是否跟前面走过的值一样，不一样还是置为0，再重新开始
    //但还是有一步不理解，感觉可以改一改看下面标注,
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
}
