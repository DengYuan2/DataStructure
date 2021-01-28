package sort;

import java.util.Arrays;

public class MergeSort {
    public static int count = 0;
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 3, 4};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后后arr=" + Arrays.toString(arr));
        System.out.println("一共合并了" + count + "次");
//        int[] array = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            array[i]=(int)(Math.random()*80000); //[0,1)*80000=[0,80000)
//        }
//        int[] temp=new int[array.length];
//        long start = System.currentTimeMillis();
//        mergeSort(array,0,array.length-1,temp);
//        long end =System.currentTimeMillis();
//        long time = end-start;
//        System.out.println("此次排序用时"+time+"ms");
//        System.out.println(Arrays.toString(array));
    }

    //使用递归 最好自己debug一遍，结合两张PNG好好理解
    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        int mid = 0;
        if (left < right) {
            mid = (left + right) / 2;
            //向左递归分解
            mergeSort(array, left, mid, temp);
            //向右递归分解
            mergeSort(array, mid + 1, right, temp);
            //合并
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * @param array 待排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转的数组
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        count++;
        int l = left; //左边有序序列的初始索引
        int r = mid + 1; //右边有序序列的初始索引
        int index = 0; //中转数组的下标
        //1、把左右两边(有序)的数据按照规则填充到temp数组，直到有一方处理完毕
        while (l <= mid && r <= right) {
            if (array[l] < array[r]) {
                temp[index] = array[l];
                index++;
                l++;
            } else {
                temp[index] = array[r];
                index++;
                r++;
            }
        }
        //2、将剩余有数据的一边
        while (l <= mid) {
            temp[index] = array[l];
            l++;
            index++;
        }
        while (r <= right) {
            temp[index] = array[r];
            r++;
            index++;
        }
        //3、将temp数组的元素拷贝到原数组
        //注意：并不是每次都拷贝所有
        index = 0;
        int tempLeft = left;
//        System.out.println("tempLeft="+tempLeft+", right="+right);
        while (tempLeft <= right) {
            array[tempLeft] = temp[index];
            index++;
            tempLeft++;
        }
    }


}
