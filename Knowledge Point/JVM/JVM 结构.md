# JVM结构
* Runtime data area，运行时数据区
## 1. method area
1. 方法区，被所有线程间共享   
## 2. heap
1. 堆区，被所有线程间共享
2. 存放对象和数组的容器
3. 内部分区：
	1. 年轻代
		1. 伊甸区
		2. 幸存一区
		3. 幸存二区
	2. 年老代
	3. 永久代
		* 不在堆区，类似于method area
4. heap,non-heap,off-heap
	1. 堆
	2. 非堆。jvm中堆之外的空间---method area,stack
	3. 离堆，jvm之外的空间。
4. 对象存在过程
	1. 首先存在于伊甸区。
	2. 伊甸区存在一定值后，对象被放置到幸存一区或幸存二区
	3. 最终被放置到年老代，等待回收。
5. 设置堆：
	1. java -Xmx500m -Xms500M
	2. X:非标准选项。mx：最大值。ms：默认值
	3. 可在编译器运行参数中设置。
	4. -XXNewSize=n:设置伊甸区大小
	5. -XX:NewRation=n:设置年轻代与年老代的比值。如：为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代和年老代和的1/4
	6. -XX:SurvivorRation=n:年轻代中Eden区与两个Survior区的比值。注意Survivor区有两个
	7. -XX:MaxPermSize=n:设置持久代大小
## 3. java stack
1. 不共享。每个线程对应一个stack，线程栈 == 方法栈
2. 调整栈空间： java -Xss1m
3. OOM：out of memory，内存溢出。
## 4. native method stack
## 5. program counter register
1. 计数器寄存器
## jvisualvn查看jvm结构
	1. cmd --> jvisualvm
	2. 打开窗口
	3. 工具菜单-->插件-->可用插件-->选中visual GC-->安装
## GC
* garbage collection，垃圾回收。
* 回收前提： 没有任何一个指针直接或者间接到达该对象
* `System.gc();` 显式回收。一般不需要开发者显式回收
### finalize()
* Object.finalize(),在对象被回收执行，类似于C++的析构函数。通常用于网络编程
### java内存泄漏
* java发生内存泄漏对象的两个特点：
	1. 对象可达，即存在引用直接或间接引用了该对象。
	2. 对象无用。

## 类加载
1. 可以阻止类被初始化，也即不调用静态代码块。在实例化时进行类初始化。
	1. Class.forName("",false,ClassLoader);
## java 守护线程
* 如果一个进程只剩守护线程，则意味这个进程的结束，提供服务。
1. Finalizer：回收线程
2. Reference Handler：引用处理器，维护所有对象的引用