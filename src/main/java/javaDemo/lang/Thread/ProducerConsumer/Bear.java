package javaDemo.lang.Thread.ProducerConsumer;

/**
 * 消费者-熊
 *
 * @author cc
 * @create 2017-05-10-1:06
 */

public class Bear extends Thread {
    private String name;
    private Box box;
    public Bear(String name,Box box){
        this.name = name;
        this.box = box;
    }
    public void run(){
        while (true){
            box.remove();
            System.out.println(name + "消费：20.");
        }

    }
}
