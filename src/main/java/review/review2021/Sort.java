package review.review2021;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author DengYuan2
 * @create 2020-12-27 10:38
 */
public class Sort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, 20};
//        int[] arr = {17, 3, 25, 14, 20, 9};
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] arr = {53, 3, 542, 748, 14, 214};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
//        int[] ints = radixSort(arr);
//        System.out.println(Arrays.toString(ints));
//        int[] testArr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            testArr[i] = (int) (Math.random() * 80000);
//        }
//////        bubbleSort(arr);
//////        int[] selectSort = selectSort(testArr);
//////        System.out.println(Arrays.toString(selectSort));
//////        int[] insertSort = insertSort(arr);
//////        System.out.println(Arrays.toString(insertSort));
//        long start = System.currentTimeMillis();
//        int[] result = shellSort(testArr);
//        long end = System.currentTimeMillis();
//        System.out.println("运行时长为：" + (end - start));
//        System.out.println(Arrays.toString(result));

        int[] heapSort = heapSort(arr);
        System.out.println(Arrays.toString(heapSort));

    }

    public static int[] bubbleSort(int[] arr) {
        int length = arr.length;
        int temp = 0;
        boolean flag;
        for (int i = 0; i < length - 1; i++) {
            flag = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return arr;
    }

    public static int[] selectSort(int[] arr) {
        int len = arr.length;
        int min;
        int minIdx;
        for (int i = 0; i < len - 1; i++) {
            min = arr[i];
            minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                arr[minIdx] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }

    public static int[] insertSort(int[] arr) {
        int len = arr.length;
        int insertValue;
        int insertIdx;
        for (int i = 1; i < len; i++) {
            insertValue = arr[i];
            insertIdx = i - 1;
            while (insertIdx >= 0 && insertValue < arr[insertIdx]) {
                arr[insertIdx + 1] = arr[insertIdx];
                insertIdx--;
            }
            if (insertIdx + 1 != i) {
                arr[insertIdx + 1] = insertValue;
            }
//            for (int j = 0; j < i; j++) {
//                if (insertValue < arr[j]) {
//                    insertIdx = j;
//                    break;
//                }
//            }
//            if (insertIdx != i) {
//                for (int j = i - 1; j >= insertIdx; j--) {
//                    arr[j + 1] = arr[j];
//                }
//                arr[insertIdx] = insertValue;
//            }
        }
        return arr;
    }

    public static int[] shellSort(int[] arr) {
        int len = arr.length;
        for (int gap = len / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < len; i++) {
                int insertValue = arr[i];
//                int insertIdx = i;
//                for (int j = i - gap; j >= 0; j = j - gap) {
//                    if (insertValue < arr[j]) {
//                        insertIdx = j;
//                        arr[j + gap] = arr[j];
//                    } else {
//                        break;
//                    }
//                }
                int insertIdx = i - gap;
                while (insertIdx >= 0 && insertValue < arr[insertIdx]) {
                    arr[insertIdx + gap] = arr[insertIdx];
                    insertIdx -= gap;
                }
                if (insertIdx + gap != i) {
                    arr[insertIdx + gap] = insertValue;
                }
            }
        }
        return arr;
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int tmp = arr[l];
        if (l < r) {
            while (l != r) {
                while (l < r && arr[r] > tmp) {
                    r--;
                }
                if (l < r) {
                    arr[l] = arr[r];
                    l++;
                }
                while (l < r && arr[l] < tmp) {
                    l++;
                }
                if (l < r) {
                    arr[r] = arr[l];
                    r--;
                }
            }
            arr[l] = tmp;
            quickSort(arr, left, l - 1);
            quickSort(arr, r + 1, right);
        }


    }

    public static int[] radixSort(int[] arr) {
        //桶
        int[][] place = new int[10][arr.length];
        //每个桶中存放的个数
        int[] placeIdx = new int[10];
        //找出最长的
        int maxValue = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }
        int maxLength = (maxValue + "").length();
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j] / (int) (Math.pow(10, i)) % 10;
                place[index][placeIdx[index]] = arr[j];
                placeIdx[index] += 1;
            }
            int index = 0;
            for (int j = 0; j < 10; j++) {
                if (placeIdx[j] != 0) {
                    for (int k = 0; k < placeIdx[j]; k++) {
                        arr[index++] = place[j][k];
                    }
                    placeIdx[j] = 0;
                }
            }
            System.out.println("这次排序的顺序为：" + Arrays.toString(arr));
        }
        return arr;
    }


    public static int[] heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            smallHeap(arr, i, arr.length);
        }
        int tmp = 0;
        for (int i = arr.length-1; i >0 ; i--) {
            tmp = arr[i];
            arr[i]=arr[0];
            arr[0]=tmp;
            smallHeap(arr,0,i);
        }
        return arr;
    }

    public static int[] smallHeap(int[] arr, int i, int length) {
        int tmp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k + 1] < arr[k]) {
                k++;
            }
            if (tmp > arr[k]) {
                arr[i]=arr[k];
                i=k;
            }

        }
        arr[i]=tmp;

        return arr;
    }
}
