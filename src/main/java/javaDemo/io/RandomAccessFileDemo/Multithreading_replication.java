package javaDemo.io.RandomAccessFileDemo;

import java.io.File;

/**
 * 多线程复制文件
 *
 * @author cc
 * @create 2017-04-30-1:03
 */

public class Multithreading_replication {
    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();
        String srcPath = "D:/net.rmvb";
        String destPath = "D:/cc/net.rmvb";
        File file = new File(srcPath);
        long len = file.length();
        int count = 7;
        int onelen = (int) len/count;
        for (int i = 0; i < count; i++){
            int start = onelen*i;
            int end;
            if(i != count-1)
                end = onelen * (i+1);
            else
                end = (int)len -1;
            CopyThread copyThread = new CopyThread(srcPath, destPath, start, end );
            copyThread.start();
            System.out.println("-----------YouMeek.com-----------i值=" + i + "," + "当前类=RandomAccessFileDemo.main()");
            long endtime = System.currentTimeMillis();
            System.out.println("Time is : "+ (endtime - starttime));
        }
    }
}
