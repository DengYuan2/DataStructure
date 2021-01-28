package recursion;

//todo 一定要debug一遍，虽然很多，走不完，但一定要结合EightQueens.PNG中的思路理解
public class EigheQueens {
    //max表示有几个皇后
    int max =8;
    //array保存皇后放置位置的结果，如array = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count=0;
    static int judgeCount=0;
    public static void main(String[] args) {
        //测试八皇后
        EigheQueens queens = new EigheQueens();
        queens.check(0);
        System.out.printf("一共有%d种解法",count);
        System.out.println();
        System.out.printf("一共判断了%d次",judgeCount);

    }

    //放置第n个皇后
    //注意:check()是每一次递归时进入check中都有一个for循环，因此会有回溯
    public void check(int n){ //n从0开始

        if (n==max){ //此时即放第9个皇后，也就是前8个皇后已经放好了
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            //先将n皇后放到该行的第一列
            array[n]=i;
            //判断是否冲突
            if (judge(n)){ //不冲突时，放下一个
                check(n+1);
            }
            //如果冲突，自然进入下一个for循环
        }

    }

    //查看当摆放第n个皇后时，是否与前n-1个相冲突【注意n在0-7之间哦，也是下标】
    public boolean judge(int n){  //冲突则为false
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //前者表示同一列，后者表示同一斜线；不用判断是否在同一行
            if (array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i]))
                return false;
        }
        return true;
    }

    //打印皇后摆放位置
    public void print(){
        count++;
        for (int i = 0; i < 8; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
