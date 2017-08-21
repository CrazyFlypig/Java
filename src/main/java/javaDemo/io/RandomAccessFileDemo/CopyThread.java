package javaDemo.io.RandomAccessFileDemo;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * multiple threads
 *
 * @author cc
 * @create 2017-04-23-15:23
 */

public class CopyThread extends Thread {
    private String srcPath;
    private String destPath;
    private int start, end;

    public CopyThread(String src, String dest, int start, int end) {
        srcPath = src;
        destPath = dest;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        RandomAccessFile in = null;
        RandomAccessFile out = null;
        try {
            //创建一个随机可读文件的对象
            in = new RandomAccessFile(srcPath, "r");
            //创建一个随机可读可写的文件对象
            out = new RandomAccessFile(destPath, "rw");
            in.seek(start);
            out.seek(start);
            byte[] tmpb = new byte[end - start + 1];
            in.read(tmpb);
            out.write(tmpb);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
