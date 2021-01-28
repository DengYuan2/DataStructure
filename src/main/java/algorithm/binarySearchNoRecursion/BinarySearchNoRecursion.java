package algorithm.binarySearchNoRecursion;

//二分查找的非递归实现
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] arr= {1,3,8,10,11,67,100};
        int res = binarySearch(arr, -8);
        System.out.println(res);
    }

    //非递归，二分查找，序列为升序
    public static int binarySearch(int[] array,int target){
        int left=0;
        int right=array.length-1;
        int mid =0;
        while (left<=right){
            mid =(left+right)/2;
            if (array[mid]==target)
                return mid;
            else if (array[mid]>target)
                right=mid-1;
            else
                left=mid+1;
        }
        return -1;
    }
}
