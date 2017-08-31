package javaDemo.Thread.synchronous;

/**
 * 40个工人吃100个面包
 *
 * @author cc
 * @create 2017-05-09-1:47
 */

public class EatBread {
    public static void main(String[] args) {
        //创建篮子
        Basket basket = new Basket();
        for (int i=1 ; i <= 40 ; i++) {
            new Worker("Worker-" + i,basket).start();
        }
    }
}
