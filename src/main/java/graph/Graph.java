package graph;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//用于测试的main方法在最下方
public class Graph {

    private ArrayList<String> vertexList; //存储顶点

    private int[][] edges; //存储图对应的邻接矩阵

    private int numOfEdges; //表示边的数目

    //定义数组，记录某个节点是否被访问
    private boolean[] isVisited;

    public Graph(int n) { //n为顶点个数
        //初始化矩阵和vertexList;
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isVisited = new boolean[n];
    }

    //对一个节点进行广度优先遍历
    public void bfs(boolean[] isVisited, int i) {
        int u; //表示队列头节点的下标
        int w; //表示邻接节点
        LinkedList<Integer> queue = new LinkedList<>();
        //标记为已访问
        isVisited[i] = true;
        //将该节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列头节点的下标
            u = queue.removeFirst();
            System.out.print(getValueByIndex(u) + "-->");
            //得到第一个邻接节点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    queue.addLast(w);
                    isVisited[w] = true;
                }
                //找下一个邻接点
                w = getNextNeighbor(u, w);
            }

        }

    }

    //重载,对所有节点进行广度优先搜索，因为有的是非连通的
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i])
                bfs(isVisited, i);
        }
    }

    //深度优先遍历
    public void dfs(boolean[] isVisited, int i) { //i第一次时为0
        //首先，访问该节点
        System.out.print(getValueByIndex(i) + "-->");
        //将该节点设置为已访问
        isVisited[i] = true;

        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载，遍历所有的节点，进行dfs,尤其是针对不连通图
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //得到第一个邻接节点的下标,不存在则返回-1
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0)
                return j;
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int k = v2 + 1; k < vertexList.size(); k++) {
            if (edges[v1][k] > 0)
                return k;
        }
        return -1;
    }

    //插入节点,即顶点上的字符串
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标，即第几个节点；例：A->0
     * @param v2     同上
     * @param weight 两顶点之间是否直连
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight; //矩阵是对称的哦
        numOfEdges++;
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
        //遍历二维数组，可以直接用这个方法，但是在一排，不好看
//        Arrays.deepToString(edges);
    }

    //得到节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i(下标)对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public static void main(String[] args) {
        //测试1
//        int n = 5; //节点个数
//        Graph graph = new Graph(n);
//        String[] vertexes = {"A", "B", "C", "D", "E"};
//        //添加顶点
//        for (String value : vertexes) {
//            graph.insertVertex(value);
//        }
//        //添加边 A-B A-C B-C B-D B-E
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
//        System.out.println("深度优先遍历");
//        graph.dfs(); //A-->B-->C-->D-->E-->

//        System.out.println("广度优先遍历");
//        graph.bfs(); //A-->B-->C-->D-->E-->

        //测试2
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(vertexes.length);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        //添加顶点
        for (String value : vertexes) {
            graph.insertVertex(value);
        }
        //显示邻接矩阵
        System.out.println("邻接矩阵为：");
        graph.showGraph();
        System.out.println("深度优先遍历");
        graph.dfs(); //1-->2-->4-->8-->5-->3-->6-->7-->
        //todo 因为深度优先遍历已经将isVisited数组全置为了true，故需要将上面两行注释掉；
        // 若打算一起看，可以将idVisited数组重置为false;或者不在构造器中初始化isVisited
        // 数组，而在dfs()的最上面初始化，在bfs()的最上面也初始化，即谁用谁初始化
//        System.out.println("广度优先遍历");
//        graph.bfs(); //1-->2-->3-->4-->5-->6-->7-->8-->

    }
}
