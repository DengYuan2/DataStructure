package sort;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {2, 5, 5, 3, 4};
////        int[] arr = {1,2,5,3,6,7,4,8,9}; //,6,7,4,5,9
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
//        long start = System.currentTimeMillis();
//        quickSort(arr,0,arr.length-1);
//        long end = System.currentTimeMillis();
//        long time = end - start;
//        System.out.println("此次排序花费的时间为" + time + "ms");
//        System.out.println(Arrays.toString(arr));
        int[] array ={2, 5, 5, 3, 4};
        quick(array,0,array.length-1);
        System.out.println(Arrays.toString(array));
    }

    //快速排序
    //使用递归,空间换时间
    //老师的太麻烦且看不太懂，用的是下面的quick
    public static void quickSort(int[] array, int left, int right) {
        //1 2 3    5     6 7
        int l = left;
        int r = right;
        int pivot = array[(left + right) / 2];
        int temp = 0;
        while (l < r) {
            //直到找到比pivot大的值为止
            while (array[l] < pivot)
                l++;
            //直到找到比pivot小的值为止
            while (array[r] > pivot)
                r--;   //l=2 r=3
            //左右已经归位
            if (l >= r) break;

            //交换位置
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            //如果l和r中有一个是在中心处停止的，下面两个if语句暂时不懂什么意思，但不能没有[尤其是当两相邻的数都为pivot时] 例：对3 5 5 6,若没有下面，则一直出不去
            //当右边【因为已经交换过了，原先是array[r]==pivot】已经归位了,例 2 6  5 7 8  ，右索引向前移，可以避免重复比较当前数和多进入while循环
            if (array[l] == pivot) //
                r--;
            //当左边已经归位好了
            if (array[r] == pivot)
                l++;
        }
//        System.out.println("" + Arrays.toString(array));
        if (l == r) { //todo 否则出现栈溢出!!
            l++;
            r--;
        }
        //向左递归
        if (left < r) //左边剩下的数多于一个
            quickSort(array, left, r);
        //向右递归
        if (right > l)  //右边剩下的数多于一个
            quickSort(array, l, right);

    }

    //思想结合 https://zhuanlan.zhihu.com/p/93129029，网页中代码有一点问题，看其思想即可
    public static void quick(int[] arr,int left,int right){
        int l=left;
        int r=right;
        int temp=0;
        if (l<r){
            temp=arr[l];
            while (l!=r){
                while (l<r&&arr[r]>temp){
                    r--;
                }
                if (l<r){
                    arr[l]=arr[r];
                    l++;
                }
                while (l<r&&arr[l]<temp){
                    l++;
                }
                if (l<r){
                    arr[r]=arr[l];
                    r--;
                }
            }
            //此时l==r
            arr[l]=temp;

            quick(arr,left,l-1);
            quick(arr,l+1,right);
        }
    }
}
