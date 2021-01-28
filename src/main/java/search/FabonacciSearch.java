package search;

import java.util.ArrayList;
import java.util.Arrays;

//斐波那契查找算法 todo 要求数组有序
public class FabonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
//        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int[] arr = {1, 8, 10, 89, 100, 123, 123, 678, 679};
        System.out.println(Arrays.toString(fib()));
//        int index = fibonacciSearch(arr, 1);
        int index = fibo(arr,123);
        if (index < 0)
            System.out.println("没有找到该值哦~~");
        else
            System.out.println("该值的下标是" + index);

    }

    //构建斐波那契数列，因为mid=low+F(k-1)-1,要用到
    //此处用非递归方式        1 1 2 3 5 8 13 21 34 55
    public static int[] fib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 斐波那契查找算法：
     * 找到一个，且不一定是前面那个，如arr = {1, 8, 10, 89, 100, 123,123,678}找123
     *
     * @param array 数组
     * @param key   要查找的值
     * @return 对应下标，没有则返回-1
     * 其实与二分法思想类似，只是比例不同；而且为了能够使用该比例，需要将原数组构造成符合斐波那契数列长度的数组
     */
    //非递归方式
    public static int fibonacciSearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        int k = 0; //表示斐波那契分割数值的下标，因为mid=low+F(k-1)-1,要找到这个k
        int mid = 0;
        int fib[] = fib(); //拿到斐波那契数列

        //初始时，数组长度要小于等于(小于时后面再填充)fib[k],不过下标都是从0开始的，故最大下标是fib[k]-1 {结合图FabonacciSearch.PNG中那个数轴}
        //以{1，3，5，6，7，9，12，14}来检验，k=5，mid=4,即前半部分是[0,4](),即5个，后面是[5-7]即3个，即3(后半部分):5(前半部分)≈0.618,就是斐波那契数列的连续两个
        //获取斐波那契分割数值的下标
        while (high > fib[k] - 1)
            k++;
        //因为fib[k]的值可能大于array的长度，因此需要Arrays类，构造新的数组
        int[] temp = Arrays.copyOf(array, fib[k]);
        //实际上需要使用array数组最后的数来填充temp(原array后的位置)
        //举例：temp={1, 8, 10, 89, 1000, 1234，0，0} ==> temp={1, 8, 10, 89, 1000, 1234，1234，1234}
        for (int i = array.length; i < fib[k]; i++) {
            temp[i] = array[high];
        }
        //使用while循环处理，找到我们的数k
//        high=temp.length-1;
        while (low <= high) {  //只要满足该条件，就可以找
            mid = low + fib[k - 1] - 1; //最后的-1主要是因为下标是从0开始的
            if (key < temp[mid]) { //向左找
                high = mid - 1;
                //为什么是k--:
                //1、全部元素=前面的元素+后面的元素
                //2、fib[k]=fib[k-1]+fib[k-2]
                //因为前面有f[k-1]个元素，所以可以继续成fib[k-1]=fib[k-2]+fib[k-3]
                //即在fib[k-1]的前面继续查找，即下次循环的时候，mid=low+fib[k-1-1]-1
                //结合图FabonacciSearch.PNG中的数轴，前半部分的长度是fib[k-1],这部分再划分的话，前半部分的长度是fib[k-1-1]
                k--;
            } else if (key > temp[mid]) { //向右找
                low = mid + 1;
                //为什么是k-2
                //1、全部元素=前面的元素+后面的元素
                //2、fib[k]=fib[k-1]+fib[k-2]
                //因为后面有f[k-2]个元素，所以可以继续成fib[k-2]=fib[k-3]+fib[k-4]
                //下次循环的时候，mid=low+fib[k-1-2]-1
                //结合图FabonacciSearch.PNG中的数轴，后半部分的长度是fib[k-2],这部分再划分的话，其前半部分的长度是fib[k-2-1] {可以比对着，长度为fib[k]是，前半部分fib[k-1];则长度为fib[k-2]是，前半部分为fib[k-2]-1}
                k -= 2;
            } else { //找到了
                //需要确定返回的是哪个下标
                if (mid <= high)
                    return mid;
                else
                    return high;

            }

        }
        return -1;
    }

    public static int fibo(int[] arr, int findValue) {
        int low = 0;
        int high = arr.length - 1;
        if (arr[0] > findValue || arr[high] < findValue) {
            return -1;
        }
        int index = 0;
        int arrLen = arr.length;
        int len = arrLen;
        for (int i = 0; i < maxSize; i++) {
            if (fib()[i] - 1 >= arrLen) {
                len = fib()[i];
                index = i;
                break;
            }
        }
        if (len > arrLen) {
            int[] array = new int[len];
            System.arraycopy(arr, 0, array, 0, arrLen);
            for (int i = arrLen; i < len; i++) {
                array[i] = arr[arr.length - 1];
            }
            arr = array;
        }
        while (low <= high) {
            int mid = low + fib()[index - 1] - 1;
            if (arr[mid] == findValue) {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
            if (arr[mid] < findValue) {
                low = mid + 1;
                index -= 2;
            } else {
                high = mid - 1;
                index--;
            }

        }
        return -1;
    }
}
