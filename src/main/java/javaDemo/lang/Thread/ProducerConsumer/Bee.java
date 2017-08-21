package javaDemo.lang.Thread.ProducerConsumer;

/**
 * 蜜蜂-生产者
 *
 * @author cc
 * @create 2017-05-10-1:11
 */

public class Bee extends Thread{
    private String name;
    private Box box;
    public Bee( String name,Box box){
        this.name = name;
        this.box = box;
    }
    public void run(){
        while (true)
        {
            int num = box.add();
            System.out.println(name + "生产：1");
            System.out.println("Box :"+ num);
        }

    }
}
