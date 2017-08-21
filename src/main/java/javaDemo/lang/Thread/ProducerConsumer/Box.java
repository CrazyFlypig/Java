package javaDemo.lang.Thread.ProducerConsumer;

/**
 * 蜜蜂和熊共操作的蜜罐
 *
 * @author cc
 * @create 2017-05-10-0:56
 */

public class Box {
    private final int Max = 20;
    private int num;
    public synchronized int add(){
        while ( num >= Max ){
            try {
                this.notifyAll();
                this.wait();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return ++num;
    }
    public synchronized void remove(){
        while ( num < Max){
            try {
                this.notifyAll();
                this.wait();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        num = 0;
    }
}
