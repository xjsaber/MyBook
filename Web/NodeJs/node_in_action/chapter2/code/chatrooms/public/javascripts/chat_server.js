/**
 * Created by xjsaber on 2016/8/31.
 */
var socketIO = require("socket.io");
var io;
var guestNumber = 1;
var nickNames = {};
var namesUsed = {};
var currentRoom = {};

exports.listen = function (server) {
    io = socketio.listen(server);
    io.set("log level", 1);

    io.sockets.on("connection", function (socket) {
        guestNumber = assignGuestName(socket, guestNumber, nickNames, namesUsed);
    });
    joinRoom(socket, "Lobby");

    handleMessageBroadcasting(socket, nickNames);

    socket.on("rooms", function () {
        socket.emit("roomes", io.sockets.manager.room);
    })
};
/**
 * 分配用户昵称
 * @param socket
 * @param guestNumber
 * @param nickNames
 * @param namesUsed
 * @returns {*}
 */
function assignGuestName(socket, guestNumber, nickNames, namesUsed) {
    var name = "Guest" + guestNumber; //生成新昵称
    nickNames[socket.id] = name; //把用户昵称跟客户端连接ID关联上
    socket.emit("nameResult", {
        success: true,
        name: name
    });
    namesUsed.push(name); //存放已经被占用的昵称
    return guestNumber + 1; //增加用来生成昵称的计数器
}
/**
 * 与进入聊天室相关的逻辑
 * @param socket
 * @param room
 */
function joinRoom(socket, room) {
    socket.join(room);
    currentRoom[socket.id] = room;
    socket.emit("joinResult", {room: room});
    socket.broadcast.to(room).emit("message", {
        text: nickNames[socket.id] + " has joined " + room + "."
    });

    var usersInRoom = io.socket.clients(room);
    if (usersInRoom.length > 1){
        var usersInRoomSummary = "Users currently in " + room + ": ";
        for (var index in usersInRoom){
            var userSocketId = usersInRoom[index].id;
            if (userSocketId != socket.id){
                if (index > 0){
                    usersInRoomSummary += ", ";
                }
                usersInRoomSummary += nickNames[userSocketId];
            }
        }
    }
    usersInRoomSummary += ".";
    socket.emit("message", {text: usersInRoomSummary});
}