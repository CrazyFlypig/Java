package javaDemo.io.ObjectInputStream;

import java.io.Serializable;

/**
 * 自定义person类，进行序列化和反序列化
 *
 * @author cc
 * @create 2017-04-30-1:33
 */

public class Person implements Serializable{
    private String name;
    private int age;
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
    public Person(){
        name = null;
        age = -1;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
