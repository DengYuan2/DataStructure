package test.fieldTest;

/**
 * @author DengYuan2
 * @create 2021-01-23 10:43
 */
public class Man extends People {
    int age;
    String name;

    public int getAge() {
        System.out.println("使用了自己的age");
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        System.out.println("使用了自己的name");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
