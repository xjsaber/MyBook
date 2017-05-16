# ch7 取消与关闭 #

## 7.1 任务取消 ##

[code1](code1)

[code2](code2)

一个可取消的任务必须拥有取消策略（Cancellation Policy），在这个策略中将详细地定义取消操作的“How”、“When”以及“What”，即其他代码

### 7.1.1 中断 ###

PrimeGenerator中的取消机制最终会使得搜索素数的任务退出，但在退出过程中需要花费一定的时间。
