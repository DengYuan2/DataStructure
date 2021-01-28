package algorithm.kmp;

//kmp算法之前讲的暴力匹配算法
//只要有一个字符不成功，就要回溯，非常浪费，由此引出kmp算法
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好"; //
        String str2 = "尚硅谷你尚硅你好";
//        System.out.println(match(str1, str2));

        System.out.println(bruteForce(str1, str2));
        System.out.println(violenceMatch(str1, str2));
    }

    //暴力匹配算法,根据思路自己写的
    public static int bruteForce(String str1, String str2) {
        String s1 = "";
        String s2 = "";
        int i = 0;
        int j = 0;
        while (i < str1.length() && j < str2.length()) {
            s1 = str1.substring(i, i + 1);
            s2 = str2.substring(j, j + 1);
            if (s2.equals(s1)) { //相等则找下一个
                i++;
                j++;
            } else { //不相等则str1从 最开始和str2相同的位置 的后面继续，str2从0继续
                i = i - j + 1; //!!! 关键是这个式子，太棒啦
                j = 0;
            }

        }
        if (j != 0)
            return i - str2.length();
        else
            return -1;
    }

    //暴力匹配算法，老师写的，和我的区别是：用的是char[]，而我是subString
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int len1 = str1.length();
        int len2 = str2.length();
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        //判别是否找到
        if (j == len2)
            return i - j;
        else
            return -1;
    }
}
