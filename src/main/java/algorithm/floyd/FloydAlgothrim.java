package algorithm.floyd;

import java.util.Arrays;

//弗洛伊德算法 todo 有一个地方要好好体会，见57行
public class FloydAlgothrim {
    public static void main(String[] args) {
        char[] vertex={'A','B','C','D','E','F','G'};
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
        Graph graph = new Graph(vertex.length, matrix, vertex);
//        graph.floyd();
        graph.flo();
        graph.show();
        graph.showRes();
    }


}

class Graph{
    private char[] vertex; //存放顶点
    private int[][] dis; //保存各顶点间的距离，不断变化
    private int[][] pre; //保存各节点的前驱节点的下标

    public Graph(int length ,int[][] matrix,char[] vertex){
        this.vertex=vertex;
        this.dis=matrix;
        this.pre=new int[length][length];
        //对pre进行初始化，前驱节点设置为自己
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i); //注：存放的是下标
        }
    }

    //弗洛伊德算法
    public void floyd(){
        int length=vertex.length;
        int distance=0;
        for (int i = 0; i < length; i++) { //中间顶点
            for (int j = 0; j < length; j++) { //出发顶点
                for (int k = 0; k < length; k++) { //终点
                    distance=dis[i][j]+dis[i][k];
                    if (distance<dis[j][k]){
                        //修改对称的距离
                        dis[j][k]=distance;
                        dis[k][j]=distance;
                        //修改对称的前驱节点
                        pre[j][k]=pre[i][k]; //！！！注意此处不是将i赋给pre[j][k],会出错的
                        pre[k][j]=pre[i][k]; //因为两节点间并不一定是只隔了一个中间节点
                    }
                }
            }
        }
    }

    public void flo(){
        int length = vertex.length;
        //中间顶点
        for (int i = 0; i < length; i++) {
            //起始顶点
            for (int j = 0; j < length; j++) {
                //终点顶点
                for (int k = 0; k < length; k++) {
                    int distance = dis[i][j]+dis[i][k];
                    if (distance<dis[j][k]){
                        dis[j][k]=distance;
                        dis[k][j]=distance;
                        pre[j][k]=pre[i][k];
                        pre[k][j]=pre[i][k];
                    }
                }
            }

        }
    }
    //显示pre数组和dis数组
    public void show(){
        System.out.println("前驱节点数组为：");
        for (int[] p:pre) {
            System.out.println(Arrays.toString(p));
        }

        System.out.println("各节点距离数组为：");
        for (int[] d:dis) {
            System.out.println(Arrays.toString(d));
        }
    }

    //显示最终结果,自己写的
    public void showRes(){
        System.out.println("*****************************");
        for (int i = 0; i < vertex.length; i++) {
            System.out.println("节点"+vertex[i]+"到各节点的路径和距离为：");
            for (int j = 0; j < vertex.length; j++) {   //i=1 j=2
                System.out.print(vertex[j]);
                int idx=j;
                while (pre[i][idx]!=i){
                    System.out.print("<-"+vertex[pre[i][idx]]);
                    idx=pre[i][idx];
                }
                System.out.print("<-"+vertex[i]);
                System.out.print(" :"+dis[i][j]);
                System.out.println();
            }
        }
    }

}
