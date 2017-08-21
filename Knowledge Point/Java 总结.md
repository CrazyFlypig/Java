# Java 基础总结
0. 特点 跨平台os
	1. jvm ： java virtual machine ，java虚拟机
	1. jdk ： java development kit， java软件开发包 = jvm + 开发工具
	1. jre ： java runtime environment java运行时环境 = jvm + 核心类库
	2. 一次编译，多出运行
	3. *.java ---javac---- *.class ----java ----- APP
	4. javac -classpath ;;; Hello.java
1. java oop
	1. 封装
	2. 继承，多层单重
	3. 多态
	4. 接口，可多重继承
	5. 抽象类
	6. final
	7. private protected public
	8. static ，与对象无关
	9. new Xxx(); 构造函数，抽象函数也有
		1. 第一句默认````super()````;
	10. this 内置成员变量，还有super，
2. 数据类型
* [基本数据类型]
	1. byte ： 1 ,   -128~127
	2. short ： 2 , -32768 ~ 32767
	3. int ： 4 , -
	4. long ： 8
	4. float ： 4
	5. double ： 8
	6. boolean ： 1
	7. char ： 2 '\u0909'
* [引用类型]
	1. class
	2. interface
3. JVM
* runtime data area：
	* Method area 所有线程共享
	* java stack
		* FILO
		* method frame
		* java -Xss1M 死递归
	* native method stack
	* heap（所有线程共享）
		* 数组 + 对象
		* java -Xmx100M -Xms100M
		* 年轻代（伊甸区，幸存一区，幸存二区） + 年老代 + 永久代（非堆）
	* program counter register
* 静态代码块，在类加载时执行。
4. 集合
* List,Set继承Collection
	1. List
		1. 有序，重复，equals()
		2. ArrayList ， 数组列表，查询快，insert()
		3. LinkedList ， 链表，写入地，查询慢
	2. Set
		1. 无序，不可重复
		2. HashSet
	3. Map
		1. Key - Value（Entry：条目）
		2. HashMap 数组+表，判断hashcode是否相等，是否是同一对象，equals
		3. Hash ， 散列
5. 多线程
	1. implements Runnable
	2. extends Thread
	3. ````new Thread (new Runnable(...)).start();开辟java stack，一个线程一个java stack````
	4. 线程状态
		1. NEW
		2. RUNNABLE
		3. WAITING //等待，队列、产生死锁，改用notifyAll()/wait(some time)
		4. TIMED_WAITING
		4. BLOCKED ，//阻塞
		5. TERMINATED //结束
		6. sleep wait
	5. 线程安全
		1. 同步代码块，synchronized(lock){...},串行，保证同步代码块执行时间短
		2. 同步方法，public static synchronized void xxx(){}
	6. yield（） //放弃，谦让
	7. join（） //等待线程执行完毕后，再继续执行其它线程
	8. daemon（） //守护线程
6. NIO 
	1. 新 IO。
	2. 异步，非阻塞，ByteBuffer
	3. channel 通道
	4. ByteBuffer 
		1. 0 <= mark <= position <= limit <= capacity
		2. ByteBuffer.allocate(size); //堆内存缓冲区 jvm heap
		3. ByteBuffer.allocateDirect(size); // 直接内存缓冲区 off-heap cleaner。clean（）反射
	5. Selector 挑选器
	6. ch.register(sel,OP_ACCEPT|OP_READ|);
7. IO
	1. Input/Output
	2. InputStream/OutputStream
	3. Reader/Writer
	4. BufferedInputStream
	5. BufferedReader
	6. InputStreamReader //转换流
	7. ByteArrayInputStream
	8. ByteArrayOutputStream
	9. ZipInputStream
	10. ZipOutputStream
	9. 串行化
		1. 将java对象转成字节数组。 implement java.io.Serializable{seri UID(反序列化）
		2. ObjectOutputStream oos = new ObjectOutputStream(baos);
		3. oos.writeObject(xxx);
		4. oos.close()
		5. byte[] arr = baos.toByteArray();
		6. ObjectInputStream
	10. 深度复制
		1. 复制整个对象图 ， transient
	6. 装饰模式
		1. 增强类的功能
8. 反射
	* 动态访问对象的属性和方法。破坏了封装规则
	1. Class //类类
		1. class.forName(); //加载类，放到方法区 
		2. class.forName(xxx,"xxx",false); 不执行静态代码快
		3. newInstance() 创建实例，通过Construct的空构造，
	2. Constructor //构造函数 setAccessibale（true）；设置可访问性
	3. Method //方法
	4. Field //字段
	5. 创建对象不经过new，可通过反序列化
	6. [内省] 操纵javabean
		1. Introspector：javabean
		2. java.bean.BeanInfo		
		2. PropertyDescriptor
		3. MethodDescriptor
9. Socket编程
	1. TCP/IP
		1. 面向连接
		2. 安全
		3. 没有容量限制
	2. UDP
		1. 无连接
		2. 不安全
		3. 基于DatagramPacket 64K
		4. 拆包、切割、定制协议
	3. ServerSocket()； 
		1. accept() //阻塞的，引出NIO，一个线程轮询
	4. Socket
		1. new Socket(ip,port)
		2. getInputStream()
		3. getOutputStream()
	5. [UDP]
		1. Sender + receiver
		2. DatagramSocket
		3. Data
10. Jdbc
	1. URL
	2. driverclass
	3. username
	4.  