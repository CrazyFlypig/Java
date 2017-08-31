package javaDemo.Thread.ProducerConsumer;

/**
 * 生产者-消费者
 *
 * @author cc
 * @create 2017-05-10-1:16
 */

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        Box box = new Box();
        for (int i=0 ; i<100 ; i++){
            new Bee("bee-"+i,box).start();
        }
        new Bear("Bearbig",box).start();
        new Bear("Bearsmall",box).start();
    }
}
