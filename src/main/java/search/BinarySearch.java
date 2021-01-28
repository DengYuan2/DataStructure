package search;

import java.util.ArrayList;
import java.util.List;

//二分(折半)查找：todo 前提:数组有序 此处使用的是递归，非递归方法以后讲，也要掌握啊
//非递归算法在algorithm包里
public class BinarySearch {
    public static int count = 0;

    public static void main(String[] args) {
        //测试1
        int[] arr = {1, 8, 10, 89, 200, 1234};
        int index = binarySearch(arr, 0, arr.length - 1, 0);
        if (index == -1)
            System.out.println("没有查找到~~");
        else
            System.out.println("找到，下标位" + index);

        System.out.println();
        //测试2
        int[] array = {1, 1, 1, 89, 200, 200, 200, 200, 200, 200, 200, 1234, 1234};
        ArrayList<Integer> list = binarySearch2(array, 0, array.length - 1, 200);
        if (list.size() == 0) System.out.println("没有查找到该值");
        System.out.println("list=" + list); //返回的索引集合可能是无序的
        //若array={1,2,3,4,5,........20},共20个数据，要找1,则一共要进行4次二分查找，这是不划算的，可以用插值查找

        //与插值查找进行对比
        System.out.println();
        int[] array2 = new int[100];
        for (int i = 0; i < 100; i++) {
            array2[i] = i + 1;
        }
        index = binarySearch(array2, 0, array2.length - 1, 75);
        System.out.println("一共查找了" + count + "次");
        if (index < 0) System.out.println("没有查找到该值");
        else System.out.println("该数据的索引是" + index);

    }

    //只找一个，递归方法
    public static int binarySearch(int[] array, int left, int right, int findVal) {
        count++;
        if (left > right)  //如果没找到的情况
            return -1;
        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (findVal > midValue)
            return binarySearch(array, mid + 1, right, findVal);
        else if (findVal < midValue)
            return binarySearch(array, left, mid - 1, findVal);
        else
            return mid;

    }

    /**
     * 当一个有序数组中有多个相同的值时,找出所有下标
     * 思路：
     * 1、找到mid索引时，不要马上返回
     * 2、向mid左边扫描，将满足元素的下标加入到集合Arraylist
     * 3、向mid右边扫描，将满足元素的下标加入到集合Arraylist
     * 4、将ArrayList返回
     */
    public static ArrayList<Integer> binarySearch2(int[] array, int left, int right, int findVal) {
        if (left > right)  //如果没找到的情况
            return new ArrayList<Integer>();
        int mid = (left + right) / 2;
        int midValue = array[mid];
        if (findVal > midValue)
            return binarySearch2(array, mid + 1, right, findVal);
        else if (findVal < midValue)
            return binarySearch2(array, left, mid - 1, findVal);
        else {
            ArrayList<Integer> list = new ArrayList<>();
            //扫描左边
            int temp = mid - 1;
            while (true) {
                if (temp >= 0 && array[temp] == findVal) { //应对temp=-1越界
                    list.add(temp--);
                } else break;
            }

            list.add(mid);

            //扫描右边
            temp = mid + 1;
            while (true) {
                if (temp < array.length && array[temp] == findVal) //应对temp=array.length越界
                    list.add(temp++);
                else break;
            }

            return list;
        }


    }

}
