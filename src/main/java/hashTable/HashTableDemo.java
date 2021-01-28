package hashTable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        //测试
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("dele:删除雇员");
            System.out.println("exit:退出系统");
            key = scanner.next().toLowerCase().substring(0, 1);
            int id=0;
            switch (key) {
                case "a":
                    System.out.println("输入id");
                    id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.addByOrder(emp);
                    break;
                case "f":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "l":
                    hashTab.list();
                    break;
                case "d":
                    System.out.println("请输入要删除的id");
                    id=scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "e":
                    flag = false;
                    scanner.close();
                    break;
                default:
                    break;
            }

        }
    }
}

//创建哈希表，管理多条链表
class HashTab {
    private int size; //链表的个数
    private EmpLinkedList[] empLinkedListArray; //存放链表的数组

    //构造器,初始化数组
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[this.size]; //此时数组中每个的值都是null哦
        //初始化每个链表；若没有，使用下面add()时会报NullPointerException
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }

    }

    //添加雇员
    public void add(Emp emp) {
        //根据雇员id得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFunc(emp.id);
        //添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //添加雇员
    public void addByOrder(Emp emp){
        int empLinkedListNo = hashFunc(emp.id);
        empLinkedListArray[empLinkedListNo].addByOrder(emp);
    }

    //遍历hashTab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据id查找雇员
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表查找
        int empLinkedListNo = hashFunc(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d个链表中找到了id为%d的雇员", empLinkedListNo + 1, id);
            System.out.println();
        } else
            System.out.println("在哈希表中没有找到该雇员");
    }


    //删除员工
    public  void deleteEmpById(int id){
        int empLinkedListNo = hashFunc(id);
        empLinkedListArray[empLinkedListNo].deleteEmpById(id);
    }
    //编写散列函数[有多种]，此处使用简单的取模法
    public int hashFunc(int id) {
        return id % size;
    }


}

//雇员类，表示链表的节点
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//链表
class EmpLinkedList {
    //头指针，指向第一个Emp【head是有效的，是直接指向第一个雇员的】
    private Emp head;

    //添加雇员[假定，当添加雇员时，id自增长，即id的分配总是从小到大，因此将该雇员直接加到本链表的最后一个即可]
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //辅助指针，定位到最后节点
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //1 3  5
    //若没有按照id顺序添加雇员【自己写的，结合SingleLinkedListDemo类中的addByOrder方法】
    public void addByOrder(Emp emp){
        boolean flag=false;
        if (head==null) { //链表为空时
            head=emp;
            System.out.println("成功添加id为"+emp.id+"的雇员咯");
            return;
        }
        //单独处理头节点的情况
        if (emp.id<head.id){ //id比头节点的还小
            emp.next=head;
            head=emp;
            System.out.println("成功添加id为"+emp.id+"的雇员咯");
            return;
        }
        if (emp.id==head.id){
            System.out.println("已有id="+emp.id+"的雇员啦~~");
            return;
        }

        Emp curEmp = head;
        while (true){
            if (curEmp.next==null){ //遍历完了
                curEmp.next=emp;
                break;
            }
            if (emp.id==curEmp.next.id){ //找到相同id的雇员
                flag=true;
                break;
            }else if (emp.id<curEmp.next.id){
                emp.next=curEmp.next;
                curEmp.next=emp;
                break;
            }
            curEmp=curEmp.next;
        }
        if (flag){
            System.out.println("已有id="+emp.id+"的雇员啦~~");
        }else{
            System.out.println("成功添加id为"+emp.id+"的雇员咯");
        }
    }

    public void list(int no) { //加个编号参数，可以知道是哪个链表在遍历
        if (head == null) {
            System.out.println("第" + (no + 1) + "个链表为空哦~~");
            return;
        }
        System.out.print("第" + (no + 1) + "个链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null)
                break;
            curEmp = curEmp.next;
        }
        System.out.println();

    }

    //根据id查找雇员;找到则返回emp,没找到则返回null
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空啊~~");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id)
                break;
            if (curEmp.next == null) { //未找到
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    //删除雇员【删除相关的内容都是自己写的】
    public void deleteEmpById(int id) {
        if (head == null) {
            System.out.println("这里一个雇员都没有哦");
            return;
        }
        if (head.id == id) { //如果是开始节点，因为它之前没有节点，故要单独判断
            head = head.next;
            System.out.println("删除唯一的员工啦"); //？？？不一定是唯一的员工吧，我写的啥啊
            return;
        }else {
            Emp curEmp = head;
            while (curEmp.next!=null){
                if (curEmp.next.id==id){
                    curEmp.next=curEmp.next.next;
                    System.out.println("删除该员工啦");
                    return;
                }
                curEmp=curEmp.next;
            }
        }
        System.out.println("没有找到该员工欸~~~");
    }
}