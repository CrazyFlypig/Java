package javaDemo.io.ObjectInputStream;

import java.io.Serializable;

/**
 * 宠物拥有者，继承Person
 *
 * @author cc
 * @create 2017-04-30-11:02
 */

public class Owner extends Person implements Serializable {
    private boolean married;
    public Owner (String name,int age,boolean married){
        super(name,age);
        this.married = married;
    }
    public boolean isMarried() {
        return married;
    }
}
