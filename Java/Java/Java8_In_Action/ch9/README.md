# 第9章 默认方法 #

## 9.1 不断演进的API ##

### 9.1.1 初始版本的API ###

### 9.1.2 第二版API ###


不同类型的兼容性：二进制、源代码和函数行为

二进制级的兼容性表示现有的二进制执行文件能无缝持续链接（包括验证、准备和解析）和运行。

## 9.2 概述默认方法 ##

	public interface Sized {
		int size();
		default boolean isEmpty() {
			return size() == 0;
		}
	}


一个类只能继承一个抽象类，但是一个类可以实现多个接口。
一个抽象类可以通过实例变量（字段）保存一个通用状态，而接口是不能有实例变量的。

## 9.3 默认方法的使用模式 ##

可选方法和行为的多继承

### 9.3.1 可选方法 ###

Iterator 接口定义了 hasNext 、 next ，还定义了 remove 方法。Java 8之前，由于用户通常不会使用该方法， remove 方法常被忽略。因此，实现 Interator 接口的类通常会为 remove 方法放置一个空的实现，这些都是些毫无用处的模板代码。

	interface Iterator<T> {
		boolean hasNext();
		T next();
		default void remove() {
			throw new UnsupportedOperationException();
		}
	}

### 9.3.2 测试使用Lambda的方法的行为 ###

Java的类只能继承单一的类，但是一个类可以实现多接口。


#### 1.类型的多继承 ####

AbstractList 、 List 、 RandomAccess 、 Cloneable 、 Serializable 、Iterable 和 Collection
 
#### 2.利用正交方法的精简接口 ####

定义一个单独的 Rotatable 接口，并提供两个抽象方法 setRotationAngle 和 getRotationAngle

	public interface Rotatable {
		void setRotationAngle(int angleInDegrees);
		int getRotationAngle();
		default void rotateBy(int angleInDegrees){
			setRotationAngle((getRotationAngle () + angle) % 360);
		}
	}

#### 3.组合接口 ####

	public class Monster implements Rotatable, Moveable, Resizable {
		…
	}

Monster 类会自动继承 Rotatable 、 Moveable 和 Resizable 接口的默认方法。

## 9.4 解决冲突的规则 ##

### 9.4.1 解决问题的三条规则 ###

1. 类中的方法优先级最高
2. 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择拥有最具体实现的默认方法的接口，即如果 B 继承了 A ，那么 B 就比 A 更加具体。
3. 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，显式地选择使用哪一个默认方法的实现。

### 9.4.2 选择提供了最具体实现的默认方法的接口 ###

	public class D implements A{
		void hello(){
			System.out.println("Hello from D");
		}
	}
	public class C extends D implements B, A {
		public static void main(String... args) {
			new C().hello();
		}
	}

### 9.4.3 冲突及如何显式地消除歧义 ###	



#### 冲突的解决 ####

	public interface A {
		void hello() {
			System.out.println("Hello from A");
		}
	}
	public interface B {
		void hello() {
			System.out.println("Hello from B");
		}
	}

	public class C implements B, A {
		void hello(){
			B.super.hello();
		}
	}

### 9.4.4 菱形继承问题 ###

* 首先，类或父类中显式声明的方法，其优先级高于所有的默认方法。
* 如果用第一条无法判断，方法签名又没有区别，那么选择提供最具体实现的默认方法的接口。
* 最后，如果冲突依旧无法解决，你就只能在你的类中覆盖该默认方法，显式地指定在你的类中使用哪一个接口中的方法。

## 9.5 小结 ##

* Java 8中的接口可以通过默认方法和静态方法提供方法的代码实现。
* 默认方法的开头以关键字 default 修饰，方法体与常规的类方法相同。
* 向发布的接口添加抽象方法不是源码兼容的。
* 默认方法的出现能帮助库的设计者以后向兼容的方式演进API。
* 默认方法可以用于创建可选方法和行为的多继承。
* 我们有办法解决由于一个类从多个接口中继承了拥有相同函数签名的方法而导致的冲突。
* 类或者父类中声明的方法的优先级高于任何默认方法。如果前一条无法解决冲突，那就选择同函数签名的方法中实现得最具体的那个接口的方法。
* 两个默认方法都同样具体时，你需要在类中覆盖该方法，显式地选择使用哪个接口中提供的默认方法。