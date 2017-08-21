# Java 异常
## Java 十大常见异常
### java.lang.NullPointerException
* 异常解释是“程序遇上了空指针”，简单来说就是调用了未经初始化的对象或者不存在的对象。
* 解决办法是在使用前进行判空比较。
### java.lang.ClassNotFoundException
* 异常解释是“指定类不存在”
* 指定类，一般默认需要加Package路径
### java.lang.ArrayIndexOutofBoundsException
* 异常解释“数组下标越界”
* 一般先检查数组````length````，再使用变量访问
### java.lang.IllegalAccessException
* 异常解释“没有访问权限”，当应用程序要调用一个类，但当前的方法即没有对该类的访问权限便会出现这个异常。
### java.lang.IncompatibleClassChangeError
* 不兼容的类变化错误。
* 当正在执行的方法所依赖的类发生了不兼容的改变时，抛出该异常。一般在修改了应用中的某些类的声明定义而没有对整个应用重新编译而直接运行的情况下，容易引发错误。
### 
### java.lang.ArithmeticException
* 异常解释是“数学运算异常”