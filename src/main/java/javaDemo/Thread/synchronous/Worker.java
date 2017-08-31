package javaDemo.Thread.synchronous;

/**
 * 工人
 *
 * @author cc
 * @create 2017-05-09-1:37
 */
//工人取馒头多线程操作
public class Worker extends Thread {
    private String name ;
    private int count = 0;
    private final int Max = 3;
    private Basket basket;
    public Worker (String name, Basket basket) {
        this.name = name;
        this.basket = basket;
    }
    public void run() {
        while (true){
            int number = 0;
            if ( count >= Max || (number = basket.getSteamed_bread()) == 0 ) {
                return;
            }
            count++;
            System.out.println(name + ":" + number);
        }
    }

}
