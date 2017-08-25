package javaDemo.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * @author cc
 * @create 2017-08-24-15:06
 */

public class HeapBufferDemo {
    /**
     * 存储于堆缓存区
     *
     */
    @Test
    public void allocateBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println("pos: " + buffer.position());
        System.out.println("cap: " + buffer.capacity());
        System.out.println("lim: " + buffer.limit());
        System.out.println("rem: " + buffer.remaining());

        buffer.put((byte)1);
        buffer.put((byte)2);
        buffer.put((byte)3);
        buffer.put((byte)4);

        System.out.println("--------------------------");
        System.out.println("pos: " + buffer.position());
        System.out.println("cap: " + buffer.capacity());
        System.out.println("lim: " + buffer.limit());
        System.out.println("rem: " + buffer.remaining());

        //拍板
        buffer.flip();
        System.out.println("--------------------------");
        System.out.println("pos: " + buffer.position());
        System.out.println("cap: " + buffer.capacity());
        System.out.println("lim: " + buffer.limit());
        System.out.println("rem: " + buffer.remaining());

        //读取
        System.out.println("--------------------------");
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }
    /**
     * 测试整数写入缓冲区时的顺序
     *  高位在前
     */
    @Test
    public void testEndian(){
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(255);
        buffer.position(0);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }
}
