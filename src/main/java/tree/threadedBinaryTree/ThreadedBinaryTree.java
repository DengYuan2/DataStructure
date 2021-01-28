package tree.threadedBinaryTree;

//线索化二叉树
//似乎未必是完全二叉树
//todo 老师只讲了中序线索化和如何遍历中序线索化二叉树，其余2种方式是类比 ThreadedBinaryTree.PNG 画出情况图，再照着改写的，一定要画出图自己检验一下！！！
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "");
        HeroNode node3 = new HeroNode(3, "");
        HeroNode node6 = new HeroNode(6, "");
        HeroNode node8 = new HeroNode(8, "");
        HeroNode node10 = new HeroNode(10, "");
        HeroNode node14 = new HeroNode(14, "");
        //建立二叉树，现在是手动建立，以后用递归建立
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(node1);
        node1.setLeft(node3);
        node1.setRight(node6);
        node3.setLeft(node8);
        node3.setRight(node10);
        node6.setLeft(node14);
        //为了后序遍历有必要设置好父节点，暂时时手动的，若要递归，可看 https://www.cnblogs.com/lishanlei/p/10707830.html 中的相关内容
        //递归方法已经抄写在下面了，不过此处没有用，要设置好相关的数组哦
        node3.setParent(node1);
        node6.setParent(node1);
        node8.setParent(node3);
        node10.setParent(node3);
        node14.setParent(node6);

//        //进行中序线索化
//        binaryTree.infixThreadedNode();
//        //测试中序线索化，以node10为例
//        HeroNode left = node10.getLeft();
//        System.out.println("node10的前驱节点是"+left);
//        HeroNode right = node10.getRight();
//        System.out.println("node10的后继节点是"+right);
//        //测试遍历中序线索化
//        System.out.println("使用线索化的方式遍历线索化");
//        binaryTree.infixThreadedList();
        //进行前序线索化
//        binaryTree.preThreadedNode();
//        System.out.println("nodeX的前驱节点是" + node14.getLeft() + "nodeX的后序节点是" + node14.getRight());
//        binaryTree.preThreadedList();
        //进行后续线索化
        binaryTree.postThreadedNode();
        System.out.println("nodeX的前驱节点是" + node3.getLeft() + "nodeX的后序节点是" + node3.getRight());
        binaryTree.postThreadedList();

    }
}

//用的就是BinaryTreeDemo中的BinaryTree,在此基础上修改
//注：此树是完全二叉树
class BinaryTree {

    private HeroNode root;
    //为了实现线索化,需要创建指向 当前节点的前驱节点 的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载下面infixThreadedNode
    public void infixThreadedNode() {
        this.infixThreadedNode(root);
    }

    //重载
    public void preThreadedNode() {
        this.preThreadedNode(root);
    }

    //重载
    public void postThreadedNode() {
        this.postThreadedNode(root);
    }

    //递归方法创建完全二叉树，参考 https://www.cnblogs.com/lishanlei/p/10707830.html
    public HeroNode createBinaryTree(int[] arr, int index) {
        HeroNode heroNode = null;
        if (index < arr.length) {
            heroNode = new HeroNode(arr[index], "");
            heroNode.setLeft(createBinaryTree(arr, 2 * index + 1));
            heroNode.setRight(createBinaryTree(arr, 2 * index + 2));
            if (heroNode.getLeft() != null) {
                heroNode.getLeft().setParent(heroNode);
            }
            if (heroNode.getRight() != null) {
                heroNode.getRight().setParent(heroNode);
            }
        }
        return heroNode;
    }

    //编写对二叉树进行中序线索化的方法
    public void infixThreadedNode(HeroNode node) { //node就是当前需要线索化的节点
        if (node == null)
            return;

        //(一) 线索化左子树
        infixThreadedNode(node.getLeft());

        //(二) 线索化当前节点【较难，注意理解,我理解啦~~】
        //处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //当前节点的左指针指向前驱节点
            node.setLeft(pre); //以8来理解，此时pre=null
            //修改当前节点的左指针的类型
            node.setLeftType(1);
        }
        //处理当前节点的后继节点，也就是当前节点往后走到下一个节点node2,此时原来的node就是pre了
        if (pre != null && pre.getRight() == null) { //用下一次来处理上一回不好完成的
            //前驱节点的右指针指向当前节点
            pre.setRight(node);
            //修改当前节点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;

        //(三) 线索化右子树
        infixThreadedNode(node.getRight());
    }

    //自己编写二叉树进行前序线索化
    public void preThreadedNode(HeroNode node) {
        if (node == null)
            return;
        //处理当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //向左递归
        //todo 注意与中序的区别，必须加if语句!!!
        if (node.getLeftType() == 0) //如果没有此判断，则8的左指针指向3，就一直在循环了
            preThreadedNode(node.getLeft());
        //向右递归
        if (node.getRightType() == 0) //如果没有此判断，则弄14时会将6的右指针指向14,则处理6时就又指回了14
            preThreadedNode(node.getRight());
    }

    //自己编写二叉树进行后序线索化
    //由于老师没有讲，我就自以为是的这么写了，感觉没错，但如果要遍历的话，就不好做了；不过的确是这么写的
    // 实际上，后序线索化比前序和中序都要复杂，虽然的确是这么写的，但创建二叉树时必须保存一下父节点，这样才能遍历啊详情参考 https://blog.51cto.com/yushiwh/2439518
    public void postThreadedNode(HeroNode node) {
        if (node == null)
            return;
        postThreadedNode(node.getLeft());
        postThreadedNode(node.getRight());
        if (node == null)
            return;
        //处理当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

    }

    //遍历线索化中序二叉树[有点难，主要是明白思路，再自己debug一遍]
    public void infixThreadedList() {
        //定义一个变量，存储当前遍历的节点
        HeroNode node = root;
        while (node != null) {
            //1、找到第一个leftType==1的节点，即第一个线索化的节点(即树的最后一层的最左边的节点)
            //注意这个while循环之所以在while循环之内的原因：如先14再16
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //2、打印当前节点
            System.out.println(node);
            //3、若当前节点的右指针指向的是后继节点，就一直输出【要一直输出的原因：考虑没有10节点，则3的右指针指向1，就会用到循环，从8一直走到1】
            while (node.getRightType() == 1) {
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }//此循环结束时说明当前节点的右指针指向的是右子树节点
            //4、代表右子树的节点
            node = node.getRight();

        }
    }

    //自己编写的遍历前序线索化二叉树，老师讲了遍历线索化中序二叉树的思路，此方法是复习时自己 根据那种思路，自己想了类似的思路，从而写的，感觉比较正确，但也不确定一定是对的
    //注：按照ThreadedBinaryTree.PNG画了前序的图，看着写的
    public void preThreadedList() {
        HeroNode node = root;
        while (node != null) {
            System.out.println(node);
            while (node.getLeftType() == 0) { //向左走
                node = node.getLeft();
                System.out.println(node);
            }
            //网上的写法根本就没有下面的while循环
//            while (node.getRightType() == 1) { //向后续的节点走
//                node = node.getRight();
//                System.out.println(node);
//            }
            node = node.getRight(); //右子树节点

        }
    }

    //自己写的遍历后序线索化二叉树
    //注：按照ThreadedBinaryTree.PNG画了后序的图，并参考着父节点，看着写的
    //可以只看下面的网上版本
    public void postThreadedList() {
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node); //14
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            if (node.getParent() != null) {
                if (node != node.getParent().getRight()){ //没：到了根节点的右节点再往上走
                    node = node.getParent().getRight();
                } else {
                    System.out.println(node.getParent());
                    break;
                }
            } else {
                break;
            }
        }
    }

    //遍历后序线索化二叉树网上版（https://blog.51cto.com/yushiwh/2439518）,根据图比较好理解
    public void postThreadListOnline(){
        HeroNode node =root;
        //找到最开始的节点，即最左边的节点
        while (node!=null&&node.getLeftType()==0){
            node=node.getLeft();
        }
        while (node!=null){
            //如果往右是后继节点
            if (node.getRightType()==1){
                System.out.println(node);
                pre=node; //记录前面的节点，以后用来判断是否要向上找父节点
                node=node.getRight();
            }else { //如果是右子树
                //如果当前小三角已经走完，则寻找父节点
                if (node.getRight()==pre){
                    System.out.println(node);
                    if (node==root){
                        return;
                    }
                    pre=node;
                    node=node.getParent();
                }else { //从新节点重新开始
                    node=node.getRight();
                    while (node!=null&&node.getLeftType()==0){
                        node=node.getLeft();
                    }
                }
            }
        }
    }

//*以上是针对线索化二叉树的方法，下面的都是以前的（复制过来的）**************************************************************************************************************

    //前序遍历
    public void preOrder() {
        if (this.root != null)
            this.root.preOrder();
        else
            System.out.println("二叉树为空，不能遍历哦~~");
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null)
            this.root.infix0rder();
        else
            System.out.println("二叉树为空，不能遍历哦~~");
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null)
            this.root.postOrder();
        else
            System.out.println("二叉树为空，不能遍历哦~~");
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null)
            return this.root.preOrderSearch(no);
        else
            return null;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null)
            return this.root.infixOrderSearch(no);
        else
            return null;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null)
            return this.root.postOrderSearch(no);
        else
            return null;
    }

    //删除节点(我写的)
    public boolean delNode(int no) {
        if (root != null) {
            if (this.root.getNo() == no) {
                root = null;
                return true;
            } else
                return this.root.delNode(no);
        } else
            System.out.println("空树欸~~你删你删你删啊");
        return false;
    }

    //删除节点改进版(我写的)
    public boolean delNode2(int no) {
        if (root != null) {
            if (this.root.getNo() == no) { //对根节点单独处理
                if (this.root.getLeft() != null) {
                    this.root = this.root.getLeft();
                } else {
                    if (this.root.getRight() != null) {
                        this.root = this.root.getRight();
                    } else
                        root = null;
                }
                return true;
            } else
                return this.root.delNode2(no);
        } else
            System.out.println("空树欸~~你删你删你删啊");
        return false;
    }

    //删除节点TV
    public void delNodeTV(int no) {
        if (root != null) {
            if (this.root.getNo() == no) {
                this.root = null;
            } else this.root.delNodeTV(no);
        } else {
            System.out.println("空树，不能删除~~~");
        }
    }
}


//用的就是BinaryTreeDemo中的HeroNode,在此基础上修改
class HeroNode {
    private static int count = 0;
    private int no;
    private String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认为null
    //todo 为了后序线索化专门准备的
    private HeroNode parent; //默认为null
    //todo 为了区分是子树还是节点，要有个东西来解释
    //若leftType==0，表示指向的是左子树，leftType==1表示指向前驱节点
    private int leftType = 0;
    //若rightType==0，表示指向的是右子树，rightType==1表示指向后驱节点
    private int rightType = 0;

    public HeroNode getParent() {
        return parent;
    }

    public void setParent(HeroNode parent) {
        this.parent = parent;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点.我是这么写的，感觉比下面老师的好，因为我认为它有点问题[思路一样，但老师没有判断是否已经删除了]
    public boolean delNode(int no) {
        //注：对root节点的处理放在调用该方法的方法里
        boolean flag = false;
        if (this.left != null) {
            if (this.left.no == no) {
                this.left = null;
                flag = true;
                return flag;
            } else {
                flag = this.left.delNode(no);
            }
        }
        if (flag == false) {
            if (this.right != null) {
                if (this.right.no == no) {
                    this.right = null;
                    flag = true;
                    return flag;
                } else
                    flag = this.right.delNode(no);
            }
        }
        return flag;
    }

    //递归删除节点tv版
    public void delNodeTV(int no) {
        //如果当前节点的左节点不为空，并且左节点就是要删除的节点，就将this.left=null；并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        //如果当前节点的右节点不为空，并且右节点就是要删除的节点，就将this.right=null;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //以上都没找到，就向左子树进行递归删除
        if (this.left != null)
            this.left.delNodeTV(no);
        //以上都没找到，就向右子树进行递归删除
        // [我觉得这里有个问题：没有判断是否以上是否找到。如果在上面那个if左递归中找到并删除了，应该停止，而不是还往右找啊，比如2的左节点还有个6]
        if (this.right != null)
            this.right.delNodeTV(no);
    }

    //删除改进版：若待删除节点A只有一个子节点B，用B代替A，若有左右两个节点B和C，用B代替A【此处C也被删除了】
    public boolean delNode2(int no) {
        //注：对root节点的处理放在调用该方法的方法里
        boolean flag = false;
        if (this.left != null) {
            if (this.left.no == no) {
                if (this.left.left == null) { //左节点的左节点为空
                    if (this.left.right == null) { //若左节点的右节点也为空，直接删除
                        this.left = null;
                    } else { //若左节点的右节点不为空
                        this.left = this.left.right;
                    }
                } else { //左节点的左节点不为空，则直接用用该左节点顶替
                    this.left = this.left.left;
                }
                //只要找到了要删除的节点，下面两步就要就行，不管谁顶替了谁
                flag = true;
                return flag;
            } else {
                flag = this.left.delNode2(no);
            }
        }
        if (flag == false) {
            if (this.right != null) {
                if (this.right.no == no) {
                    if (this.right.left == null) { //右节点的左节点为空
                        if (this.right.right == null) { //若右节点的右节点也为空，直接删除
                            this.left = null;
                        } else { //若右节点的右节点不为空
                            this.right = this.right.right;
                        }
                    } else { //右节点的左节点不为空，则直接用用该左节点顶替
                        this.right = this.right.left;
                    }
                    flag = true;
                    return flag;
                } else
                    flag = this.right.delNode2(no);
            }
        }
        return flag;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }

    //中序遍历
    public void infix0rder() {
        if (this.left != null)
            this.left.infix0rder();
        System.out.println(this);
        if (this.right != null)
            this.right.infix0rder();
    }

    //后序
    public void postOrder() {  //2431
        if (this.left != null)
            this.left.postOrder();
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }


    //前序遍历查找：找到一个就返回
    public HeroNode preOrderSearch(int no) {
        count++;
        if (this.no == no)
            return this;
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { //说明在左边找到了
            return resNode;
        } else {
            if (this.right != null) {
                resNode = this.right.preOrderSearch(no);
            }
        }
        return resNode;

    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        count++; //todo 注意count的位置，和前序查找不一样；例如：若还放在最开始，找5时，当找过1、2进入3时，count加1了，可实际上并没有检查3啊，要检查的是3的左边；故真正的检查是看它是否进了this.no==no
        if (this.no == no)
            return this;
        if (this.right != null)
            resNode = this.right.infixOrderSearch(no);
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null)
            return resNode;
        if (this.right != null)
            resNode = this.right.postOrderSearch(no);
        if (resNode != null)
            return resNode;
        count++; //注意位置
        if (this.no == no)
            resNode = this; //注意这里不能直接return this，因为最后返回的是resNode,若找根节点，直接return this 就错了
        return resNode;
    }
}

