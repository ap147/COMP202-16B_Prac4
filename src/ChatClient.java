import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
//https://www.mkyong.com/java/how-do-convert-byte-array-to-string-in-java/
class ChatClient
{

    public static void main(String[] args) throws IOException {
        System.out.println("-------- CHAT CLIENT STARTED --------");
        System.out.println();
        //Multicast that we wil be listening to
        InetAddress group = InetAddress.getByName("239.0.202.1");
        //Listening on port
        MulticastSocket s = new MulticastSocket(40202);

        //Getting it to listen to 239.0.202.1
        s.joinGroup(group);

        BufferedReader userInput;
        DatagramPacket message;
        DatagramPacket recvmsg;
        byte [] buf;
        String msg; String responseIP;
        InetAddress sendersAdd;

        MulticastSocketThread Thread = new MulticastSocketThread(s);

        Thread.start();
        while(true)
        {
            //Getting what user typed in console & Sending it
            userInput = new BufferedReader(new InputStreamReader(System.in));
            msg = userInput.readLine();
            message = new DatagramPacket(msg.getBytes(), msg.length(), group, 40202);
            s.send(message);
        }
    }

}