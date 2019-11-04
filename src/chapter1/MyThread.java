package chapter1;

/**
 * 子线程通过检查当前线程中断标志来控制是否退出循环，
 * 主线程在休眠 1s 后调用 thread的 interrupt() 方法设置了中断标志，
 * 所以线程退出了循环
 * */
public class MyThread {

    public static void main(String[] args) throws InterruptedException{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                // 如果当前线程被中断则退出循环
                while(!Thread.currentThread().isInterrupted()) {

                    System.out.println(Thread.currentThread() + " hello");
                }
            }
        });

        // 启动子线程
        thread.start();

        // 主线程休眠 1s，以便中断前让子线程输出
        Thread.sleep(1000);

        // 中断子线程
        System.out.println("main thread interrupt thread");
        thread.interrupt();

        // 等待子线程执行完毕
        thread.join();
        System.out.println("main is over");
    }
}
