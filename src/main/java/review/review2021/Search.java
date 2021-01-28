package review.review2021;

import java.util.ArrayList;

/**
 * @author DengYuan2
 * @create 2020-12-30 20:07
 */
public class Search {
    public static void main(String[] args) {
        int[] arr = {1, 8, 89, 200, 1234};
//        int index = binarySearch(arr, 0, arr.length - 1, 8);
//        System.out.println(index);
//        int result = insertSearch(arr, 0, arr.length - 1, 200);
//        System.out.println(result);
        int insert = insert(arr, 200);
        System.out.println(insert);
    }


    public static int binarySearch(int[] arr, int left, int right, int finalValue) {
        int l = left;
        int r = right;
        if (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == finalValue) {
                return mid;
            }
            if (arr[mid] > finalValue) {
                return binarySearch(arr, l, mid - 1, finalValue);
            }
            return binarySearch(arr, mid + 1, r, finalValue);
        }
        return -1;
    }

    public static int bSearch(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == findValue) {
                return mid;
            }
            if (arr[mid]<findValue){
                left=mid+1;
            }else {
                right=mid-1;
            }
        }
        return -1;
    }

    public static int insertSearch(int[] arr, int left, int right, int finalValue) {
        int l = left;
        int r = right;
        if (l > r || arr[0] > finalValue || arr[arr.length - 1] < finalValue) return -1;
        int mid = l + (r - l) * (finalValue - arr[l]) / (arr[r] - arr[l]);
        int midValue = arr[mid];
        if (midValue == finalValue) {
            return mid;
        }
        if (midValue > finalValue) {
            return insertSearch(arr, l, mid - 1, finalValue);
        }
        return insertSearch(arr, mid + 1, r, finalValue);
    }

    public static int insert(int[] arr, int findValue) {
        int left = 0;
        int right = arr.length - 1;
        if (findValue > arr[right] || findValue < arr[left]) {
            return -1;
        }
        while (left <= right) {
            int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
            int midValue = arr[mid];
            if (midValue == findValue) {
                return mid;
            }
            if (midValue < findValue) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
