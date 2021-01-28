package test;

public class Test4 {
    public static void main(String[] args) {
//        int b=1;
//        b |= 256;
//        String binaryString = Integer.toBinaryString(b);
//        System.out.println(binaryString);
//        System.out.println();
//        String s = Integer.toBinaryString(256);
//        System.out.println(s);
//        System.out.println(Integer.toBinaryString(28));
//        String strBytes="00011100";
//        int i = Integer.parseInt(strBytes, 2);
//        System.out.println(i);
        byte a =-88;
        String s = byteToBitString(true, a);
        System.out.println(s);
        String s1="1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100";
        String s2="1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100";
        System.out.println("####"+s1.equals(s2));



    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) { //可能最后一个不需要高位补齐,虽然只是可能，但老师把这里理解成了就是，额。。。
            temp |= 256;
        }
        String binaryString = Integer.toBinaryString(temp);
        if (flag) {
            return binaryString.substring(binaryString.length() - 8);
        } else {
            return binaryString;
        }

    }




}
