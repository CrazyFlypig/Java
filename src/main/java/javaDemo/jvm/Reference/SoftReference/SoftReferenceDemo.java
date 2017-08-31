package javaDemo.jvm.Reference.SoftReference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * java 软引用测试
 *
 * @author cc
 * @create 2017-08-27-0:47
 */
/**
 *  软引用：对于软引用关联着的对象，在系统将要发生内存溢出异常之前，
 * 将会把这些对象列进回收范围之中进行第二次回收( 因为是在第一次回收后才会发现内存依旧不充足，才有了这第二次回收 )。如果这次回收还没有足够的内存，才会抛出内存溢出异常。
 *  对于软引用关联着的对象，如果内存充足，则垃圾回收器不会回收该对象，如果内存不够了，就会回收这些对象的内存。
 *  通过debug发现，软引用在pending状态时，referent就已经是null了。
 *
 * 启动参数：-Xmx5m
 *
 */
public class SoftReferenceDemo {
    private static ReferenceQueue<MyObject> queue = new ReferenceQueue<MyObject>();

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        MyObject object = new MyObject();
        SoftReference<MyObject> softRef = new SoftReference<MyObject>(object, queue);
        new Thread(new CheckRefQueue()).start();
//        Thread.sleep(2000);

        object = null;
        System.gc();
        System.out.println("Afer GC : Soft Get = " + softRef.get());
        System.out.println("分配最大内存");
        /**
         * ===============控制台打印========================
         * After GC ：Soft Get = I am MyObject.
         * 分配最大内存
         * MyObject's finalize called
         * Object for softReference is null
         * After new byte[] : Soft Get = null
         * ===============控制台打印=========================
         *
         * 总共触发了 3 次 full gc。第一次有System.gc();第二次在分配字节数组时。
         */
        byte[] b = new byte[5*1024*706];
        //java 堆溢出
        //b = new byte[5*1024*790];
        System.out.println("After new byte[] : Soft Get = " + softRef.get());
        System.out.println("hah");
    }

    public static class CheckRefQueue implements Runnable {

        Reference<MyObject> obj = null;

        @Override
        public void run() {
            try {
                obj = (Reference<MyObject>) queue.remove();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj != null){
                System.out.println("Object for softReference is" + obj.get());
            }
        }
    }

    public static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("MyObject's finalize called");
            super.finalize();
        }

        @Override
        public String toString() {
            return "I am MyObject.";
        }
    }
}



