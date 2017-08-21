# Java 虚拟机类加载机制
  先上代码
```` java
class SSClass{
	static {
		System.out.println("SSClass");
	}
}
class SuperClass extends SSClass{
	static{
		System.out.println("SuperClass init!");
	}
	public static int value = 123;
	public SuperClass(){
		System.out.println("init SuperClass");
	}
}
class SubClass extends SuperClass{
	static{
		System.out.println("SubClass init");
	}
	static int a;
	public SubClass(){
		System.out.println("init SubClass");
	}
}
public class Test {
	public static void main(String[] args) {
		System.out.println(SubClass.value);
	}
}
````
输出结果

	SSClass
	SuperClass init!
	123
对于静态字段，只有直接定义这个字段的类才能被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化。
## 类的加载过程
![类加载过程](http://i.imgur.com/1AfoEJO.png)
  如图，类从被加载到虚拟机内存中开始，到卸载出内存为止，它的整个生命周期包括：**加载（Loading）、验证（Verification）、准备（Preparation）、解析（Resolution）、初始化（Initialization）、使用（Using）和卸载（Unloading）7个阶段**。其中准备、验证、解析3个部分统称为连接（Linking）。
* 加载、验证、准备、初始化和卸载这5个阶段的顺序是确定的，类的加载过程必须按照这种顺序按部就班地开始，而解析阶段则不一定：它在某些情况下可以在初始化阶段开始之后再开始，这是为了支持Java语言地运行时绑定（也称动态绑定或晚期绑定）。以下内容以HotSpot为基准。
### 加载
加载阶段（可参考java.lang.ClassLoader的loadClass()方法），虚拟机需要完成以下三件事情：
1. 通过一个类的全限定名来获取定义此类的二进制字节流；（class文件、网络、动态生成、数据库）
2. 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构；
3. 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口；
[原文链接](http://mp.weixin.qq.com/s/vyCRw_6-_JAH05gcwQl-Pg)
	