# java.util. Interface Set<E> #
## 用Map对象创建Set对象 ##
HashSet其实是一个披着Set方法外衣的HashMap，同样，TreeSet也是一个披着Set方法外衣的TreeMap。**Map不支持直接用迭代器进行遍历**，我们可以通过遍历Map中的Key集合、value集合和entry集合来实现Map的遍历。由于Map中的value是可以重复出现的，因此返回一个Collection集合。而Map中的key是不允许重复的，因此keySet()方法和entrySet()返回的都是Set类型的集合。

因此，我们可以用value来遍历Map：

    Map<String.Double> salaries = new HashMap<>();
	for(double salary : salaries.values()){
	}
可以通过key来遍历Map：

    Map<Stirng,Double> salaries = new HashMap<>();
	for(String name : salaries.keySet()){
	}
也可以同通过entry来遍历Map：

    Map<String,Double> salaries = new HashMap<>();
	for(Map.Entry<String,Double> entry: salaries.entrySet()){
		String name = entry.getKey();
		double salary = entry.getValue();
	}
程序员一般遍历Map：先获取keySet，然后对keys进行遍历，并通过get()方法找到对应的vale。

从直观上看，采用遍历entry的方式更高效，时间复杂度是O(n)。对于分布均与的HashMap，调用get()方法的时间复杂度O(1),则entry与keySet都是O(n)。

在Java 6中，java.util.Collections类提供一个newSetFromMap()方法，该方法能够基于指定的Map对象创建一个新的Set对象。在创建这个Map<K,V>对象时，K的数据类型必须与你想要创建的类型一致；而V必须是Boolean类型的，标记该元素是否存在。

    import java.util.*;
	import java.util.concurrent.*;

	public class ConcurrentSetTest{
		public static void main(String[] args) {
			Set<String> names = Collections.newSetFromMap(new ConcurrentHashMap<String,Boolean>());
			names.add("Brain Goetz");
			names.add("Victor Grazi");
			names.add("Heinz Kabutz");
			names.add("Brain Goetz");
			System.out.println("names =" +names)
		}
	}
newSetFromMap()方法只能返回标准Set接口类型对象。在调用此方法时，指定映射必须为空，并且不能在此方法返回之后直接访问。如果将映射创建为空，直接传递给此方法，并且没有保留对该映射的引用，则这些条件都可以得到保证，如以下代码片段所示：

    Set<Object> weakHashSet = Collections.newSetFromMap(new WeakHashMap<Object, Boolean>());