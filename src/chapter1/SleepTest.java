package chapter1;

/**
 * 子线程在睡眠期间，主线程中断了它，
 * 所以子线程在调用sleep方法处抛出了 InterruptionException 异常
 * */
public class SleepTest {

    public static void main(String[] args) {
        // 创建线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("child thread is in sleep");

                    Thread.sleep(1000);

                    System.out.println("child thread is in awaked");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        thread.start();

        // 主线程休眠 2s
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 主线程中断子线程
        thread.interrupt();
    }
}
