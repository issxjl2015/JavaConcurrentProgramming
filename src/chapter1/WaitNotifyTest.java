package chapter1;

public class WaitNotifyTest {

    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException{
        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 获取resourcesA 共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("tyhreadA get resourceA lock");

                        synchronized (resourceB) {
                            System.out.println("resourcesA get resourceB lock");

                            // 线程A阻塞，并释放获取到resourcesA的锁
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建线程
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // 休眠一秒
                    Thread.sleep(1000);

                    // 获取resourceA 共享资源的监视器锁
                    synchronized (resourceA) {
                        System.out.println("threadB get resourcesA lock");

                        System.out.println("threadB try get resourcesB lock...");

                        // 获取resourceB 共享资源的监视器锁
                        synchronized (resourceB) {
                            System.out.println("threadB get resourceB lock");

                            // 线程B阻塞，并释放获取到的 resourceA 的锁
                            System.out.println("threadB release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待两个线程结束
        threadA.join();
        threadB.join();

        System.out.println("main over");
    }
}
