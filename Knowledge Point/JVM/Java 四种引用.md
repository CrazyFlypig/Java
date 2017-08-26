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

### 软引用 Soft Reference
* 用来描述一些还有用但并非必须的对象。
* 系统内存足够时，不会被吸收；系统内存不足时，被回收。若回收后依旧内存不足则会抛出内存溢出异常。
* 通过SoftReference被实现。
* 软引用可用来实现内存敏感的高速缓存。
* 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，java虚拟机就会把这个软引用加入到与之关联的引用队列里。
* **注意**：GC准备对SoftRefence所指的对象进行回收时，调用对象的`finalize()`方法之前，SoftRefence对象自身会被加入到这个RefenceQueue对象中，此时可以通过ReferenceQueue的`poll()`方法取到它们。
* 
### 弱引用
* 用来描述非必须的对象，强度比软引用更软一些，被弱引用关联的对象只能生存到下一次垃圾收集发生之前。
* 当垃圾回收机制运行时，不管系统内存是否足够，都会被回收。 
* 通过WeakReference类实现
* 一旦弱引用对象被垃圾回收器回收，便会加入到一个注册引用队列中。
* **注意**：Java垃圾回收器准备对WeakReference所指向的对象进行回收时，调用这个对象的`finalize()`方法之前，WeakReference对象自身会被加入到这个ReferenceQueue对象中，此时可以通过ReferenceQueue的`poll()`方法取到它们。
### 虚引用 Phantom Reference
* 不同于软引用和弱引用，虚引用无法通过`get()`方法来取得目标对象的强引用从而使用目标对象，在源码中，虚引用的`get()`方法永远返回null。
* 
* 主要用于跟踪对象被垃圾回收的状态。不能单独使用，必须和引用队列（ReferenceQueue）联合使用。通过查看引用队列中是否包含对象所对应的虚引用来判断对象是否被回收。
* 目标对象被回收前，虚引用被放入一个ReferenceQueue对象中，说明referent的`finalize()`方法已经调用。
* **注意**：PhantomReference只有当java垃圾回收器对其所指的对象真正进行回收时，会将其加入到ReferenceQueue对象中，如此就可以跟踪对象的销毁情况。这里referent对象的`finalize()`方法已经调用过了。
* 具体用法和之前不同，它必须传入一个ReferenceQueue对象。
