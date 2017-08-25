package javaDemo.nio;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * 直接内存缓冲区测试,数据存在于离堆。
 *
 * @author cc
 * @create 2017-08-25-23:07
 */

public class DirectorBufferDemo {
    @Test
    public void DirectorByteBuffer() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);

        buffer.put((byte) 1);
        buffer.flip();
        ClearBuffer(buffer);
        //缓冲区已回收，抛出 A fatal error has been detected by the Java Runtime Environment
        System.out.println(buffer.get());
    }

    /**
     * 使用反射方法调用直接字符缓冲区的释放方法
     */
    public void ClearBuffer(ByteBuffer buffer) {
        try {
            Class clazz = Class.forName("java.nio.DirectByteBuffer");
            Method cleaner = clazz.getDeclaredMethod("cleaner");
            cleaner.setAccessible(true);
            Object Cleaner = cleaner.invoke(buffer);

            clazz = Class.forName("sun.misc.Cleaner");
            Method clean = clazz.getDeclaredMethod("clean");
            clean.setAccessible(true);
            clean.invoke(Cleaner);
            System.out.println("clean over!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
