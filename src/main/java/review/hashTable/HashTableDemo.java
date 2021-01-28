package review.hashTable;


import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
//测试
        HashTable hashTab = new HashTable(7);
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
                    //创建雇员
                    Emp emp = new Emp(id);
                    hashTab.addByOrder(emp);
                    break;
                case "f":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "l":
                    hashTab.showList();
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

class Emp {
    public int no;
    public Emp next;

    public Emp(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "no=" + no +
                '}';
    }
}

class EmpLinkedList {
    Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            } else {
                temp = temp.next;
            }
        }
        temp.next = emp;
    }

    public void addByOrder(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        //单独处理头节点
        if (head.no > emp.no) {
            emp.next = head;
            head = emp;
            return;
        }
        if (head.no == emp.no) {
            System.out.println("已经有该节点啦");
            return;
        }
        Emp temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) { //已经走到最后
                flag = true;
                break;
            }
            if (temp.next.no == emp.no) {
                break;
            } else if (temp.next.no > emp.no) { //找到合适的位置了
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag) {
            emp.next = temp.next;
            temp.next = emp;
            System.out.println("已经插入到合适的位置啦");
        } else {
            System.out.println("该编号的雇员已经存在，请查看她/他的身份");
        }
    }

    public void list(int no) {
        System.out.println("第"+(no+1)+"个链表内容为：");
        if (head == null) {
            System.out.println("链表为空啊~~");
            return;
        }
        Emp temp = head;
        while (true) {
            System.out.println(temp);
            temp = temp.next;
            if (temp == null) {
                break;
            }
        }
    }

    public Emp findEmpById(int no) {
        if (head == null) {
//            System.out.println("链表为空，找不到任何人啊~~");
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void deleteEmpById(int no) {
        if (head == null) {
            System.out.println("链表为空，先给我个人吧~~");
            return;
        }
        if (head.no == no) {
            head = head.next;
            System.out.println("删除这第一个成员啦~~~");
            return;
        }
        Emp temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到该成员啊~~~");
                break;
            }
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                System.out.println("已删除该成员~~");
                break;
            }
            temp = temp.next;
        }
    }
}

class HashTable {
    int maxSize;
    EmpLinkedList[] lists;

    public HashTable(int maxSize) {
        this.maxSize=maxSize;
        lists = new EmpLinkedList[maxSize];
        for (int i = 0; i < maxSize; i++) {
            lists[i] = new EmpLinkedList();
        }
    }

    public int getHashCode(int no) {
        return no % maxSize;
    }

    public void add(Emp emp) {
        int index=getHashCode(emp.no);
        lists[index].add(emp);
    }
    
    public void addByOrder(Emp emp){
        int index=getHashCode(emp.no);
        lists[index].addByOrder(emp);
    }

    public void showList(){
        for (int i = 0; i < maxSize; i++) {
            lists[i].list(i);
        }
    }

    public void findEmpById(int no){
        int index = getHashCode(no);
        Emp emp = lists[index].findEmpById(no);
        if (emp==null){
            System.out.println("没有找到该雇员哦");
        }else {
            System.out.println("在第"+(index+1)+"个链表中找到了id为"+no+"的雇员");
        }
    }
    
    public void deleteEmpById(int no){
        lists[getHashCode(no)].deleteEmpById(no);
    }
}