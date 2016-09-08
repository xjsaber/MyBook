// var fs = require("fs");
// var stream = fs.createReadStream("./resource.json");
// stream.on("data", function (chunk) {
//     console.log(chunk);
// });
// stream.on("end", function () {
//     console.log("finished");
// });

var http = require("http");
var fs = require("fs");
http.createServer(function (req, res) {
    res.writeHead(200, {"Content-Type": "image/png"});
    fs.createReadStream("./image.png").pipe(res);
}).listen(3000);
console.log("server running as http://localhost:3000");