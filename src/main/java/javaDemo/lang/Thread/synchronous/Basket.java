package javaDemo.lang.Thread.synchronous;

/**
 * 篮子
 *
 * @author cc
 * @create 2017-05-09-1:31
 */

public class Basket {
    private int num = 100;
    //从篮子中取馒头，应该同步加锁
    public synchronized int getSteamed_bread()  {
        int tmp = num--;
        return tmp > 0 ? tmp : 0;
    }
}
