# String 10个面试问题 #
## 1.如何比较两个字符串？使用"=="还是equals()方法？
"=="测试的是两个对象的引用是否相同，而equals()方法比较的是两个字符串的值是否相等。除非想检查的是两个字符串是否是同一个对象，否则应该使用equals()方法来比较字符串。
##2.为什么针对安全保密高的信息，char[]比String更好？
因为String不可变的，它一旦建立，就不能更改了，直到垃圾收集器将它收走。而字符数组中的元素是可以更改的（意味着你可以在使用完后将其更改，而不保留原始数据）。所以使用字符数组的话，安全保密性高的信息将不会存在于系统中被他人看到。
##3.我们可以针对字符串使用switch条件语句吗？##
对于，JDK7是肯定的。从JDK7开始可以，之前版本不可以。

    //java 7或者以后的版本
    switch(str.toLowerCase()){
    	case "a":
    		value = 1;
    		break;
    	case "b":
			value = 2;
    		break;
    }
##4.如何将字符串用空白字符转换成int？##

    int n = Integer.parseInt("10");
##5.如何将字符串用空白字符分割开？##
我们可以用正则表达式来分割字符串。"\s"代表空格字符，"\t","\r","\n".

    String[] strArray = aString.spilt("\\s+");
##6.substring()方法到底做了什么？
在JDK6中，substring()做法是，用一个字符数组来表示现存的字符串，然后给这个字符数组提供一个“窗口”，但实际并没有创建一个新的字符数组。要创建一个新的字符串对象由新的字符数组表示的话，你需要加一个空字符串，如下示：

    str.substring(m,n)+"";

这会创建一个新的字符数组，用来表示新的字符串。这种方法会让你的代码更快，因为垃圾收集器会收集不用的长字符串，而仅仅保留要使用的字符串。

在JDK7中，substring()会创建新的字符数组，而不是使用现存的字符数组。
##7.String vs StringBuilder vs StringBuffer
String vs StringBuilder:StringBuilder是可变的，这意味着它创建后仍可更改它的值。

StringBuilder vs StringBuffer：StringBuffer是sychronized的，它是线程安全的，但速度慢于StringBuilder。
##8.如何重复一个字符串？##
在Python中，我们可以乘一个数字来重复一个字符串。在Java中，我们可以使用Apache Commons Lang包中的StringUtils.repeat()方法来重复一个字符串。

    String str = "abcd";
	String repeated = StringUtils.repeat(str,2);
	//abcdabcd
##9.如何将字符串转换成时间？##

    String str = "Sep 17,2013";
	Date date = new SimpleDateFormat("MMMM d,yy",locate.ENGLISH).parse(str);
	System.out.println(date);
	//Tue Sep 17 00:00:00 EDT 2013
##10.如何计算一个字符串某个字符的出现次数？
使用apache commons lang包中的StringUtils:

    int n = StringUtils.countMatches("11112222","1");
	System.out.println(n);\