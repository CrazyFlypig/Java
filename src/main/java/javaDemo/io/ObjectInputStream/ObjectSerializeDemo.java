package javaDemo.io.ObjectInputStream;

import org.junit.Test;

import java.io.*;

/**
 * Person对象的序列化和反序列化
 *
 * @author cc
 * @create 2017-04-30-1:58
 */

public class ObjectSerializeDemo {
    @Test
    //序列化
    public void Serialize() throws IOException {
        //创建对象
        Person person = new Person("Tom",20);
        //开启文件输出流
        FileOutputStream fout = new FileOutputStream("D:/person.data");
        //通过文件输出流构建对象输出流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fout);
        //序列化
        objectOutputStream.writeObject(person);
        //关闭流
        objectOutputStream.close();
        fout.close();
        System.out.println("Serialize over!");

    }
    @Test
    //反序列化
    /*Warining:
    *java.io.InvalidClassException: com.Person; local class incompatible:
    *  stream classdesc serialVersionUID = 2093355103409005490, local class serialVersionUID = -2940455527100223516
     */
    public void deSerialize() throws IOException, ClassNotFoundException {
        //开启流
        FileInputStream fin = new FileInputStream("D:/person.data");
        ObjectInputStream objectInputStream = new ObjectInputStream(fin);
        //反序列化
        Person person = (Person)objectInputStream.readObject();
        //关闭流
        objectInputStream.close();
        fin.close();

        System.out.println("deSerialize over!");
        System.out.println("Person name is "+person.getName());
    }
}
