package review.algorithm;

public class HannoTower {
    static int count =0;
    public static void main(String[] args) {
        hannoTower(4,'a','b','c');
    }

    public static void hannoTower(int n,char a,char b,char c){
        if (n==1){
            System.out.println("第"+(++count)+"次："+a+"-->"+c);
            return;
        }else {

            hannoTower(n-1,a,c,b);
            //该句就不用再进入递归了，直接输出即可
//            hannoTower(1,a,b,c);
            System.out.println("第"+(++count)+"次："+a+"-->"+c);

            hannoTower(n-1,b,a,c);
        }
    }
}
