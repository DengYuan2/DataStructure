package review.algorithm;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535; //表示不可连接
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        MGraph mGraph = new MGraph(vertexs, matrix);
        mGraph.dijkstra(1);

    }
}

class MGraph {
    char[] vertexs; //顶点值
    int[][] weight; //权重/距离
    int[] isVisited; //该节点是否访问过
    int[] preVisited; //该节点前面的节点
    int[] dis; //出发节点到该节点的距离

    public MGraph(char[] vertexs, int[][] weight) {
        this.vertexs = vertexs;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MGraph{" +
                "vertexs=" + Arrays.toString(vertexs) +
                ", weight=" + Arrays.toString(weight) +
                '}';
    }

    //思路基本正确
    public void dijkstra(int index) {
        int len = vertexs.length;
        isVisited = new int[len];
        preVisited=new int[len];
        dis = new int[len];
        Arrays.fill(dis, 65535);
        dis[index] = 0;
        int start = index;

        while (true) {
            for (int i = 0; i < len; i++) {
                if (dis[i] > weight[start][i]+dis[start]) {
                    dis[i] = weight[start][i]+dis[start];
                    preVisited[i]=start;
                }
            }
            int min = 65535;
            int record = -1;
            //找到距离最小的
            for (int i = 0; i < len; i++) {
                if (isVisited[i] == 0) {
                    if (dis[i] < min) {
                        min = dis[i];
                        record = i;
                    }
                }
            }
            start = record;
            if (start == -1) {
                break;
            }
            isVisited[start]=1;
        }
        System.out.println("到各点的距离为："+Arrays.toString(dis));
        System.out.println("之前访问的节点为："+Arrays.toString(preVisited));
    }


}