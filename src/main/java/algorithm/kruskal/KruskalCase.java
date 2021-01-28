package algorithm.kruskal;

import java.util.Arrays;

public class KruskalCase {
    private int edgeNum; //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵

    //使用INF表示两个顶点不能连同
    private static final int INF = Integer.MAX_VALUE;

    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点,使用复制拷贝的方式
        int vlen = vertexs.length;
        this.vertexs = new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //以上其实可以直接用this.vertexs=vertexs,但是为了不影响传进来的参数vertexs，在此只是复制；虽然这里也无所谓是否影响传进来的参数，但以后可以注意一下哦
        //初始化边(使用复制拷贝的方式)
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计有效边的数量
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (matrix[i][j] != INF)
                    this.edgeNum++;
            }
        }
    }

    //打印邻接矩阵
    public void print() {
        //这样输出不太好看，用下面的
//        for (int[] i : matrix) {
//            System.out.println(Arrays.toString(i));
//        }
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%13d", matrix[i][j]); // %20d指每个数字有20的位置，不够则空着
            }
            System.out.println();
        }
    }

    //对边进行排序,用冒泡排序
    private void sortEdges(EData[] edges) {
        EData temp = null;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    //返回顶点对应的下标
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch)
                return i;
        }
        return -1;
    }

    //获取图中的边，放到EData数组中
    private EData[] getEdges() {
        int index = 0;
        EData[] eData = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) { //遍历上三角即可
                if (matrix[i][j] != INF)
                    eData[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
            }
        }
        return eData;
    }

    /**
     * 获取下标为i的顶点的终点,用于判断是否形成回路（两顶点的终点是否相同）
     *
     * @param ends 记录各个顶点对应的终点，该数组是在遍历过程中不断形成的
     * @param i    传入的下标为i的顶点
     * @return i(下标)顶点的终点对应的下标
     */
    private int getEnd(int[] ends, int i) { //todo 超级巧妙！！结合传进来的ends的形成！！
        while (ends[i] != 0) //!!! 节点i的直接终点j,再找j的终点，直至找到最后的终点，而某一顶点的最终终点为0
            i = ends[i];       //!!!
        return i;  //若是没加入之前，终点就是自己；

        //不一定要让终点为0，也可以让 当end[i]=i的时候是终点，此时kruskal中必须要加一行 ends[m2]=m2;
        //不过老师的简单，直接用0判断是否是终点，还少写了代码，少赋了一次值，还是用老师的好
//        while (ends[i]!=i&&ends[i]!=0){
//            i=ends[i];
//        }
//        return i;
    }

    //
    public void kruskal() {
        //创建结果数组，保存最后的最小生成树
        EData[] res = new EData[vertexs.length - 1];
        //res数组的索引
        int index = 0;
        //用于保存已有最小生成树中的每个顶点在最小生成树中的终点
        int[] ends = new int[vertexs.length]; //老师写的edgenum
        //对边进行排序
        EData[] edges = getEdges();
        sortEdges(edges);
        //将边添加到最小生成树，判断是否形成回路
        int p1 = 0; //某条边的一个顶点(的下标)
        int p2 = 0; //某条边的另一个顶点(的下标)
        int m1 = -1;
        int m2 = -1;
        for (int i = 0; i < edgeNum; i++) { //多循环几次也没关系，反正会形成回路，也可以记一下数，到了vertexs.length-1就跳出循环
            p1 = getPosition(edges[i].start);
            p2 = getPosition(edges[i].end);
            //获取顶点在已有最小生成树中的终点
            m1 = getEnd(ends, p1);
            m2 = getEnd(ends, p2);
            //是否构成回路
            if (m1!=m2){
                ends[m1]=m2; //设置m1在“已有最小生成树”中的终点
                res[index++]=edges[i]; //将边加入结果数组
            }
        }

        //统计并打印最小生成树
        System.out.println("最小生成树为：");
        for (EData data:res) {
            System.out.println(data);
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = { //自己与自己时用0表示
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        //显示矩阵
        System.out.println("邻接矩阵为；");
        kruskalCase.print();
        System.out.println("边的个数为: " + kruskalCase.edgeNum);
        System.out.println("未排序时的边：");
        EData[] edges = kruskalCase.getEdges();
        for (EData data : edges) {
            System.out.println(data);
        }
        //排序后的边
        System.out.println("排序后的边:");
        kruskalCase.sortEdges(edges);
        for (EData data : edges) {
            System.out.println(data);
        }

        //最小生成树
        kruskalCase.kruskal();

    }
}

//创建边的类
class EData { //我觉得可以实现Comparable<EData>接口，放在ArrayList中用sort直接排序，而老师是放在数组里，自己写了冒泡排序，
    char start; //边的一个点
    char end; //边的令一个点
    int weight; //边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

//    @Override
//    public int compareTo(EData o) {
//        return this.weight - o.weight;
//    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

}