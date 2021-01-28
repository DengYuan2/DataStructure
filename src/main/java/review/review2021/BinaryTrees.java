package review.review2021;

import lombok.Data;


/**
 * @author DengYuan2
 * @create 2021-01-01 10:11
 */
public class BinaryTrees {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        Node hero1 = new Node(1, "宋江");
        Node hero2 = new Node(2, "吴用");
        Node hero3 = new Node(3, "卢俊义");
        Node hero4 = new Node(4, "林冲");
        Node hero5 = new Node(5, "关胜");
        Node hero6 = new Node(6, "魏邑尘"); //自己编的名字，别当真
//        Node hero7 = new Node(7, "魏邑尘");
//        Node hero8 = new Node(8, "魏邑尘");
//        Node hero9 = new Node(9, "魏邑尘");
//        Node hero10 = new Node(10, "魏邑尘");


        //先手动创建二叉树，后面会学习用递归方式创建二叉树
        /**
                    1
             2           3
         6            5      4
         */
        binaryTree.setRoot(hero1);
        hero1.setLeft(hero2);
        hero1.setRight(hero3);
        hero3.setLeft(hero5);
        hero3.setRight(hero4);
        hero2.setLeft(hero6);
        Node node = binaryTree.preOrderSearch(5);
        System.out.println(node);
        System.out.println("-前---------------------------");
        binaryTree.preOrder();
        System.out.println("-中---------------------------");
        binaryTree.infixOrder();
        System.out.println("-后---------------------------");
        binaryTree.postOrder();
        System.out.println("=中序遍历查找==================");
        Node node1 = binaryTree.infixOrderSearch(4);
        System.out.println(node1);


    }


}

class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node preOrderSearch(int no) {
        if (this.root == null) {
            return null;
        }
        return root.preOrderSearch(no);
    }

    public Node infixOrderSearch(int no){
        if (this.root==null){
            return null;
        }
        return root.infixOrderSearch(no);
    }

    public void preOrder() {
        if (this.root == null) {
            System.out.println("空！");
        } else {
            this.root.preOrder();
        }
    }

    public void infixOrder() {
        if (this.root == null) {
            System.out.println("空！");
        } else {
            this.root.infixOrder();
        }
    }

    public void postOrder() {
        if (this.root == null) {
            System.out.println("空！");
        } else {
            this.root.postOrder();
        }
    }

    public void delNode(int no){
        if (this.root==null){
            System.out.println("空！没什么可以删除的呀~~");
        }else {
            if (this.root.getNo()==no){
                this.root=null;
            }else {
                this.root.delNode(no);
            }
        }
    }


}


@Data
class Node {
    private int no;
    private String name;
    private Node left;
    private Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     *           1
     *     2           3
     * 6            5      4
     */
    public Node preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        Node node = null;
        if (this.left != null) {
            node = this.left.preOrderSearch(no);
        }
        if (node != null) {
            return node;
        } else {
            if (this.right != null) {
                node = this.right.preOrderSearch(no);
            }
        }
        return node;
    }

    public Node infixOrderSearch(int no) {
        Node node = null;
        if(this.left!=null){
            node = this.left.infixOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        if (this.no==no){
            return this;
        }
        if (this.right!=null){
            node=this.right.infixOrderSearch(no);
        }
        return node;
    }


    public void delNode(int no){
        if (this==null){
            return;
        }
        boolean isDel=false;
        if (this.left!=null){
            if (this.left.getNo()==no){
                this.left=null;
                isDel=true;
            }else {
                this.left.delNode(no);
            }
        }
        if (!isDel){
            if (this.right!=null){
                if (this.right.getNo()==no){
                    this.right=null;
                }else {
                    this.right.delNode(no);

                }
            }
        }

    }


}