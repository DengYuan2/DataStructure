package algorithm.dijkstra;

import java.util.Arrays;
import java.util.HashMap;

//用迪杰斯特拉算法求得某一出发点到其他各点得最短距离
public class DijkstraAlgorithm {
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
        Graph graph = new Graph(vertexs, matrix);
        System.out.println("显示邻接矩阵");
        graph.showGraph();
        //测试狄克斯特拉算法
        graph.dij(6); //G
        graph.vv.show();
    }
}

class Graph {
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵
    VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //更新index下标顶点到周围顶点的(最短)距离和周围顶点的前驱节点
    //todo 更新最短距离的关键代码！！！
    private void update(int index) {
        for (int j = 0; j < matrix[index].length; j++) {
            int len = vv.getDis(index) + matrix[index][j];
            if (!vv.isVisited(j) && len < vv.getDis(j)) {
                vv.updateDis(j, len);
                vv.updatePre(j, index);
            }
        }
    }


    //迪杰斯特拉算法
    public void dijkstra(int index) { //index是出发节点对应的下标
        vv = new VisitedVertex(vertex.length, index);
//        update(index); //此处想让G直接先运行一次，要改下面的for循环为i=1,且初始化vv时加上this.already_arr[index]=1;
        int idx = 0;
        for (int i = 0; i < vertex.length; i++) {
            idx = 0;
            int min = 65535;
            //寻找距离最小的节点，第一次是G（6），第二次是A（0）
            for (int j = 0; j < vv.dis.length; j++) {
                if (vv.dis[j] < min && vv.already_arr[j] == 0) {
                    min = vv.dis[j];
                    idx = j;
                }
            }
            vv.already_arr[idx] = 1;
            update(idx);

            //以下只是查看一下每一次得输出结果
//            System.out.printf("第%d次\n", i );
//            System.out.println("各节点到G的距离为：" + Arrays.toString(vv.dis));
//            System.out.println("各节点的前驱节点为");
//            for (int j = 0; j < vertex.length; j++) {
//                if (j == index)
//                    continue;
//                System.out.print(vertex[j]+"->"+vertex[vv.pre_visited[j]] + "  ");
//            }
//            System.out.println();

        }

        //以下查看最终结果
        System.out.println("用迪杰斯特拉算法求得某一出发点到其他各点得最短距离为：");
        for (int i = 0; i < vertex.length; i++) {
            System.out.println("出发点" + vertex[index] + "到顶点" + vertex[i] + "的最小距离为" + vv.dis[i]);
            System.out.print("路线为：");
            int j = i; //i=3
            System.out.print(vertex[i] + "<-");
            while (vv.pre_visited[j] != index) {
                j = vv.pre_visited[j];
                System.out.print(vertex[j] + "<-");
            }
            System.out.print(vertex[index]);
            System.out.println();
        }

    }

    public void dij(int index) {
        vv = new VisitedVertex(vertex.length, index);
        int idx = 0;
        for (int i = 0; i < vertex.length; i++) {
            idx=0;
            int min = 65535;
            for (int j = 0; j < vertex.length; j++) {
                if (vv.dis[j] < min && !vv.isVisited(j)) {
                    min = vv.dis[j];
                    idx = j;
                }
            }
            vv.already_arr[idx] = 1;
            upd(idx);
        }

        //起始节点的先前节点设置为自己
        vv.pre_visited[index]=index;

        System.out.println("最短距离为：");
        for (int i = 0; i < vertex.length; i++) {
            System.out.println("出发点"+vertex[index]+"到顶点"+vertex[i]+"的最小距离为"+vv.dis[i]);
            System.out.print("路线为：");
            System.out.print(vertex[i]+"<-");
            int j =i;
            while (vv.pre_visited[j]!=index){
                j=vv.pre_visited[j];
                System.out.print(vertex[j]+"<-");

            }
            System.out.print(vertex[index]);
            System.out.println();
        }
    }

    public void upd(int index) {
        for (int j = 0; j < vertex.length; j++) {
            int len = vv.dis[index] + matrix[index][j];
            if (len < vv.dis[j] && !vv.isVisited(j)) {
                vv.updateDis(j, len);
                vv.updatePre(j, index);
            }
        }
    }

}

//已访问顶点集合
class VisitedVertex {
    public int[] already_arr; //记录各顶点是否访问过，访问过则为1，否则为0
    public int[] pre_visited; //数组中每个值为前一个顶点的下标
    public int[] dis; //记录出发顶点(一开始固定后就不变了)到其他所有顶点的最短距离(暂时的)；会不断更新

    public VisitedVertex(int length, int index) { //length为顶点个数，index为出发顶点的下标
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis,除自己外全部设为最大值65535
        Arrays.fill(dis, 65535);
        dis[index] = 0;

    }

    //判断下标index对应的顶点是否访问过，访问过则为true,否则为false
    public boolean isVisited(int index) {
        return already_arr[index] == 1;
    }

    //更新出发顶点到index顶点的距离len
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    //更新下标为now的节点的前驱节点为index
    public void updatePre(int now, int index) {
        pre_visited[now] = index;
    }

    //返回出发顶点到index顶点的距离
    public int getDis(int index) {
        return dis[index];
    }

    //显示最后的结果
    public void show() {
        System.out.println("************************************");
        for (int a : already_arr) {
            System.out.print(a + " ");
        }
        System.out.println();
        for (int p : pre_visited) {
            System.out.print(p + " ");
        }
        System.out.println();
        for (int d : dis) {
            System.out.print(d + " ");
        }
    }

}