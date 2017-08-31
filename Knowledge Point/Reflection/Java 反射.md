# Java 反射
* 反射（Reflection）是Java语言的特征之一，它允许运行中的Java程序获取自身的信息，并且可以操作类或对象的内部属性。
* 规则破坏者
* class 定义类的关键字
* Class 类，类的描述符
	* getName() 完整名称
	* getSimpleName() 简单名称
	* newInstance()；通过反射创建实例，经过空构造
	* getDeclaredConstructor(); 得到类的构造函数
		* Constructor 构造函数描述符
		* newInstance()，构造函数描述符也可调用构造函数创建实例，Class 类的创建实例就是调用无参构造函数
		* newInstance()，也可有参实例化对象，但需要先指定参数类型，与构造函数匹配，使用`getDeclaredConstructor()`指定，基本数据类型也可以，如`int.class`
		* 可通过`setAccessible()`,设置构造方法的可访问性，设`true`时可调用私有构造
		* 
	* Method
		* 方法描述符
		* Class对象调用`getDeclaredMethod(name,parameterTypes)`获取方法对象
		* 方法对象可调用`.invoke(object,实参)`方法，实现方法调用,静态方法调用不需要对象
		* 方法对象也可调用`setAccessible();`，设置可访问性
		* ````java
			Class clazz = String.class;
			//返回类中声明的方法
			Method[] ms = clazz.getDeclaredMethods();
			//返回类中的共有方法，包括接口和继承的
			Method[] ms = clazz.getMethods();
		  ````
			````java
			Method m = clazz.getDeclaredMethod(方法名,参数类型);
			//得到方法修饰符总和
			int modifer = m.getModifiers();
			//总和值&相应参数值，判断是不是1
			Modifier.isPublic(modifer));
			Modifier.isPrivate(modifer));
			````
	* Field
		* 字段描述符,成员，变量
		* 通过`getDeclaredField(name);`获取字段对象
		* 通过`set(object,值)`给字段设置值
		* 通过`setAccessible();`设置可访问性
		* 取出字段值`字段对象.get(object)`
	* Modifier
		* 修饰符，判断是否含有指定的修饰符
		* Modifier.isStatic(m.getModifier());
		* ` & | `实现
	* Annotation 注解
		````java
		Class clazz = object.class;
		Method m = clazz.getMethod("toString",null);
		Annotation[] annos = m.getAnnotations();//得到该方法上的注解
		````
		* 基于注解和配置文件编程
		* 
## 内省，专业操作Java bean
* Introspctor
* BeanInfo //bean信息
* PropertyDescriptor //属性描述符
* MethodDescriptor //方法描述符
## 实例
````java
/**
  * 得到指定包下所有类
*/
Class clazz = Cat.class;
ClassLoader loader = clazz.getClassLoader();
//统一资源定位符
URL url = loader.getResource("com/xiyou/reflect");
//文件夹
File file = new File (url.toURI());
File[] files = file.listFiles();
for (File f : files ) {
	String fname = f.getName();
	if ( !fname.endsWith(".class")) {
		continue;
	}
	String className = fname.substring(0,fname.lastIndexOf("."));
	className = "com.xiyou.reflect." + className;
	clazz = Class.forName(className);
	System.out.println(clazz);
}
````
## Filed 与 Property
* Filed 是类定义的字段，由类的声明决定
* Property 属性，由java bean的set、get方法获取得到
## 面试
### Java反射的作用
1. 运行时判断任意一个对象所属的类
2. 在运行时判断任意一个类所具有的成员变量和方法
3. 在运行时调用一个类的方法
4. 在运行时构造任意一个类的对象
### 什么是反射机制
* 程序在运行时能够获取自身的信息。在java中，只要给定类的名字，那么就可以通过反射机制来获得类的所有信息。
### java反射机制提供了什么功能？
1. 在运行时判断任意一个对象所属的类
2. 在运行时构造任意一个类的对象
3. 在运行时判断任意一个类所具有的成员变量和方法
4. 在运行时调用任一对象的方法
5. 在运行时创建新类对象
### 反射机制的用处？
* jdbc中用到反射，很多框架也用到了反射机制，如hibernate
### 反射机制的优缺点？
* 反射机制可以实现动态创建对象和编译，体现出很大灵活性
* 影响性能