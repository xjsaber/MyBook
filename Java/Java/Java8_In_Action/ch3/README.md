# 第3章 Lambda表达式 #

## 3.1 Lambda管中窥豹 ##

## 3.3 把Lambda付诸实践：环绕执行模式 ##

### 3.3.1 第1步：记得行为参数化 ###

	String result = processFile((BufferedReader br) -> 
		br.readLine() + br.readLine());

