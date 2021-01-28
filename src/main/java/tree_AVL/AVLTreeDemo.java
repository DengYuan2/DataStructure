package tree_AVL;

public class AVLTreeDemo {
    public static void main(String[] args) {

//        int[] arr = {4, 3, 6, 5, 7, 8};
//        AVLTree avlTree = new AVLTree();
//        //未平衡处理前，添加节点后，变成二叉排序树
//        for (int i = 0; i < arr.length; i++) {
//            //此处的add方法已经是平衡化的了
//            avlTree.add(new Node(arr[i]));
//        }
//        System.out.println("中序遍历");
//        avlTree.infixOrder();
//        System.out.println("平衡处理后~~");
//        System.out.println("树的高度=" + avlTree.getRoot().height());
//        System.out.println("左子树高度=" + avlTree.getRoot().left.height());
//        System.out.println("右子树高度=" + avlTree.getRoot().right.height());
        //测试右旋转
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("平衡处理后~~");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("左子树高度=" + avlTree.getRoot().left.height());
        System.out.println("右子树高度=" + avlTree.getRoot().right.height());
        System.out.println("当前根节点为" + avlTree.getRoot());
        System.out.println("根节点的左节点为"+avlTree.getRoot().left);
        System.out.println("根节点的左节点的左节点为"+avlTree.getRoot().left.left);
        avlTree.infixOrder();
    }
}


//创建AVL树,从里拿来的BinarySortTree，在此基础上增加的方法
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    //添加节点
    public void add(Node node) {
        if (root == null)
            root = node;
        else
            root.add(node);
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
}


//节点类,从BinarySortTreeDemo.java里的Node类抄来的，在此基础上进行增改
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //左旋转方法
    public void leftRotate() {
        //1、创建新节点，赋以当前节点(根节点)的值
        Node newNode = new Node(this.value);
        //2、把新节点的左子树设置为当前节点的左子树
//        if (this.left != null) //不用if，为null就null呗，下同
        newNode.left = this.left;
        //3、把新节点的右子树设置为当前节点的右子树的左子树
//        if (this.right.left != null) //不用
        newNode.right = this.right.left;
        //4、将当前节点的值替换为右子节点的值
        this.value = this.right.value;
        //5、把当前节点的右子树设置为当前节点的右子树的右子树
//        if (this.right.right != null) //不用
        this.right = this.right.right;
        //6、把当前节点的左子树设置成新节点
        this.left = newNode;

    }

    //右旋转方法
    public void rightRotate() {
        //1、创建新节点，赋以当前节点(根节点)的值
        Node newNode = new Node(value);
        //2、把新节点的右子树设置为当前节点的右子树
        newNode.right = right;
        //3、把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        //4、将当前节点的值替换为左子节点的值
        value = left.value;
        //5、把当前节点的左子树设置为当前节点的左子树的左子树
        left = left.left;
        //6、把当前节点的右子树设置成新节点
        right = newNode;
    }

    //添加节点，平衡处理
    public void add(Node node) {
        if (node == null)
            return;
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (right == null) {
                right = node;
            } else {
                right.add(node);
            }
        }

        //当添加完节点后，判断是否要进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            //若它的右子树的 左子树的高度大于右子树的高度，先对右子树进行右旋转
            if (this.right!=null&&this.right.leftHeight()>this.right.rightHeight()){
                this.right.rightHeight();
            }

            leftRotate();
            //!!!!该方法结束后没有必要再做下面的if判断，而且防止出现问题(但是是什么问题呢？没说)
            return;
        }
        //判断是否进行右旋转
        if (leftHeight() - rightHeight() > 1){
            //若它的左子树的 右子树的高度大于左子树的高度，先对左子树进行左旋转
            if (left!=null&&left.rightHeight()>left.leftHeight()){
                left.leftRotate();
            }

            rightRotate();
        }


    }

    //返回当前节点的高度，即以该节点为根节点的树的高度
    //递归
    public int height() { //!!!!!!

//        int l=0;
//        int r=0;
//        if (this.left!=null){
//            l=this.left.height();
//        }
//        if (this.right!=null){
//            r=this.right.height();
//        }
//        return Math.max(l,r)+1;

        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1; //+1是因为还要包括该节点，即调用该方法的节点
    }

    //左子树的高度
    public int leftHeight() {
        if (left == null)
            return 0;
        return left.height();
    }

    //右子树的高度
    public int rightHeight() {
        if (right == null)
            return 0;
        return right.height();
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
