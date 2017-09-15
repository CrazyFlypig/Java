# 泛型
## 泛型基础
* 泛型包括泛型类，泛型接口，泛型方法
* 泛型工作：消除代码中的强制类型转换，获得一个附加的类型检查层，防止有人将错误类型的键或值保存在集合中。
### 优点
1. 类型安全
	* 使用泛型定义的变量的类型限制，编译器可以在一个高得多的程度上验证类型假设。
2. 消除强制类型转换
3. 潜在的性能收益
	* 更多信息可用于编译器，为未来的JVM的优化带来可能。
### 使用规则和注意
1. 泛型的参数只能是类
2. 泛型的参数可以有多个
3. 泛型的参数可以使用````extends````语句，例如````< T extens superClass >````习惯上称为“有界类型”。
4. 泛型的类型参数可以是通配符。例如````Class<?> classType = class.forName(java.lang.String);````
5. 
### 泛型类
````java
public class Box<T>{
	//T stands for "Type"
	private T t;
	public void set(T t){this.t = t; }
	public T get(){ return t; }
}
````
Box类便可以得到复用，我们可以将T替换成任何我们想要的类型
### 泛型方法
 声明一个泛型方法只要在返回类型前加上一个类似<K,V>的形式就行。
 然后调用时使用具体的类型代替标识符就可以了。
### 边界符
限定标示符所能标示的对象范围
### 通配符
告诉编译器标识符所表示的对象与其他对象之间的关系。
### PECS原则
* “Producer Extends” -如果你需要一个只读List，用它来produce T，那么使用“？ extends T”。
* “Consumer Super” 如果你需要一个只写List，用它来consume T,那么使用“ ？super T”。
* 如果需要同时读取以及写入，那么就不能使用通配符了。
 **一般将两者结合起来一起用。**
## 类型擦除
 java泛型只能用于编译期间的静态类型检查，然后编译器生成的代码就会擦除相应的类型信息，这样到了运行期间实际上JVM根本不知道泛型所代表的具体类型。
### 1.不允许创建泛型数组
* 如果允许创建泛型数组，就绕过了泛型的编译时的类型检查，类型错误就会暴露在运行期。
### 2.类型擦除后，编译器会在父类方法和子类方法之间创建一个Bridge method
### 3.Java允许直接创建类型参数的实例，但可以利用反射机制来为类型参数创建实例
````java
public static <E> void append(List<E> list,Class<E> cls)throws Exception{
	E elem = cls.newInstance();//OK
	list.add(elem);
}
````
 可以这样调用：
````java
List <String> ls = new ArrayList<>();
append(ls,String.class);
````
 还可以采用Factory和Template两种设计模式解决。
### 4.无法使用instanceof关键字
* instanceof关键字用来判断一个运行时的对象是不是某个特定类的实例。
* 因为Java编译器在生成代码时会擦除所有相关泛型的类型信息。如无法识别ArrayList\<Intger>和ArrayList\<String>之间的区别。