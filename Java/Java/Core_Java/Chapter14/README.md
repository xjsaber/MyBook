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

#### java.lang.Thread1.0 ####
* Thread(Runnable target)
* void start()
* void run()
#### java.lang.Thread1.0 ####
* void run()

## 14.2 中断线程 ##
当线程的run方法执行方法体中最后一条语句后，并经由执行return语句返回时，或者出现了在方法中没有捕获的异常时，线程将终止。

## 14.3 线程状态 ##
线程可以有如下6种状态：
* New(新创建)
* Runnable(可运行)
* Blocked(被阻塞)
* Waiting(等待)
* Timed waiting(计时等待)
* Terminated(被终止)

要确定一个线程的当前状态，可调用getState方法。

### 14.3.1新创建线程 ###
当用new操作符创建一个新线程时，如new Thread(r),该线程还没有开始运行。这意味着它的状态是new。
### 14.3.2可运行线程 ###
一旦调用start方法，线程处于runnable状态。一个可运行的线程可能正在运行也可能没有运行，着取决于操作系统给线程提供运行的时间。
### 14.3.3 被阻塞线程和等待线程 ###
当线程处于被阻塞火等待状态时，它暂时不活动。它不运行任何代码且消耗最小的资源。

* 当一个线程试图获取一个内部的对象锁，而该锁被其他线程持有。
* 当一个线程试图获取
* 当一个线程试图获取
### 14.3.4 被终止的线程 ###
线程因而如下两个原因之一而被终止：

* 因为run方法正常退出而自然死亡
* 因为一个没有捕获的异常终止了run方法而意外死亡。

#### java.lang.Thread 1.0 ####
* void join() 等待终止指定的线程
* void join(long millis) 等待指定的线程死亡或者经过指定的毫秒数
* Thread.State getState()5.0 得到这一线程的状态；
* void stop 过时
* void suspend() 过时
* void resume() 过时

## 14.4 线程属性 ##
线程优先级、守护线程、线程组以及处理未捕获异常的处理器。

### 14.4.1线程优先级 ###
每一个线程有一个优先级。默认情况下，一个线程继承它的父线程的优先级。可以用setPriority方法提高或降低任何一个线程的优先级。可以将优先级设置为再MIN_PRIORITY（再Thread类中定义为1）与MAX_PRIORITY
(定义为10)之间的任何值。NORM_PRIORITY被定义为5。

每当线程调度器有机会选择新线程时，它首先选择具有较高优先级的线程。

#### java.lang.Thread1.0 ####
* void setPriority(int newPriority)
* static int MIN_PRIORITY() 最小优先级，1
* static int NORM_PRIORITY() 默认优先级，5
* static int MAX_PRIORITY() 最高优先级，10
* static void yield() 导致当前执行线程处于让步状态。

### 14.4.2守护线程 ###
可以通用调用
t.setDaemon(boolean isDaemon);
标识该线程为守护线程或用户线程。这一方法必须再线程启动之前调用。守护线程的唯一用途时为其他线程提供服务。但守护线程应该永远不去访问固有资源。

#### java.lang.Thread1.0 ####
void setDaemon(boolean, isDaemon)

### 14.4.3未捕获异常处理器 ###
线程的run方法不能抛出任何被检测的异常，但是不被检测的异常会导致线程终止。

线程组是一个可以统一管理的线程集合。默认情况下，创建的所有线程属于相同的线程组，但是，也可能会建立其他的组。（建议不要在自己的程序中使用线程组）。

ThreadGroup类实现Thread.UncaughtExceptionHandler接口。它的uncaughtException方法做如下操作：

1. 
2. 

## 14.5 同步 ##

### 14.5.1 竞争条件的一个例子 ###
同步存取。

### 14.5.2 竞争条件详解 ###

accounts[to] += amount
问题在于这不是原子操作。该指令可能呗处理如下：

1)将accounts[to]加载到寄存器。
2)增加amount
3)将结果写回accounts[to]。

### 14.5.3 锁对象 ###
synchronized关键字自动提供一个锁以及相关的“条件”。

用ReentrantLock保护代码块的基本结构如下：
myLock.lock();
try {
	TODO
}
finally {
	myLock.unlock(); //make sure the lock is unlocked event if an exception is thrown
}
PS：每一个Bank对象有自己的ReentrantLock对象。如果两个线程试图访问同一个Bank对象，那么锁以串行方式提供服务。如果两个线程访问不同的Bank对象，每一个线程得到不同的锁对象，两个线程就不会发生阻塞。

锁是**可重入**的，因为线程可以重复地获得已经持有的锁。锁保持一个持有计数（hold count）来跟踪对lock方法的嵌套调用。线程在每一次调用lock都要调用unlock来释放锁。

PS：把解锁操作括在finnally子句之内是至关重要的。如果在临界区的代码抛出了异常，锁必须被释放。否则，其他线程将永远阻塞。

#### java.util.concurrent.locks.Lock 5.0 ####
* void lock() 获取这个锁；如果锁同时另一个线程拥有则发上阻塞
* void unlock() 释放这个锁
#### java.util.concurrent.locks.ReentrantLock 5.0 ####
* ReentrantLock() 构建一个可以被用来保护临界区的可重入锁。
* ReentrantLock(boolean fair) 构建一个带有公平策略的锁。一个公平锁偏爱等待时间最长的线程。但是，公平锁将大大降低性能。

假定一个线程调用transfer，在执行结束前剥夺了运行权。假定第二个线程也调用transfer，由于第二个线程不能获得锁，将在调用lock方法被阻塞。它必须等待第一个线程完成transfer方法的执行之后才能再度被激活。当第一个线程释放锁时，那么第二个线程才能开始运行。

### 14.5.4 条件对象 ###
线程进入临界区，发现在某一条件满足之后才能执行。条件对象经常被称为条件变量（conditional variable）。

	public void transfer(int from, int to, int amount){
		bankLock.lock();
		try {
			while(accounts[from] < amount) {
				//wait...
			}
			// transfer funds
			...
		}
		finally {
			bankLock.unlock();
		}
	}
	如果

通常，对await的调用应该在如下形式的循环体中

	while(!(ok to proceed))
		condition.await();
需要某个其他线程调用signalAll方法。当一个线程调用await时，它没有办法重新激活自身。它寄希望于其他线程。如果没有其他线程来重新激活等待的线程，它就永远不再运行，导致死锁。

何时调用signalrAll，经验上讲，在对象的状态有利于等待线程的方向改变时调用signalAll。

另一个方法signal，则是随机解除等待集中某个线程的阻塞状态。如果随机选择的线程发现自己仍然不能运行，那么它再次被阻塞。

PS 警告：当一个线程拥有某个条件的锁时，它仅仅可以在该条件上调用await、signalAll或signal方法

#### java.util.concurrent.locks.Lock 5.0 ####
* Condition newCondition() 返回一个与该锁相关的条件对象。
#### java.util.concurrent.locks.Conidition 5.0 ####
* void await() 将该线程放到条件的等待集中。
* void signalAll() 解除该条件的等待集中的所有线程的阻塞状态。
* void signal() 从该条件的等待集中随机地选择一个线程，解除其阻塞状态。
### 14.5.5 synchronized关键字 ###
* 锁用来保护代码片段，任何时刻只能用一个线程执行被保护的代码。
* 锁可以管理试图进入被保护代码段的线程。
* 锁可以拥有一个或多个相关的条件对象
* 每个条件对象管理那些已经进入被保护的代码段但还不能运行的线程。

Lock和Condition接口为程序设计人员提供了高度的锁定控制。

Java种的每一个对象都有一个内部锁。如果一个方法用了synchronized关键字声明，那么对象的锁将保护整个方法。也就是说，要调用该方法，线程必须获得内部的对象锁。

	public synchronize void method() {
		method body
	}
等价于

	public void melthod() {
		this.intrinsiclock.lock()
		try {
			method body
		}
		finally {this.instrinsiclock.unlock();}
	}

内部锁和条件存在一些局限。包括：

* 不能中断一个正在试图获得锁的线程。
* 试图获得锁时不能设定超时。
* 每个锁仅有单一的条件，可能是不够的。

在代码中应该是用哪一种？Lock和Condition对象还是同步方法？下面是一些建议：

* 最好既不使用Lock/Condition也不使用synchronized关键字。在许多情况下你可以使用java.until.concurrent包中的一种机制，它会为你处理所有的加锁。
* 如果synchronized关键字适合你的程序，那么请尽量使用它，这样可以减少编写的代码数量，减少出错的几率。
* 如果特别需要Lock/Condition结构提供的独有特性时，才使用Lock/Condition。

#### java.lang.Object 1.0 ####
* void notifyAll() 解除那些在该对象上调用wait方法的线程的阻塞状态。该方法只能在同步方法或同步块内部调用。如果当前线程不是对象锁的持有者，该方法抛出一个IllegalMonitorStateException异常
* void notify() 随机选择一个在该对象上调用wait方法的线程，解除其阻塞状态。该方法只能在一个同步方法或同步块中调用。如果当前线程不是对象锁的持有者，该方法抛出一个IllegalMonitorStateException异常。
* void wait() 导致线程进入等待状态直到它被通知。该方法只能在一个同步方法中调用。如果当前线程不是对象锁的持有者，该方法抛出一个IllegalMonitorStateException异常。
* void wait(long millis)
* void wait(long millis, int nanos) 导致线程进入等待状态直到它被通知或者经过指定的时间。这些方法只能在一个同步方法中调用。如果当前线程不是对象锁的持有者该方法抛出一个IllegalMonitorStateException异常。

### 14.5.6 同步阻塞 ###
每一个Java对象有一个锁，线程可以通过调用同步方法获得锁。还有另一种机制可以获得锁，通过进入一个同步阻塞。当线程进入如下形式的阻塞：

	synchronized(obj) //this is the syntax for a synchronized block
	{
		critical section
	}

### 14.5.7 监视器概念 ###
锁和条件是线程同步的强大工具

监视器（monitor）

### 14.5.8 Volatile域 ###

* 多处理器的计算机能够暂时在寄存器或本地内存缓冲区

### 14.5.9 final变量 ###
声明域为final时，多个线程可以安全地访问一个共享域。
	
	final Map<String, Double> accounts = new HashMap<>();
其他线程会在构造函数完成构造之后才看到这个accounts变量。如果不使用final，就不能保证其他线程看到的是accounts更新后的值，它们可能都只是null，而不是新构造的HashMap。PS.对这个映射表的操作并不是线程安全。如果多个线程在读写这个映射表，仍然需要进行同步。

### 14.5.10 ###
假设对共享变量除了赋值之外并不完成其他操作，那么可以将这些共享变量声明为volatile。

java.util.concurrent.atomic包中有很多类使用了很高效的机器级指令（而不是使用锁）来保证其他操作的原子性。PS.AtomicInteger类提供了方法incrementAndGet和decrementAndGet，它们分别以原子方式将一个整数自增或自减。

AtomicBoolean、AtomicLong和AtomicReference以及Boolean值、整数、long值和引用的原子数组。应用程序员不应该使用这些类，它们仅供那些开发并发工具的系统程序员使用。

### 14.5.11 死锁 ###

### 14.5.12 线程局部变量 ###
可能要避免共享变量，使用ThreadLocal辅助类为各个线程提供各自的实例。

ThreadLocal辅助类为各个线程提供一个单独的生成器，在JavaSE7提供了一个便利类。
	
	int random = ThreadLocalRandom.current().nextInt(upperBound); ThreadLocalRandom.current()调用会返回特定于当前线程的Random类实例。

#### Java.lang.ThreadLocal<T> 1.2 ####
* T get() 得到这个线程的当前值。
* protected initialize() 应覆盖这个方法来提供一个初始值。
* void set(T t) 为这个线程设置一个新值。
* void remove() 删除对应这个线程的值。
#### Java.lang.ThreadLocal<T> 1.2 ####
* static ThreadLocalRandom current() 返回特定于当前线程的Random类实例

### 14.5.13 锁测试与超时 ###
线程在调用lock方法来获得另一个线程所持有的锁的时候，可能发生阻塞。tryLock方法试图申请一个锁，在成功获得锁后返回true，否则，立即返回false，而且线程可以立即离开。

	if(myLock.tryLock()){
		// now the thread owns the lock
		try{...}
		finally {myLock.unlock();}
	}
	else { //do something else }
可以调用tryLock时，使用超时参数:

    if(myLock.tryLock(100, TimeUnit.MILLISECONDS))...

### 14.5.14 读/写锁 ###

## 14.6 阻塞队列  ##

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

## 14.7 线程安全的集合 ##
可以通过提供锁来保护共享数据结构，但是选择线程安全的实现作为代替可能更容易些。

### 14.7.1 高效的映射表、集合和队列 ###
java.util.concurrent包提供了映射表、有序表和队列的高效实现：ConcurrentHashMap、ConcurrentSkipListMap、ConcurrnetSkpList和ConcurrnetLinkedQueue。

集合返回*弱一致性*（weakly consisteut）的迭代器。这意味着迭代器不一定反映出它们被构造之后的所有修改。但是，它们不会将同一个值返回两次，也不会抛出ConcurrentModificationException异常。

并发地散列映射表，可高效地支持大量地读者和一定数量地写者。默认情况下，假定可以有多大16个写者线程同时执行。可以有更多地写者线程，但是，如果同一个时间多于16个，其他线程将暂时被阻塞。

ConcurrentHashMap和ConcurrentSkipMap类有相应的方法用于原子的关联插入以及关联删除。putIfAbsent方法自动地添加新的关联，前提是原来没有这一关联。

	cache.putIfAbsent(key, value); 
	cache.remove(key, value); 如果在映像表中出现地话，将原子性地删除键值对。
	cache.replace(key, oldValue, newValue); 原子性地用新值替换旧值。

### java.util.concurrent.ConcurrentLinkedQueue<E> 5.0 ###
* ConcurrentLinkedQueue<E>() 构造一个可以被多线程安全访问地无边界非阻塞地队列
java.util.concurrent.ConcurrentLinkedQueue<E> 6
* ConcurrentHashMap<K, V>()
* ConcurrentHashMap<K, V>(int initialCapaciry)
* ConcurrentHashMap<K, V>(int initialCapacity, float loadFactor, int concurencyLevel)

构造一个可以被多线程安全访问地散列映射表。
参数：initCapacity 集合地初始容量。默认值为16。
loadFactor 控制调整：如果每一个桶地平均负载超过这个因子，表的大小会被重新控制。默认值为0.75。
concurrencyLevel 并发写者线程的估计数目。

* ConcurrentSkipListMap<K, V>()
* ConcurrentSkipListMap<K, V>(Comparator<? super K> comp)
构造一个可以被多线程安全访问的有需的迎像表。第一个构造器要求健实现Comparaable接口。
* V putIfAbsent(K key, V value) 如果该健没有在映像表中出现，则将给定的值同给定的键关联起来，并返回null。否则返回与该键关联的现有值。
* boolean remove(K key, V value)
* boolean replace(K key, V oldValue, V newValue)
### 14.7.2 写数组的拷贝 ###

	import static java.lang.System.*;

### 14.7.3 较早的线程安全集合 ###

### 4.7.4 包作用域 ###

## 4.8 类路径 ##
类存储在文件系统的子目录中。类的路径必须与包名匹配。

## 14.8 Callable与Future ##
Runnable封装一个异步运行的任务，可以把它想象成一个没有参数和返回值的异步方法。Callable与Runnable类似，但是有返回值。Callable接口时一个参数化的类型，只有一个方法call。

	public interface Callable<V>{
		V call() throws Exception;
	}
	类型参数时返回值的了类型。

	public interface Future<V>{
		V get() throws...;//调用被阻塞，直到计算完成。
		V get(long timeout, TimeUnit unit) throws...;//如果计算未完成，调用超时，抛出一个TimeoutExcepiton异常。
		//如果运行该计算的线程被中断，两个方法都将抛出InterruptedException。如果计算已经完成，那么get方法立即返回。
		void cancel(bollean mayInterrupt);
		boolean isCancelled();
		boolean isDone();//如果计算还在运行，isDone方法返回false；如果完成了，则返回true。可以用cancel方法取消该计算。如果计算还没有开始，它被取消且不再开始。如果计算处于运行之中，那么如果mayInterrupt参数未true，它就被中断。
	}


FutureTask，可将Callable转化成Future和Runnable。它同时实现两者的接口。
	
	Callable<Integer> myComputation = ...;
	FutureTask<Integer> task = new FutureTask<Integer>(myComputation);
	Thread t = new Thread(task); //Runnable
	t.start();
	...
	Integer result = task.get(); //Future

在call方法内部，使用相同的递归机制。对于每一个子目录，我们产生一个新的MatchCounter并为它启动一个线程。然后把FutureTask对象隐藏在ArrayList<Future<Integer>>中。
	
	for (Future<Integer> result: results){
		count += result.get();
	}
	每次对get的调用都会发生阻塞直到结果可获得为止。当然，线程时并行运行的。

#### java.util.concurrnet.Callable<V> 5.0 ####
* V call() 运行一个将产生结果的任务。
#### java.util.concurrnet.Future<V> 5.0 ####
* V get()
* V get(long time, TimeUnit unit) 获取结果，如果没有结果可用，则阻塞直到真正得到结果超过指定的时间为止。
* boolean cancel(boolean mayInterrupt)
* boolean isCancelled()
* boolean isDone(0
#### java.util.concurrnet.Future<V> 5.0 ####
* FutureTask(Callable<V> task)
* FutureTask(Runnable task, V result)构造一个既是Future<V>又是Runnable的对象

## 14.9 执行器 ##

### 4.9.1 注释的插入 ###

### 4.9.2 类注释 ###

### 4.9.3 方法注释 ###

* @param变量描述
* @return描述
* @throws类描述

### 4.9.4 域注释 ###

### 4.9.5 通用注释 ###

### 4.9.6 包与概述注释 ###

## 14.10 同步器 ##

1. 一定要保证数据私有
2. 一定要对数据初始化
3. 不要在类中使用过多的基本类型
4. 不是所有的域都需要独立的域访问器和域更改器。
5. 将职责过多的类进行分解
6. 类名和方法名要能够体现它们的职责

## 14.11 线程与Swing ##