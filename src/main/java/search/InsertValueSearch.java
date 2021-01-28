package search;

//插值查找算法 todo 要求数组有序
public class InsertValueSearch {
    public static int count=0;
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i]=i+1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 35);
        System.out.println("一共查找了"+count+"次");
        if (index<0) System.out.println("没有找到你感兴趣的数据~~~");
        else System.out.println("该数据的索引是"+index);
    }

    //和二分查找的区别只在mid
    //查找1个,返回对应下标
    //todo 对于数据量较大，数据分布比较均匀的数组来说，采用插值查找，速度更快；而数据分布不均匀的情况下，该方法不一定比二分查找要好

    public static int insertValueSearch(int[] array,int left,int right,int findVal){
        count++;
        //todo findVal<array[0]||findVal>array[array.length-1]必须要有，否则mid可能越界
        // 附加功能:提前判断，findVal小于最小值和大于最大值都直接停止
        if (left>right||findVal<array[0]||findVal>array[array.length-1])
            return -1;
        int mid=left+(right-left)*(findVal-array[left])/(array[right]-array[left]);
        int midValue=array[mid];
        if (midValue<findVal)
            return insertValueSearch(array,mid+1,right,findVal);
        else if (midValue>findVal)
            return insertValueSearch(array,left,mid-1,findVal);
        else
            return mid;
    }

    //后来自己写的非递归方式
    public static int insertSearch(int[] arr,int findValue){
        int left =0;
        int right=arr.length-1;
        int mid =0;
        if (arr[0]>findValue||findValue>arr[arr.length-1]){
            return -1;
        }
        while (left<=right){
            mid=left+(right-left)*(findValue-arr[left])/(arr[right]-arr[left]);
            count++;
            if (findValue<arr[mid]){
                right=mid-1;
            }else if (findValue>arr[mid]){
                left=mid+1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
