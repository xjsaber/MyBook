# 自己动手写Java虚拟机 #

# 第1章 命令行工具 #

# 第2章 搜索class文件 #

## 2.1 类路径 ##

Oracle的Java虚拟机实现根据类路径（class path）来搜索类。按照搜索的先后顺序，类路径可以分为以下3个部分：

* 启动类路径（booststrap classpath）
* 扩展类路径（extension classpath）
* 用户类路径

