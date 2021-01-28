package algorithm.divideAndConquer;

//分治算法经典-汉诺塔问题
//还未彻底理解，后面还要好好看啊~~~
public class HannoiTower {
    public static void main(String[] args) {
        //测试
        hanoiTower(4,'A','B','C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1)
            System.out.println("第1个盘从" + a + "-->" + c);
        else {
            //如果n>=2,总是可以看作两个盘 1：最下边的一个盘 2：上面的（所有）盘
            //1. 把上面的所有盘a->b,移动过程会使用到c
            hanoiTower(num - 1, a,c , b);
            //2. 把下面的的盘a->c
            System.out.println("第"+num+"个盘从"+a+"-->"+c);
            //3. 把b的所有盘(看作一个盘)移到c,即b->c;移动过程使用到a
            hanoiTower(num - 1, b, a, c);
        }
    }

}
