package sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3,9,-1,10,-4};
////        int arr[] = {-1,4,10,20};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));

        //测试给80000个随机数排序的时间
        int[] array = new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i]=(int)(Math.random()*80000); //[0,1)*80000=[0,80000)
        }
        long start = System.currentTimeMillis();
        bubbleSort(array);
        long end =System.currentTimeMillis();
        long time = end-start;
        System.out.println("此次排序用时"+time+"ms"); //时间不固定

    }

    //todo 时间复杂度：O(n^2)
    public static void bubbleSort(int[] arr) {
        boolean flag = true; //用于优化
        int len = arr.length;
        int temp = 0; //临时变量
        for (int i = 0; i < len - 1; i++) { //外循环n-1次
            flag = true; //重置flag
            for (int j = 0; j < len - i - 1; j++) { //内循环比较n-1-i次
                if (arr[j] >= arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
//            System.out.println("第"+(i+1)+"次的情况为"+Arrays.toString(arr));
            if (flag) break; //某趟循环中未发生任何移动，则可以提前结束
        }
    }

    public static void sort(int[] arr){
        int temp = 0;
        boolean flag=false;
        for (int i = 0; i < arr.length-1; i++) {
           flag=false;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag=true;
                }
            }
            if (!flag){
                break;
            }
        }
    }
}
