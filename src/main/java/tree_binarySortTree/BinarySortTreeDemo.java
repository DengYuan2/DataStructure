package tree_binarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加节点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.infixOrder();
        //测试删除叶子节点
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);
//        binarySortTree.delNode(1);
//        System.out.println("删除叶子节点后");
//        binarySortTree.infixOrder();
        //测试删除只有一颗子树的节点
//        binarySortTree.delNode(1);
//        System.out.println("删除该节点后");
//        binarySortTree.infixOrder();
        //测试删除有两颗子树的节点
//        binarySortTree.delNode(10);
//        System.out.println("删除该节点后");
//        binarySortTree.infixOrder();
        System.out.println();
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(1);
        binarySortTree.delNode(3);
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //添加节点
    public void add(Node node) {
        if (root == null)
            root = node;
        else
            root.add(node);
    }

    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }


    //查找要删除的节点
    public Node search(int value) {
        if (root == null)
            return null;
        else
            return root.search(value);
    }

    //查找要删除的节点的父节点
    public Node searchParent(int value) {
        if (root == null)
            return null;
        else
            return root.searchParent(value);
    }


    //删除节点（叶子节点）
    public void delNode(int value) {
        if (root == null)
            return;
        Node targetNode = search(value);

        //若这棵树的根节点只有左节点或只有右节点，则删除根节点时该方法就没有达到效果，因为此时父节点为空，故单独考虑根节点情况
        if (root == targetNode) {
            if (root.left == null && root.right == null) {
                root = null;
                return;
            } else if (root.left == null) {
                root = root.right;
                return;
            } else if (root.right == null) {
                root = root.left;
                return;
            } //还有一种情况是有两个子节点，这时即使删除的是根节点也可以在下面的第三种情况里处理，因为它不用父节点
        }

        if (targetNode != null) {
            Node parent = searchParent(value);
            if (targetNode.left == null && targetNode.right == null) { //第一种情况：要删除的节点是叶子节点
                if (parent.left == targetNode)
                    parent.left = null;
                else if (parent.right == targetNode)
                    parent.right = null;
            } else if (targetNode.left != null && targetNode.right != null) { //第三种情况：要删除的节点有两颗子树
                //找到右边子树的最小值，用最小值替换待删除节点的值，并将最小值的节点删除；
                // 【也可以找左边子树的最大值，思路相似】
                Node target = targetNode.right;
                while (target.left != null) {
                    target = target.left;
                }
                delNode(target.value); //此时left节点最多有一个子节点
                targetNode.value = target.value;
            } else { //第二种情况：小技巧：因为写if的话比较复杂，所以直接写在else里，让第二种情况写在if里
                if (targetNode.left != null) {
                    if (parent.left == targetNode)
                        parent.left = targetNode.left;
                    else if (parent.right == targetNode)
                        parent.right = targetNode.left;
                } else if (targetNode.right != null) {
                    if (parent.left == targetNode)
                        parent.left = targetNode.right;
                    else if (parent.right == targetNode)
                        parent.right = targetNode.right;
                }
            }

        } else {
            System.out.println("没有该节点啊~~~");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else
            System.out.println("当前二叉排序树为空");
    }

    //前序遍历
    public void preOrder() {
        if (root != null)
            root.preOrder();
        else
            System.out.println("当前二叉排序树为空~~");
    }


}

//节点类
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加节点，满足二叉排序树的要求，递归形式
    public void add(Node node) {
        if (node == null)
            return;
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
//                return; //不用return,因为运行完上步骤后该方法结束了(因为下方都是if-else，不会进去的)，调用该方法的方法也结束了，不论套了多少个，都结束了
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

    }

    //做利扣时看到二叉排序树，就来复习一下，自己又写了一遍增加节点方法
    public void addNode(Node node) {
        //假设已经有了根节点

        if (node.value < this.value) {
            if (this.left != null) {

                this.left.addNode(node);
            } else {
                this.left = node;
            }
        } else {
            if (this.right != null) {

                this.right.addNode(node);
            } else {
                this.right = node;
            }
        }

    }

    //查找要删除节点的父节点,没有则返回null
    public Node searchParent(int value) {
        //如果当前节点就是父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else //此种情况中也包括根节点哦
                return null;
        }

    }

    //查找要删除的节点,没有则返回null
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null)
                return this.left.search(value);
            else
                return null;
        } else {
            if (this.right != null)
                return this.right.search(value);
            else
                return null;
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null)
            this.left.infixOrder();
        System.out.println(this);
        if (this.right != null)
            this.right.infixOrder();
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
}
