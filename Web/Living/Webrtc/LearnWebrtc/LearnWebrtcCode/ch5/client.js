var name,
    connectedUser;

var connection = new WebSocket('ws://localhost:8888');

connection.onopen = function (ev) {
    console.log("Connected");
};

// 通过回调函数处理所有的信息
connection.onmessage = function (message) {
    console.log("Got message", message.data);

    var data = JSON.parse(message.data);
    switch (data.type){
        case "login":
            break;
        case "offer":
            break;
        case "answer":
            break;
        case "candidate":
            break;
    }
};

connection.onerror = function (err) {
    console.log("Got error", err);
};

function send(message) {
    if (connectedUser) {
        message.name = connectedUser;
    }
    connection.send(JSON.stringify(message))
}