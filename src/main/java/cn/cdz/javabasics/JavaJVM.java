package cn.cdz.javabasics;

/**
 * Created by CDz_ on 2017/11/23.
 */
public class JavaJVM {
    static {
        System.out.print(1);
    }
    {
        System.out.print(3);
    }

    public JavaJVM() {
        System.out.print(2);
    }

    static class JavaJVM2 extends JavaJVM {

        static {
            System.out.print("a");
        }
        {
            System.out.print("c");
        }

        public JavaJVM2() {
            System.out.print("b");
        }
    }

    public static void main(String[] args) {
        JavaJVM javaJVM = new JavaJVM2();
        System.out.println("-------------------");
        javaJVM = new JavaJVM2();
    }
}
