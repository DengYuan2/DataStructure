package review.algorithm;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好"; //
        String str2 = "尚硅谷你尚硅";
        System.out.println(violenceMatch(str1, str2));
    }

    public static int violenceMatch(String str1, String str2) {
        int i = 0;
        int j = 0;
        String s1 = "";
        String s2 = "";
        while (i < str1.length() && j < str2.length()) {
            s1 = str1.substring(i, i + 1);
            s2 = str2.substring(j, j + 1);
            if (!s1.equals(s2)) {
                i = i - j + 1;
                j = 0;
            } else {
                i++;
                j++;
            }
        }
        if (j == 0) {
            return -1;
        } else
            return i-j;

    }
}
