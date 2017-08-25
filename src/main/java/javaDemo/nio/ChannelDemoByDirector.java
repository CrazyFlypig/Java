package javaDemo.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接内存缓冲区通道
 *
 * @author cc
 * @create 2017-08-25-23:49
 */

public class ChannelDemoByDirector {
    @Test
    public void copyFile() throws Exception {
        //源文件
        RandomAccessFile src = new RandomAccessFile("D:\\cloudmusic\\Daniel Powter-Free Loop.mp3", "r");
        //源文件至缓冲区之间的通道
        FileChannel srcChannel = src.getChannel();

        //缓冲区,return new HeapByteBuffer(capacity, capacity);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 );

        //目标目录,路径填写完整路径
        RandomAccessFile dst = new RandomAccessFile("F:\\usr\\Daniel Powter-Free Loop.mp3", "rw");
        //目标目录至缓冲区之间的通道
        FileChannel dstChannel = dst.getChannel();

        while (srcChannel.read(buffer) != -1) {
            buffer.flip();//缓冲区拍板，移动pos和limit的位置
            dstChannel.write(buffer);
            buffer.clear();
        }
        System.out.println("over!");
    }
}
