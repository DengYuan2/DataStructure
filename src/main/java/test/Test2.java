package test;


//todo
public class Test2 {
    public static void main(String[] args) {
        long test = test(20);
        System.out.println(test);
    }
    public static long test(long n){
        if (n==1){
            return 1;
        }
        else{

            return n*test(n-1);
        }
    }



}
