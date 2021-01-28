package sort;


public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {80000, 9, 1, 7, 2, 3, 5, 4, 6, 0}; //, 5, 4, 6,0
//        shellSort2(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        shellSort2(arr);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("此次排序花费的时间为" + time + "ms");
    }

    //希尔排序-交换法
    //最好自己debug走一遍
    //交换过多，速度过慢，优化-->移动(位)法
    //todo  时间复杂度：很复杂
    public static void shellSort(int[] array) {
        int temp = 0;
//        int count=1; //记录排序的次数
        for (int gap = array.length / 2; gap > 0; gap /= 2) { //分成gap组
            //todo 明明在一个数组中，却分成了若干组（每组内的下标不连续却有规律），并每组在组内进行比较（或其他行为），该写法值得记忆和借鉴！！！
            for (int i = gap; i < array.length; i++) { //从第一组开始，每组都是向该组的前一个数比较；
                for (int j = i - gap; j >= 0; j -= gap) { //若该数（arr[j+gap]）与该组的前一个数相比较小，就在该组内往前移，并不断与该组前面的数比较,直到找到合适位置
                    if (array[j] > array[j + gap]) { //较小就互换位置，类似于InsertSort.java中我的写法
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }

            }
//            System.out.println("第"+(count++)+"轮得到的排序结果为" + Arrays.toString(array));
        }


    }

    //希尔排序-移动(移位)法：不要比较后就立即互换，找到正确的位置后再放入（思想同插入法的TV版）
    //比交换法要快
    public static void shellSort2(int[] array) {
        int index = 0;
        int insertValue = 0;
        int count = 1;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                index = i;
                insertValue = array[i];
                //将61行的if优化提前，而且更加优化，因为避免多进入while循环
                if (insertValue < array[index - gap]) { //此处index-gap必定大于0
                    while (index >= gap && insertValue < array[index - gap]) {
                        array[index] = array[index - gap]; //较大的值往后移
                        index -= gap;
                    }
//                if (index != i)
                    array[index] = insertValue;
                }
            }
//            System.out.println("第" + (count++) + "轮结果为" + Arrays.toString(array));
        }
    }


    public static void shell(int[] arr) {
        int gap = arr.length / 2;
        int temp = 0;
        int index = 0;
        for (int k = gap; k >= 1; k = k / 2) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                index = i;
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (temp < arr[j]) {
                        arr[j + gap] = arr[j];
                        index = j;
                    } else {
                        break;
                    }
                }
                if (index != i)
                    arr[index] = temp;
            }
        }

    }
}
