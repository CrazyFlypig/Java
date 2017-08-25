package javaDemo.nio;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件映射测试
 *
 * @author cc
 * @create 2017-08-26-0:54
 */

public class MemoryMappedDemo {
    @Test
    public void readBuff() throws IOException {
        //随机访问文件
        RandomAccessFile raf = new RandomAccessFile("D:\\qq_wechat\\305979984\\AppWebCache\\1\\pub.idqqimg.com\\qqfind\\css\\main.c3aa262b.css","rw");
        //得到文件通道
        FileChannel channel = raf.getChannel();

        System.out.println("fileLength: " + raf.length()/1024);

        //创建与file相对应的内存字节缓冲区
        //buffer 是直接内存缓冲区
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY,0,raf.length());

        int cap = buffer.capacity();
        System.out.println("buffer cap: " + cap/1024);
    }
}
