package sort;

import java.util.Arrays;

//基数排序（桶排序[桶就是数组]） todo 稳定的排序方法
public class RadixSort {
    public static void main(String[] args) {
        int[] arr={53,3,542,748,14,214};
        radixSort(arr);
//        int[] arr = new int[80000]; //若80000000: OutOfMemoryError: Java heap space
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 80000);
//        }
//        long start = System.currentTimeMillis();
//        radixSort(arr);
//        long end = System.currentTimeMillis();
//        long time = end - start;
//        System.out.println("此次排序花费的时间为" + time + "ms");
//        System.out.println(Arrays.toString(arr));

//        //测试第一次
//        int[] arr = {53, 3, 542, 748, 14, 214};
//        radix(arr);
    }

    public static void radixSort(int[] array) {
        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明：
        // 1、二维数组包含10个一维数组
        // 2、为了防止放入数据的时候，数据溢出，每个桶的大小必须为array.length[考虑到极端情况]
        // 3、典型的空间换时间
        int[][] bucket = new int[10][array.length];
        //每个桶都应有一个下标，来决定是否从桶里取数和取几个[因为每一次全部数据放入桶时不能确定完全覆盖上一次的结果（这次放入之前，上一次的结果还在桶中呢）]，用一维数组来记录
        //如bucketElementCounts[0]就是记录第1个桶内有多少个有效数据
        int[] bucketElementCounts = new int[10];
        int digitOfElement = 0;
        //得到数组中最大的数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        //得到组大数的位数
        int maxLength = (max + "").length(); //哈哈，很巧妙了
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < array.length; j++) {
                //取出每个元素的个位数(第一次)，十位数(第二次)，。。。
                digitOfElement = array[j] / n % 10;
                //如果不用n，用Math.pow(double a,double b)可以表示a的b次方，但用n更巧妙啊
                //digitOfElement=array[j]/(int)(Math.pow(10,i))%10; //第1轮为array[i]%10,第2轮为array[i]/10%10,第3轮为array[i]/100%10
                //放在适当桶的适当位置（适当桶=digitOfElement,适当位置=某个桶的某个下标，某个下标=bucketElementCounts[digitElement]）
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++; //将某个桶的下标加1,代表多了一个有效数据，且便于该桶中再次填入数据
            } //完成此循环后将数据全部放入了桶中
            //按桶的顺序取出数据，放入原来数组
            int index = 0;
            for (int k = 0; k < 10; k++) {
                //如果桶中有数据，才取出
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        array[index++] = bucket[k][l];
                    }
                }
                //将bucketElementCounts数组全部置0，为下一轮做准备
                //将该桶的有效数据重置为0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+(i+1)+"轮结果为："+Arrays.toString(array));
        }

    }


    public static void radix(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketIndex = new int[10];
        int max = 0;
        int size = 0;
        int index = 0; //个位数末尾的数字，也是放到哪个桶的数字

        //太麻烦了，应该用老师的方式，先找出最大值，然后再看其位数
        for (int i = 0; i < arr.length; i++) {
            size = (arr[i] + "").length();
            if (size > max) {
                max = size;
            }
        }

        for (int i = 0, n = 1; i < max; i++, n = n * 10) { //根据位数决定一共循环多少次
            for (int j = 0; j < arr.length; j++) {
                index = arr[j]/n % 10;
                bucket[index][bucketIndex[index]] = arr[j];
                bucketIndex[index]++;
            }
            int m = 0;
            for (int j = 0; j < bucket.length; j++) {
                if (bucketIndex[j] > 0) {
                    for (int k = 0; k < bucketIndex[j]; k++) {
                        arr[m++] = bucket[j][k];
                    }
                    bucketIndex[j] = 0;
                }
            }

            System.out.println("第"+(i+1)+"次：" + Arrays.toString(arr));
        }

    }
}
