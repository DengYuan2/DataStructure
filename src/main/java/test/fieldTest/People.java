package test.fieldTest;

/**
 * @author DengYuan2
 * @create 2021-01-23 10:43
 */
public class People {
    int age;

    public int getAge() {
        System.out.println("使用了父亲的age");
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
