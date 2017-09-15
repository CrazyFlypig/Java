package javaDemo.Thread.VolatileDemo;

/**
 * Volatile变量自增测试
 *
 * @author cc
 * @create 2017-09-11-10:10
 */

public class VolatileTest {
    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        //等待所有累加线程结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
            System.out.println(race);
        }
    }

}
