package javaDemo.lang.Runnable;

/**
 * @author cc
 * @create 2017-05-20-1:42
 */

public class Dog extends Animal implements Runnable{
    private String food;
    public Dog ( String name , String food ){
        super(name);
        this.food = food;
    }
    public String getFood (){
        return food;
    }
    public void run (){
        for (int i = 0 ; i < 100 ; i++){
            System.out.println(i);
        }
    }
}
