package javaDemo.jvm.Reference.WeakReference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * @author cc
 * @create 2017-08-27-11:08
 */

public class WeakReferenceDemo {
    private static ReferenceQueue<MyObject> queue = new ReferenceQueue<MyObject>();

    public static void main(String[] args) {
        /**
         * ====================== 控制台打印 ======================
         * 创建的弱引用为 : java.lang.Reference.WeakReference@1d44bcfa
         * Before GC: Weak Get = I am MyObject
         * After GC: Weak Get = null
         * MyObject's finalize called
         * 删除的弱引用为 : java.lang.Reference.WeakReference@1d44bcfa , 获取到的弱引用的对象为 : null
         * ====================== 控制台打印 ======================
         */
        MyObject object = new MyObject();
        Reference<MyObject> weakRef = new WeakReference<MyObject>(object, queue);
        System.out.println("创建的弱引用为 ：" + weakRef);
        new Thread(new CheckRefQueue()).start();

        object = null;
        System.out.println("Before GC: Weak Get = " + weakRef.get());
        System.gc();
        System.out.println("After GC : Weak Get = " + weakRef.get());
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
            if (obj != null) {
                System.out.println("删除的弱引用为： " + obj + " , 获取的弱引用对象为：" + obj.get());
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
