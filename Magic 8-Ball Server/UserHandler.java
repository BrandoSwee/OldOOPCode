package magic_8BallServer;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;


public class UserHandler implements Runnable{
    protected Socket sock;
    protected int num;
    protected String[] responses = new String[]{"It is certain", 
                                                "It is decidedly so", 
                                                "Without a doubt", 
                                                "Yes, definitely", 
                                                "You may rely on it", 
                                                "As I see it, yes", 
                                                "Most likely", 
                                                "Outlook good", 
                                                "Yes", 
                                                "Signs point to yes", 
                                                "Reply hazy try again", 
                                                "Ask again later", 
                                                "Better not tell you now", 
                                                "Cannot predict now", 
                                                "Concentrate and ask again", 
                                                "Don't count on it", 
                                                "My reply is no", 
                                                "My sources say no", 
                                                "Outlook not so good", 
                                                "Very doubtful",};
    protected Random rnd = new Random();
    //Constructors for the UserHandler class
    UserHandler(){
        this.sock = null;
        num = rnd.nextInt(20);
    }
    UserHandler(Socket sock){
        this.sock = sock;
        num = rnd.nextInt(20);
    }
/***************************************************************** 
* 
* Name:   run
* Description:  This run method implements the run method of the
*       Runnable interface. This lets the program run multiple send
*       threads at the same time.
* Inputs: None
* Returned value: The web page 
* Preconditions: None
* 
*****************************************************************/
    @Override
    public void run() {
        try{
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());
            System.out.println("HTTP/1.0 200 OK\nThis web page was sent by our simple Java Server");
            String text = "HTTP/1.0 200 OK\n\nThis web page was sent by our simple Java Server\n<h1>Greetings</h1>\n"
                    + responses[num];
            
            output.writeBytes(text);
            output.close();
            sock.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
