# Chapter 2: 一等公民的函数 #
            
## 快速概览 ##
         
    var BlogController = (function() {
      var index = function(posts) {
        return Views.index(posts);
      };
    
      var show = function(post) {
        return Views.show(post);
      };
    
      var create = function(attrs) {
        return Db.create(attrs);
      };
    
      var update = function(post, attrs) {
        return Db.update(post, attrs);
      };
    
      var destroy = function(post) {
        return Db.destroy(post);
      };
    
      return {index: index, show: show, create: create, update: update, destroy: destroy};
    })();

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