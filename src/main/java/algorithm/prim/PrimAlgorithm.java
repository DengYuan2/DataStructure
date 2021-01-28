package algorithm.prim;


import java.util.Arrays;

//普利姆算法
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看图是否创建好
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
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.showGraph(mGraph);

        minTree.prim(mGraph, 0); //无论从哪个节点开始，最后总长度都一样
        System.out.println("----------------------------------------------");
        minTree.primA(mGraph,2);
    }
}

//创建最小生成树->村庄的图
class MinTree {
    //创建图的邻接矩阵
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图
    public void showGraph(MGraph graph) {
        for (int[] row : graph.weight) {
            System.out.println(Arrays.toString(row));
        }
    }


    //普利姆算法
    public void prim(MGraph graph, int v) {
        int[] visited = new int[graph.verxs]; //标记是否被访问过，默认为0，表示未访问过
        //将该节点标记为已访问
        visited[v] = 1;
        //记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        int sum = 0;
        for (int k = 1; k < graph.verxs; k++) { //一共只有verxs-1条边

            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    //妙啊！！！
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }

            }
            visited[h2] = 1;
            System.out.println("第" + k + "次是边<" + graph.data[h1] + "," + graph.data[h2] + ">,权值为：" + graph.weight[h1][h2]);
            //记得重置minWeight
            minWeight = 10000;
            sum += graph.weight[h1][h2];
        }
        System.out.println("从顶点" + graph.data[v] + "开始，总长度为" + sum);

    }

    /**
     * 复习时的写法-用res[]数组记录了被访问的节点的下标;原来是边遍历边判断该节点是否被访问
     * @param graph
     * @param v
     */
    public void primA(MGraph graph, int v) {
        int verxs = graph.verxs;
        boolean[] isVisited = new boolean[verxs];
        int[] res = new int[verxs];
        res[0] = v;
        isVisited[v] = true;
        int minVal = 10000;
        int minIdx = -1;
        int preIdx = 0;
        int sum = 0;
        for (int i = 1; i < verxs; i++) {
            minVal = 10000;
            for (int k = 0; k < i; k++) {
                for (int j = 0; j < verxs; j++) {
                    if (graph.weight[res[k]][j] < minVal && isVisited[j] == false) {
                        minVal = graph.weight[res[k]][j];
                        preIdx = res[k];
                        minIdx = j;
                    }
                }
            }
            isVisited[minIdx] = true;
            res[i] = minIdx;
            sum+=graph.weight[preIdx][minIdx];
            System.out.println("第"+i+"次是边<"+graph.data[preIdx]+","+graph.data[minIdx]+">"+" ,权值为"+graph.weight[preIdx][minIdx]);

        }
        System.out.println("总长度为"+sum);
    }

}

//图
class MGraph {
    int verxs; //图的节点个数
    char[] data; //节点的数据
    int[][] weight; //边，即邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];

    }
}
