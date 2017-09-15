# Interger #
## Integer.valueOf(String)方法 ##
实验代码：

    System.out.println(Integer.vauleOf("127")==Integer.valueOf("127"));
	System.out.println(Integer.valueOf("128")==Integer.valueOf("128"));
	System.out.println(Integer.parseInt("128")==Interger.valueOf("128"));
输出结果：

    true
	false
	true
对上述现象的解释：
Integer.valueOf(String)确有一个不同寻常的行为。**valueOf会返回一个Integer对象，当被处理的字符串在-128-127之间时，返回的对象时预先缓存的**。对于第一行的调用会返回true，127这个整型对象是预先缓存的，所以两次返回的是同一个对象。对于第二行，因为128没有被缓存，所以每次调用，都会生成一个新的整型对象，所以返回false。

**parseInt()返回的不是整型对象，而是一个int型基础的元素**。第三行比较时，因为比较操作符使用了==同时等号的两边存在一个int型和一个Integer对象的引用。所以右边自动拆箱为int值，实际比较的是两个原子型数值。