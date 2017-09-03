# JDK7 文件操作和异常处理
## 异常改进
### 1. try-with-resources
````java
try{
	//使用流对象
	stream.read();
	stream.write();
} catch(Exception e){
	//处理异常
} finally{
	if(stream != null){
		stream.close();
	}
}
````
* 之前的try-catch-finally异常处理机制，不仅繁琐，而且finally模块会抛出异常。
* 在JDK7中提出try-with-resource机制，它规定你操作的类只要是实现了AutoCloseable接口就可以在try语句块退出的时候自动调用close方法关闭流资源。
````java
public static void tryWithResources() throws IOException {
        try( InputStream ins = new FileInputStream("/home/biezhi/a.txt") ){
            char charStr = (char) ins.read();
            System.out.print(charStr);
        }
    }
````
* 使用多个资源时
````java
public static void tryWithResources() throws IOException {
        try ( InputStream is  = new FileInputStream("/home/biezhi/a.txt");
              OutputStream os = new FileOutputStream("/home/biezhi/b.txt")
        ) {
            char charStr = (char) is.read();
            os.write(charStr);
        }
    }
````
* 使用非标准库的类也可以自定义AutoCloseable，只要实现其close方法即可。
### 2. 捕获多个异常
* JDK允许捕获多个异常
````java
	try {
            Thread.sleep(2000);
            FileInputStream fin = new FileInputStream("xx");
        } catch (InterruptedException | FileNotFoundException e) {
            e.printStackTrace();
        }
````
* 使用`|`符分隔异常，并且catch语句后面的异常参数是final的，不可以再修改/赋值。
### 3. 处理反射异常
* 有时候操作反射方法的时候会抛出很多不相关的检查异常，如：
````java
	try {
            Class<?> clazz = Class.forName("java.io.FileInputStream");
            clazz.getMethods()[0].invoke(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
````
* 尽管可以使用catch多个异常的方法将上述异常都捕获，但令人不爽。
* JDK7修复了这个缺陷，引入了一个新类ReflectOperationException可以捕获这些反射异常。
````java
	try {
            Class<?> clazz = Class.forName("java.io.FileInputStream");
            clazz.getMethods()[0].invoke(object);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
````
## 文件操作
### Path
* Path用于表示文件路径和文件，与File对象类似，它是一个路径的抽象序列。
* 创建一个Path对象有多种方法：
	1. 从路径字符串构造
	````java
	    Path path1 = Paths.get("XX/XXX/XX","a.txt");
        Path path2 = Paths.get("XX/XXX/XX/a.txt");
        URI u = URI.create("file:////home/xx/a.txt");
        Path pathURI = Paths.get(u);
	````
	2. 通过FileSystem构造
	````java
        Path filePath = FileSystems.getDefault().getPath("/home/xx","a.txt");
	````
	3. Path、URI、File之间的转换
	````java
        File file = new File("/home/xx/a.txt");
        Path p1 = file.toPath();
        p1.toFile();
        file.toURI();
	````
### 读写文件
* 使用Files类快速实现文件操作。
    1. 读取文件内容。
    ````java
        byte[] data = Files.readAllBytes(Paths.get("/home/a.txt"));
        String content = new String (data,StandardCharsets.UTF_8);
    ````
    2. 按行读取文件。
    ````java
        List<String> lines = Files.readAllLines(Path.get("/home/a.txt"));
    ````
    3. 将字符串写入文件。
    ````java
        Files.write(Path.get("/home/a.txt"),"Hello JDK7!".getBytes());
    ````
    4. 按照行写入文件,Files.write方法的参数中支持传递一个实现Iterable接口的类实例。
    5. 将内容追加到指定文件可以使用write方法的第三个参数OpenOption。
    ````java
        Files.write(Paths.get("/home/a.txt"),"Hello JDK7".getBytes(),StandardOption.APPEND);
    ````
    6. 默认情况Files类中的所有方法使用UTF-8编码进行操作，当你不愿意这么干时可以传递Charset参数进去变更。
    7. 其它常用方法。
    ````java
        InputStream ins = Files.newnInputStream(path);
        OutputStream ops = Files.newOutputStream(path);
        Reader reader = Files.newBufferedReader(path);
        Writer writer = Files.newBufferedWriter(path);
    ````
### 创建、移动、删除
* 创建文件、目录
````java
    if(!Files.exists(path)){
        Files.createFile(path);
        Files.createDirectory(path);
    }
````
* Files还提供了一些方法让我们创建临时文件/临时目录：
````java
    Files.createTempFile(dir,prefix,suffix);
    Files.createTempFile(prefix,suffix);
    Files.createTempDirectory(dir,prefix);
    Files.createTempDirectory(prefix);
````
* 代码中的dir是一个Path对象，并且字符串prefix和suffix都可能为null。例如调用`Files.createTempFile(null,".txt")`会返回一个类似/tmp/21211555455245245.txt.
* 读取一个目录下的所有文件请使用Files.list和Files.walk方法。
* 复制、移动一个文件内容到某个路径
````java
    Files.copy(in,path);
    Files.move(path,path);
````
* 删除一个文件
````java
    Files.delete(path);
````
---
# java 8 字符串、集合、注解等
* java8是一个较大改变的版本，包含了API和库方面的修正，它还对我们常用的API进行很多调整。
## 字符串
* 将数组中的字符串拼接成用逗号分隔的一长串。Java8中添加了join方法实现：
````java
    String str = String.join(",","a","b","c");
````
* 第一个参数是分隔符，后面接收一个CharSequence类型的可变参数数组或一个Iterable。
## 集合

