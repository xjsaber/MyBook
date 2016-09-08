/**
 * Created by xjsaber on 2016/8/31.
 */
var Chat = function (socket) {
    this.socket = socket;
};
Chat.prototype.sendMessage = function (room, text) {
    var message = {
        room: room,
        text: text
    };
    this.socket.emit("message", message);
};
Chat.prototype.changeRoom = function (room) {
    this.socket.emit("join", {
        newRoom: room
    });
};