# Chapter 3: Pure Happiness with Pure Functions #
            
## 再次强调“纯” ##
         
纯函数是这样一种函数，即相同的输入，永远会得到相同的输出，而且没有任何可观察的副作用。
         
         var xs = [1,2,3,4,5];
         
         // 纯的
         xs.slice(0,3);
         //=> [1,2,3]
         
         xs.slice(0,3);
         //=> [1,2,3]
         
         xs.slice(0,3);
         //=> [1,2,3]
         
         
         // 不纯的
         xs.splice(0,3);
         //=> [1,2,3]
         
         xs.splice(0,3);
         //=> [4,5]
         
         xs.splice(0,3);
         //=> []
         


这个可笑的控制器（controller）99% 的代码都是垃圾。我们可以把它重写成这样：
var BlogController = {index: Views.index, show: Views.show, create: Db.create, update: Db.update, destroy: Db.destroy};
...或者直接全部删掉，因为它的作用仅仅就是把视图（Views）和数据库（Db）打包在一起而已。
            
## 为何钟爱一等公民？ ##


你一定要非常小心 this 值
    var fs = require('fs');
    
    // 太可怕了
    fs.readFile('freaky_friday.txt', Db.save);
    
    // 好一点点
    fs.readFile('freaky_friday.txt', Db.save.bind(Db));