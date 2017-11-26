package cn.cdz.java8;

import cn.cdz.java8.bean.Man;
import cn.cdz.java8.bean.SuperMan;
import cn.cdz.java8.utils.MyJava8Utiles;

/**
 * User: Cdz
 * Date: 2017/7/18
 * Time: 19:17
 */
public class Test {
    private static Test test = null;

    public static void main(String[] args) throws InterruptedException{
//
//        test = new Test();
//        testHelp();
//        testHelp();
//
//        Man superMan = new SuperMan();
//        System.out.println("-----------------------------");
//        superMan.speak();

        long l = MyJava8Utiles.forkJoinSum(11111);
        System.out.println(l);


    }

    public static void testHelp() throws InterruptedException{

        test = null ;//将其移除“关系网”

        System.gc();

        //等待1s，让优先级低的线程执行完
        Thread.sleep(1000);

        if (test==null){
            System.out.println("我挂啦");
        }else {
            System.out.println("我还活着");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        test = this;//将其重新加入关系网中
        System.out.println("我被调用啦");
    }
}
