// giveMe函数声明了一个叫config的参数，希望容器根据这个名字找到同名对象，并且注入进来
var giveMe = function(config){
    //经过注入后，此处config的内容为{delay: 1}
    //跟registry中保存的是同一个实例
};
//注册表，这里保存了可注入对象，包括一个名为config的对象
var registry = {
    config: {
        delay: 1
    }
}
// 注入函数
var inject = function(func, thisForFunc){
    // 获取func的源码，这样我们才能知道func需要什么参数
    var sourceCode = func.toString();
    // 用正则表达式解析源码
    var matcher = sourceCode.match("\(.+\)"); //正则表达式有些复杂，省略
    let objectIds = sourceCode.match(matcher);
    // 从matcher中解析出各个参数的名称，解析过程省略
    var objects = [];
    // 准备调用func时用的参数表
    for (var i = 0; i < objectIds.length; i++){
        var objectName = objectIds[i];
        // 根据对象名称查阅出相应的对象
        var object = registry[objectName];
        // 放到数组中准备作为参数传过去
        objects.push(object);
    }
    // 通过apply调用func函数，并且把参数表传过去
    func.apply(thisForFunc || func, objects);
};

inject(giveMe);