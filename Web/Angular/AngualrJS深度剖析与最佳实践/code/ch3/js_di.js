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

var inject = function(func, thisForFunc){
    var sourceCode = func.toString();
    var matcher = sourceCode.match();
    var objects = [];
    for (var i = 0; i < objectIds.length; i++){
        var objectName = objectIds[i];
        var object = registry[objectName];
        objects.push(object);
    }
    func.apply(thisForFunc || func, objects);
};