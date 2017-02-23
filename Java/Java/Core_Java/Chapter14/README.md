# 第14章 多线程 #
一个程序同时执行多个任务。通常，每一个任务称为一个线程（thread），它是线程控制的简称。可以同时运行一个以上线程的程序称为多线程程序（multithreaded）。

线程共享资源。共享变量使线程之间的通信比进程之间的通信更有效、更容易。

## 14.1 什么是线程 ##

1)
	//
	public interface Runnable{
		void run();
	}
	//
	class MyRunnable implements Runnable{
		public void run() {
			task code
		}
	}
	// 创建一个类对象
	Runnable runnable = new MyRunnable();
	// 由Runnable创建一个Thread对象
	Thread thread = new Thread(r);
	// 启动线程
	t.start()

** 已经不推荐 **
应该从运行机制上减少需要并行运行的人物数量。如果有很多人物，要为每个任务创建一个独立的线程所付出的代价太大了。可以使用*线程池*来解决这个问题
	class MyThread extends Thread{
		public void run(){
			task code
		}
	}

PS：直接调用Thread类火Runnable对象的run方法，只会执行同一个线程中的任务，而不会启动新线程。

## 14.2 中断线程 ##

### 4.2.1 对象与对象变量 ###

### 4.2.2 Java类库中的GregorianCalendar类 ###

### 4.2.3 更改器方法与访问器方法 ###

	GregorianCalendar now = new GregorianCalenda();
	int month = now.get(Calendar.MONTH);
	int weekday = now.get(Calendar.DAY_OF_WEEK);

	dealine.set(Calendar.YEAR, 2001);
	dealine.set(Calendar.MONTH, Ca;emdar.APRIL);
	dealine.set(Calendar.DAY_OF_MONTH, 15);

## 4.3 用户自定义类 ##
强类型语言

### 4.3.1 Employee类 ###
[Employee类](code/4_1_Employee.java)

### 4.3.2 多个源文件的使用 ###

### 4.3.3 剖析Emplyee类 ###

### 4.3.4 从构造器开始 ###

### 4.3.5 隐式参数与显式参数 ###

### 4.3.6 封装的优点 ###
不要编写返回引用可变对象的访问器方法，如果需要访问一个可变对象，则应该先对它进行克隆（clone）。

	public Date getHireDay()
	{
		return hireDay.clone();
	}

### 4.3.7 基于类的访问权限 ###

### 4.3.8 私有方法 ###

### 4.3.9 final实例域 ###

	final
## 4.4 静态域与静态方法 ##

### 4.4.1静态域 ###

### 4.4.2静态常量 ###

### 4.4.3静态方法 ###

### 4.4.4工作方法 ###

	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
	NumberFormat percentFormatter = NumberFormat.getPercentInstance();
	double x = 0.1;
	System.out.println(currencyFormatter.format(x));
	System.out.println(percentFormatter.format(x));
* 无法命名构造器。
* 当使用构造器时，无法改变所构造的对象类型
### 4.4.5main方法 ###

## 4.5 方法参数 ##

## 4.6 对象构造  ##

### 4.6.1 子重载 ###

### 4.6.2 默认域初始化 ###

### 4.6.3 无参数的构造器 ###

### 4.6.4 显示域初始化 ###

### 4.6.5 参数名 ###

### 4.6.6 调用另一个构造器 ###
关键字this引用方法的隐式参数。

### 4.6.7 初始化快 ###

### 4.6.8 对象析构与finalize方法 ###
finalize方法

## 4.7 包 ##
建议将公司的因特网域名以逆序的形式作为包名
### 4.7.1 类的导入 ###

### 4.7.2 静态导入 ###

	import static java.lang.System.*;

### 4.7.3 将类放入包中 ###

### 4.7.4 包作用域 ###

## 4.8 类路径 ##
类存储在文件系统的子目录中。类的路径必须与包名匹配。

## 4.9 文档注释 ##
javadoc

### 4.9.1 注释的插入 ###

### 4.9.2 类注释 ###

### 4.9.3 方法注释 ###

* @param变量描述
* @return描述
* @throws类描述

### 4.9.4 域注释 ###

### 4.9.5 通用注释 ###

### 4.9.6 包与概述注释 ###

## 4.10 类设计技巧 ##

1. 一定要保证数据私有
2. 一定要对数据初始化
3. 不要在类中使用过多的基本类型
4. 不是所有的域都需要独立的域访问器和域更改器。
5. 将职责过多的类进行分解
6. 类名和方法名要能够体现它们的职责