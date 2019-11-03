package chapter1;

/**
 * RunableTask 可以继承其他类，
 * 缺点：任务没有返回值
 * */
public class RunableTask implements Runnable {

    @Override
    public void run() {
        System.out.println("I am a child thread");
    }

    public static void main(String[] args) {

        RunableTask task = new RunableTask();
        // 两个线程共用一个task代码逻辑
        new Thread(task).start();
        new Thread(task).start();
    }
}
