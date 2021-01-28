package array;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        /**
         *一、 二维数组转稀疏数组
         *思路：
         * 1、遍历原始数组，获得非0数据的个数count，以建立稀疏数组[count+1][3]
         * 2、再次遍历原始数组，将非0数据所在行、列及数据放到稀疏数组中【注：该数组的第一行为原始数组的行、列、非0数据个数】
         */
        //原始的二维数组
        System.out.println("原始数组为：");
        int[][] arraySrc = new int[11][11];
        arraySrc[1][2] = 1;
        arraySrc[2][3] = 2;
        int row = arraySrc.length;
        int col = arraySrc[0].length;
        //输出原始数组
        for (int[] subArray : arraySrc) {
            for (int data : subArray) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //变成稀疏数组
        System.out.println("稀疏数组为：");
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arraySrc[i][j] != 0) {
                    count++;

                }
            }
        }
        int count2 = 1;
        int[][] sparseArray = new int[count + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = count;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arraySrc[i][j] != 0 && count2 <= count) {
                    sparseArray[count2][0] = i;
                    sparseArray[count2][1] = j;
                    sparseArray[count2][2] = arraySrc[i][j];
                    count2++;
                }
            }
        }

        //输出稀疏数组
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        /**
         * 二、稀疏数组转为二维数组
         *1、读取稀疏数组的第一行，获得二维数组的行和列，建立二维数组
         * 2、依次读取稀疏数组剩余列，读入二维数组
         */
        System.out.println("再次转为二维数组：");
        int twoRow = sparseArray[0][0];
        int twoCol = sparseArray[0][1];
        int amount = sparseArray[0][2];
        int[][] srcArray = new int[twoRow][twoCol];
        for (int i = 1; i <= amount; i++) {
            srcArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        for (int[] subArray : srcArray) {
            for (int data : subArray) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        System.out.println("******保存到磁盘中********");
        System.out.println();
        FileWriter fileWriter =null;
        try {
            File file = new File("hello.txt");

            fileWriter = new FileWriter(file);

            for (int[] subArray : srcArray) {
                for (int data : subArray)
                    fileWriter.write(data+"\t");
//                    System.out.println(data);
                fileWriter.write("\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter!=null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("*********读出磁盘中的数据***************");
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile("hello.txt","r");
            String s="";
            while ((s=randomAccessFile.readLine())!=null) {
                System.out.println(s);
            }
//            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
