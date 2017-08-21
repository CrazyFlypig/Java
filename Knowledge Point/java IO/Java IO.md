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
7. fs.available() //获取流长度
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
