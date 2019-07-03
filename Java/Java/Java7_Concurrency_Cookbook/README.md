# Java 7 并发编程实战手册 #

## 序章 ##

* 线程管理，通过基础的范例来讲解线程的创建、线程的执行以及线程的状态管理；
* 线程同步基础，为读者讲解如何使用低级的Java机制，比如采用Lock锁接口和synchronized关键字来同步代码；
* 线程同步辅助类，讲解如何使用Java的高级工具类来管理Java中的线程同步；
* 线程执行器（Thread Executor），讲解如何将线程管理委托给执行器（Executor）。执行器将为并发任务负责线程的创建、运行、管理并返回任务的结果；
* Fork/Join框架（Fork/Join Framework），讲解Java7新的一种特殊的执行器Fork/Join框架。用来解决通过分治技术（Divide and conquer Technique）将任务拆分成多个子任务的问题；
* 并发集合，使用一些由Java语言提供的并发数据结构，从而避免在程序的实现中采用synchronized代码块；
* 定制并发类（Customizing Concurrency Classe）。
* 测试并发应用（Testing Concurrent Application），讲解如何获取Java7并发API中最有用的结构的状态信息。

## 前言 ##

## 第1章 线程管理 ##

* 线程的创建和运行
* 线程信息的获取和设置
* 线程的中断
* 线程中断的控制
* 线程的休眠和修复
* 等待线程的终止
* 守护线程的创建和运行
* 线程中不可控异常的处理
* 线程局部变量的使用
* 线程的分组
* 线程组中不可控异常的处理
* 使用工厂类创建线程

### 1.1 简介 ###

### 1.2 线程的创建和运行 ###

在Java语言中，线程跟其他所有元素一样，都是对象（Object）。Java提供了两种方式来创建线程：

* 继承Thread类，并且覆盖run()方法
* 创建一个实现Runnable接口的类。使用带参数的Thread构造器来构建Thread对象。这个参数就是实现Runnable接口的类的一个对象。

**工作原理**

每个Java程序都至少有一个执行线程。当运行程序的时候，JVM将启动这个执行线程来调用程序的main()方法。

当调用Thread对象的start()方法时，另一个执行线程将被创建。因而在我们的程序中，每次调用start()时，都会创建一个执行线程。

当一个程序的所有线程都运行完成时，更明确的说，当所有非守护（non-daemon）线程都运行完成的时候，这个Java程序将宣告结束。如果初始线程（执行main()方法的线程）结束了，其余的线程仍将继续执行直到他们运行结束。如果某一个线程调用了System.exit()指令来结束程序的执行，所有的线程都将结束。

对一个实现了Runnable接口的类来说，创建Thread对象并不会创建一个新的执行线程：同样的，调用它的run()方法，也不会创建一个新的执行线程。只有调用它的start()方法，才会创建一个新的执行线程。

**更多信息**

编写一个类并继承Thread类，在这个类里覆盖run()方法，然后创建这个类的对象，并且调用start()方法，也会创建一个执行线程。

### 1.3 线程信息的获取和设置 ###

Thread类有一些保存信息的属性，这些属性可以用来标识线程，显示线程的状态和控制线程的优先级。

ID:保存了线程的唯一标识符。
Name：保存了线程名称。
Priority：保存了线程对象的优先级。线程的优先级是从1到10，其中1是最低优先级；10是最高优先级。
Staus：保存了线程的状态。在Java中，线程的状态有6种：new、runnable、blocked、waiting、time waiting或者terminated。

**工作原理**

Thread类的属性存储了线程的所有信息。JVM使用线程的priority属性来决定某一刻由哪个线程来使用CPU，并且根据线程的情景为它们设置实际状态。

如果没有为线程指定一个名字，JVM将自动给它分配一个名字，格式是Thread-XX，其中XX是一组数字。线程的ID和状态是不允许被修改的，线程类没有提供setId()和setStatus()方法来修改它们。

**更多信息**


### 1.4 线程的中断 ###

所有的非守护线程运行结束时，或者其中一个线程调用了System.exit()方法时，这个Java程序才运行结束。

Java提供了中断机制，使用它来结束一个线程。这种机制要求线程检查它是否被中断了，然后决定是不是响应这个中断请求。

**工作原理**

Thread类有一个表明线程被中断与否的属性，它存放的是布尔值。线程的interrupt()方法被调用时，这个属性就会被设置位true。isInterrupted()方法只是返回这个属性的值。

**更多信息**

Thread类的静态方法interrupted()，用来检查当前执行的线程是否被中断。

isInterrupted()和interrupt()方法有一个很大的区别。isInterrupted()不能改变interrupted属性的值，但是后者能设置interrupted属性位false。因为interrupted()是一个静态方法，梗推荐使用isInterrupted()方法。

### 1.5 线程中断的控制 ###

如果线程实现了复杂的算法并且分布在几个方法中，或者线程里有递归调用的方法，我们就得使用一个更好的机制来控制线程的中断。

InterruptedException异常。当检查到线程中断的时候，就抛出这个异常，然后在run()种捕获并处理这个异常。

**工作原理**

使用Java异常来控制线程的中断，程序将递归调用processDirectory()方法3次。不管递归用了多少次，只要线程检测到它已经被中断，就会立即抛出InterruptedException异常，然后继续执行run()方法。

### 1.6 线程的休眠和恢复 ###

某一个预期的时间中断线程的执行。

sleep()方法的另一种使用方式是通过TimeUnit枚举类元素进行调用。这个方法也使用Thread的sleep()方法来使当前线程休眠，但是它接收的参数单位是秒，最后会被转化成毫秒。

**工作原理**

当调用sleep()方法之后，线程会释放CPU并且不再继续执行任务。在这段时间内，线程不占用CPU时钟，所以CPU可以执行其他的任务。

如果休眠中线程被中断，该方法就会立即抛出InterruptedException异常，而不需要等待到线程休眠时间结束。

**更多信息**

Java并发API还提供了另外一个方法来使线程对象释放CPU，即yield()方法，将通知JVM这个线程对象可以释放CPU了

**参见**

### 1.7 等待线程的终止 ###

Thread类的join()方法。当一个线程对象的join()方法被调用时，调用它的线程将被挂起，直到这个线程对象完成它的任务。

**工作原理**

DataSourcesLoader线程运行结束，NetworkConnectionsLoader线程也运行结束的是偶，主线程对象才会继续并且打印出最终的信息。

**更多信息**

	join(long milliseconds)
	join(long milliseconds, long nanos)

当一个线程调用其他某个线程的join()方法时，如果使用第一种join()方式，那么不必等到被调用线程运行终止，如果参数指定的毫秒时钟已经到达，它将继续运行。

thread1有这样的代码thread2.join(1000)，thread1将挂起运行，直到

* thread2运行已经完成；
* 时钟已经过去1000毫秒。

当两个条件中的任何一条成立时，join()方法将返回。第二中join()方法跟第一种相似，只是需要接受毫秒和纳秒两个参数。

### 1.8 守护线程的创建和运行 ###

守护（Daemon）线程。线程的优先级很低，通常来说，当同一个应用程序里没有其他的线程运行的时候，守护线程才运行。当守护线程是程序中唯一运行的线程时，守护线程执行结束后，JVM也就结束了这个程序。

守护线程通常被用来做为同一程序中普通线程（也称为用户线程）的服务提供者。通常是无限循环的，以等待服务请求或者执行线程的任务。不能做重要的工作，因为不可能知道守护线程什么时候能够获取CPU时钟，并且，在没有其他线程的运行的时候，守护线程随时可能结束。一个典型的守护线程是Java的垃圾回收器（Garbage Collector）。

### 1.9 线程种不可控异常的处理 ###

setDaemon()方法只能在start()方法被调用之前设置。一旦线程开始运行，将不能再修改为守护状态。

* 非运行时异常（CheckedException）：这种异常必须在方法声明的throws语句指定，或者在方法体内捕获。IOException和ClassNotFoundException
* 运行时异常（Unchecked Exception）：这种异常不必再方法声明中指定，也不需要再方法中捕获。NumberFormatException

isDaemon()方法被用来检查一个线程是否是守护线程，返回值true表示这个线程是守护线程，false表示这个线程是用户线程。

run()方法不支持throws语句，所以当线程对象的run()方法抛出非运行异常时，必须捕获并且处理它们。当运行时异常从run()方法中抛出时，默认行为是再控制台输出堆栈记录并且推出程序。

**工作原理**

当一个线程抛出了异常并且没有被捕获时（这种情况只可能是运行时异常），JVM检查这个线程是否被预置了未捕获异常处理器。如果找到，JVM将调用线程对象的这个方法，并将线程对象和异常作为传入参数。

**更多信息**

Thread类还有另一个方法可以处理未捕获到的异常，即静态方法setDefaultUncaughtExceptionHandler()。这个方法再应用程序中为所有的线程对象创建了一个异常处理器。

异常处理器

1. 线程对象的未捕获异常处理器
2. 找不到，JVM继续查找线程对象所在的线程组（ThreadGroup）的未捕获异常处理器
3. 如果还找不到，将继续查找默认的未捕获异常处理器

### 1.10 线程局部变量的使用 ###

共享数据是并发程序最核心的问题之一，对于继承了Thread类或者实现了Runnable接口的对象来说尤其重要。

如果创建的对象是实现了Runnable接口的类的实例，用它作为传入参数创建多个线程对象并启动这些线程，那么所有的线程将共享相同的属性。也就是说，如果在一个线程中改变了一个属性，那么所有线程都将会被这个改变影响。

在某种情况下，这个对象的属性不需要被所有线程共享。Java并发API提供了一个干净的机制，即线程局部变量（Thread-Local Variable），其具有很好的性能。

**工作原理**

线程局部变量分别为每个线程存储了各自的属性值，并提供给每个线程使用。使用get()方法读取这个值，并用set()方法设置这个值。如果线程是第一次访问线程局部变量，线程局部变量可能还没有为它存储值，这个时候initialValue()方法就会被调用，并且返回当前的时间值。

**更多信息**

线程局部变量提供了remove()方法，用来为访问这个变量的线程删除已经存储的值。Java并发API包含了InheritableThreadLocal类，如果一个线程是从其他某个线程中创建的，这个类将提供继承的值。

如果一个线程A在线程局部变量已有值，当它创建其他某个线程B时，线程B的线程局部变量将线程A是一样的。可以覆盖childValue()方法，这个方法用来初始化子线程在线程局部变量中的值。它使用父线程在线程局部变量中的值作为传入参数。

### 1.11 线程的分组 ###

并发API，能够把线程分组。

Java提供ThreadGroup类标识一组线程。线程组可以包含线程对象，也可以包含其他的线程组对象，它是一个树形结构。

**工作原理**

list()方法的输出及每个线程对象的状态。

通过activeCount()方法获取线程组包含的线程数目，通过enumerate()方法获取线程组包含的线程列表。

	Thread[] threads = new Thread[threadGroup.activeCount()];
	threadGroup.enumerate(threads);
	for (int i = 0; i < threadGroup.activeCount(); i++) {
		System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
	}

使用interrupt()方法中断这个组中的其余线程。

	threadGroup.interrupt();

调用waitFinish()方法，将等到线程组的第一个线程运行结束
	
	private static void waitFinish(ThreadGroup threadGroup) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}



线程组类存储了线程对象和关联的线程组对象，并可以访问它们的信息（例如状态），将执行的操作应用在所有成员上（例如中断）。

### 1.12 线程组种不可控异常的处理 ###

Java提供了捕获和处理异常的机制，有的异常必须被捕获，或者必须使用方法的throws声明再次抛出，这类异常叫作非运行时异常。还有一类异常叫作运行时异常，它们不需要被捕获或者声明抛出。

**工作原理**

当运行范例的时候，当一个线程对象抛出了异常，其余的线程对象都被中断。

当线程抛出非捕获异常时，JVM将会这个异常寻找3中可能的处理器。

1. 寻找抛出这个异常的线程的非捕获异常处理器
2. JVM继续查找这个线程所在的线程组的非捕获异常处理器

**更多信息**

### 1.13 使用工厂类创建线程 ###

工厂模式是面向对象编程中最常使用的模式之一。

使用工厂类，可以将对象的创建集中化：

1. 更容易修改类，或者改变创建对象的方式；
2. 更容易为有限资源限制创建对象的数目。
3. 更容易为创建的对象生成统计数据。

Java提供了ThreadFactory接口，这个接口实现了线程对象工厂。

**工作原理**

ThreadFactory接口只有一个方法，即newThread，它以Runnable接口对象为传入参数并且范佳慧一个线程对象。当实现ThreadFactory接口时，必须实现覆盖这个方法。大多数基本的线程工具类只有一行。
	
	return new Thread(r);

可以通过增加一些边划来强化实现方法覆盖。

* 创建一个个性化线程，如本范例使用一个特殊的格式作为线程名，或者通过继承Thread类来创建自己的线程类；
* 保存新创建的线程统计数据，如本节的范例那样；
* 限制创建的线程的数量；
* 对生成的线程进行验证；
* 更多你可以想到的。

**更多信息**


# 第2章 线程同步基础 #

* synchronized实现同步方法
* 使用非依赖属性实现同步
* 在同步代码块中使用条件
* 使用锁实现同步
* 使用读写锁同步数据访问
* 修改锁的公平性
* 在锁中使用多条件

## 2.1 简介 ##

多个执行线程共享一个资源的情景，是最常见的并发编程情景之一。

* synchronized关键字机制
* Lock接口及其实现机制

## 2.2 使用synchronized实现同步方法 ##

使用synchronized关键字来控制一个方法的并发访问。如果一个对象已用synchronzied关键字声明，那么只有一个执行线程被允许访问它。如果其他某个线程视图访问这个对象的其他方法，将被挂起，直到第一个线程执行完正在运行的方法。

每一个用synchronized关键字声明的方法都是临界区。在Java中，同一个对象的临界区，在同一时间只有一个允许被访问。

用synchronized关键字声明的静态方法，同时只能被一个执行线程访问，但是其他线程可以访问这个对象的非静态方法。因为两个线程可以同时访问一个对象的两个不同的synchronized方法，即其中一个是静态方法，另一个是非静态方法。如果两个方法都改变了相同的数据，将会出现数据不一致的错误。

**工作原理**

synchronized关键字的使用，保证了在并发程序中对共享数据的正确访问。

**更多信息**

synchronized关键字会降低应用程序的性能。

可以递归调用被synchronized声明的方法。当线程访问一个对象的同步方法时，还可以调用这个对象的其他的同步方法，也包含正在执行的方法，而不必再次去获取这个方法的访问权。

通过`synchronized`关键字来保护代码块（而不是整个方法）的放问，方法的其余部分保持在synchronized代码块之外，以获取更好的性能。临界区（即同一时间只能被一个线程访问的代码块）的访问呢应该尽可能地短。

this关键字来引用正在执行的方法所属的对象

	synchronized(this) {
		// Java code
	}

## 2.3 使用非依赖属性实现同步 ##

当使用synchronized关键字来保护代码块时，必须把对象引用作为传入参数。通常情况下，使用this关键字来引用执行方法所属的对象，也可以使用其他的对象对其进行引用。

**工作原理**

用`synchronized`关键字保护代码块时，使用对象作为它的传入参数。JVM保证同一时间只有一个线程能够访问这个对象的代码保护块。

**更多信息**

## 2.4 在同步代码中使用条件 ##

在并发编程中一个典型的问题是生产者-消费者（Producer-Consumer）问题，我们有一个数据缓冲区，一个或者多个数据生产者将把数据存入这个缓冲区，一个或者多个数据消费者将数据从缓冲区中取走。

这个缓冲区是一个共享数据结构，必须使用同步机制控制对它的访问，例如使用`synchronized`关键字，但是会受到更多的限制。如果缓冲区是满地，生产者就不能再放入数据，如果缓冲区是空地，消费者就不能读取数据。

Java在**Object**类中提供了**wait()**、**notify()**和**notifyAll()**方法。线程可以在同步代码块中调用**wait()**方法，JVM将抛出**IllegalMonitorStateException**异常。当一个线程调用**wait()**方法时，JVM将这个线程置入休眠，并且释放这个同步代码块的对象，同时允许其他线程执行这个对象控制的其他同步代码块。为了唤醒这个线程，必须在这个对象控制的某个同步代码块中调用**notify()**或者**notifyAll()**方法。

**synchronized**关键字和**wait()**、**notify()**及**notifyAll()**方法。

**工作原理**

EventStorage类的set()和get()方法。

1. get()方法检查存储列表storage是否还有数据
2. 如果没有，就调用wait()方法挂起线程并等待
3. 当其他线程调用notifyAll()方法时，挂起的线程将被唤醒并且再次检查这个条件

PS:必须在while循环中调用wait()，并且不断查询while的条件，直到条件为真的时候才能继续。

1. set()方法检查列表storage是否还有空间，如果满了，就调用wait()方法挂起线程并等待空余空间出现。
2. 当其他线程调用notifyAll()方法时，挂起的线程将被唤醒并且再次检查这个条件。
3. notifyAll()并不保证哪个线程会被唤醒。这个过程持续进行直到存储列表有空余空间出现，然后生产者将生成一个新的数据并且存入存储列表storage。

PS. 必须在while循环中调用wait()，并且不断查询while的条件，直到条件为真的时候才能继续。

**更多信息**

synchronized关键字还有其他的重要作用。

## 2.5 使用锁实现同步 ##

Java提供了同步代码块的另一种机制，它是一种比synchronized关键字更强大也更灵活的机制。这种机制基于Lock接口及其实现类（例如ReetrantLock），提供了更多的好处。

* 支持更灵活的同步代码块结构，使用synchronized关键字时，只能在同一个synchronized块结构中获取和释放控制。Lock接口允许实现更复杂的临界区结构（即控制的获取和释放不出现在同一个块结构中）。
* 相比synchronized关键字，Lock接口提供了更多的功能。tryLock()方法的实现。这个方法试图获取锁，如果锁已被其他线程获取，它将返回false并继续往下执行代码。使用synchronized关键字时，如果线程A视图执行一个同步代码块，而线程B已在执行这个同步代码块，则线程A就会被挂起直到线程B运行完这个同步代码块。使用锁的tryLock()方法，通过返回值将得知是否有其他线程正在使用这个锁保护的代码块。
* Lock接口允许分离读和写操作，允许多个读线程和只有一个写线程
* 相比synchronized关键字，Lock结构具有更好的性能。

**工作原理**

在临界区的开始，必须通过lock()方法获取对锁的控制。当线程A访问这个方法时，如果没有其他线程获取对这个锁的控制，lock()方法将让线程A获得锁并且允许它立刻执行临界区代码。否则，如果其他线程B正在执行这个锁保护的临界区代码，lock()方法将让线程A休眠直到线程B执行完临界区的代码。

当线程离开临界区的时候，必须使用unlock()方法来释放它持有的锁，以让其他的线程来访问临界区。如果在离开临界区的时候没有调用unlock()方法，其他线程将永久地等待，从而导致了死锁（Deadlock）情景。如果在临界区是用了try-catch块，不要忘记将unlock()方法放入finally部分。

**更多信息**

Lock接口（和它的实现类ReentrantLock）提供了另一个方法来获取锁，即tryLock()方法，即lock()方法最大的不同是，不仅能够获取锁，而且会立即返回，不会将线程置入休眠。

ReentrantLock类允许使用递归调用。如果一个线程获取了锁并且进行了递归调用，它将继续持有这个锁，因此调用lock()方法后也将立即返回，并且线程将继续执行递归调用。

必须很小心使用锁，以避免死锁。

## 2.6 使用读写锁实现同步数据访问 ##

锁机制的最大的改进之一就是ReadWriteLock接口和它的唯一实现类ReentrantReadWriteLock。读操作锁和写操作锁。使用读操作锁时可以允许多个线程同时访问，但是写操作锁时只允许一个线程进行。在一个线程执行写操作时，其他线程不能够执行读操作。

**范例实现**

**工作原理**

**更多信息**

ReetranReadWriteLock类有两个锁：一种是都操作锁，另一种是写操作锁。读操作锁是通过ReadWriteLock接口的readLock()方法获取的，这个锁实现了Lock接口，所以可以使用lock()，unlock()和tryLock()方法。写操作锁是通过ReadWriteLock接口的writeLock()方法获取的，这个锁同样也实现了Lock接口，所以可以使用lock()、unlock()和tryLock()方法。

当获取Lock接口地读锁时，不可以进行修改操作，否则将引起数据不一致地错误。

**参见**

## 2.7 修改锁的公平性 ##

**ReetrantLock**和**ReentrantReadWriteLock**类的构造器都含有一个布尔参数**fair**，它允许控制着两个类的行为。默认**fair**值是false，称为非公平模式（Non-Fair Mode）。在非公平模式下，当有许多线程再等待锁（**ReentranLock**和**ReentranReadWriteLock**）时，锁将选择它们中的一个来访问临界区，而且选择的是等待事件最长的。

lock()和unlock()，Lock接口的tryLock()方法没有将线程置于休眠，fair属性并不影响这个方法。

**范例实现**

**工作原理**

所有线程是按顺序创建的，每个线程都执行两个被锁保护的代码块。然后访问时线程并没有按照创建的先后顺序。锁将选择任一线程并让它访问锁保护的代码。JVM没有对线程的执行顺序提供保障。

**更多信息**

读/写锁的构造器也有一个公平策略的参数

**参见**

## 2.8 在锁中使用多条件（Multiple Condition） ##

一个锁可能关联一个或多个条件，这些条件通过Condition接口声明。目的是允许线程获取锁并且查看等待的某一个条件是否满足，如果不满足就挂起直到某个线程唤醒它们。

Condition接口提供了挂起线程和唤起线程的机制。

并发编程中的一个典型问题是生产者-消费者（Producer-Consumer）问题。使用一个数据缓冲区，一个或者多个数据生产者（Producer）将数据保存到缓冲区，一个或者多个数据消费者（Consumer）将数据从缓冲区中取走。

**工作原理**

与锁绑定的所有条件对象都是通过Lock接口的声明的newCondition()方法创建的。在使用条件的时候，必须获取这个条件绑定的锁，所以带条件的代码必须再调用Lock对象的lock方法和unlock()方法之间。

当线程调用条件的await()方法时，将自动释放这个条件绑定的锁，其他某个线程才可以获取这个锁并且执行相同的操作，或者执行这个锁保护的另一个临界区代码。

signal()或者signalAll()方法后，一个或者多个在该条件上挂起的线程将被唤醒，但这并不能保证让它们挂起的条件已经满足，所以必须在while循环中调用await()，在条件成立之前不能离开这个循环。如果条件不成立，将再次调用await()。

必须小心使用await()和signal()方法。如果调用一个条件的await()方法，却从不调用它的signal()方法，这个线程将永久休眠。

因调用await()方法进入休眠的线程可能会被中断，所以必须处理InterruptedException异常。

**更多信息**

Condition接口还提供了await()方法的其他形式。

await(long time, TimeUnit unit)，直到发生以下的情况之一之前，线程将一直处于休眠状态。

* 其他某个线程中断当前线程。
* 其他某个线程调用了将当前线程挂起的条件的signal()或signalAll()方法。
* 只当的等待时间已经过去。
* 通过TimeUnit类的常量DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES|ANOSECONDS和SECONDS指定的等待时间已经过去。

awaitUnitnterruptibly():它是不可中断你的。这个线程将休眠直到其他某个线程调用了将它挂起的条件的signal()或signalAll()方法。
awaitUnit(Date date):直到发生以下情况之一之前，线程将一直处于休眠状态。

* 其他某个线程中断当前线程
* 其他某个线程调用了将它挂起的条件的signal()或signalAll()方法
* 指定的最后期限到了
* 也可以将条件与读写锁ReadLock和WriteLock一起使用。

**参见**

# 第3章 线程同步辅助类 #

* 资源的并发访问控制
* 资源的多副本的并发访问控制
* 等待多个并发事件的完成
* 在集合点的同步
* 并发阶段任务的运行
* 并发阶段任务中的阶段切换
* 并发任务间的数据交换

## 3.1 简介 ##

* 信号量（Semaphore）：是一种计数器，用来保护一个或者多个共享资源的访问。
* CoundDownLatch：是Java语言提供的同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许线程一直等待。
* CyclicBarrier：也是Java语言提供的同步辅助类，它允许多个线程在某个集合点（common point）处进行相互等待。
* Phaser：也是Java语言提供的同步辅助类。它把并发任务分成阶段运行，在开始下一阶段之前，当前阶段中的所有阶段必须执行。
* Exchanger：也是Java语言提供的同步辅助类。

在应用程序中，任何时候都可以使用Semaphore来保护临界区，因为它是一个基础的同步机制。而其他的同步机制，则需要根据各自的上述条件来对其选择使用。

## 3.2 资源的并发访问控制 ##

信号量是一种计数器，用来保护一个或者多个共享资源的访问。

如果线程要访问一个共享资源，必须先获得信号量。如果信号量的内部计数器大于0，信号量将减1，然后允许访问这个共享资源。计数器大于0意味着可以使用的资源，因此线程被允许使用其中一个资源。

否则，如果信号量地计数器等于0，信号量将会把线程置入休眠直至计数器大于0.计数器等于0的时候意味着所有的共享资源已经被其他线程使用了，所以需要将放问这个共享资源的线程必须等待。

当线程使用完某个共享资源时，信号量必须被释放，以便其他线程能够放问共享资源。释放操作将使信号量的内部计数器增加1。

使用信号晾类Semaphore来实现二进制信号量（Binary Semaphore）二进制信号量是一种比较特殊的信号量，用来保护对唯一共享资源的访问，因而它的内部计数器只有0和1两个值。

### 工作原理 ###

使用信号量实现临界区必须遵循的三个步骤，从而保护对共享资源的访问：

1. 必须通过acquire()方法获得信号量
2. 使用共享资源执行必要的操作
3. 必须通过release()方法释放信号量

### 更多信息 ###

* acquireUninterruptibly():其实就是acquire()方法。当信号量的内部计数器变成0的时候，信号量将阻塞线程直到其被释放。线程在被阻塞的这段时间中，可能会被终端，从而导致acquire()方法抛出InterruptedException异常。而acquireUninterruptibly()方法会忽略线程的中断并且不会抛出任何异常。
* tryAcquire()试图获得信号量。如果能获得就返回true；如果不能，就返回false，从而避开线程的阻塞和等待信号量的释放。——>可以根据返回值是true还是false来做恰当的处理。

#### 信号量的公平性 ####

在Java语言中，只要一个类可能出现多个线程被阻塞并且等待同步资源的释放（例如信号量），就会涉及公平性概念。默认的模式是对平方模式。在这种模式中，被同步的资源被释放后，所有等待的线程中会有一个被选中来使用共享资源，而这个选择是没有任何条件的。

### 参见 ###

## 3.3 资源的多副本的并发访问控制 ##

基础信号量，保护单一共享资源，或者单一临界区的访问，从而使得保护的资源在同一个时间内只能被一个线程访问。然而，信号量也可以用来保护一个资源的多个副本，或者多个线程同时执行的临界区。

**范例实现**

**工作原理**

1. 调用acquire()方法的3个线程将获得对临界区的访问，其他的线程将被阻塞。
2. 当一个线程完成了对临界区的访问并且释放了信号量，另一个线程将获得这个信号量。

**更多信息**

acquire()、acquireUninterruptibly()、tryAcquire()和release()方法都有另一种实现方式，即提供了一个int型的传入参数。这个参数声明了线程试图获取或者释放的共享资源数目，也就是这个线程想要在信号量内部计数器上删除或增加的数目。

对于acquire()、acquireUninterruptibly()、tryAcquire()方法来讲，如果计数器的值少于参数对应的值，那么线程将被阻塞直到计数器重新累加到或者超过这个值。

**参见**

## 3.4 等待多个并发事件的完成 ##

CountDownLatch类，一个同步辅助类。在完成一组正在其他线程中执行的操作之前，允许线程一直等待。这个类使用一个整数进行初始化，这个证书就是i县城要等待完成的操作的数目。当一个线程要等待某些某些操作先执行完时，需要调用await()方法，这个方法让线程进入休眠直到等待的所有操作都完成。

countDown()方法将CountDownLatch类的内部计数器减1.当计数器变成0的时候，CountDownLatch类将唤醒所有调用await()方法而进入休眠的线程。

**准备工作**

**范例实现**

**工作原理**

CountDownLatch类三个基本元素：

* 一个初始值，即定义必需等待的先行完成的操作的数目；
* await()方法，需要等待其他事件先完成的线程调用；
* countDown()方法，每个都等待的事件在完成的时候调用。

当创建CountDownLatch对象时，使用构造器来初始化内部计数器。当countDown()方法被调用后，计数器将减1。当计数器到达0的时候，CountDownLatch对象将唤起所有在await()方法上等待的线程。

CountDownLatch

**更多信息**

**参见**

## 3.5 在集合点的同步 ##

CyclicBarrier类，同步辅助类，允许两个或者多个线程在某个点上进行同步。

CyclicBarrier类使用一个整数型进行初始化，这个数是需要在某个点上同步的线程数。当一个线程到达指定的点后，将调用await()方法等待其他的线程。

**准备工作**

**范例实现**

**工作原理**

**更多信息**

**参见**

## 3.6 并发阶段任务的运行 ##

Phaser，允许执行并发多阶段任务。当我们有并发任务并且需要分解成几步执行时，这种机制就非常适用。Phaser类机制是在每一步结束的位置对线程进行同步，当所有的线程都完成了这一步，才允许执行下一步。

1. 在指定的文件夹及其子文件夹中获得扩展名为.log的文件；
2. 对第一步的结构进行过滤，删除修改时间超过24小时的文件；
3. 将结果打印到控制台。

**范例实现**

**工作原理**

**更多信息**

Phaser类提供了一些其他改变Phaser对象的方法：

* arrive()
* awaitAdvance(int phase)
* awaitAdvanceInterruptibly(int phaser)

## 3.7 并发阶段任务中的阶段切换 ##

Phaser类提供了onAdvance()方法，它在phaser阶段改变的时候会自动执行。onAdvance()方法需要两个int型的传入参数：当前的阶段数以及住测的参与者数量。

**范例实现**

**工作原理**

**更多信息**

**参见**

## 3.8 并发任务间的数据交换 ##

Exchanger，允许在并发任务之间交换数据。Exchange类允许在两个线程之间定义同步点（Synchronization Point）。当两个线程都到达同步点是，它们交换数据结构。

**范例实现**

**工作原理**

**更多信息**

**参见**

# 第4章 线程执行器 #

* 创建线程执行器
* 创建固定大小的线程执行器
* 在执行器中执行任务并返回结果
* 运行多个任务并处理所有结果
* 在执行器中延时所有结果
* 在执行器中延时执行任务
* 在执行器中周期性执行任务
* 在执行器中取消任务
* 在执行器中控制任务的完成
* 在执行器中分离任务的启动与结果的处理
* 处理在执行器中被拒绝的任务

## 4.1 简介 ##

* 必须实现所有与Thread对象管理相关的代码，比如线程的创建、结束以及结果获取；
* 需要为每一个任务创建一个Thread对象。如果需要执行大量的任务，这将大大地影响应用程序的处理能力；
* 计算机的资源需要高效地进行控制和管理，如果创建过多的线程，将会导致系统负荷过重。

执行器框架（Executor Framework），围绕着Executor接口和它的子接口ExecutorService，以及实现这两个接口的ThreadPoolExecutor类展开。

Callable接口，类似于Runnable接口

* 这个接口的主方法名称为call()，可以返回结果。
* 当发送一个Callable对象给执行器时，将获得一个实现了Future接口的对象。可以使用这个对象控制Callable对象的状态和结果。

## 4.2 创建线程执行器 ##

使用执行器框架（Executor Framework）的第一步是创建ThreadPoolExecutor对象。可以ThreadPoolExecutor类提供的四个构造器或者使用Executors工厂类来创建ThreadPoolExecutor对象。一旦有了执行器，可以将Runnable或Callable对象发送给它去执行了。

**工作原理**

* getPoolSize()：返回执行器线程池中实际的线程数。
* getActiveCount()：返回执行器中正在执行任务的线程数。
* getCompleteTaskCount()：返回执行器已经完成的任务数。

为了完成执行器的执行，可以使用ThreadPoolExecutor类的shutdown()方法。这执行器执行完成所有待运行的任务后，将结束执行。调用shutdown()方法之后，如果尝试再发送另一个任务给执行器，任务将被拒绝，并且执行器也将抛出RejectedExecutionException异常。

**更多信息**

ThredPoolExecutor类提供了许多方法来获取自身状态的信息。

* shutdownNow()：这个方法会立即关闭执行器。执行器将不再执行那些正在等待执行的任务。这个方法将返回等待执行的任务列表。调用时，正在运行的任务将继续运行，但是这个方法并不等待这些任务完成。
* isTerminated()：如果调用了shutdown()或shutdownNow（）方法，并且执行器完成了关闭的过程，那么这个方法将返回true。
* isShutdown()：如果调用了shutdown()方法，那么这个方法将返回true。
* awaitTermination(long timeout, TimeUnit unit)：这个方法将阻塞所调用的线程，直到执行器完成任务或者达到所指定的timeout值。

TimeUnit是一个枚举类，有如下的常量：DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS。

## 4.3 创建固定大小的线程执行器 ##

当使用Executor类的newCachedThreadPool()方法创建基本的ThreadPoolExecutor是，执行器运行过程中将碰到线程数量的问题。如果线程池里没有空闲的线程可用，那么执行器将为接收到的每个任务创建一个新线程，当发送大量的任务给执行器并且任务需要持续较长的时间时，系统将会超负荷，应用程序将性能不佳。

创建固定大小的线程执行器

**工作原理**

Executors工厂类的newFixedThreadPool()方法来创建执行器，具有线程最大数量值的执行器。如果发送超过线程数的任务给执行器，剩余的任务将被阻塞直到线程池里有空闲的线程来处理它们。

newFixedThreadPool()方法接收执行器将拥有的线程数量的最大值作为参数。

ThreadPoolExecutor类

* getPoolSize():返回执行器中线程的实际数量。
* getActiveCount()：返回执行器正在执行任务的线程数量。

控制台输出的信息是5，表示执行器拥有5个线程，并且执行器不会超过这个最大的线程连接数。

当发送最后一个任务给执行器时，由于执行器只有5个活动的线程，所以剩余的95个任务只能等待空闲线程。getTaskCount()方法可以用来显示有多少个任务已经发送给执行器。

**更多信息**

Executors工厂类提供newSingleThreadExecutor()方法。这是一个创建固定大小线程执行器的极端场景，它将创建一个只有单一线程的执行器。因此，这个执行器只能在同一时间执行一个任务。

## 4.4 在执行器中执行任务并返回结果 ##

执行器框架（Executor Framework）的优势之一是，可以运行并发任务并返回结果。

Callable：声明了call()方法，可以在这个方法离实现任务的具体逻辑操作。Callable接口是一个泛型接口，这就意味着必须声明call方法返回的数据类型。
Future：接口声明了一些方法获取由Callable对象产生的结果，并管理它们的状态。

**工作原理**

* 控制任务的状态：可以取消任务和检查任务是否已经完成。isDone()来检查任务是否已经完成。
* 通过call()方法获取返回的结果。如果get()方法在等待结果时线程中断了，则将抛出一个InterruptedException异常。如果call()方法抛出异常那么get()方法将随之抛出ExecutionException异常。

**更多信息**

在调用Future对象的get()方法时，如果Future对象所控制的任务并未完成，那么这个方法将一直阻塞到任务完成，Future接口也提供了get()的其他调用方式。

* get(long timeout, TimeUnit unit)：如果调用这个方法时，任务的结果并未准备好，则方法等待所指定的timeout时间。如果等待超过了指定的时间而任务的结果还没有准备号好，那么这个方法将返回null。

## 4.5 运行多个任务并处理第一个结果 ##

采用多个并发任务来解决一个问题，往往只关心这些任务的第一个结果。

ThreadPoolExecutor类的invokeAny()方法接收以额任务列表，然后运行任务，并返回第一个完成任务并且没有抛出异常的任务的执行结果。每一个UserValidator对象被TaskValidator对象使用，TaskValidator对象实现了Callable接口，如果UserValidator类的validate()方法返回false值，那么TaskValidator类将抛出Exception异常。否则，返回true值。

1. 两个任务都返回true，那么invokeAny()方法的结果就是i首先完成任务的名称
2. 第一个任务返回true只，第二个任务抛出Exception异常，那么invokeAny()方法的结果就是第一个任务的名称。
3. 如果第一个任务抛出Exception异常，第二个任务返回true值，那么invokeAny()方法的结果就是第二个任务的名称。
4. 如果两个任务都抛出Exception异常，那么invokeAny()方法将抛出ExecutionException异常。

**更多信息**

ThreadPoolExecutor类还提供了invokeAny()方法的其他版本：invokeAny(Coolection<? extends Callable<T>> tasks, long timeout, TimeUnit unit):这个方法执行所有的任务，如果在给定的超时期满之前某个任务已经成功完成（也就是未抛出异常），则返回其结果。

TimeUnit是一个枚举类，有如下的常量：DAYS、HOURS、MICROSECONDS、MILLSECONDS、MINUTES、NANOSECONDS和SECONDS。

## 4.6 运行多个任务并处理所有结果 ##

* 如果任务执行结束，那么Future接口的isDone()方法将返回true
* 在调用shutdown()方法后，ThreadPoolExecutor类的awaitTermination()方法会将线程休眠，直到所有的任务执行结束。

**工作原理**

通过invokeAll()方法等待所有任务的完成，接收一个Callable对象列表，并返回一个Future对象列表。在这个列表中，每一个任务对应一个Future对象。Future对象列表中的第一个对象控制Callable列表中第一个任务。

**更多信息**

ExecutorService接口提供了invokeAll()方法的另一个版本：


## 4.7 在执行器中延时执行任务 ##

执行器框架（Executor Framework）提供了ThreadPoolExecutor类并采用线程池来执行Callable和Runnable类型的任务，采用线程池额可以避免所有线程的创建操作和提高应用程序的性能。

周期性地执行

**工作原理**

ThreadPoolExecutor类来创建定时执行器，在Java并发API中则推荐利用Executors工厂类来创建

* 即将执行的任务；
* 任务执行前所要等待的时间；
* 等待时间的单位，由TimeUnit类的一个常量来制指定。

**更多信息**

可以使用Runnable接口来实现任务，因为ScheduledThreadPoolExecutor类的schedule()方法可以同时接受这两个类型的任务。

ScheduledThreadPoolExecutor类是ThreadPoolExecutor类的子类，因而继承了ThreadPoolExecutor类所有的特性。但是，Java推荐仅在开发定时任务程序时采用SecheduledThreadPoolExecutor类

## 4.8 在执行器中周期性执行任务 ##

执行器框架（Executor Framework）提供了ThreadPoolExecutor类，通过线程池来执行并发任务从而避免了所有线程的创建操作。当发送一个任务给执行器后，根据执行器的配置，它将尽快地执行这个任务。当任务执行结束后，这个任务从执行器中删除；如果想再次执行这个任务，则需要再次发送这个任务到执行器。

**更多信息**

ScheduledThreadPoolExecutor类提供了其他方法来安排周期性任务的运行，比如，scheduleWithFixedRate()方法。

* scheduledAtFixedRate() 第3个参数表示任务两次执行开始时间的间隔
* schedulledWithFixedDelay() 第3个参数则是表示任务上一次执行结束的时间与任务下一次开始执行的时间的间隔

ScheduledThreadPoolExecutor实现shutdown()方法的行为，默认行为是当调用shutdown()方法后，定时任务就结束了。可以通过ScheduledThreadPoolExecutor类的setContinueExistingPeriodicTasksAfterShutdownPolicy()方法来改变这种行为，传递参数true给这个方法，这样调用shutdown()方法后，周期性任务仍将继续执行。

## 4.9 在执行器中取消任务 ##

使用执行器时，不需要管理线程，只需要Runnable或Callable任务并发送任务给执行器即。执行器负责创建线程，管理线程池中的线程，当线程不再需要时就销毁它们。

使用Future接口的cancel()方法来执行取消操作。

**工作原理**

Future接口的cancel()方法。根据调用cancel()方法所传递的参数以及任务的状态。

* 如果任务已经完成，或者之前被取消，或者由于某种原因而不能取消，那么方法将返回false并且任务也不能取消。
* 如果任务在执行器中等待分配Thread对象来执行它，那么任务被取消，并且不会开始执行。如果任务已经在运行，那么它依赖于调用cancel()方法时所传递的参数。如果传递的参数为true并且任务正在运行，那么任务将被取消。如果传递的参数为false并且任务正在运行，那么任务不会被取消。

**更多信息**

如果Future对象所控制任务已经被取消，那么使用Future对象的get()方法时抛出的CancellationException异常。

## 4.10 在执行器中控制任务的完成 ##

FutureTask类提供了一个名为done()的方法，允许在执行器中的任务执行结束之后，还可以实行一些代码。

当任务执行完成是受FutureTask类控制时，这个方法在内部被FutureTask类调用。在任务结果设置后以及任务的状态已改变为isDone之后，无论任务是否被取消或者正常结束，done()方法才被调用。

**工作原理**

FutureTask类会调用done()方法。

Callable类、ExecutableTask、FutureTask（ResultTask）

在创建号返回值以及改变任务状态为isDone之后，FutureTask类就会在内部调用done()方法，虽然无法改变任务的结果值，也无法改变任务的状态，但是可以通过任务来关闭系统资源、输出日志信息、发送通知等。

## 4.11 在执行器中分离任务的启动与结果的处理 ##

执行并发任务时，将Runnable或Callable任务发送给执行器，并获得Future对象来控制任务。

CompletionService类有一个方法用来发送任务给执行器，共享CompletionService对象，并发送任务到执行器，然后其他的对象可以处理任务的结果。



## 4.12 处理在执行器中被拒绝的任务 ##

shutdown()方法表示执行器应当结束

为了处理在执行器中被拒绝的任务，需要创建一个实现RejectedExecutionHandler接口的处理器。这个接口有一个rejectedExecution()方法

1. 一个Runnable对象，用来存储被拒绝的任务
2. 一个Executor对象，用来存储任务被拒绝的执行器

被执行器决绝的每一个任务都将调用这个额方法。需要先调用Executor类的setRejectedExecutionHandler()方法来设置用于被决绝的任务的处理程序。

**更多信息**

当接收器接收一个任内务并开始执行时，检查shutdown()方法是否已经被调用了。如果是，那么执行器就拒绝这个任务。

1. 执行器会寻找通过setRejectedExecutionHandler()方法设置的用于被拒绝的处理程序，如果找到一个处理程序，执行器就调用器rejectedExecution()方法；否则就抛出RejectedExecutionException异常。这是一个运行时异常，因此并不需要cache语句来对其进行处理

# 第5章 Fork/Join框架 #

* 创建Fork/Join线程池
* 合并任务的结果
* 异步运行任务
* 在任务中抛出异常
* 取消任务

## 5.1 简介 ##

执行器框架（Executor Framework）将任务的创建和执行进行分离，通过这个框架，只需要实现Runnable接口的对象和使用Executor对象，然后将Runnable对象发送给执行器。

1.0 Thread -> 1.5 Executor -> 7 ExecutorService接口的另一种实现，Fork/Join框架（分解/合并框架）

* 分解（Fork）操作：当需要将一个任务拆分成更小的多个任务时，在框架中执行这些任务；
* 合并（Join）操作：当一个主任务等待其创建的多个子任务的完成执行。

Fork/Join
* 任务只能使用fork()和join()操作当作同步机制。如果使用其他的同步机制，工作着线程就不能执行其他任务，当然这些任务是在同步操作里时。

* 任务不能执行I/O操作
* 任务不能抛出非运行时异常

Fork/Join框架的核心由下列两个类组成的。

* ForkJoinPool:
* ForkJoinTask:

* ResursiveAction:用于任务没有返回结果的场景。
* RecursiveTask:用于任务由返回结果的场景。

## 5.2 创建Fork/Join线程池 ##

* 创建用来执行任务的ForkJoinPool对象；
* 创建即将在线程池中被执行的任务ForkJoinTask子类。

* 采用默认的构造器创建ForkJoinPool对象；
* 在任务中将使用JavaAPI文档推荐的结构。

	if (problem size > default size) {
		tasks = divide(task);
		execute(tasks);
	} else {
		resolve problem using another algorithm
	}

**工作原理**

ForkJoinPool对象，和一个将在线程池中执行的ForkJoinTask的子类。使用了无参的类构造器创建了ForkJoinPool对象，因此它将执行默认的配置。创建一个线程数等于计算机CPU数目的线程池，创建好ForkJoinPool对象之后，那些线程也创建就绪了，在线程池中等待任务的到达，然后开始执行。

调用invokeAll()方法来执行一个主任务锁创建的多个子任务。

Fork/Join框架提供了一中比Runnable和Callable对象更加高下哦的任务管理机制。

调用shutdown()方法来结束ForkJoinPool的执行

**更多信息**

ForkJoinPool

* execute(Runnabletask)：这个方法发送一个Runnable任务给ForkJoinPool类。
* invoke(ForkJoinTask<T>task)
* ExecutorService类中声明的invokeAll()和invokeAny()方法，接受Callable对象作为参数。

ForkJoinTask

* invokeAll(ForkJoinTask<?>...tasks)接受一个可变的参数列表，可以传递尽可能多的ForkJoinTask对象给这个方法作为参数。
* invokeAll(Collection<T>tasks)这个版本的方法接受一个泛型类型T的对象集合（比如，ArrayList对象、LinkedList对象或者TreeSet对象）。这个泛型类型T必须是ForkJoinTask类或者它的子类。

## 5.3 合并任务的结果 ##

Fork/Join

RecursiveTask类来实现的。RecursiveTask类继承了ForkJoinTask类，并且实现了由执行器框架（Executor Framework）提供的Future接口。

**更多信息**

ForkJoinTask类提供了另一个complete()方法来结束任务的执行并返回结果。这个方法接收一个对象，对象的类型就是RecursiveTask类的泛型参数，然后在任务调用join()方法后返回这个对象作为结果。（异步任务来返回任务的结果）

RecursiveTask实现了Future接口，因此还有get()方法调用的其他版本；

TimeUnit是一个枚举类，有如下的常量：DAYS、HOURS、MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS。

## 5.4 异步运行任务 ##

在ForkJoinPool中执行ForkJoinTask时，可以采用同步或异步方式。

当采用同步方式执行时，发送任务

**更多信息**

join()方法来等待任务的技术，然后获取它们的结果。

* get()：如果ForkJoinTask类执行结束，或者一直等到结束，那么get()方法的这个版本则返回compute()方法返回的结果。
* get(long timeout, TimeUnit unit)：如果任务的结果未准备好，那么get()方法的这个版本将等待指定的时间。如果超过指定的时间了，任务仍未准备好，那么这个方法将返回null值。TimeUnit是一个枚举类，有如下的常量：DAYS、HOURS、MICROSECONDS、MILLISCONDS、MINUTES、NANOSECONDS、和SECONDS。

get()方法和join()方法还存在两个主要的区别：
* join方法不能被中断，如果中断调用join()方法的线程，方法将抛出InterruptedptException异常；
* 如果任务抛出任何任务时异常，那么get()方法将返回ExecutionException异常，但是join()方法将返回RuntimeException异常。

## 5.5 在任务中抛出异常 ##

* 非运行时异常（Checked Exception）：这些异常必须在方法上通过throws子句抛出，或者在方法体内通过 try{...}catch{...}方式进行捕获处理。比如IOException或ClassNotFoundException异常。
* 运行时异常（Unchecked Exception）：这些异常不需要在方法上通过throws子句抛出，也不需要在方法体内通过try{...}catch{...方式进行捕捉处理。比如NumberFormatException异常。}

1. 不能再ForkJoinTask类的compute()方法中抛出任务非运行时异常，因为这个方法实现没有包含任何throws声明。
2. compute()方法可以抛出运行时异常

## 5.6 取消任务 ##

ForkJoinPool类中执行ForkJoinTask对象时，在任务开始执行前可以取消它。

ForkJoinTask类提供了cancel()方法来达到取消任务的目的。在取消一个任务时要注意以下两点：

* ForkJoinPool类不提供任何方法来取消线程池中正在运行或者等待运行的所有任务；
* 取消任务时，不能取消已经被执行的任务。

ForkJoinTask类提供的cancel()方法允许取消一个仍没有被执行的任务。

如果任务已经开始执行，那么调用cancel()发给发也无法取消。这个方法接收一个名为mayInterruptIfRunning的boolean值参数。如果传递true值给这个方法，即使任务正在运行也将被取消。

Fork/Join框架的局限性，ForkJoinPool线程池中的任务不允许被取消。为了克服这种局限性，实现了TaskManager类，存储发送到线程池中的所有任务，可以用一个方法来取消存储的所有任务。如果任务正在运行或者已经执行结束，那么任务就不能被取消，cancel()方法返回false值。

# 第6章 并发集合 #

## 6.1 简介 ##

数据结构（DataStructure）

* 阻塞式集合
* 非阻塞式集合

* 非阻塞式列表对应的实现类：ConcurrentLinkedDeque类；
* 阻塞式列表对应的实现类：LinkedBlockingDeque类；
* 用于数据生成或消费的阻塞式列表对应的实现类：LinkedTrabsferQueue类；
* 按优先级排序列表元素的阻塞式列表对应的实现类：PriorityBlockingQueue类；
* 带有延迟列表元素的阻塞式列表对应的实现类：DelayQueue类；
* 非阻塞式可遍历映射对应的实现类：ConcurrentSkipListMap类；
* 随机数字对应的实现类：ThreadLocalRandom类；
* 原子变量对应的实现类：AtomicLong和AtomicIntegerArray类。

## 6.2 使用非阻塞式线程安全列表 ##

最基本的集合类型的是列表（List）。一个列表包含的元素数量不定，可以在任何位置添加、读取或移除元素。并发列表允许不同的线程在同一时间添加或移除列表中的元素，而不会造成数据不一致。

* 添加大量的数据到一个列表中
* 从同一个列表中移除大量的数据

**更多信息**

ConcurrentLinkedDeque类提供了其他从列表中读取数据的方法。

* getFirst()和getLast()：分别返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，这两个方法抛出NoSuchElementException异常。
* peek()、peekFirst()和peekLast()：分别返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，这些方法返回null。
* remove()、removeFirst()和removeLast()：分别返回列表中第一个和最后一个元素，返回的元素将会从列表中移除。如果列表为控，这些方法抛出NoSuchElementException异常。

## 6.3 使用阻塞式线程安全列表 ##

最基本的集合类型是列表。一个列表包含的元素数量补丁，可以在任何位置添加、读取或移除。并发列表允许不同的线程在同一时间添加或移除列表中的元素，而不会造成数据不一致。

**工作原理**

String LinkedBlockingDeque对象，用来实现一个阻塞式并发数据列表。

put()方法将字符串插入到列表中。如果列表已满（列表生成时指定了固定的容量），调用这个方法线程将被阻塞直到列表中有了可用的空间。take()方法从列表中取字符串。如果列表为空，调用这个方法的线程将被阻塞直到列表不为空（即用可用的元素）。

**更多信息**

LinkedBlockingDeque类提供了其他存取元素的方法，这些方法不会引起阻塞，而不是抛出异常或返回null。

* takeFirst()和takeList()：分别返回列表中第一个和最后一个元素，返回的元素会从列表中移除。如果列表为空，调用方法的线程将被阻塞到列表中有可用的元素出现。
* getFirst()和getLast()：分别返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，则抛出NoSuchElementExcption异常。
* peek()、peekFirst()和peekLast()：分贝返回列表中第一个和最后一个元素，返回的元素不会从列表中移除。如果列表为空，返回null。
* poll()、pollFirst()和pollLast()：分别返回列表中中第一个和最后一个元素，返回的元素将从列表中移除。如果列表为控，返回null。
* add()、addFirst()和addLast()：分别将元素添加到列表中第一位和最后一位。如果列表已满（列表生成时指定了固定的容量），这些方法将抛出IllegalStateException异常。

## 6.4 使用按优先级排序的阻塞式线程安全列表 ##

有序列表。Java引入了PriorityBlockingQueue类来满足这类需求。

**工作原理**

PriorityBlockingQueue类实现了一个含有Event对象啊的优先级队列。PriorityBlockingQueue中存放的所有元素都必须实现Comparable接口，所以Event类也实现了compareTo()方法。

所有event都有 优先级属性。带有最高优先级值的元素是队列中第一个元素。当实现compareTo()方法时，如果event本身的优先级高于作为参数的event的优先级值。结果返回-1.另一方面，如果event本身的优先级值低于作为参数的event的优先级值，结果返回1.如果两个对象的优先级值相等额，结果返回0.在返回值为0的情况下，PriorityBlockingQueue类不保证元素的次序。

**更多信息**

PriorityBlockingQueue类还提供了其他方法。

* clear(): 移除队列中的所有元素。
* take(): 返回队列中的第一个元素并将其移除。如果队列为空，线程阻塞直到队列中有可用的元素。
* put(E e):E是PriorityBlockingQueue的泛型参数，表示传入参数的类型，这个方法参数对应的元素插入到队列中。
* peek()：返回队列中的第一个元素，但不将其移除。

## 6.5 使用带有延迟元素的线程安全列表 ##

DelayQueue，这个类可以存放带有激活日期的元素。当调用方法从队列中返回或提取元素时，未来的元素日期将被忽略。

为了具有调用行为，存放到DelayQueue类中的元素中必须继承Delayed接口。Delayed接口使对象成为延迟对象，它使存放在DelayQueue类中的对象具有激活日期，即到激活日期的时间。

* compareTo(Delayed o)：Delayed接口继承了Comparable接口，因此有了这个方法。如果当前对象的延迟值小于参数对象的值，将返回一个小于0的值；如果当前对象的延迟值大于参数对象的值，将返回一个大于0的值；如果两者的延迟值相等则返回0.
* getDelay(TimeUnit unit)：返回到激活日期的剩余时间，单位由单位参数指定。TimeUnit磊是一个由下列常量组成的枚举类型：DAYS、HOURS|MICROSECONDS、MILLISECONDS、MINUTES、NANOSECONDS和SECONDS。

## 6.6 使用线程安全可遍历映射 ##

## 6.7 生成并发随机数 ##

## 6.8 使用原子变量 ##

# 第7章 地址并发类 #

## 7.1 简介 ##

* 实现一个接口以拥有接口定义的功能
* 覆盖类的一些方法，改变这些方法的行为，来满足需求，例如，覆盖Thread类的run()方法，它默认什么都不做，可以被用来覆盖以提供预期的功能。

## 7.2 定制ThreadPoolExecutor类 ##

Executor框架是一种将线程的创建和执行分离的机制。基于Executor和ExecutorService接口，及这两个接口的实现类ThreadPoolExecutor展开。Executor有一个内部线程池，并提供了将任务传递到池中线程以获得执行的方法。

* 通过Runnable接口实现的任务，它不返回结果；
* 通过Callable接口实现的任务，它返回结果。

**工作原理**

ThreadPoolExecutor类并覆盖了它的4个方法。beforeExecutor()和afterExecute()方法被用来计算任务的运行时间。

* 通过调用getCompletedTaskCount()方法获得已执行过的任务数；
* 通过调用getActiveCount()方法获得正在执行的任务数。



## 7.3 实现基于优先级的Executor类 ##

**工作原理**

把一个普通的执行器转换为基于优先级的执行器是非常简单的，只需要把PriorityBlockingQueue对象作为其中一个传入参数，并且要求它的泛型参数是Runnable接口即可。使用这种执行器，存放在优先队列中的所有对象必须实现Comparable接口。

MyPriorityTask实现了Runnable接口以成为执行任务，也实现了Comparable接口以存放再优先队列中。这个类有一个priority属性用来存放任务的优先级。
一个任务的优先级属性值越高，它越早北执行。compareTo()方法决定了优先队列中的任务顺序。

**更多信息**

配置Executor使用BlockingQueue接口的任意实现。DelayQueue，这个类用来存放带有延迟激活的元素，提供了只返回活动对象的方法。可以使用ScheduledThreadPoolExecutor类定制自己的类。

## 7.4 实现ThreadFactory接口生成定制线程 ##

工厂模式（Factory Pattern）在面向对象编程中是一个应用广泛的设计模式。它是一个创建模式（Creational Pattern），目标是床架哪一个类并通过这个类创建一个或多个类的对象。当创建一个类的对象时，使用工厂类而不是new 操作符。

通过工厂模式，能够将对象创建集中化（好处）改变对象的创建方式将会变得很容易，并且针对限定资源还可以限制创建对象的数量。例如：通过工厂模式生成了一个类型的N个对象，很容易获得创建这些对象的统计数据。

**工作原理**

MyThread类有三个属性分别存放它的创建时间、执行起始时间和执行结束时间。

getExecutionTime()方法使用了执行起始时间和结束属性，返回线程执行的时间。

实现ThreadFactory接口只有一个方法，即newThread()方法，它接受一个Runnable对象作为参数并返回一个执行Runnable对象的Thread。

**更多信息**

Java并发API提供了Executor类来生成执行线程，生成的执行线程通常是ThreadPoolExecutor类的对象。也可以使用这个类的defaultThreadFactory()方法获取ThreadFactory接口的最基本实现，

这个工厂能够生成基本的线程对象，并且生成线程都属于同一个线程组对象。

自由使用ThreadFacttory接口，而不必拘泥于Executor框架。

## 7.5 在Executor对象中使用ThreadFactory ##

# 第8章 测试并发应用程序 #

* 监控Lock接口
* 监控Phaser类
* 监控执行器框架
* 监控Fork/Join池
* 输出高效的日志信息
* 使用FindBugs分析并发代码

## 8.1 简介 ##

* 如何获取应用程序中元素的信息，这些信息有助于测试应用程序；
* 如何使用IDE（Integrated Development Environment）和其他工具（如FindBugs）来测试应用程序；
* 如何使用类库（如MultithreadedTC）来进行自动化测试

## 8.2 监控Lock接口 ##

Lock接口是Java并发API同步代码块的基本机制之一。定义了临界区（Critical Section）。临界区是同一时间只能被一个线程执行的共享资源的代码块。这种机制是通过Lock接口和ReentranLock类而实现的。

**工作原理**

**更多信息**

ReentrantLock类还提供了其他方法来获取Lock对象信息。

* getHoldCount

