package chapter1;

/**
 * threadA 调用共享对象 obj 的 wait() 方法后阻塞挂起了自己，
 * 然后主线程休眠了 1s 后，中断了 threadA 线程，
 * 中断后，threadA 在 obj.wait() 处抛出 java.lang.InterruptException 异常而返回并终止
 *
 * Note: Thread.sleep(1000)居然有报错.
 * 解决方法：加一个捕捉中断异常的语句就OK了
 * */
public class WaitNotifyInterupt {

    static Object obj = new Object();

    public static void main(String[] args) {

        // 创建线程
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("----begin------");

                    // 阻塞当前线程
                    synchronized (obj) {
                        obj.wait();
                    }
                    System.out.println("-----end------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----begin interrupt threadA---");
        threadA.interrupt();
        System.out.println("----end interrupt threadA----");
    }
}
