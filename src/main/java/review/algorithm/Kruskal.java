package review.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    private static final int INF = Integer.MAX_VALUE;
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

        ArrayList<Lever> list = new ArrayList<>();
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j <vertexs.length ; j++) {
                if (matrix[i][j]!=INF){
                    list.add(new Lever(i,j,matrix[i][j]));
                }
            }
        }
        kruskal(list,vertexs);
    }

    public static void kruskal(List<Lever> list,char[] vertexs){
        ArrayList<Lever> selects = new ArrayList<>();
        int[] end = new int[vertexs.length];
        Arrays.fill(end,-1);
        Collections.sort(list);
        int count=0;
        while (count<vertexs.length-1){
            Lever remove = list.remove(0);
            int end1 = getEnd(end, remove.start);
            int end2 = getEnd(end, remove.end);
            if (end1!=end2){
                end[end1]=end2; //注意此处的写法啊
                end[end2]=end2;
                selects.add(remove);
                count++;
                System.out.println("第"+count+"个加入的是点"+vertexs[remove.start]+"和点"+vertexs[remove.end]+"组成的边，权重为"+remove.weight);
            }
        }


    }

    //这种方法不太行，还是看老师写的吧
    public static int getEnd(int[] end,int index){
        int first = end[index];
        if (first==-1){
            return index;
        }
        while (end[first]!=first){
            first=end[first];
        }
        return first;
    }
}

class Lever implements Comparable<Lever>{
    int start;
    int end;
    int weight;

    public Lever(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Lever{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Lever o) {
        return this.weight-o.weight;
    }
}
