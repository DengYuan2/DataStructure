package linkedList;

/**
 * Josephu问题：用单向循环链表实现
 * 约瑟夫问题：设编号为1，2，...n的n个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始
 * 报数，数到m的那个人出列，他的下一位又从1开始报数，数到m的那个人又出列，以此类推，
 * 直到所有人都出列为止，由此产生一个出队编号的序列
 * <p>
 * 构建一个单项环形链表思路：
 * 1、先创建一个节点，让first指向该节点，并形成环形（自己指向自己）
 * 2、后面当我们每创建一个新的节点，就将新节点的next指向first,原来最后一个节点的next指向该新节点
 */
public class CircleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.createList(25);
//        circleLinkedList.showList();
//        System.out.println(circleLinkedList.getLength());
        System.out.println("约瑟夫问题-我的方法");
        circleLinkedList.solveProblem(2,6);
        System.out.println("约瑟夫问题——老师的写法");
        circleLinkedList.solveTV(2,6,25);
    }
}

class CircleLinkedList {
    private Node first = null;

    public Node getFirst() {
        return first;
    }

    //构建环形链表
    public void createList(int num) { //num代表环形链表的节点的个数
        if (num < 1) {//数据校验，要求不能少于一个节点
            System.out.println("num的值不正确");
            return;
        }
        Node cur = null;//辅助指针，帮助构建环形链表
        for (int i = 1; i <= num; i++) {
            Node node = new Node(i);
            if (i == 1) {
                first = node;
                first.setNext(first);
                cur = first;
            } else {
                cur.setNext(node);
                node.setNext(first);
                cur = node;
            }

        }

    }

    //遍历当前的环形链表
    public void showList() {
        if (first == null) {
            System.out.println("当前链表为空");
            return;
        }
        Node cur = first;
        while (true) {
            System.out.printf("当前节点的编号为%d\n", cur.getNo());
            if (cur.getNext() == first) break;//遍历完毕
            cur = cur.getNext();
        }

    }

    //链表中节点个数
    public int getLength() {
        if (first == null) {
            System.out.println("当前链表为空");
            return 0;
        }
        Node cur = first;
        int count = 0;
        while (true) {
            count++;
            if (cur.getNext() == first) break;//遍历完毕
            cur = cur.getNext();
        }
        return count;
    }

    //思路：将每轮数1的那个人设为first，他的前一个人设为helper;当现在的人数（第三声）到m,则将他的下一个人设为first,helper的下一个为新的first；当只剩下一个人时停止
    //我的写法：
    public void solveProblem(int k, int m) {//k=1,m=2:2 4  1 5 3   m=3时:3 1 5 2 4  //k=2,m=2:3 5 2 1 4  m=3:4 2 1 3 5  //k=2,m=6  2 4 3 1 5
        int length = getLength();
        //校验
        if (k <= 0 || k > length) {//1 5
            System.out.println("这个开始的位置不合适吧~~");
            return;
        }
        if (m==1) showList();

        Node cur = first;
        Node helper = null;
        //先找到第k个，设为first,并将前一个设为helper
        if (k == 1) {//如果k=1,helper在最后一个
            while (true) {
                cur = cur.getNext();
                if (cur.getNext() == first) break;
            }
            helper = cur;
        }else {//k≠1时，定位好first和helper
            for (int i = 1; i < k; i++) {
                helper = cur;
                cur = cur.getNext();
            }
            first = cur;
        }

        while (true) {
            if (first == helper) { //只留下最后一个时
                System.out.println(first.getNo());
                break;
            }

            for (int i = 1; i < m; i++) {
                helper = first;
                first = first.getNext();
            }
            System.out.println(first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }


    }

    //老师的写法://反思：一样的思路，老师的写法更普遍化，没有区分k是否为1，而且省略了cur这个变量
    public void solveTV(int startNo,int countNum,int nums){
        createList(nums);
        //数据校验
        if (first==null||startNo<1||startNo>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        Node helper = first;
        //不管k是多少，都可以找到helper
        while (true){
            if (helper.getNext()==first) break;
            helper=helper.getNext();
        }
        //确定初始的first和helper
        for (int i = 1; i < startNo; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        while(true){
            if (helper==first) break;
            for (int i = 1; i < countNum; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.printf("出圈的节点编号为%d\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈内的节点编号为%d\n",first.getNo());
    }

}

class Node {
    private int no;
    private Node next;//指向下一个节点,默认为null

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}