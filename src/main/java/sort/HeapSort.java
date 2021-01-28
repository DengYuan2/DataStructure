package sort;

import java.util.Arrays;

//思路图解见evernote2020/3/2
public class HeapSort {
    public static void main(String[] args) {
        //要求升序排列，故用大顶堆
//        int[] arr = {4, 6, 8, 5, 9};
        int[] arr = {4, 6, 8, 5, 9,1,90,89,56,-999};
        System.out.println("升序排列：");
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("降序排列：");
        heap(arr);
        System.out.println(Arrays.toString(arr));
//        int[] array = new int[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            array[i]=(int)(Math.random()*8000000); //[0,1)*8000000=[0,8000000)
//        }
//        long start = System.currentTimeMillis();
//        heapSort(array);
//        long end =System.currentTimeMillis();
//        long time = end-start;
//        System.out.println("此次排序用时"+time+"ms");
//        System.out.println(Arrays.toString(array));
    }

    //todo 时间复杂度：O(nlogn)
    public static void heapSort(int[] arr) {
        System.out.println("堆排序~~~");
        int temp=0;
        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr)); //4 9 8 5 6
//        adjustHeap(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr)); //9 6 8 5 4
        //完成最终代码

        //1、将无序序列构建成一个堆，并根据升序降序要求选择大顶堆或小顶堆
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }

        //2、将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
        //3、重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，发福执行调整+交换步骤，直到整个序列有序
        for (int j = arr.length-1; j >0; j--) { //n个数，则一共交换n-1次
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j); //从下标0开始是因为：只有堆顶元素是无序的，其他都还好好的，大小都排好了

        }

    }


    /**
     * 将一个数组（二叉树）调整成一个大顶堆
     *
     * @param array
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐减小的
     * 功能：将以i为顶点的子树调整成大顶堆：例：array = {4, 6, 8, 5, 9},i=1,==>array={4,9,8,5,6}，若再次调用adjustHeap,应传入i=0,使变成整体的大顶堆
     */
    public static void adjustHeap(int[] array, int i, int length) {
        //取出当前元素的值，保存在临时变量中
        int temp = array[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) { //首先是当前节点A的左子节点B，然后是B节点是的左节点C，然后是C节点的左节点D。。。。。
            if (k + 1 < length && array[k] < array[k + 1]) { //前一个判断是保证有右节点
                k++; //若右节点大，让k=右节点的下标
            }
            if (array[k] > temp) { //若左右子节点中有一个大，就把大的放到当前节点上
                array[i] = array[k];
                i = k; //!!!i指向k，继续循环比较
            } else
                break;
        }
        //结束循环时，已经将以i为父节点的树的最大值放在了最顶(局部)
        array[i] = temp; //将temp值放到合适的位置
    }


    //小顶堆（模仿大顶堆，只是改了两处的符号）
    public static void adjust(int[] arr,int i,int length){
        int temp=arr[i];
        for (int j = 2*i+1; j < length; j=j*2+1) {
            if (j+1<length&&arr[j]>arr[j+1]){
                j++;
            }
            if (arr[j]<temp){
                arr[i]=arr[j];
                i=j;
            }else{
                break;
            }
        }
        arr[i]=temp;

    }

    //从大到小排序，不变，变得是变成小顶堆的方式，我只是自己重写一遍而已
    public static void heap(int[] arr){
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjust(arr,i,arr.length);
        }
        int temp=0;
        for (int i = 0; i < arr.length-1; i++) {
            temp=arr[0];
            arr[0]=arr[arr.length-1-i];
            arr[arr.length-1-i]=temp;
            adjust(arr,0,arr.length-1-i);
        }

    }
}
