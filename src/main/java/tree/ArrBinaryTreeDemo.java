package tree;

import com.sun.org.apache.bcel.internal.generic.NEW;

//顺序存储二叉树[就我的理解，是已经将完全二叉树(≠满二叉树)上的数据放到数组里，现在是按三种方式将数据从数组里读出来，相当于是用数组表示二叉树]
//顺序存储二叉树应用实例：八大排序算法中的堆排序，就会使用到顺序存储二叉树，以后会讲（以后：堆排序在tree包和sort包都有）
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree binaryTree = new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println();
        System.out.println("后序遍历");
        binaryTree.postOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;//存储数据节点的数据

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder[因为每次调用下面那个方法时都要传相同的参数0，故不如自己先写好]
    public void preOrder() {
        this.preOrder(0);
    }

    //重载infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }

    //重载后序遍历
    public void postOrder() {
        this.postOrder(0);
    }

    //完成顺序存储二叉树的前序遍历
    public void preOrder(int index) { //index为数组下标
        //如果数组为空或长度为0
        if (arr.length == 0 || arr == null) {
            System.out.println("数组为空，不能进行二叉树的前序遍历啊~~");
            return;
        }
        System.out.print(arr[index] + "  ");
        if (2 * index + 1 < arr.length)
            preOrder(2 * index + 1);
        if (2 * index + 2 < arr.length)
            preOrder(2 * index + 2);
    }

    //中序遍历
    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行二叉树的中序遍历啊~~");
            return;
        }
        if (2 * index + 1 < arr.length)
            infixOrder(2 * index + 1);
        System.out.print(arr[index] + "  ");
        if (2 * index + 2 < arr.length)
            infixOrder(2 * index + 2);
    }

    //后序遍历
    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能进行");
            return;
        }
        if (2 * index + 1 < arr.length)
            postOrder(2 * index + 1);
        if (2 * index + 2 < arr.length)
            postOrder(2 * index + 2);
        System.out.print(arr[index] + "  ");
    }
}
