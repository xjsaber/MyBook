# 第七章 并行数据处理与性能 #

## 7.1 并行流 ##

## 7.2 分支/合并框架 ##

分支/合并框架的目的是以递归方式将可以并行的任务拆分成更小的任务，然后将每个子任务的结果合并起来生成整体结果。

ExecutorService接口的一个实现

### 7.2.1 使用RecursiveTask ###

	if （任务足够小或不可分）{
		顺序计算该任务
	} else {
		将任务分成两个子任务
		递归调用本方法，拆分每个子任务，等待所有子任务完成
		合并每个子任务的结果
	}

### 7.2.2 使用分支/合并框架的最佳做法 ###

### 7.2.3 工作窃取 ###

## 7.3 Spliterator ##

	public interface Spliterator<T> {
		boolean tryAdvance(Consumer<? super T> action);
		Spliterator<T> trySplit();
		long eastimateSize();
		int characteristics();
	}

### 7.3.1 拆分过程 ###
将Stream拆分成多个部分的算法是一个递归过程。

### 7.3.2 实现你自己的Spliterator ###


## 7.4 小结 ##

