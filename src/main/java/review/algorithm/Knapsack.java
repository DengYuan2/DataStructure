package review.algorithm;

import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {
        int[] weight ={1,4,3};
        int[] value={1500,3000,2000};
        int volume=4; //背包容量
        int mount=weight.length; //物品数量
        int[][] pack= new int[mount+1][volume+1];
        knapSack(pack,value,weight,volume);
        for (int[] p:pack             ) {
            System.out.println(Arrays.toString(p));
        }
    }

    //老师是第i件物品，容量为1，2，3，4
    //我是：容量为i时，第1，2，3件物品，思路其实一样
    public static void knapSack(int[][] pack,int[] value,int[] weight,int volume){
        for (int v = 1; v <=volume ; v++) { //第一行第一列都为0
            for (int i = 0; i < value.length; i++) {
                if (weight[i]<=v){ //可以放进去
                    //判断放不放进去
                    pack[i+1][v]=Math.max(pack[i][v],value[i]+pack[i][v-weight[i]]);

                }else { //没法放进去
                    pack[i+1][v]=pack[i][v];
                }
            }
        }
        //未将放入的物品打印出来
    }

}
