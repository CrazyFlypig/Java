package javaDemo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM测试代码
 *
 * @author cc
 * @create 2017-08-21-11:49
 */

public class TestJVM {
    public static void main(String[] args) {
        System.out.println("start");
        List<byte[]> list = new ArrayList<byte[]>();
        while (true){
            list.add(new byte[1024 * 1024 * 10]);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
