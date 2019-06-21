# JVM字节码从入门到精通 #

# 字节码初体验——从Hello World说起 #

## 0x01 java文件如何变成.class文件 ##

## 0x02 魔数 0xCAFEBABE ##

0xCAFEBABE，JVM识别.class文件的标志，虚拟机在加载类文件之前会先检查这四个字节。如果不是0xCAFEBABE则拒绝加载该文件。

## 0x03 javap 详解 ##

类文件是二进制块。

* `-c`选项



* `-p`选项



* `-v`选项


* `-s`选项



## 0x04 小结 ##

## 0x05 思考 ##

javap的 `-l`参数有什么用

# 字节码原理初步——基于栈的执行引擎 #

字节码是运行在JVM上的。

## 0x01 虚拟机：stack based vs register based ##

虚拟机常见的实现方式有两种：Stack based和Register based。

* Stack based：Hotspot JVM、.net CLR
* Register based：LuaVM和DalvikVM

## 0x02 栈帧 ##

栈帧（Stack Frame）是用于支持虚拟机进行方法调用和方法执行的数据结构 栈帧随着方法调用而创建，随着方法结束而销毁，栈帧的存储空间分配在 Java 虚拟机栈中，每个栈帧拥有自己的**局部变量表（Local Variables）**、**操作数栈（Operand Stack）** 和 **指向运行时常量池的引用**
