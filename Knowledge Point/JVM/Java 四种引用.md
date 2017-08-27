# Java的四种引用
## Java对象的状态转换
![](http://i.imgur.com/FIzNsPu.jpg)
## 四种引用
### 强引用 Strong Reference
* 程序创建一个对象，并把这个对象赋给一个引用变量。被引用的Java对象绝不会被垃圾回收机制回收。这个引用变量就称为强引用，类似`A a = new A();`。
* 当内存空间不足时，JVM宁愿抛出`OutOfMemoryError`错误，使程序异常终止，也不会随意回收具有强引用的对象。
#### 强引用特性
1. 强引用可以直接访问目标对象。
2. 强引用所指向的对象在任何时候都不会被系统回收。
3. 强引用可能导致内存泄漏。
### Final Reference
* 当前类是否是finalizer类（指由JVM标志的，而非java.lang.ref.Finalizer类，但JVM会将finalizer类注册到java.lang.ref.Fianlizer类中的。）
	1. 当前类或父类中含有一个参数为空，返回值为void的名为finalize的方法。
	2. 并且该finalize方法必须非空。
* GC 回收问题
	1. 对象因为Finalizer的引用而变成了一个临时的强引用，即使没有其它的强引用，还是无法立即被回收；
	2. 对象至少经历两次GC才能被回收。
		* 因为只有在FinalizerThread执行完了finalizer对象的finalize方法的情况下才有可能被下次GC回收，有可能期间已经经历过多次GC了，但还一直没有执行对象的finalize方法。
	3. CPU资源比较稀缺的情况下FinalizerThread线程因为优先级较低而延迟执行对象的finalize方法。
	4. 因为对象finalize方法迟迟没有执行，有可能导致大部分finalizer对象进入到老年代，此时容易引发老年代的GC，甚至Full GC，GC暂停时间明显变长，甚至导致OOM。
	5. 对象的finalize方法被调用后，这个对象有可能未被回收，但在不久的将来会被回收。
### 软引用 Soft Reference
* 用来描述一些还有用但并非必须的对象。
* 系统内存足够时，不会被吸收；系统内存不足时，被回收。若回收后依旧内存不足则会抛出内存溢出异常。
* 通过SoftReference被实现。
* 软引用可用来实现内存敏感的高速缓存。
* 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，java虚拟机就会把这个软引用加入到与之关联的引用队列里。
* **注意**：GC准备对SoftRefence所指的对象进行回收时，调用对象的`finalize()`方法之前，SoftRefence对象自身会被加入到这个RefenceQueue对象中，此时可以通过ReferenceQueue的`poll()`方法取到它们。
* [https://github.com/CrazyFlypig/Java/blob/master/src/main/java/javaDemo/lang/ref/SoftReference/SoftReferenceDemo.java](https://github.com/CrazyFlypig/Java/blob/master/src/main/java/javaDemo/lang/ref/SoftReference/SoftReferenceDemo.java "测试代码")
### 弱引用
* 用来描述非必须的对象，强度比软引用更软一些，被弱引用关联的对象只能生存到下一次垃圾收集发生之前。
* 当垃圾回收机制运行时，不管系统内存是否足够，都会被回收。 
* 通过WeakReference类实现
* 一旦弱引用对象被垃圾回收器回收，便会加入到一个注册引用队列中。
* **注意**：Java垃圾回收器准备对WeakReference所指向的对象进行回收时，调用这个对象的`finalize()`方法之前，WeakReference对象自身会被加入到这个ReferenceQueue对象中，此时可以通过ReferenceQueue的`poll()`方法取到它们。
* [https://github.com/CrazyFlypig/Java/blob/master/src/main/java/javaDemo/lang/ref/WeakReference/WeakReferenceDemo.java](https://github.com/CrazyFlypig/Java/blob/master/src/main/java/javaDemo/lang/ref/WeakReference/WeakReferenceDemo.java "测试代码")
### 虚引用 Phantom Reference
* 不同于软引用和弱引用，虚引用无法通过`get()`方法来取得目标对象的强引用从而使用目标对象，在源码中，虚引用的`get()`方法永远返回null。
* 主要用于跟踪对象被垃圾回收的状态。不能单独使用，必须和引用队列（ReferenceQueue）联合使用。通过查看引用队列中是否包含对象所对应的虚引用来判断对象是否被回收。
* 目标对象被回收前，虚引用被放入一个ReferenceQueue对象中，说明referent的`finalize()`方法已经调用。
* **注意**：PhantomReference只有当java垃圾回收器对其所指的对象真正进行回收时，会将其加入到ReferenceQueue对象中，如此就可以跟踪对象的销毁情况。这里referent对象的`finalize()`方法已经调用过了。
* 具体用法和之前不同，它必须传入一个ReferenceQueue对象。
* [https://github.com/CrazyFlypig/Java/blob/master/src/main/java/javaDemo/lang/ref/PhantomReference/PhantomReferenceDemo.java](https://github.com/CrazyFlypig/Java/blob/master/src/main/java/javaDemo/lang/ref/PhantomReference/PhantomReferenceDemo.java "测试代码")
## System.gc()操作
* System.gc()文档：
````java
/**
     * Runs the garbage collector.
     * <p>
     * Calling the <code>gc</code> method suggests that the Java Virtual
     * Machine expend effort toward recycling unused objects in order to
     * make the memory they currently occupy available for quick reuse.
     * When control returns from the method call, the Java Virtual
     * Machine has made a best effort to reclaim space from all discarded
     * objects.
     * <p>
     * The call <code>System.gc()</code> is effectively equivalent to the
     * call:
     * <blockquote><pre>
     * Runtime.getRuntime().gc()
     * </pre></blockquote>
     *
     * @see     java.lang.Runtime#gc()
     */
````
* 当方法返回时，Java虚拟机已经尽最大努力去回收所有丢弃的对象空间。
* 连续调用`System.gc()`影响性能，但作用之一就是可以清除“漂浮垃圾”。
