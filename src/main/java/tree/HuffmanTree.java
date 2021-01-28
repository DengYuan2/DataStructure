package tree;

import java.util.*;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrder();
    }

    //创建霍夫曼树
    public static Node createHuffmanTree(int[] arr) {
        //为操作方便：遍历数组，将每个元素构建成一个node，将node放到ArrayList中
        ArrayList<Node> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new Node(arr[i]));
        }
        Node leftNode;
        Node rightNode;
        while (list.size() > 1) {
            //排序
            Collections.sort(list);
            //取出权值最小的节点(二叉树)
            leftNode = list.remove(0);
            //取出权值第二小的节点(二叉树)
            rightNode = list.remove(0);
            //构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //将新的节点加入到list中
            list.add(parent);
        }
        //返回霍夫曼树的root节点
        return list.get(0);
    }
}

//创建节点类
//为了让Node实现持续排序，让Node类实现Comparable接口
class Node implements Comparable<Node> {
    int value; //节点权值
    Node left; //指向左节点
    Node right; //指向右节点

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;  //若要从大到小，-(this.value-o.value)
    }


}