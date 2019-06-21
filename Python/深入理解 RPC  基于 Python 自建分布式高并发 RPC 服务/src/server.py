import socket

sock = socket.socket(socket.AF_INET, socket.SOCK_NONBLOCK)
sock.bind(("localhost", 8080))
sock.listen(1)
while True:
    conn, addr = sock.accept()
    print(conn.recv(1024))
    conn.sendall(bytes("world"))
    conn.close()