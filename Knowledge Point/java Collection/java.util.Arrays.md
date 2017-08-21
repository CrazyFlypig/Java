# Arrays #
## Arrays.sort() ##
**Arrays.sort(T[],Comparator <? super T>c)是用来对用户自定义的对象数组进行排序。**
### 1.简单实例：如何使用Arrays.sort() ###
    import java.util.Arrays;
	import java.util.Comparator;

		class Dog{
		int size;
		public Dog(int s){
			size = s;
		}
	}
	class DogSizeComparator implements Comparator<Dog>{
		@Override
		public int comparator(Dog 01,Dog o2){
			return 01.size-o2.size
		}
	}
	public class ArraySort{
		public static void main(String[] args) {
		Dog d1 = new Dog(2);
		Dog d2 = new Dog(1);
		Dog d3 = new Dog(3);

		Dog[] dogArray = {d1,d2,d3};
		printDogs(dogArray);

		Arrays.sort(dogArray,new DogSizeComparator());
		printDogs(dogArray);		
		}
		public static void printDogs(Dog[] dogs)
		{
			for (Dog d:dogs ) {
				System.out.print(d.size+" ");
			}
			System.out.println();
		}
	}
输出：

    2 1 3
	1 2 3
### 2.策略模式的使用 ###
**策略模式使得不同的算法在运行时得以选择。**通过传递不同的Comparator，可以选择不同的算法。基于上例可以创建一个新的Comparator：

    import java.util.Arrays;
	import java.util.Comparator;
	
	class Dog{
		int size;
		int weight;
		public Dog(int s,int w){
			size = s;
			weight = w;
		}
	}
	class DogSizeComparator implements Comparator<Dog>{
		@Override
		public int compare(Dog 01,Dog o2){
			return 01.size-o2.size
		}
	}
	class DogWeightComparator implements Comparator<Dog>{
		@Override
		public int compare(Dog o1,Dog o2)
		{
			return o1.weight-o2.weight;
		}
	}
	public class ArraySort{
		public static void main(String[] args) {
		Dog d1 = new Dog(2,50);
		Dog d2 = new Dog(1,30);
		Dog d3 = new Dog(3,40);
	
		Dog[] dogArray = {d1,d2,d3};
		printDogs(dogArray);
	
		Arrays.sort(dogArray,new DogSizeComparator());
		printDogs(dogArray);
	
		Arrays.sort(dogArray,new DogWeightComparator());
		printDogs(dogArray);		
		}
		public static void printDogs(Dog[] dogs)
		{
			for (Dog d:dogs ) {
				System.out.print(d.size+" ");
			}
			System.out.println();
		}
	}
输出：

    size=2 weight=50 size=1 weight=30 size=3 weight=40
	size=1 weight=30 size=2 weight=50 size=3 weight=40
	size=2 weight=30 size=3 weight=40 size=2 weight=50
Comparator仅仅是一个接口，任何实现Comparator在运行时都可以被使用，这是策略模式的核心理念。
### 3.为什么使用"super" ###
"Comparator< ? super T >c",使用<? super T>意味着类型可以是T或者它的超类。**这种方式允许所有的子类使用同一个comparator**。

    	import java.util.Arrays;
	import java.util.Comparator;

	class Animal{
		int size;
	}
	class Dog extends Animal{
		public Dog(int s)
		{
			size = s;
		}
	}
	class Cat extends Animal{
		public Cat(int s)
		{
			size = s;
		}
	}
	class AnimalSizeComparator implements Comparator<Animal>{
		@Override
		public int compare(Animal o1,Animal o2)
		{
			return o1.size-o2.size
		}
		//in this way,all sub classes of Animal can use this comparator.
	}
	public class ArraySort {
    	public static void main(String[] args) {
        	Dog d1 = new Dog(2);
        	Dog d2 = new Dog(1);
        	Dog d3 = new Dog(3);
        	Dog[] dogArray = {d1, d2, d3};
        	printDogs(dogArray);
 
	        Arrays.sort(dogArray, new AnimalSizeComparator());  
    	    printDogs(dogArray);
 	
    	    System.out.println();
     	    //when you have an array of Cat, same Comparator can be used. 
        	Cat c1 = new Cat(2);
        	Cat c2 = new Cat(1);
        	Cat c3 = new Cat(3);
         	Cat[] catArray = {c1, c2, c3};
        	printDogs(catArray);
         	Arrays.sort(catArray, new AnimalSizeComparator());  
        	printDogs(catArray);
    	}
   	  public static void printDogs(Animal[] animals){
    	    for(Animal a: animals)
        	    System.out.print("size="+a.size + " ");
        	System.out.println();
    	}
	}
输出：

    size=2 size=1 size=3
	size=1 size=2 size=3

	size=2 size=1 size=3
	size=1 size=2 size=3
### 4.总结 ###
1. generic(泛型)————super
2. 策略模式
3. 归并排序————nlog(n)时间复杂度
4. java.util.Collections.sort( List<T>list,Comparator<> super T>c)类似于Arrays.sort