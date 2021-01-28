package test;

import java.io.ObjectInputStream;

//多态
public class Test5 {
    public static void main(String[] args) {
        Test5 test5 = new Test5();
        Cat cat = new Cat();
        test5.func(cat);
        System.out.println();
        Dog dog = new Dog();
        test5.func(dog);
        System.out.println();
        test5.func(new Animal());
        Animal animal=new Dog();
        Dog dog1= (Dog) animal;
        System.out.println("狗转化成功了");
        if (animal instanceof Dog){ //true
            System.out.println("前者是后者的实例哇");
        }
        //编译时通过，运行时报错！！！因为animal本质上是Dog
        //ClassCastException: test.Dog cannot be cast to test.Cat
        try {

            Cat cat1= (Cat) animal;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            System.out.println("猫转化成功了？no!");
        }

        System.out.println(animal instanceof Cat); //false
        System.out.println("***********************************");
        Dog dog2 = new Dog();
        Animal animal1=dog2;
        System.out.println(animal1.getClass()); //class test.Dog
        System.out.println(animal1 instanceof Animal); //true
        System.out.println(animal1 instanceof Dog); //true
        System.out.println("*************************************");
        Object o=new Cat();
        Animal animal2= (Animal) o; //编译通过，运行也通过
        System.out.println("编译通过，运行也通过");
        System.out.println("******************************");
        //！！！
        dog.show(); //调用的是dog的speak方法啊
    }

    public void func(Animal animal) {
        animal.eat();
        animal.speak();
        System.out.println(animal.a); //多态只适用于方法，对于属性，还是看父类的
    }
}

class Animal {
    int a = 3;

    public void eat() {
        System.out.println("动物会吃饭");
    }

    public void speak() {
        System.out.println("动物会发出声音");
    }

    public void show(){
        System.out.println(a);
        speak();
    }


    //看一下这几个方法算重载吗？
    //方法一和方法三、方法二和方法三构成重载
    //方法一和方法二不构成重载，虽然这两个方法可以编译，但使用时会报错啊，如get(1,1)根本无法编译
    //方法一：
//    public int get(int i,int ... ints){
//        return 1;
//    }
    //方法二：
    public int get(int ... ints){
        return 1;
    }
    //方法三：
    public int get(int i){
        return 1;
    }
}

class Dog extends Animal {
    int a =4;
    public void eat() {
        super.eat();
        System.out.println("狗吃骨头");
    }

    public void speak() {
        System.out.println("汪汪汪~~");
    }
}

class Cat extends Animal {
    int a =5;
    public void eat() {
        System.out.println("猫爱吃鱼");
    }

    public void speak() {
        System.out.println("喵喵喵~~");
    }
}