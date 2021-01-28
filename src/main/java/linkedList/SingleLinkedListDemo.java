package linkedList;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试，先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(5, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(7, "林冲", "豹子头");
        HeroNode hhero1 = new HeroNode(2, "宋江", "及时雨");
        HeroNode hhero2 = new HeroNode(4, "卢俊义", "玉麒麟");
        HeroNode hhero3 = new HeroNode(6, "吴用", "智多星");
        HeroNode hhero4 = new HeroNode(8, "林冲", "豹子头");
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero4);
//        singleLinkedList.list();
//        System.out.println("***测试添加节点-我的方法*****************************************************************");
//        SingleLinkedList s = new SingleLinkedList();
//        s.addSequence(hero2);
//        s.addSequence(hero4);
//        s.addSequence(hero3);
//        s.addSequence(hero1);
//        s.addSequence(hero3);
//        s.list();
        System.out.println("****按顺序添加节点-老师的方法***************************************************************");
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero2);
        linkedList.addByOrder(hero3);
        linkedList.addByOrder(hero4);
        linkedList.list();

        //测试对节点的修改
//        System.out.println("******测试对节点的修改**********************************************");
//        HeroNode heroNode1 = new HeroNode(3, "小无", "无双");
//        HeroNode heroNode2 = new HeroNode(4, "whatever", "Never Mind");
//        linkedList.update(heroNode1);
//        linkedList.update(heroNode2);
//        linkedList.list();
//        System.out.println("链表长度为："+linkedList.size());

//        //测试对节点的删除
//        System.out.println("******测试对节点的删除*****************************************************");
//        linkedList.delete(4);
//        linkedList.delete(3);
//        System.out.println(linkedList.size());
//        linkedList.delete(1);
//        linkedList.delete(2);
//        linkedList.list();
//        System.out.println("*****测试面试题四*********************************************");
//        linkedList.showReverseList();
        System.out.println("****************第二个链表*****************************************");
        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.addByOrder(hhero1);
        linkedList2.addByOrder(hhero2);
        linkedList2.addByOrder(hhero3);
        linkedList2.addByOrder(hhero4);
        linkedList2.list();

//        System.out.println("*****测试面试题五*************************************************");
////        linkedList.mergeList(linkedList, linkedList2);
//        linkedList.mergeLists(linkedList, linkedList2);

//        System.out.println("*****测试面试题二【我】*************************************************");
//        try {
//            HeroNode descNode = linkedList.findDescNode(6);
//            System.out.println(descNode);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("********测试面试题二【老师】**********************************");
//        System.out.println(linkedList.getLastIndexNode(4));
//        System.out.println("*****测试面试题三*********************************************");
//        linkedList.reverseList();
//        linkedList.reverse();
//        linkedList.reverseTV();
//        linkedList.list();
//        System.out.println("*********测试面试题四-方法一-栈*************************************************************");
//        linkedList.adverseListByStack();

    }
}

/**
 * 定义一个管理英雄节点的类
 */
class SingleLinkedList {
    //先初始化一个头节点，头节点不要变
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点到单向链表的最后（这种插入方式）
     * 思路(当不考虑编号顺序时):
     * 1. 找到当前链表的最后一个节点
     * 2. 将该节点的next指向新的节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，故需要一个辅助变量tempNode
        HeroNode tempNode = head;
        //遍历链表，找到最后
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }//结束循环时，tempNode节点指向链表的最后
        tempNode.next = heroNode;

    }

    /**
     * 根据排名将英雄插入到适当的节点
     * 如果有这个排名，则添加失败，并给出提示
     * 方法二：老师的方法
     *
     * @param
     */
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;//判断要添加的排名是否已经存在
        while (true) {
            if (temp.next == null) {//走到了链表最后
                break;
            }
            if (temp.next.no == node.no) {//说明存在相同的排名
                flag = true;
                break;
            } else if (temp.next.no > node.no) {//找到啦，要插在temp后面
                break;
            }
            //以上情况都没有，则继续往下找
            temp = temp.next;
        }
        //循环后进行添加操作
        if (flag) {
            System.out.printf("准备添加的英雄%d已经有啦\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }

    }

    /**
     * 根据排名将英雄插入到适当的节点
     * 如果有这个排名，则添加失败，并给出提示
     * 方法一：我写的,但好像有个奇怪的问题。。。将11-17行的注释撤销的话，会多出来一句话
     *
     * @param addNode
     */
    public void addSequence(HeroNode addNode) {
        //temp是辅助变量，且是待添加变量的前一个节点
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = addNode;
                break;
            } else {
                if (temp.next.no < addNode.no) {
                    temp = temp.next;
                } else if (temp.next.no == addNode.no) {
                    System.out.printf("已经有%d啦，该排名不适合吧~~~\n", addNode.no);
                    break;
                } else {
                    addNode.next = temp.next;
                    temp.next = addNode;
                    break;
                }
            }
        }

    }

    /**
     * 按英雄的排名修改某一节点，排名不改变
     *
     * @param newNode
     */
    public void update(HeroNode newNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;//判断是否找到该排名
        while (true) {
            if (temp == null) {//走完链表[也可以判断链表为空呀，我认为]
                break;
            }
            if (temp.no == newNode.no) {//找到该排名所在的节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else System.out.printf("没有找到%d排名的英雄啊\n", newNode.no);
    }

    /**
     * 根据排名删除某一节点
     */
    public void delete(int rank) {
        HeroNode temp = head;
        boolean flag = false;//判断待删除节点的前一个节点时候存在
        while (true) {
            if (temp.next == null) {//遍历完链表
                break;
            }
            if (temp.next.no == rank) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到排名为%d的英雄", rank);
        }

    }

    //显示链表[遍历]
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode tempNode = head.next;
        //我是这么写的，与下方的一致
//        while (tempNode != null) {
//            System.out.println(tempNode.toString());
//            tempNode = tempNode.next;
//        }

        while (true) {
            if (tempNode == null) break;
            System.out.println(tempNode);
            tempNode = tempNode.next;
        }


    }

    //面试题一：求单链表中有效节点的个数(如果是带头节点的链表，要求不包括头节点)
    public int size() {
        int count = 0;
        HeroNode temp = head;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        return count;

    }

    //面试题二：查找单链表中的倒数第k个节点
    //我的方法
    public HeroNode findDescNode(int k) {
        int ascCount = size() - k + 1;
        int count = 0;
        if (size() >= ascCount && ascCount >= 0) {
            HeroNode temp = head;
            for (int i = 0; i < ascCount; i++) {
                temp = temp.next;
            }
            return temp;
        } else {
            throw new RuntimeException("不存在你要找的节点哇~~~");
        }
    }

    //面试题二：
    //老师的方法：思路基本一致，但他们一般是先排除所有不符合的情况,将其设置为空
    public HeroNode getLastIndexNode(int index) {
        int size = size();
        if (index <= 0 || index > size) return null;
        if (head.next == null) return null;
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //面试题三：单链表的反转
    //我的方法，虽然可用，但过于复杂，不用再看了
    public void reverseList() {
        HeroNode tp = null;//保存最后一个节点
        HeroNode temp;
        int size = size();
        for (int i = size; i > 0; i--) {
            temp = head;
            if (i == 1) {//第一个节点的next指向空
                temp.next.next = null;
                break;
            }
            for (int j = 0; j < i - 1; j++) {//找到最后一个节点的前一个节点
                temp = temp.next;
            }
            if (i == size) tp = temp.next;//将最后一个节点赋值给临时变量tp保存

            temp.next.next = temp;

        }
        head.next = tp;
    }

    //面试题三：
    //老师的思路：新定义一个节点；从头到尾遍历原来的链表，每遍历一个节点，就将其从原链表中取出并放到新链表的最前端（修改新加入节点的下一个节点和头节点的下一个节点）
    //根据思路自己先写的
    public void reverse() {
        HeroNode heroNode = new HeroNode(0, "", "");
        HeroNode temp;
        while ((temp = head.next) != null) {//temp为原链表的第一个节点，是不断改变的
            //修改原链表
            head.next = temp.next;//头节点指向下一个节点(我的想法)
            //修改新链表
            temp.next = heroNode.next;//新节点（原链表的第一个节点）指向新头节点的下一个节点（即使是空的也没关系，正好最后一个节点【即原链表最开始的第一个节点】要指向空节点）
            heroNode.next = temp;//新链表的头节点指向新的节点
        }
        head.next = heroNode.next;
        list();//展示反转后的新链表
    }

    //面试题三：老师的思路和版本TV=teacher version 【这个方法感觉比我的好，虽然也差不多吧】
    public void reverseTV() {
        HeroNode cur = head.next;
        HeroNode next;
        HeroNode heroNode = new HeroNode(0, "", "");
        //如果为空或只有一个节点，则不用反转
        if (cur == null || cur.next == null) return;
        while (cur != null) { //注意以下四个的顺序啊
            next = cur.next;
            cur.next = heroNode.next;
            heroNode.next = cur;
            cur = next;
        }
        head.next = heroNode.next;
//        list();
    }

    //面试题四：从尾到头打印单链表（1. 反向遍历 2. Stack栈）
    //我的想法：用reverseTV()反转链表，再打印 ---------- 会破坏链表的结构，不建议使用该方式
    //方式二：栈

    public void showReverseList() {
        reverseList();
        list();
    }

    public void adverseListByStack() {
        if (head.next == null) return;//空链表不打印
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0)
            System.out.println(stack.pop());
    }

    //面试题五(老师未讲)：合并两个有序的单链表，合并之后的链表依然有序
    //我的想法：主要是根据面试题三reverseTV()和有序加入addByOrder()的方法写的
    //将list1中的一个个拿出来与list2的比较，插入到list2中
    public void mergeList(SingleLinkedList list1, SingleLinkedList list2) {
//        boolean flag = false;
        HeroNode temp1 = list1.head.next;
        HeroNode temp2 = list2.head;
        HeroNode temp;
        while (temp1 != null) {
            while (true) {
                if (temp2.next == null) break;
                if (temp2.next.no >= temp1.no) break;
                temp2 = temp2.next;
            }
            temp = temp1.next;

            temp1.next = temp2.next;
            temp2.next = temp1;

            temp1 = temp;
        }
        System.out.println("*************list2");
        list2.list();
        System.out.println("*************list1");
        list1.list();
        //注：list1=list2
    }

    //面试题五：老师的思路：比较两链表中谁更小，将更小的放到新建的链表中
    //根据老师的思路我的写法：使链表1和2原有的结构都改变了，不知道有没有问题
    public void mergeLists(SingleLinkedList list1, SingleLinkedList list2) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HeroNode heroNode = singleLinkedList.getHead();
        HeroNode temp1 = list1.head;
        HeroNode temp2 = list2.head;
        HeroNode temp3 = heroNode;
        HeroNode temp;
        while (temp1 != null && temp2 != null) {
            if (temp1.no <= temp2.no) {
                temp = temp1.next;//存储链表一的下一个节点

                temp3.next = temp1;//修改新链表
                temp3 = temp3.next;//新链表往前走

                temp1 = temp;//恢复链表一的下一个节点
            } else {//同上，此处no2更小
                temp = temp2.next;

                temp3.next = temp2;
                temp3 = temp3.next;

                temp2 = temp;
            }
        }
        if (temp1 == null) {
            temp3.next = temp2;
        }
        if (temp2 == null) {
            temp3.next = temp1;
        }
        singleLinkedList.list();
        System.out.println("**list1******");
        list1.list();
        System.out.println("**list2******");
        list2.list();
    }

    public void addByOrder2(HeroNode node){
        HeroNode cur = head.next;
        boolean flag =false;
        while (true){
            if (cur==null){
                break;
            }
            if (cur.getNo()==node.getNo()){
                System.out.println();
                return;
            }
            if (cur.getNo()>node.getNo()){
                flag=true;
                break;
            }
            cur=cur.next;
        }
        if (flag){
            node.next=cur.next;
            cur.next=node;
        }else {

        }
    }

}


class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


//    @Override
//    public String toString() {
//        return "HeroNode{" +
//                "no=" + no +
//                ", name='" + name + '\'' +
//                ", nickName='" + nickName +
//                "'}";
//    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", next=" + next +
                '}';
    }


//
//    @Override
//    public String toString() {
//        return "HeroNode{" +
//                "no=" + no +
//                ", name='" + name + '\'' +
//                ", nickName='" + nickName + '\'' +
//                ", next=" + next +
//                '}';
//    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}