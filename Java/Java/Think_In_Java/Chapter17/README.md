# 第17章 容器深入研究 #

## 17.1 完整的容器分类法 ##

![img/]()

* Queue接口 及其实现PriorityQueue和各种风格的BlockingQueue
* ConcurrentMap接口及其实现ConcurrentHashMap，用于多线程机制的
* CopyOnWriteArrayList和CopyOnWriteArraySet，用于多线程机制
* 在Collections类中的多个便利方法。

## 17.2 填充容器 ##

使用注解的过程中，很重要的一个部分就是创建与使用 *注解处理器*。

### 20.2.1 注解元素 ###

标签@UseCase由UseCase.java定义，其中包含int元素id，以及一个String元素description

* 所有基本类型（int， float， boolean等）
* String
* Class
* enum
* Annotation
* 以上类型的数组

### 20.2.2 默认值限制 ###

1. 元素不能由不确定的值
2. 元素必须要么具有默认值，要么在使用注解时提供元素的值。

对于非基本类型的元素，在源代码中声明时，或是在注解接口中定义默认值时，都不能以null作为其值。

### 20.2.3 生成外部文件 ###

Web Service、自定义标签库以及对象/关系映射工具，一般需要XML描述文件，而这些描述文件脱离于源代码之外。

假设希望提供一些基本的对象/关系映射功能，能够自动生成数据库表，用以存储JavaBean对象。可以选择使用XML描述文件，指明类的名字、每个成员以及数据库映射的相关信息。然而，如果使用注解的话，可以将搜娱哦的信息保存在JavaBean源文件中。

### 20.2.4 注解不支持继承 ###

不能使用关键字extends来继承某个@interface。

### 20.2.5 实现处理器 ###


## 20.3 使用apt处理注解 ##