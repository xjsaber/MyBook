# ch12 新的日期和时间API #

## 12.1 LocalDate、LocalTime、Instante、Duration以及Period ##

### 12.1.1 使用LocalDate和LocalTime ###

	LocalDate date = LocalDate.of(2014, 3, 18); //2014-03-18

### 12.1.2 合并日期和时间 ###

### 12.1.3 机器的日期和时间格式 ###

### 12.1.4 定义Duration和Period ###

## 12.2 操纵、解析和格式化日期 ##

withAttribute方法会创建对象的一个副本，并按照需要修改它的属性。

### 12.2.1 使用TemporalAdjuster ###

### 12.2.2 打印输出及解析日期-时间对象 ###

## 12.3 处理不同的时区和历法 ##

java.time.ZoneId <- java.util.TimeZone

### 12.3.1 利用和UTC/格林尼治时间的固定偏差计算时区 ###

### 12.3.2 使用别的日历系统 ###

