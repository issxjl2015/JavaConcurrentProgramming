package chapter1;

public class ThreadTest {

    // 继承Thread类并重写run方法
    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {

        // 创建线程——创建完Thread对象后，该线程并没有启动执行
        MyThread myThread = new MyThread();

        // 启动线程——直到调用了 start 方法后才真正启动了线程
        myThread.start();
    }
}
