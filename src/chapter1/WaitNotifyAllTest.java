package chapter1;

public class WaitNotifyAllTest {

    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException{
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                    // 获取resourcesA 共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("tyhreadA get resourceA lock");
                        try {
                            System.out.println("threadA begin wait");
                            resourceA.wait();
                            System.out.println("threadA end wait");
                        } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }});

        // 创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (resourceA) {
                    System.out.println("threadB get resourcesA lock");
                    try {
                        System.out.println("threadB begin wait");
                        resourceA.wait();
                        System.out.println("threadB end wait");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // 创建线程
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadC begin notify");
                resourceA.notifyAll();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadC.start();

        // 等待两个线程结束
        threadA.join();
        threadB.join();
        threadC.join();
        
        System.out.println("main over");
    }
}
