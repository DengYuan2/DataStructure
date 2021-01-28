package sort;

import java.util.Arrays;
import java.util.LinkedList;

public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = { 3,-2,34, 65,8,32,101,-1, 77,78,45,0};
//        insertSortTV(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        insertSortTV(arr);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("此次排序花费的时间为" + time + "ms");

    }

    //老师的版本：比我的写法2还要快
    //思路：将要插入的值与前面的一个个比较，如果前面的数小，则将其后移，
    // 直到找到正确的位置，再将待插入的值放进去
    //todo 时间复杂度：O(n^2)
    public static void insertSortTV(int[] array) {
        int insertValue = 0; //要插入的值
        int insertIndex = 0; //要插入的位置
        for (int i = 1; i < array.length; i++) {
            insertValue = array[i];
            insertIndex = i - 1;  //待插入位置首先在该数的前一个位置
            //保证不越界+待插入的数还未找到正确的位置
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                //较大的那个数后移
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            //退出while循环时，说明插入的位置找到，是在insertIndex+1处
            //此处加个判断语句优化一下，待插入位置正好是现在的位置时不移动,否则移动
            if (insertIndex + 1 != i)
                array[insertIndex + 1] = insertValue;
//            System.out.println("第"+i+"轮的排序结果为"+Arrays.toString(array));
        }
    }

    //我的写法：与老师的有点差异，而老师的更快，因为我移动的次数过多，没有必要
    //我的思路：同样是与前面的比较，但每次都会处理待插入的值，
    // 即当前面的数小时，就将该数与待插入数互换【而这没有必要，完全可以在找到正确位置后再互换】
    public static void insertSort(int[] array) {
        int len = array.length;
        int insertValue = 0; //要插入的值
        for (int i = 1; i < len; i++) {
            insertValue = array[i];
            for (int j = i - 1; j >= 0; j--) { //与该数前面的数比较，小则前后互换，再与前面的比较...
                if (insertValue < array[j]) {
                    array[j + 1] = array[j];
                    array[j] = insertValue;
                } else break;
            }
            System.out.println("第" + i + "轮的排序结果为" + Arrays.toString(array));
        }
    }

    //自己的写法二：与老师的想法一致，不过我是用的for循环，老师直接while一次性搞定
    public static void insert(int[] arr) {
        int temp = 0;
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            index = i;
            for (int j = i - 1; j >= 0; j--) {
                if (temp < arr[j]) {
                    arr[j + 1] = arr[j];
                    index = j;
                } else {
                    break;
                }

            }
            if (index != i)
                arr[index] = temp;
        }
    }

}
