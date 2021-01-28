package recursion;

//递归
public class RecursionTest {
    public static void main(String[] args) {
        //测试打印问题
        System.out.println("测试打印问题");
        test(4);
        /*
        运行过程：
        第一个test,即test(4),n=4,进入第二个test,即test(3),n=3,
        进入第三个test,即test(2),n=2,输出2，第三个test结束，
        重回第二个test,运行test(3)中的sout，输出3，第二个test结束，
        重回第一个test,运行test(4)中的sout,输出4,结束。
         */

        //测试阶乘
        System.out.println("测试阶乘问题");
        System.out.println(factorial(4));
    }

    //打印问题：
    public static void test(int n) {
        if (n > 2)
            test(n - 1);
        //若在此处加上else，则只会输出n=2，就不是递归了
        System.out.println("n=" + n);

    }

    //阶乘问题：
    public static int factorial(int n){
        if (n==1)
            return 1;
        else
            return factorial(n-1)*n;
    }

}
