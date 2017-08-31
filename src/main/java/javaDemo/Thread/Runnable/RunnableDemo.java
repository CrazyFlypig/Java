package javaDemo.Thread.Runnable;

/**
 * @author cc
 * @create 2017-05-20-1:57
 */

public class RunnableDemo {
    public static void main(String[] args) {
//        Dog dog = new Dog("dog","clone");
//        new Thread(dog).start();
//        System.out.println(dog.getName());
//        System.out.println(dog.getFood());
//        new Thread(new Runnable1() {
//            public void run() {
//                for (int i = 0; i<15 ; i++){
//                    System.out.println(i);
//                }
//            }
//        }).start();
        new Thread(){
            public void run(){
                for (int i=0 ; i<100 ; i++){
                    System.out.println(i);
                }
            }
        }.start();
    }
}
