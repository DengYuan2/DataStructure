package recursion;

//todo 迷宫回溯问题实现和分析
//迷宫图见同一个包下的MiGong.JPG，红色表示墙或挡板
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7]; //8行7列
        //使用1表示墙
        for (int i = 0; i < 7; i++) { //上面墙和下面墙设为1
            map[0][i]=1;
            map[7][i]=1;
        }
        for (int i = 0; i < 8; i++) { //左右墙设为1
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
//        map[1][2]=1;
//        map[2][2]=1;
        //输出地图
        System.out.println("地图为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        //使用递归给小球找路
        boolean res = setWays(map, 1, 1);
        System.out.println("能找到通路? "+res);
        System.out.println("小球在迷宫中的路线：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }


    /**
     *使用递归回溯来给小球找路：
     * @param map 地图
     * @param i 开始位置的行
     * @param j 开始位置的列
     * @return 找到通路则返回true，否则为false
     * 说明：若小球能到map[6][5],则说明找到通路
     * 约定：map[i][j]的意义：0-没走过   1-墙   2-通路    3-已走过，但不通
     * 策略（方法）：下-->右-->上-->左
     */
    public static boolean setWays(int[][] map,int i,int j){
        if (map[6][5]==2){
            return true;
        }
        if (map[i][j]==0){ //若还未走过
            map[i][j]=2; //先假定是通路
            if (setWays(map,i+1,j)) return true; //向下走
            else if (setWays(map,i,j+1)) return true; //向右走
            else if (setWays(map,i-1,j)) return true; //向上走
            else if (setWays(map,i,j-1)) return true; //向左走
            else { //怎么走都不行
                map[i][j]=3; //设为死路
                return false;
            }
        }else { //其他情况 1 2 3
            return false;
        }
    }


}
