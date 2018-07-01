# ch10 用Optional取代null #

## 10.1 如何为缺失的值建模 ##

### 10.1.1 采用防御式检查减少NullPointerException ###

### 10.1.2 null 带来的种种问题 ###

* 它是错误之源。NullPointerException 是目前Java程序开发中最典型的异常。

null 并不属于任何类型，这意味着它可以被赋值给任意引用类型的变量。这会导致问题，原因是当这个变量被传递到系统中的另一个部分后，你将无法获知这个 null 变量最初的赋值到底是什么类型。

	java.util.Optional<T>
变量存在时， Optional 类只是对类简单封装。变量不存在时，缺失的值会被建模成一个“空”的 Optional 对象，由方法 Optional.empty() 返回。

Optional.empty() 方法是一个静态工厂方法，它返回 Optional 类的特定单一实例。

null 引用和 Optional.empty()有什么本质的区别吗？
如果你尝试解 引 用一 个null ，一定会触发 NullPointerException ，不过使用Optional.empty() 就完全没事儿，它是 Optional 类的一个有效对象，多种场景都能调用

## 10.2 Optional 类入门  ##

## 10.3 应用 Optional 的几种模式 ##

### 10.3.1 创建 Optional 对象 ###

#### 1. 声明一个空的 Optional ####

	Optional<Car> optCar = Optional.empty();

#### 2. 依据一个非空值创建 Optional ####

你还可以使用静态工厂方法 Optional.of ，依据一个非空值创建一个 Optional 对象：

	Optional<Car> optCar = Optional.of(car);

如果 car 是一个 null ，这段代码会立即抛出一个 NullPointerException ，而不是等到你
试图访问 car 的属性值时才返回一个错误。

#### 3. 可接受 null 的 Optional ####

Optional.ofNullable ，你可以创建一个允许 null 值的 Optional
对象：

	Optional<Car> optCar = Optional.ofNullable(car);

如果 car 是 null ，那么得到的 Optional 对象就是个空对象。

### 10.3.2 使用 map 从 Optional 对象中提取和转换值 ###

### 10.3.3 使用 flatMap 链接 Optional 对象 ###

	Optional<Person> optPerson = Optional.of(person);
	Optional<String> name =
		optPerson.map(Person::getCar)
		.map(Car::getInsurance)
		.map(Insurance::getName);

1. 使用 Optional 获取 car 的保险公司名称

2. 使用 Optional 解引用串接的 Person / Car / Insurance 对象

### 10.3.4 默认行为及解引用 Optional 对象 ###

### 10.3.5 两个 Optional 对象的组合 ###

### 10.3.6 使用 filter 剔除特定的值 ###

10.4 

## 10.5 小结 ##

* java.util.Optional<T>
* 