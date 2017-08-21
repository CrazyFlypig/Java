# 集合类
## 0.数组与集合
### 数组
1. 元素类型必须相同，包括基本数据类型和对象引用。
2. 数组长度固定。
3. 数组地址连续
4. 可使用下标访问
5. 易抛出null指针异常和索引越界异常
### 集合
1. 只能存储对象
2. 容器
3. 长度可变
4. 元素类型可以不同
5. 元素个数size
6. 位于 java.util.*;
7. 判断集合有效性：先判断集合是否存在，在判断集合不为空。顺序相反时，会发生逻辑短路。
## == 和 equals
1. ==
	* 判断对象是否是一个。地址是否相同
2. equals
	* 判断对象内容是否相同，默认调用Object的equals()方法，而该方法判断地址是否相同
## 1.List


### 特点
1. 可重复
	* 只与equals()方法有关
2. 有序
### ArrayList 线程不安全
  --------class java.util.ArrayList
1. 内部封装数组，扩容机制是数组复制
2. 内存连续
2. 查快，删、增慢
### Vector
* 线程安全版ArrayList
* 不推荐使用
### LinkedList 线程不安全
* 链表结构的集合
1. 双指针节点，双向链表，无环
2. 增删快，查慢
## 2.Set


1. 不可重复，可用于去重
	* 依赖于equals()方法
		* 一般需要重写该方法实现对象内容的判断。
2. Set是特殊的Map，其value是一个占位的垃圾对象。
2. 只可通过迭代器迭代访问元素
### HashSet 线程不安全
1. 内部实现调用HashMap
2. 与HashMap完全相同
3. 存取速度快
### TreeSet 线程不安全
* 线程不安全，可以对Set进行排序
* 通过compareTo或compare方法来保证元素的唯一性，元素以二叉树形式存放。
* 排序实现方式（两种法则）：
	* ````Comparable````接口，实现````compareTo()````方法。
		* 数据类实现Comparable接口，实现compareTo()方法
	* ````Comparator````,对比器。
		* 定义数据特有的Comparator对比器类，实现Comparator对比器，实现compare()方法。定义集合时，把对比器对象作为参数传递。
## 3.Map


  --interface java.util.Map
1. 映射 key-value
2. Entry集合。一个Entry对应一个key-value对
3. key不重复，无序，同set
4. Map.size是Entry个数
5. Map接口是单独接口，没有继承任何接口，与之前版本不同，但引用了collection接口
6. put()
	1. 将key和value关联，如果key存在，则value被覆盖
6. Map集合没有直接取出元素方法，先转成set集合，再通过迭代获取
7. get(key)
8. entrySet
9. keySet;
### HashMap 线程不安全
  --class java.util.AbstractMap
  ---class java.util.HashMap
* 数组+链表
* 存取速度块
1. hash()实现原理，为什么：
	1. 获取key的hashcode，然后````^````右移16位后值得到一个Newhash,移位目的与更多特征值相关，异或为了使对象更加分散（与运算积聚在低位，或运算积聚在高位）
	2. Newhash与数组长度减一与运算得到在数组下标范围内的值。桶的概念
2. clear 清空桶
2. 元素重复判断标准 ： ````p.hash == hash && ((k = p.key ) == key || (key != null && key.equals(k)))````
	1. new hash不同，对象不同
	2. new hash相同，对象是同一对象，则对象相同。
	3. new hash相同，对象不是同一对象，在判断equals方法，则对象相同性判定和equals一致。
	* hash不相等 > hash相等
3. 数据类型：Node<K,V>
	1. int hash
	2. final Key：key
	3. Value : value
	3. next ： Node<K,V>
4. 自定义Key数据，需重写````hash()````和````equals()````。使用按位运算来实现，所有属性都存至一个整型值，判断两个是否相等
````java
//重写hashCode(),返回三个特征值的hashcode
public int hashCode(){
	return this.height | this.weight <<8 | this.age << 16 ;
}
//重写equals方法，判断是否是同一对象，判断内容是否相等
public boolean equals( Object obj ){
	if (obj == null ) return false;
	if (obj == this ) return true;
	if ( obj instanceof Student ){
		return this.hashCode == obj.hashCode();
	}
}
````
### TreeMap
* 对key进行排序，排序原理与TreeSet相同
* key - value
### HashTable 线程安全
## 4.collections


* 集合工具类
1. binarySearch
2. copy
3. synchronizedList
	1. ````List<String> names = Collections.synchroizedList(new ArrayList<String>());````
4. max
5. sort
	1. 指定对比器对象
### arraycopy
* 数组拷贝，类型不限制
	* 基本类型拷贝内容数据副本
	* 对象拷贝内容是对象地址（引用），对象具有一致性
## 5.Arrays 数组工具类
* asList()将参数转换成Array$ArrayList集合对象
* sort 数组排序
* binarySearch 二分查找
## 6.foreach（）
* 增强for()循环
* 数组可以、集合可以、Iterable<T>
## 变长参数
````java
public void adTel (String... str){
	for( String s : str){
		tels.add(s);
	for( int i=0; i<str.length; i++){
		tels.add(str[i]);
}
````
* 三点表示变长参数，使用增强for循环遍历变长参数的值,相当于数组
* 没有空值异常，即参数可零个或多个
* 变长参数必须位于方法最后一个参数，一个方法不能有多个变长参数
# 面试
1. java集合框架的优点：
	1. 使用核心集合类降低开发成本，不用自己开发类
	2. 使用经过严格测试的集合框架类，代码质量会提高
	3. 通过使用jdk附带的集合类，可以降低代码维护成本
	4. 复用性和可操作性
2. 集合框架中泛型有什么优点：
	1. 泛型允许我们为集合提供一个可以容纳的对象类型。将添加错误对象类型的问题在编译期间暴露，避免运行时出现ClassCastException。
	2. 泛型使得代码整洁，避免了显式类型转换和instanceOf操作符。
	3. 运行时，不会产生类型检查的字节码指令。
3. Java集合框架的基础接口有哪些？
	1. Collection为集合层级的根接口。一个集合代表一组对象，这些对象即为它的元素。Java平台不提供这个接口的任何直接实现。
	2. Set是一个不能包含重复元素的集合。
	3. List是一个有序集合，可以包含重复元素。可通过索引来访问任何元素。
	4. Map是一个将key映射到value的对象。一个Map不能包含重复的key：每个key只能映射一个value。
	5. 一些其它接口Queue、Dequeue、SortedSet，SortedMap和ListIterator。
4. 为何Collection不从Cloneable和Serializable接口继承？
	1. Collection接口定义一组对象，如何维护这些对象由Collection的具体实现决定。Collection是一个抽象表现
	2. 当与具体实现打交道时，克隆或序列化的语义和含义才发挥作用。所以，具体实现应该决定如何对它进行克隆或序列化。
5. 为何Map接口不继承Collection接口？
	1. Map不是集合，集合也不是Map，继承Collection毫无意义。
	2. Map包含Key-value，它提供抽取key或value列表集合的方法，不适合“一组对象”规范。
6. Iterator是什么？
	1. Iterator接口提供遍历任何Collection的接口。
	2. Iterator取代了Java集合框架中的Enumeration。因为迭代器允许调用者在迭代过程中移除元素。
7. Enumeration和Iterator接口区别？
	1. Enumeration的速度是Iterator的两倍，使用更少的内存。
	2. Iterator更加安全（当一个集合正在被遍历的时候组织其它线程取修改集合），允许调用者从集合中移除元素。
8. 为何没有`Iterator.add()`这样的方法，向集合中添加元素
	1. 语义不明
9. 为何迭代器没有一个方法可以直接获取下一个元素，而不需要移动游标？
	1. 可以在当前Iterator的顶层实现，但用得少，如果添加至接口，每个类都去实现，没有意义
10. Iterator和ListIterator之间的区别？
	1. Iterator可以遍历Set和List，而ListIterator只能遍历List。
	2. Iterator只能向前遍历，而ListIterator可以双向遍历
	3. ListIterator从Iterator接口继承，然后添加一些额外的功能，比如添加一个元素，替换一个元素，获取前面或后面元素的索引位置。
11. 遍历一个List有哪些不同方式？
	1. for-each循环
	2. Iterator
	3. 使用Iterator更安全，若遍历时集合被修改，它会抛出ConcurrentModificationException。
12. 迭代器的fail-fast属性是什么？
	1. 当我们尝试获取下一个元素的时候，Iterator fail-fast属性检查当前集合结构里的任何改动。如果出现改动，则抛出ConcurrentModificationException。Collection中所有Iterator的实现都是按fail-fast来设计，除ConcurrentHashMap和CopyOnWritable这类的并发集合除外
13. fail-fast和fail-safe有什么区别？
	1. fail-fast会抛出ConcurrentModificationException
	2. fail-safe不会抛出
14. 在迭代一个集合时，如何避免ConcurrentModificationException？
	1. 使用并发集合来避免
15. Iterator接口没有具体的实现？
	1. Iterator定义遍历集合的方法，它的实现则是集合实现类的责任。每个能够返回用于遍历的Iterator的集合类都有它自己的Iterator的实现内部类。
	2. 允许集合类去选择fail-fast还是fail-safe的。
16. UnsupportedOperationException是什么？
	1. 用于表明操作不支持的异常。在集合框架java.util.Collections.UnmodifiableCollection将会在所有add和remove操作中抛出这个异常。
17. 在Java中，HashMap是如何工作的？
	1. HashMap在Map.Entry静态内部类中实现存储key-value对。
	2. HashMap使用哈希算法，在put和get方法中，它使用hashCode()和equals()方法。
	3. 当我们通过传递key-value对调用put方法的时候，HashMap使用Key hashCode()和哈希算法来找出存储key-value的索引。Entry存储在LinkedList中，如果存在Entry，使用equals()方法检查传递的key是否已经存在，如果存在，它会覆盖value值，如果不存在，它会创建一个新的entry然后保存。
	4. 当我们通过传递key调用get方法是，它再次使用hashCode()来找到数组中的索引，然后使用equals()方法找出正确的Entry，然后返回它的值。
	5. HashMap默认初始容量是32，负荷系数是0.75。我们尝试添加一个Entry时，如果map的大小比阈值大时，HashMap会对map的内同进行重新哈希，且使用更大的容量。容量总是2的幂。
18. hashCode()和equals()方法有何重要性？
	1. HashMap使用Key对象的hashCode()和equals()方法去决定key-value对的索引。
19. 能否使用任何类作为Map的Key？
	1. 可以使用任何类作为Map的key，但需考虑以下几点：
	2. 类需要重写equals()方法和hashCode()方法。
	3. 尽量使用按位运算来实现，所有属性都存至一个整型值，判断两个是否相等
	4. 用户自定义key类的最佳实践是使之为不可变的，这样hashCode()值可以被缓冲起来，拥有更好的性能。不可变类也可以确保hashCode()和equals()在未来不会改变，这样就解决与可变相关的问题了。
20. Map接口提供哪些不同的集合视图？
	1. Set keyset():返回map包含的所有key的一个Set视图
	2. Collection values():返回一个map中包含的所有value的一个Collection视图。
	3. Set<Map.Entry<K,V>> entrySet():返回一个map所包含的所有映射的一个集合视图。
21. HashMap和HashTable有何不同？
	1. HashMap允许key和value为null，而HashMap不允许
	2. HashMap适合单线程环境，HashTable是同步的，适合多线程环境。
	3. HashMap的一个子类LinkedHashMap有序，而HashTable顺序不可知
	4. HashMap提供对key的Set进行遍历，因此它是fail-fast的，但HashTable提供对Key的Enumeration进行遍历，它不支持fail-fast。
	5. HashTable被认为是个遗留的类，如果你寻求在迭代的时候修改Map，应该使用ConcurrentHashMap。
22. 如何决定选用HashMap还是TreeMap？
	1. 对于在Map中插入、删除和定位元素这类操作，HashMap是最好的选择。
	2. 需要一个有序key集合进行遍历，TreeMap是更好的选择。
23. ArrayList和Vector有何异同？
	1. 相同点：
		1. 两者都是基于索引的，内部由一个数组支持
		2. 两者维护插入顺序
		3. 两者的迭代器都是fail=fast的
		4. 都允许存在null值，支持索引的随机访问。
	2. 不同点：
		1. Vector是同步的，而ArrayList不是。寻求在迭代时对列表进行改变，应该使用CopyOnWritableArrayList。
		2. ArrayList速度优于Vector，因为无同步
		3. ArrayList更加通用
24. ArrayList和Array有什么区别？
	1. Array可以容纳基本类型和对象，而ArrayList只能容纳对象
	2. Array大小是固定的，而ArrayList大小是可以动态扩展的
	3. 当以下情况Array比较好用
		1. 列表大小已经指定，大部分情况是存储和遍历它们
		2. 在指定大小的基本类型的列表上，Collections使用自动装箱，速度变慢
		3. 使用多维数组，使用[][]比List<List<>>更容易。
25. ArrayList和LinkedList有何区别？
	1. ArrayList是由Array所支持的基于一个索引的数据结构，它提供对元素的随机访问，复杂度为O(1)；LinkedList存储一系列双指针节点数据，底层是基于双向链表实现的。元素访问是基于链表遍历，时间复杂度O(n)。
	2. ArrayList的查改性能优，而LinkedList增删性能优。
	3. LinkedList的内存消耗高于ArrayList，因为LinkedList每个节点存储双引用。
26. 哪些集合类提供对元素的随机访问？
	1. ArrayList，HashMap，TreeMap，HashTable
27. EnumSet是什么？
	1. 使用枚举类型的集合实现。
	2. 枚举集合中的所有元素必须来自单个指定的枚举类型，可以显式或隐式的。
	3. EnumSet是不同步的，不允许值为null的元素。
28. 哪些集合类是线程安全的？
	1. Vector，HashTable，Properties，Stack
29. 并发集合类
	1. java.util.concurrent包含线程安全集合类，允许在迭代时修改集合。
30. BlockingQueue是什么？
	1. 是一个队列。主要用于实现生产者-消费者模式。
31. Collections类是什么？
	1. 一个工具类仅包含静态方法。
	2. 包含操作集合的多态算法。
32. Comparable何Comparator接口是什么？
	1. 使用Array或Collection的排序方法时，需要在自定义类中实现Comparable接口，重写compareTo(T OBJ)方法。只能基于一个字段进行排序。this对象阐述更大或相等时，返回正整数或0。
	2. Comparator接口的compare(Object o1,Object o2)方法实现传递两个对象参数。若第一个大于第二个返回正整数，相等返回0
33. Comparable和Comparator接口有何区别？
	1. 都是用来对对象集合或数据进行排序。
	2. Comparable用它提供基于单逻辑的排序。
	3. Comparator接口被用来提供不同的排序算法。
34. 如何对一组对象进行排序？
	1. 可以使用Arrays.sort()或Collection.sort()方法进行排序
	2. 可以使用Comparable的自然排序，也可使用Comparator的基于标准排序。
	3. Collections另需花时间将列表转换为数组。
35. 当一个集合被作为参数传递给一个函数时，如何才能确保函数不能修改它？
	1. 在作为参数传递之前，我们可以使用Collections.unmodifiableCollection(Collection c)方法创建一个只读集合，这将确保改变集合的任何操作都会抛出UnsupportedOperationException。
36. 如何从给定集合哪里创建一个synchronized的集合？
	1. 使用Collections。synchronizedCollection（Collection c）根据指定集合来获取一个synchronized（线程安全）集合。
37. 与Java集合框架相关的有哪些好的实践？
	1. 根据需要选择合适的集合类型
	2. 一些集合类允许指定初始容量，如果我们能够估计到存储元素的数量，我们可以使用它，就避免了重新哈希或大小调整
	3. 基于接口编程而非基于实现编程，它允许我们后来可以轻易地改变实现
	4. 使用类型安全的泛型，避免运行时ClassCastException
	5. 使用Jdk提供的类作为Map的key，可以避免自己实现hashCode()和equals()。
	6. 尽可能使用Collections工具类，或者获取只读、同步或空的集合。提高代码重用性，更稳定和可维护。