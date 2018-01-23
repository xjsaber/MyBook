# Pragmatic Scala #

小试牛刀

## 第1章 探索 Scala ##



### 小结 ###



## 第2章 体验Scala ##

### 使用RERL ###

### 以独立脚本运行Scala代码 ###

### Scala的IDE支持 ###

### 编译Scala ###

### 小结 ###

## 第3章 从Java到Scala ##




### Scala：简洁的Java ###

#### Less Boilerplate 减少样板代码 ####

	//Java code
	public class Greetings {
		public static void main(String[] args) {
			for (int i = 1; i < 4; i++) {
				System.out.print(i + ",");
			}
			System.out.println("Scala Rocks!!!");
		}
	}
	//Scala code
	for (i <- 1 to 3) {
		print(s"$i,")
	}
	println("Scala Rocks!!!")

#### 减少模板代码 ####

	val buffer = new StringBuffer()



#### 更多便利特性 ####

val或var定义变量，使用val定义的变量是不可变的，即初始化后不能更改。那些使用var定义（不推荐使用）的变量是可变的，可以被改变任意次。

#### Leaning Toward Functional Style 转向函数式风格 ####

### Java基本类型对应的Scala类 ###

### 远足和多重赋值 ###

## 细粒度的访问控制 ##

## 小结 ##



## 第4章 处理对象 ##

### Creating and Using Classes ###

### 创建实例 ###

	new StringBuilder()

Scala把空括号看作多余的。

	new StringBuilder

#### Creating Classes ####

	class Car(val year: Int) {
		private var milesDriven: Int = 0

		def miles: Int = milesDriven

		def drive(distance: Int): Unit = {
			milesDriven += Math.abs(distance)
		}
	}

#### Defining Fields，Methods，and Constructors ####

### Following the JavaBean Convention 遵循JavaBean惯例 ###

### Type Aliasing 类型别名 ###

### Extending a Class ###

### 扩展一个类 ###

### 参数化类型 ###


## 第5章 ##

## 第6章 ##

## 第7章 特质 ##

## 第12章 并行集合和惰性求值 ##

