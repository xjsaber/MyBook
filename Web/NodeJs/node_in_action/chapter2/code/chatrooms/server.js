/**
 * Created by xjsaber on 2016/8/31.
 */
/**
 * 内置的 http 模块提供了HTTP服务器和客户端功能
 */
var http = require("http");
/**
 *  文件流模块 读取文件流
 */
var fs = require("fs");
/**
 * 内置的 path模块提供了与文件系统路径相关的功能
 */
var path = require("path");
/**
 * 附加的 mime 模块有根据文件扩展名得出 MIME 类型的能力
 */
var mime = require("mine");
/**
 * cache 是用来缓存文件内容的对象
 */
var cache = {};
function send404(response) {
    response.writeHead(404, {"Content-Type": "text/plain"});
    response.write("Error 404: resource not found.");
    response.end();
}
function sendFile(response, filePath, fileContents) {
    response.writeHead(200,
        {"Content-Type": mime.lookup(path.basename(filePath))}
    );
    response.end(fileContents);
}
function serveStatic(response, cache, absPath) {
    if (cache[absPath]){
        sendFile(response, absPath, cache[absPath]);
    }
    else {
        fs.exists(absPath, function (exists) {
            if (exists){
                fs.readFile(absPath, function (err, data) {
                    if (err){
                        send404(response);
                    }
                    else {
                        cache[absPath] = data;
                        sendFile(response, absPath, data);
                    }
                })
            }
        })
    }
}
var server = http.createServer(function (request, response) {
    var filePath = false;
    if (request.url == "/"){
        filePath = "public/index.html";
    } else {
        filePath = "public" + request.url;
    }
    var absPath = "./" + filePath;
    serveStatic(response, cache, absPath);
});
server.listen(3000, function () {
    console.log("Server listening on port 3000.");
});