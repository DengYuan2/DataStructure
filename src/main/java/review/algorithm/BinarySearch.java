package review.algorithm;

public class BinarySearch {
    public static void main(String[] args) {

    }

    public static int BinarySearch2(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }

        int mid = 0;
        mid = (left + right) / 2;
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] > value) {
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return binarySearch(arr, mid + 1, right, value);
        }

    }
}
