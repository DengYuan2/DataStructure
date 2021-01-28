package review.sort;

import java.util.Arrays;

/**
 * @author DengYuan2
 * @create 2021-01-23 8:32
 */
public class Sort {
    public static void main(String[] args) {
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        // 10208
//        bubbleSort(array);
        //1911
//        selectSort(array);
//        607
//        insertSort(array);
        //16
//        shellSort(array);
        //15
        int[] tmp = new int[array.length];
        mergeSort(array, 0, array.length - 1, tmp);
        long end = System.currentTimeMillis();
        System.out.println("运行时间为：" + (end - start));
        System.out.println(Arrays.toString(array));

    }


    public static void bubbleSort(int[] arr) {
        boolean isSorted = false;
        for (int i = 0; i < arr.length - 1; i++) {
            isSorted = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    isSorted = true;
                }
            }
            if (!isSorted) {
                return;
            }
        }

    }

    /**
     * 2 3 4 5
     * 不是稳定排序：如5 8 5 2 9中的5
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            int idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    idx = j;
                }
            }
            if (idx != i) {
                arr[idx] = arr[i];
                arr[i] = min;
            }
        }
    }

    /**
     * 3 5 5 4 1
     * 2 3 5 5
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int idx = i - 1;
//            for (int j = i - 1; j >= 0; j--) {
//                if (arr[j] > val) {
//                    arr[j + 1] = arr[j];
//                    idx = j - 1;
//                } else {
//                    break;
//                }
//            }
            while (idx >= 0 && arr[idx] > val) {
                arr[idx + 1] = arr[idx];
                idx--;
            }
            if (idx + 1 != i) {
                arr[idx + 1] = val;
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                int idx = i - gap;
                int value = arr[i];
                // 提前优化，避免多进入while循环
                if (arr[idx] > value) {
                    while (idx > -1 && arr[idx] > value) {
                        arr[idx + gap] = arr[idx];
                        idx -= gap;
                    }
                    arr[idx + gap] = value;
                }
            }
        }
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr, int left, int right, int[] tmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, tmp);
            mergeSort(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int idx = 0;
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
            //我认为，这里有=,才能保证稳定性
            if (arr[l] <= arr[r]) {
                tmp[idx++] = arr[l++];
            } else {
                tmp[idx++] = arr[r++];
            }
        }
        while (l <= mid) {
            tmp[idx++] = arr[l++];
        }
        while (r <= right) {
            tmp[idx++] = arr[r++];
        }
        int tmpLeft = left;
        idx = 0;
        while (tmpLeft <= right) {
            arr[tmpLeft++] = tmp[idx++];
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        if (l < r) {
            int tmp = arr[l];
            while (l != r) {
                while (l < r && arr[r] >= tmp) {
                    r--;
                }
                if (l < r) {
                    arr[l] = arr[r];
                    l++;
                }
                while (l < r && arr[l] <= tmp) {
                    l++;
                }
                if (l < r) {
                    arr[r] = arr[l];
                    r--;
                }
            }
            arr[l] = tmp;
            quickSort(arr, left, l - 1);
            quickSort(arr, l + 1, right);
        }
    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2-1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            int tmp = arr[0];
            arr[0]=arr[j];
            arr[j]=tmp;
            adjustHeap(arr,0,j);

        }
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        int tmp = arr[i];
        for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > tmp) {
                arr[i] = tmp;
                i = k;
            } else {
                break;
            }
        }
        arr[i] = tmp;
    }
}
