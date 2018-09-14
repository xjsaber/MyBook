# C# 指南 #

## C# 中的新增功能 ##

### C# 7.2中的新增功能 ###

#### 具有值类型的引用语义 ####


#### 数值文字中的前导下划线 ####



#### private protected访问修饰符 ####

### C# 7.0中的新增功能 ###

#### out 变量 ####

before
	
	int numericResult;
	if (int.TryParse(input, out numbericResult))
		WriteLine(numbericResult);
	else
		WriteLine("Could not parse input");

7.0

	if (int.TryParse(input, out int result))
    	WriteLine(result);
	else
    	WriteLine("Could not parse input");	

#### 元组 ####

ValueTuple 必须添加Nuget包`System.ValueTuple`

	var letters = ("a", "b");
	(string Alpha, string Btta) = namedLetters = ("a", "b");

#### 模式匹配 ####

#### 更多的 expression-bodied 成员 ####



#### 引发表达式 ####

	public string Name
	{
	    get => name;
	    set => name = value ?? 
	        throw new ArgumentNullException(paramName: nameof(value), message: "New name must not be null");
	}

	private ConfigResource loadedConfig = LoadConfigResourceOrDefault() ?? 
	    throw new InvalidOperationException("Could not load config");

以前的版本

	public ApplicationOptions()
	{
	    loadedConfig = LoadConfigResourceOrDefault();
	    if (loadedConfig == null)
	        throw new InvalidOperationException("Could not load config");
	
	}

#### 数字文本语法改进 ####

二进制文本和数字分隔符。


### C# 6中的新增功能 ###

#### 自动属性增强功能 ####

**只读自动属性**

	public string FirstName {get; }
	public string LastName {get; }

**自动属性初始值设定项**


#### Expression-bodied函数成员 ####

	public override string ToString() => $"{LastName}, {FirstName}"

	public string FullName => $"{FirstName} {LastName}"

#### Using static ####



#### Null 条件运算符 ####

	var first = person?.FirstName;

	first = person?.FirstName ?? "Unspecified";

#### 字符串内插 ####

	public string FullName => $"{FirstName} {LastName}";

**字符串内插和特定区域性**

	public string GetAllGrades() =>
	    $@"All Grades: {Grades.OrderByDescending(g => g)
	    .Select(s => s.ToString("F2")).Aggregate((partial, element) => $"{partial}, {element}")}";

#### 异常筛选器 ####

如果用于异常筛选器的表达式计算结果为 `true`，则 catch 子句将对异常执行正常处理。 如果表达式计算结果为 `false`，则将跳过 `catch` 子句。

	public static async Task<string> MakeRequest()
	{ 
	    var client = new System.Net.Http.HttpClient();
	    var streamTask = client.GetStringAsync("https://localHost:10000");
	    try {
	        var responseText = await streamTask;
	        return responseText;
	    } catch (System.Net.Http.HttpRequestException e) when (e.Message.Contains("301"))
	    {
	        return "Site Moved";
	    }
	}

老版本

	public static async Task<string> MakeRequest()
	{ 
	    var client = new System.Net.Http.HttpClient();
	    var streamTask = client.GetStringAsync("https://localHost:10000");
	    try {
	        var responseText = await streamTask;
	        return responseText;
	    } catch (System.Net.Http.HttpRequestException e)
	    {
	        if (e.Message.Contains("301"))
	            return "Site Moved";
	        else
	            throw;
	    }
	}

#### `nameof`表达式 ####



#### Catch 和 Finally块中的Await ####

C# 5 对于可放置 await 表达式的位置有若干限制。 其中一个限制已在 C# 6 中删除。 现在，可以在 catch 或 finally 表达式中使用 await。

	public static async Task<string> MakeRequestAndLogFailures()
	{ 
	    await logMethodEntrance();
	    var client = new System.Net.Http.HttpClient();
	    var streamTask = client.GetStringAsync("https://localHost:10000");
	    try {
	        var responseText = await streamTask;
	        return responseText;
	    } catch (System.Net.Http.HttpRequestException e) when (e.Message.Contains("301"))
	    {
	        await logError("Recovered from redirect", e);
	        return "Site Moved";
	    }
	    finally
	    {
	        await logMethodExit();
	        client.Dispose();
	    }
	}

#### 索引初始值设定项 ####

	现在初始化可以用于Dictionary<TKey, TValue>

	private Dictionary<int, string> webErrors = new Dictionary<int, string> {
		[404] = "Page not Found",
		[302] = "Page moved, but left a fowwarding address.",
		[500] = "The web server can't come out to play today."
	}

#### 集合初始值设定项中的扩展Add方法 ####



#### 改进重载解析 ####

在以前的一些构造中，以前版本的 C# 编译器可能会发现涉及 lambda 表达式的一些方法不明确。

	static Task DoThings() 
	{
	     return Task.FromResult(0); 
	}

## C# 发展历史 ##

### C# 1.0版 ###

### C# 2.0 版 ###

* 泛型
* 分部类型
* 匿名方法
* 可以为null的类型
* 迭代器

####  协变和逆变 ####

协变和逆变能够实现数组类型、委托类型和泛型类型参数的隐式引用转换。 协变保留分配兼容性，逆变则与之相反。

### C# 3.0 版 ###

* 自动实现的属性
* 匿名类型
* 查询表达式
* Lambda表达式


#### 表达式树 ####

	// t1 int
    // t2 int 
    // tResult int
    //public delegate TResult Func<T1, T2, TResult>(T1 arg1, T2 arg2);
    Func<int, int, int> function = (a, b) => a + b;
	// c = 8
    int c = function(3, 5);

#### 扩展方法 ####

The following list contains basic features and properties of extension methods:

1. It is a static method.
2. It must be located in a static class.
3. It uses the "this" keyword as the first parameter with a type in .NET and this method will be called by a given type instance on the client side.
4. It also shown by VS intellisense. When we press the dot (.) after a type instance, then it comes in VS intellisense.
5. An extension method should be in the same namespace as it is used or you need to import the namespace of the class by a using statement.
6. You can give any name for the class that has an extension method but the class should be static.
7. If you want to add new methods to a type and you don't have the source code for it, then the solution is to use and implement extension methods of that type.
8. If you create extension methods that have the same signature methods as the type you are extending, then the extension methods will never be called.


### C# 4.0版 ###

#### 动态绑定 ####

在通过`dynamic`类型实现的操作中，该类型的作用是绕过编译时类型检查。改为在运行时解析这些操作。

[dynamic](https://docs.microsoft.com/zh-cn/dotnet/csharp/language-reference/keywords/dynamic)

* 命名参数/可选参数
* 泛型协变和逆变
* 嵌入的互操作类型

### C# 5.0版 ###

* 异步成员
* 调用方信息特性

async和await





