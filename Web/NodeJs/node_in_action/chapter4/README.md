# 第4章 构建Node Web程序 #

## 4.1 HTTP服务器的基础知识

### 4.1.1 Node如何向开发者呈现HTTP请求

    var http = require("http");

### 4.1.2 一个用"Hello World"做响应的HTTP服务器
    
    var http = require("http");
    var server = http.createServer(function(request, response) {
        response.write("Hello World");
        response.end()
    });

