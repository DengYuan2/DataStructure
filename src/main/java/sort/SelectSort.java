package sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr= {101,34,119,1,-1,90,123};
//        selectSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr=new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*80000);
        }
        long start =System.currentTimeMillis();
        selectSort(arr);
        long end =System.currentTimeMillis();
        long time = end-start;
        System.out.println("此次排序花费的时间为"+time+"ms");

    }

    //todo 时间复杂度：O(n^2)  比冒泡的时间要短，因为for循环内赋值次数少；交换次数也少，而且还有个判断
    public static void selectSort(int[] array){
        int len =array.length;
        int temp=0; //最小数
        int index=0; //最小数的下标
        for (int i = 0; i < len-1; i++) {
            temp=array[i];
            index=i;
            for (int j = i+1; j < len; j++) {
                if (array[j]<temp){
                    index=j;
                    temp=array[j];
                }
            }
            if (index!=i){ //index==i时位置不用变动时
                array[index]=array[i];
                array[i]=temp;
            }
//            System.out.println("第"+(i+1)+"次排序的结果为："+Arrays.toString(array));
        }
    }
}
