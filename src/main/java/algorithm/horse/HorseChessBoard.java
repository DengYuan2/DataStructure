package algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//马踏棋盘算法
public class HorseChessBoard {
    private static int X; //棋盘的列数
    private static int Y; //棋盘的行数
    //标记棋盘各个位置是否被访问过
    private static boolean isVisited[];
    //标记是否棋盘的所有位置都被访问过
    private static boolean isFinished;

    //马踏棋盘算法
    //现在在棋盘chessBoard的第row行，第col列，处于第step步
    public static void travelsalChessBoard(int[][] chessBoard, int col, int row, int step) {
        chessBoard[row][col] = step;
        //因为isVisited是一维数组,找到合适的位置设置其为已访问
        isVisited[row * X + col] = true;
        ArrayList<Point> next = next(new Point(col, row));
        //用贪婪算法进行优化，步骤二：
        sort(next);
        for (int i = 0; i < next.size(); i++) {
            Point point = next.get(i);
            if (!isVisited[point.y * X + point.x])
                travelsalChessBoard(chessBoard, point.x, point.y, step + 1);
        }
        //若接下来无路可走了，注意消除该棋走过的痕迹
        if (step < X * Y && !isFinished) {
            chessBoard[row][col] = 0;
            isVisited[row * X + col] = false;
        } else {
            isFinished = true;
        }

    }

    //用贪婪算法进行优化，使每次选择的位置都是走法更少的那种方式，让回溯的次数更少！！
    //例（还是PNG中的棋盘）：选5的话接下来有7种走法，选1的话有6种，则选1
    //用贪婪算法进行优化，步骤一：
    //根据 当前这一步的所有下一步 选择位置，进行非递减排序
    public static void sort(ArrayList<Point> ps) {
//        ps.sort(new Comparator<Point>() {
//            @Override
//            public int compare(Point o1, Point o2) {
//                return next(o1).size() - next(o2).size();
//            }
//        });
        ps.sort((o1,o2)->(next(o1).size()-next(o2).size()));
    }

    //判断能走的位置，并放入到集合中; 此处系统自带的Point类，代表坐标轴上的点(x,y)
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //判断马儿是否能走5这个位置（见HorseChessBoard.PNG图中棋盘的标号）
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿是否能走6这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿是否能走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿是否能走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿是否能走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿是否能走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿是否能走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿是否能走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;

    }

    //复习时自己写的，改了好几次才对
    public static void travel(int[][] board, int row, int col, int step) {
        board[row][col] = step;
        ArrayList<Point> next = next(new Point(col, row));
        sort(next);
        for (int i = 0; i < next.size(); i++) {
            Point point = next.get(i);
            if (board[point.y][point.x] == 0) {
                travel(board, point.y, point.x, step + 1);
            }
        }
        if (step != 64 && !isFinished) {
            board[row][col] = 0;
        } else {
            isFinished = true;
        }
    }

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        isVisited = new boolean[X * Y];
        isFinished = false;
        int[][] chessBoard = new int[X][Y];
        long start = System.currentTimeMillis();
        travelsalChessBoard(chessBoard, 0, 0, 1);
//        travel(chessBoard,0,0,1);
        long end = System.currentTimeMillis();
        long time = end - start;
        //用贪婪算法优化后顺序不一样，时间大大缩短！！！
        System.out.println("共耗时" + time + "ms");

        //输出棋盘的最后情况
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

//        for (int i = 0; i < X; i++) {
//            for (int j = 0; j < Y; j++) {
//
//                travelsalChessBoard(chessBoard,i,j,1);
//            }
//
//        }
    }
}
