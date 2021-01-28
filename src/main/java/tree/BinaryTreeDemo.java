package tree;


import java.awt.*;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode hero1 = new HeroNode(1, "宋江");
        HeroNode hero2 = new HeroNode(2, "吴用");
        HeroNode hero3 = new HeroNode(3, "卢俊义");
        HeroNode hero4 = new HeroNode(4, "林冲");
        HeroNode hero5 = new HeroNode(5, "关胜");
        HeroNode hero6 = new HeroNode(6, "魏邑尘"); //自己编的名字，别当真
//        HeroNode hero7 = new HeroNode(7, "魏邑尘");
//        HeroNode hero8 = new HeroNode(8, "魏邑尘");
//        HeroNode hero9 = new HeroNode(9, "魏邑尘");
//        HeroNode hero10 = new HeroNode(10, "魏邑尘");


        //先手动创建二叉树，后面会学习用递归方式创建二叉树
        binaryTree.setRoot(hero1);
        hero1.setLeft(hero2);
        hero1.setRight(hero3);
        hero3.setLeft(hero5);
        hero3.setRight(hero4);
        hero2.setLeft(hero6);
//        hero6.setLeft(hero7);
//        hero6.setRight(hero8);
//        hero5.setLeft(hero9);
//        hero4.setLeft(hero10);
//        System.out.println("前序遍历"); //12354
//        binaryTree.preOrder();
//        System.out.println("中序遍历");//21534
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");//25431
//        binaryTree.postOrder();
//        System.out.println("***************");
        //测试查找方法
//        HeroNode resNode = null;
//        System.out.println("***前序遍历查找****************");
//        resNode = binaryTree.preOrderSearch(15);
//        int preCount=HeroNode.getCount();
//        System.out.println("*查找次数为"+preCount);
//        if (resNode!=null){
//            System.out.printf("*找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
//        }else
//            System.out.println("*没有找到信息~~~");
//
//        System.out.println("***中序遍历查找****************");
//        resNode=binaryTree.infixOrderSearch(5);
//        int infixCount=HeroNode.getCount()-preCount; //因为我自己搞得count是全局变量，所以为了得到每次的count数，需要减去原来的
//        System.out.println("*查找次数为"+infixCount);
//        if (resNode!=null){
//            System.out.printf("*找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
//        }else {
//            System.out.printf("*没有找到信息~~~");
//        }
        System.out.println("***后序遍历查找****************");
        HeroNode resNode=binaryTree.postOrderSearch(1);
//        int postCount=HeroNode.getCount()-preCount-infixCount; //同上
//        System.out.println("*查找次数为"+postCount);
        if (resNode!=null){
            System.out.printf("*找到了，信息为no=%d name=%s",resNode.getNo(),resNode.getName());
        }else {
            System.out.printf("*没有找到信息~~~");
        }
        //我写的删除节点的测试
//        binaryTree.preOrder();
//        System.out.println();
//        boolean flag = binaryTree.delNode(6);
//        System.out.println(flag);
//        binaryTree.preOrder();
        //老师的删除节点的测试
//        binaryTree.preOrder();
//        System.out.println();
//        binaryTree.delNodeTV(6);
//        binaryTree.preOrder();
        //测试自己写的删除改进版
//        binaryTree.preOrder();
//        boolean isDel = binaryTree.delNode2(6);
//        System.out.println(isDel);
//        binaryTree.preOrder();

    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

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

class HeroNode {
    private int no;
    private static int count = 0;
    private String name;
    private HeroNode left; //默认为null
    private HeroNode right; //默认为null

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
//        count++;
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
