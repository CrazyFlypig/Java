package javaDemo.io.ObjectInputStream;

import org.junit.Test;

import java.io.*;

/**
 * java序列化一个对象时同时将类图关系内所有内均进行序列化
 *
 * @author cc
 * @create 2017-04-30-11:12
 */

public class DeepCopy {
    @Test
    public void Serialize () throws IOException {
        Owner owner = new Owner("Tom",20,true);
        Dog dog = new Dog(owner,"happy",8);
        FileOutputStream fileOutputStream = new FileOutputStream("D:/deepcopy.data");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(dog);
        objectOutputStream.close();
        fileOutputStream.close();
        System.out.println("Serialize over");
    }
    @Test
    public void deSerialize () throws IOException, ClassNotFoundException {
        FileInputStream fileinputstream = new FileInputStream("D:/deepcopy.data");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileinputstream);

        Dog dog =(Dog)objectInputStream.readObject();

        objectInputStream.close();
        fileinputstream.close();

        System.out.println("deSerialize over!");

        System.out.println(dog.getName());
        System.out.println(dog.getOwner().getName());
        System.out.println(dog.getOwner().isMarried());
    }
}
