package review.algorithm;

import java.util.Arrays;

public class Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535; //表示不可连接
        int[][] matrix = { //注意与迪杰斯特拉算法中矩阵的不同，对角线上距离为0
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        FGraph fGraph = new FGraph(vertex, matrix);
        fGraph.floyd();


    }
}

class FGraph {
    char[] vertex;
    int[][] weight;
    int[][] pre;

    public FGraph(char[] vertex, int[][] weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public void floyd() {
        int len = vertex.length;
        pre = new int[len][len];
        for (int i = 0; i < len; i++) {
            Arrays.fill(pre[i], i);
        }
        for (int i = 0; i < len; i++) { //中间节点  中间节点要放在最上面哦,否则前驱节点矩阵不太对，不过为什么呢？？？？
            for (int j = 0; j < len; j++) { //开始节点
                for (int k = 0; k < len; k++) { //末尾节点
                    if (weight[j][i] + weight[k][i] < weight[j][k]) {
                       weight[j][k]= weight[j][i] + weight[k][i];
                       weight[k][j]=weight[j][i] + weight[k][i];
                       pre[j][k]=pre[i][k]; //将开始节点和末尾节点的前驱节点 赋值为 中间节点和末尾节点的前驱节点
                       pre[k][j]=pre[i][k];
                    }

                }

            }

        }
        System.out.println("前驱节点矩阵为：");
        for (int i = 0; i < pre.length; i++) {
            System.out.println(Arrays.toString(pre[i]));
        }
        System.out.println("节点间距离为：");
        for (int i = 0; i <weight.length ; i++) {
            System.out.println(Arrays.toString(weight[i]));
        }
    }
}
