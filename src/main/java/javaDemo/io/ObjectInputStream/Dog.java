package javaDemo.io.ObjectInputStream;

import java.io.Serializable;

/**
 * dog class
 *
 * @author cc
 * @create 2017-04-30-11:10
 */

public class Dog implements Serializable {
    private Owner owner;
    private String name;
    private int age;
    public Dog(Owner owner,String name,int age){
        this.owner = owner;
        this.name = name;
        this.age = age;
    }
    public Dog()
    {
        super();
        name = null;
        age = -1;
        System.out.println("Dog的无参构造");
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Owner getOwner() {
        return owner;
    }
}
