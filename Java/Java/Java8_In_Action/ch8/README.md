# 第8章 重构、测试和调试 #

## 8.1 并行流 ##

### 8.1.1 改善代码的可读性 ###

### 8.1.2 从匿名类到 Lambda 表达式的转换 ###

	Runnable r1 = new Runnable(){
		public void run(){
			System.out.println("Hello");
		}
	};
	Runnable r2 = () -> System.out.println("Hello");

1. 匿名类和Lambda表达式中的 this 和 super 的含义是不同的。在匿名类中， this 代表的是类自身，但是在Lambda中，它代表的是包含类。其次，匿名类可以屏蔽包含类的变量，而Lambda表达式不能（它们会导致编译错误）。
2. 在涉及重载的上下文里，将匿名类转换为Lambda表达式可能导致最终的代码更加晦涩。

### 8.1.3 从Lambda表达式到方法引用的转换 ###

	Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
		menu.stream()
			.collect(
				groupingBy(dish -> {
					if (dish.getCalories() <= 400) return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
					else return CaloricLevel.FAT;
				}));

	Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
	menu.stream().collect(groupingBy(Dish::getCaloricLevel));
	public class Dish{
		…
		public CaloricLevel getCaloricLevel(){
			if (this.getCalories() <= 400) return CaloricLevel.DIET;
			else if (this.getCalories() <= 700) return CaloricLevel.NORMAL;
			else return CaloricLevel.FAT;
		}
	}

#### 8.1.4 从命令式的数据处理切换到Stream ####

筛选和抽取

#### 8.1.5 增加代码的灵活性 ####

1.采用函数接口

2.有条件的延迟执行

日志器的状态（它支持哪些日志等级）通过 isLoggable 方法暴露给了客户端代码。

3.环绕执行

## 8.2 分支/合并框架 ##

### 8.2.1 策略模式 ###

策略模式代表了解决一类算法的通用解决方案，你可以在运行时选择使用哪种方案。

* 一个代表某个算法的接口（它是策略模式的接口）
* 一个或多个该接口的具体实现，它们代表了算法的多种实现（比如，实体类 Concrete-StrategyA 或者 ConcreteStrategyB ）
* 一个或多个使用策略对象的客户

### 8.2.2 模版方法 ###

	public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy.accept(c);
	}
	new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello " + c.getName());

### 8.2.3 观察者模式 ###

### 8.2.4 责任链模式 ###

### 8.2.5 工厂模式 ###

## 8.3 测试Lambda表达式 ##

	public interface Spliterator<T> {
		boolean tryAdvance(Consumer<? super T> action);
		Spliterator<T> trySplit();
		long eastimateSize();
		int characteristics();
	}

### 8.3.1 测试可见Lambda函数的行为 ###
将Stream拆分成多个部分的算法是一个递归过程。

### 8.3.2 测试使用Lambda的方法的行为 ###


### 8.3.3 将复杂的 Lambda 表达式分到不同的方法 ###


### 8.3.4 高阶函数的测试 ###


## 8.4 调试 ##

* 查看栈跟踪
* 输出日志

### 8.4.1 查看栈跟踪 ###

#### Lambda表达式和栈跟踪 ####

	at Debugging.lambda$main$0(Debugging.java:6)
	at Debugging$$Lambda$5/284720968.apply(Unknown Source)
这些表示错误发生在Lambda表达式内部。由于Lambda表达式没有名字，所以编译器只能为它们指定一个名字。看起来非常不直观。

涉及Lambda表达式的栈跟踪可能非常难理解

### 8.4.2 使用日志调试 ###

	numbers.stream()
	.map(x -> x + 17)
	.filter(x -> x % 2 == 0)
	.limit(3)
	.forEach(System.out::println);

一旦调用 forEach ，整个流就会恢复运行。到底哪种方式能更有效地帮助我们理解Stream流水线中的每个操作（比如 map 、 filter 、 limit ）产生的输出？

	peek

peek 的设计初衷就是在流的每个元素恢复运行之前，插入执行一个动作。但是它不像 forEach 那样恢复整个流的运行，而是在一个元素上完成操作之后，它只会将操作顺承到流水线中的下一个操作。

	List<Integer> result =
	numbers.stream()
	.peek(x -> System.out.println("from stream: " + x))
	.map(x -> x + 17)
	.peek(x -> System.out.println("after map: " + x))
	.filter(x -> x % 2 == 0)
	.peek(x -> System.out.println("after filter: " + x))
	.limit(3)
	.peek(x -> System.out.println("after limit: " + x))
	.collect(toList());

## 8.5 小结 ##

* Lambda表达式能提升代码的可读性和灵活性。
* 如果你的代码中使用了匿名类，尽量用Lambda表达式替换它们，但是要注意二者间语义的微妙差别，比如关键字 this ，以及变量隐藏。
* 跟Lambda表达式比起来，方法引用的可读性更好 。
* 尽量使用Stream API替换迭代式的集合处理。
* Lambda表达式有助于避免使用面向对象设计模式时容易出现的僵化的模板代码，典型的比如策略模式、模板方法、观察者模式、责任链模式，以及工厂模式。
* 即使采用了Lambda表达式，也同样可以进行单元测试，但是通常你应该关注使用了Lambda表达式的方法的行为。
* 尽量将复杂的Lambda表达式抽象到普通方法中。
* Lambda表达式会让栈跟踪的分析变得更为复杂。
* 流提供的 peek 方法在分析Stream流水线时，能将中间变量的值输出到日志中，是非常有用的工具。