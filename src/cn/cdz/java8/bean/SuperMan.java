package cn.cdz.java8.bean;

/**
 * User: Cdz
 * Date: 2017/8/3
 * Time: 20:22
 */
public class SuperMan extends Man {
    public SuperMan() {
        this.speak();
    }

    @Override
    public void speak() {
        System.out.println("i'm a superman");
    }
}
