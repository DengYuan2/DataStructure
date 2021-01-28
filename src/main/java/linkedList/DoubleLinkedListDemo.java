package linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        System.out.println("测试双向链表的基本方法");
        //创建节点
        HeroNodeDouble hero1 = new HeroNodeDouble(1, "", "");
        HeroNodeDouble hero2 = new HeroNodeDouble(2, "", "");
        HeroNodeDouble hero3 = new HeroNodeDouble(3, "", "");
        HeroNodeDouble hero4 = new HeroNodeDouble(4, "", "");
        HeroNodeDouble hero5 = new HeroNodeDouble(5, "", "");
        HeroNodeDouble hero6 = new HeroNodeDouble(6, "", "");
        HeroNodeDouble hero7 = new HeroNodeDouble(7, "", "");
        HeroNodeDouble hero8 = new HeroNodeDouble(8, "", "");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //增加
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        //修改
        HeroNodeDouble nodeDouble = new HeroNodeDouble(3, "J", "");
        doubleLinkedList.update(nodeDouble);
        System.out.println("修改后的链表");
        doubleLinkedList.list();
        //删除
        doubleLinkedList.del(2);
        System.out.println("删除后的链表");
        doubleLinkedList.list();
        //按顺序增加
        DoubleLinkedList list = new DoubleLinkedList();
        System.out.println("按顺序增加的链表");
        list.addByOrder(hero6);
        list.addByOrder(hero5);
        list.addByOrder(hero8);
        list.addByOrder(hero7);
        list.list();
        list.addByOrder(hero7);
        list.list();
    }
}

class DoubleLinkedList {
    //初始化一个头节点
    private HeroNodeDouble head = new HeroNodeDouble(0, "", "");

    public HeroNodeDouble getHead() {
        return head;
    }

    //遍历双向链表[与单项链表相同]
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNodeDouble node = head.next;
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
//        与上面的一致
//        while(true){
//            if (node==null) break;
//            System.out.println(node);
//            node=node.next;
//
//        }

    }

    //添加到双向链表的尾部
    public void add(HeroNodeDouble nodeDouble) {
        HeroNodeDouble tmp = head;
        while (true) {
            if (tmp.next == null) break;
            tmp = tmp.next;
        }
        tmp.next = nodeDouble;
        nodeDouble.pre = tmp;
    }

    //添加的第二种方式:按编号顺序添加
    public void addByOrder(HeroNodeDouble nodeDouble) {
        //链表为空时
        if (head.next == null) {
            head.next = nodeDouble;
            nodeDouble.pre = head;
            return;
        }

        HeroNodeDouble temp = head;
        boolean flag = false;

        while (true) {
            if (temp.next == null)//走到最后一个节点了
                break;
            if (temp.next.no > nodeDouble.no)
                break;

            if (temp.next.no==nodeDouble.no) {
                flag=true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备添加的英雄%d已经有啦\n", nodeDouble.no);
        } else {
            if (temp.next==null){//放到最后一个节点后面
                temp.next=nodeDouble;
                nodeDouble.pre=temp;
            }else{
                temp.next.pre = nodeDouble;
                nodeDouble.next = temp.next;
                nodeDouble.pre = temp;
                temp.next = nodeDouble;
            }


        }

    }

    //修改链表某一节点的内容，no不做改动[同单项链表一致]
    public void update(HeroNodeDouble nodeDouble) {
        HeroNodeDouble temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) break;
            if (temp.no == nodeDouble.no) {
                flag = true;
                break;
            }
            temp = temp.next;

        }
        if (flag) {
            temp.name = nodeDouble.name;
            temp.nickname = nodeDouble.nickname;
        } else
            System.out.printf("没有找到排名为%d的英雄~~", nodeDouble.no);
    }

    //删除某一节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNodeDouble temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //todo 注意：可能删除的使最后一个节点，故只有temp.next.pre=temp.pre;是不对的，会有空指针异常，要加上判断语句
            if (temp.next != null)
                temp.next.pre = temp.pre;
        } else
            System.out.printf("没有找到排名为%d的英雄~~", no);
    }
}

class HeroNodeDouble {
    public int no;
    public String name;
    public String nickname;
    public HeroNodeDouble next;//指向下一个节点，默认为null
    public HeroNodeDouble pre;//指向前一个节点，默认为null

    public HeroNodeDouble(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNodeDouble{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname
                + '\'' + '}';
    }
}
