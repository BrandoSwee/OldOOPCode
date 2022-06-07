////////////////////////////////////////////////
//
//  Brandon Sweeney
//  Assignment 4 
//  Magic 8-Ball Server
//  11 - 15 - 19
//
////////////////////////////////////////////////
package magic_8BallServer;

import java.net.ServerSocket;
import java.net.Socket;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// 
// DESCRIPTION: This program sends a web page with a java server. The server
//              waits on port 8000. Users can recieve the contents of the
//              web page by connecting with the socket.
//
// INPUTS: None
// 
// OUTPUTS:  The program outputs a page on port 8000 for a user to connect to
//           with a socket.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Magic_8BallServer {
    public static void main(String[] args) {
        System.out.println("waiting for connection on port 8000");
        try{
            ServerSocket socket = new ServerSocket(8000);
            while(true){
                Socket connectionSocket = socket.accept();
                UserHandler handler = new UserHandler(connectionSocket);
                Thread aThread = new Thread(handler);
                aThread.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
