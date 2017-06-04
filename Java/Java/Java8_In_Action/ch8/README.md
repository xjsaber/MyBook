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

### 7.3.1 拆分过程 ###
将Stream拆分成多个部分的算法是一个递归过程。

### 7.3.2 实现你自己的Spliterator ###


