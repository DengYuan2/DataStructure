package algorithm.dynamicProgramming;

import java.util.Arrays;

//动态规划经典问题-背包问题
public class KnapsackProblem {


    public static void main(String[] args) {
        int[] weight = {1, 4, 3}; //某物品的重量
        int[] value = {1500, 3000, 2000}; //某物品的价值
        int volume = 4; //背包容量
        knapsackProblem(weight, value, volume);
//        problem(weight,value,volume);
    }

    public static void knapsackProblem(int[] weight, int[] value, int volume) {
        int[][] totalMaxValue = new int[weight.length + 1][volume + 1];
        //记录存放的物品，和totalMaxValue的大小一致；以下关于path1数组的都是自己写的方式，老师的方法用path2数组记录
//        String[][] path1 = new String[totalMaxValue.length][totalMaxValue[0].length];
//        for (int i = 0; i < path1[0].length; i++) {
//            path1[0][i]=" "; //否則为null,打印出来不好看
//        }
//        for(int i =0;i<path1.length;i++) {
//            path1[i][0]=" ";
//        }
        int[][] path2 = new int[weight.length + 1][volume + 1]; //记录存放的物品，和totalMaxValue的大小一致
        for (int i = 1; i <= weight.length; i++) {
            //见evernote相关表格，第一行和第一列全为0，不用特别设置，因为默认
            for (int j = 1; j <= volume; j++) {  //第i个物品的重量的下标是i-1，下面关于weight数组和value数组的都是如此
                if (weight[i - 1] > j) {
                    //沿用上一次的策略
                    totalMaxValue[i][j] = totalMaxValue[i - 1][j];
//                    path1[i][j]=path1[i-1][j];
                } else {
                    //以往的价值和加入物品i后的价值相比较，选大的那个；放入物品i后的价值=物品i的价值加上剩余空间可以存放的最大价值
                    int max1 = totalMaxValue[i - 1][j];
                    int max2 = value[i-1]+totalMaxValue[i - 1][j - weight[i - 1]];
//                    path1[i][j]=max1>max2?path1[i-1][j]:path1[i-1][j-weight[i-1]]+" "+(i-1);

                    if (max1 < max2) { //新加入物件才做记录
                        path2[i][j] = 1;
                    }

                    int max = max1 > max2 ? max1 : max2; //也可用Math.max(a,b)

                    totalMaxValue[i][j] = max;
                }
            }
        }
        System.out.println(totalMaxValue[weight.length][volume]);
        System.out.println("打印表格：");
        for (int[] i : totalMaxValue) {
            System.out.println(Arrays.toString(i));
        }

//        System.out.println("打印路径：");
//        for (String[] str:path1 ) {
//            System.out.println(Arrays.toString(str));
//        }

        System.out.println();
        //用老师的方法记录路径，打印时要特别注意！！！
        int i = totalMaxValue.length - 1;
        int j = totalMaxValue[0].length - 1;
        //打印背包内的东西，注意从后往前，注意查找时i、j的变化
        while (i > 0 && j > 0) {
            if (path2[i][j] == 1){
                System.out.println("第" + i + "件物品放入背包中");
                j = j - weight[i - 1]; // ！！！
            }
            i--;
        }

        //看一下path2[]数组:
        System.out.println();
        System.out.println("看一下path2[]数组:");
        for (int[] path:path2) {
            System.out.println(Arrays.toString(path));
        }
    }

    public static void problem(int[] weight,int[] value,int volume){
        int[][] maxValue = new int[weight.length + 1][volume + 1];
        for (int i = 1; i <= weight.length; i++) {
            for (int j = 1; j <=volume; j++) {
                if (weight[i-1]>j){
                    maxValue[i][j]=maxValue[i-1][j];
                }else {
                    maxValue[i][j]=Math.max(maxValue[i-1][j],maxValue[i-1][j-weight[i-1]]+value[i-1]);
                }
            }
        }
        for (int[] val:maxValue){
            System.out.println(Arrays.toString(val));
        }
    }
}
