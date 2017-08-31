# Java	--I/O
## 基础知识
* Java程序中，对于数据的输入/输出操作都是以“流”的方式进行。
* 以程序为参考，如果数据的流向是程序至设备，我们称其为输出流，反之，为输入流。
* Java分为字节流（Stream结尾）和字符流（Reader、Writer结尾），再分为输入（InputStream、Reader）和输出流（OutputStream、Writer）。Buffered开头的流只是缓冲区，为提高读写效率。
* Java 2 SDK中有三种基本类型的节点：文件（file）、内存（Memory）、管道（pipe）。
## 用法分析
1. 按数据来源
	1. 文件:	FileInputStream,FileOutputStream,FileReader,FileWriter
	2. byte[]: ByteArrayInputStream,ByteArrayOutputStream
	3. char[]: CharArrayReader,CharArrayWriter
	4. String: StringBufferInput,StringReader,StringWriter
	5. 网络数据流：InputStream,OutputStream,Reader,Writer
2. 按是否格式化输出
	1. 格式化输出： PrintStream，PrintWriter
3. 按数据格式分：
	1. 二进制格式（非纯文本）：InputStream，OutputStream及其子类
	2. 纯文本格式（含语言编码方式）： Reader，Writer及其子类
4. 按输入输出分：
	1. 输入：Reader，InputSteam类型子类
	2. 输出：Writer，OutputSteam类型子类
5. 特殊需要：
	1. 从字节流向字符流的转换：InputStreamReader，OutputSteamWriter
	2. 对象输入输出：ObjectInputSteam，ObjectOutputStream
	3. 进程间通信： PipeInputStream，PipeOutputStream，PipeReader，PipeWriter
	4. 合并输入： SequenceInputStream
6. 流缓冲
	1. BufferedInputStream，BufferedOutputStream，BufferedReader,BufferedWriter
## 实例
1. 文件内容追加
````java
/**
 *使用FileWriter，适用于字符文件
 *filename 文件名
 *content 追加内容
*/
//打开一个写文件器，构造函数中第二个参数 true 表示以追加形式写文件
FileWriter writer = new FileWriter(file,true);
writer.writer(content);
writer.flush();
writer.close();
````
````java
/**
 *使用RandomAccessFile
 *filename 文件名
 *content 追加内容
*/
//打开一个随机访问文件流，按读写方式
RandomAccessFile randomFile = new RandomAccessFile(filename,"rw");
//获取文件长度，字节数
Long fileLength = randomFile.length();
//定位文件指针至文件尾
randomFile.seek(fileLength);
randomFile.writeBytes(content);
````
# 面试
1. java中由几种类型的流？jdk为每种类型的流提供了一些抽象类以供继承，请说出它们分别是什么？
	1. 字符流和字节流。字节流继承InputStream和OutputStream，字符流继承InputStreamReader和OutputStreamWriter。在java.io包中还有其它许多流，主要为了提供性能和使用方便。
2. 字符流和字节流是什么？有什么区别？
	1. 字节流处理的基本单位为单个字节。
		1. java中最基本的两个字节流类是InputStream和OutputSteam，它们分别代表了一组基本的输入字节流和输出字节流。我们在实际应用中通常使用他们的子类。
	2. 字符流处理的最基本单元是Unicode码元（大小2字节），它通常用来处理文本数据。
		1. 输出字符流：要把写入文件的字符序列（实际上是Unicode码元序列）转为指定编码方式下的字节序列，然后再写入文件中；
		2. 输入字符流：要把读取的字节序列按指定编码方式解码为相应的字符序列（实际是Unicode码元序列）从而可以存在内存中。
		3. 由于字符流在输出前实际上是要完成Unicode码元序列到相应编码方式的字节序列的转换，所以它会使用内存缓冲区来存放转换后得到的字节序列，等待转换完成后再一同写入磁盘文件中。
	3. 字符流和字节流区别？
		1. 字节流操作的基本单元是字节；字符流操作的基本单元为Unicode码元。
		2. 字节流默认不使用缓冲区；字符流使用缓冲区
		3. 字节流常用于处理二进制数据，实际上他可以处理任意类型的数据，但不支持直接写入或读取Unicode码元；字符流通常处理文本数据，它支持写入及读取Unicode码元。
3. PrintStream、BufferWriter、PrintWriter的比较？
	1. PrintStream类输出功能强大。
		1. 如果输出文本内容，将输出流包装成PrintStream后进行输出。
		2. PrintStream永远不会抛出IOException
		3. 异常情况仅设置可通过checkError方法测试的内部标志
	2. BufferedWriter：将文本写入字符输出流，缓冲各个字符从而提供单个字符、数组和字符串的高效写入。
		1. 通过write()方法可以将获取到的字符输出，然后通过newLine()进行换行操作。BufferedWriter中的字符流必须通过flush()方法才能将其刷出去。
		2. BufferedWriter只能对字符流进行操作。
	3. PrintWriter的println方法自动添加换行，调用checkError方法看是否有异常，PrintWriter构造方法可指定参数，实现自动刷新缓存。
4. 什么是java序列化，如何实现java序列化？
	1. java对象的序列化指将一个java对象写入IO流中，与此对应的是，对象的反序列化则从IO流中恢复该java对象。
	2. 一个类必须是可序列化的，才支持序列化机制。一个类实现Java.io.Serializable
	3. 