package socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by xjsaber on 2017/3/13.
 *
 */
public class DaytimeServer {
    private final static int PORT = 13;

    public static void main(String[] args){
        try(ServerSocket server = new ServerSocket(PORT)){
            while (true){
                try(Socket connection = server.accept()){
                    Writer out = new OutputStreamWriter(connection.getOutputStream());
                    Date now = new Date();
                    out.write(now.toString() + "\r\n");
                    out.flush();
                    connection.close();
                }catch (IOException ignored){}
            }
        }catch (IOException ex){
            System.err.println(ex);
        }
    }
}
