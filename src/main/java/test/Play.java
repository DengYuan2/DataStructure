package test;

import jdk.nashorn.internal.runtime.linker.BoundCallable;

interface Play {
    void play();
}

interface Bounce {
    void play();
}

//接口与接口之间可以继承，而且可以多继承
interface Roll extends Play, Bounce {
    Ball ball = new Ball("PingPong");
}


class Ball implements Roll {
    private String name;

    public String getName() {
        return name;
    }

    public Ball(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println(ball.getName());
    }



}

