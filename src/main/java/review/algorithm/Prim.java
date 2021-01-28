package review.algorithm;

import java.util.ArrayList;

public class Prim {
    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = { //10000表示两个点不连通
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        Graph graph = new Graph(verxs, data, weight);
        graph.prim(0);

    }
}

class Graph {
    int count; //顶点数
    char[] vertex; //顶点值
    int[][] weight; //边的权重
    boolean[] isVisited; //记录顶点是否被访问过

    public Graph(int count, char[] vertex, int[][] weight) {
        this.count = count;
        this.vertex = vertex;
        this.weight = weight;
        this.isVisited = new boolean[count];
    }


    //看了一遍老师的写法后再自己写的，注意巧妙的方法
    public void prim(int start) {
        isVisited[start]=true;
        int min=10000;
        int h1=-1;
        int h2=-1;
        int sum=0;
        for (int i = 1; i < count; i++) { //count-1条边,一共进行count-1次
            min=10000;
            for (int j = 0; j < count; j++) { //从上到下，从左到右，遍历weight表
                if (isVisited[j]){ //
                    for (int k = 0; k < count; k++) {
                        if (!isVisited[k]&&weight[j][k]<min){
                            min=weight[j][k];
                            h1=j;
                            h2=k;
                        }
                    }
                }
            }
            isVisited[h2]=true;
            sum=sum+min;
            System.out.println("第"+i+"次是从点"+vertex[h1]+"到点"+vertex[h2]+"，权重为"+weight[h1][h2]);

        }
        System.out.println("一共的距离是"+sum);


    }



}