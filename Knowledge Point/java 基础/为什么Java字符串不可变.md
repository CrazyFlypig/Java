# 为什么Java 字符串不可变
String是java中一个不可变的类，一旦被实例化就无法修改。
## 字符串池
字符串池是方法区中的一部分特殊存储。当一个字符串被创建时，首先会去字符串池中查找，如果找到，直接返回该字符串的引用。

下面代码只会在堆中创建一个字符串
    
	String string1 = "abcd";
	String string2 = "abcd";
图示：
![](http://i.imgur.com/F4BG8D1.jpg)
若字符串可变，则通过其中一个引用修改就引起另一个发生变化。
## 缓存Hashcode
Java中经常会用到字符串的哈希码(hashcode),不可变能保持其hashcode永远一致。

在String类中，有以下代码：
    
	private int hash;//this is used to cache hash code
一旦对象被创建，该hash值也无法改变。所以需使用该对象的hashcode时，直接返回即可。
## 使其它类的使用更加便利
## 安全性
String被广泛的使用在其他Java类中充当参数。比如网络连接，打开文件等操作。如果字符串可变，那么类似操作可能导致安全问题。可变的字符串也可能导致反射的安全问题。
## 不可变对象天生就是线程安全
可以在多个线程之间共享。不需要同步处理。