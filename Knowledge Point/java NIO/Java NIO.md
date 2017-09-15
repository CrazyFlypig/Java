# Java NIO
* new IO
* 完成高速IO，而无需编写自定义native代码。
* java io包基于stream技术，按照一个字节处理。
* java NIO是基于块，使用分块方式处理数据。比流技术更加快捷。编程不易。
* Buffer和Channel是NIO核心。
* NIO的非阻塞模式，使一个线程从某个通道发送请求读取/写入数据请求，若条件不足以进行操作，则该线程可以做别的事情。线程通常将非阻塞IO的空闲时间用于在其它通道上执行IO操作，所以一个单独的线程也可以个管理多个输入和输出通道（Channel）。
## Buffer
1. 存在于堆缓冲区。
2. [],数组
3. 是容器（包含基本数组类型）对象的核心。
4. 从Channel中读取数据进入buffer中。
5. NIO必须通过Buffer完成数据读写，跟踪读写过程。
6. 术语：
	1. capacity	//容量
	2. limit //限制，第一不能读取的元素索引
	2. position //位置，读写元素的下一个索引
	3. mark //标记
	4. 0 <= mark <= pos <= limit <= capacity
	5. Clearing：清除
	6. flipping：拍板
	7. rewinding：回绕
7. 常见操作：
	1. buffer.limit() //取出limit位置
	2. buffer.limit(int nl) //设置新的limit位置
	3. buffer.position() //得到pos
	4. buffer.position(int) //设置新的pos
	5. buffer.mark() //mark=pos，当前位置做标记
	6. buffer.remaining() //lim - pos空间
	7. buffer.hasRemaining() //lim - pos == 0?
	8. buffer.rewind() //pos=0,mark丢弃（mark=-1），重绕
	9. buffer.clear() //pos=0,lim=cap,mark丢弃，清空
	10. buffer.flip() //lim=pos,pos=0,mark丢弃
	11. buffer.reset() //pos = mark
	12. buffer.slice() //切片，从当前缓冲区中划出前n个元素构造新缓冲区，n等于当前buffer的remaining大小。两个buffer操纵同一byte[]，但有各自的属性，例如limit、pos等。
	13. buffer.compact() //将当前buf中的remaining部分移到最前面并空出，lim放大到capacity。
	14. truncate //mark=-1；position=0;limit=0;capacity=0; 禁用整个缓冲区
8. ByteBuffer wrap（byte[] array) 将现有的字符数组包装成ByteBuffer对象。
### HeapBuffer
* 存储于堆缓冲区
* 获取方式：`ByteBuffer.allocate(size);`
### DirectByteBuffer
* 直接内存缓冲区
* 数据存放在离堆，脱离出JVM
* 获取方式：`ByteBuffer.allocateDirect(size);`
* 在程序结束时，自调用`clean()`方法回收空间。
* 对于开发者来说，DirectByteBuffer是不安全的。主要表现在，在直接内存缓冲区依然被使用和存在引用的情况下，依旧可以通过反射调用`clean()`方法，释放缓冲区，从而引发` A fatal error has been detected by the Java Runtime Environment`错误。
* 除特殊情况下，一般不使用直接内存缓冲区进行开发。
## Channel
1. 打开的连接（硬件，File，Socket，程序）。
	1. SocketChannel sc = SocketChannel.open();sc.connect(new InetSocketAddress(host,port));
	2. 
2. 类似于Stream，数据通过Channel传输。
3. 实现：
	1. FileChannel
	2. SocketChannel
	3. DatagramChannel
4. java.nio使用RandomAccessFile，FileInputStream，FileOutputSteam，Socket，ServerSocket or DatagramSocket object关联通道。
5. NIO buffer可以直接对channel进行读写。
6. ![](http://i.imgur.com/CyfWcbc.png)
## Memory-Mapped Files
* 虚拟内存可以将物理文件映射到一个程序内存中的区域。一旦文件被映射，访问文件就非常快，不必通过系统的read()或write()。
* `map(MapMode, Long position, Long size)`
## 应用
### 文件复制
1. 根据文件路径构造RandomAccessFile对象。
2. 使用RandomAccessFile对象的`getChannel()`方法获取FileChannel对象。
3. 构建ByteBuffer缓冲区对象。
4. 使用缓冲区进行流读取和写入。
## Selector(选择区)
* Selector用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单线程可以监听多个数据通道。
* Selector运行单线程处理多个Channel，如果你的应用打开了多个通道，但每个连接的流量都很低，使用Selector就会很方便。
* 如：
