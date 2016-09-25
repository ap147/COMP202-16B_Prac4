import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;

//https://www.mkyong.com/java/how-do-convert-byte-array-to-string-in-java/

class MulticastSocketThread extends Thread
{
    //Multicast Socket that we are listening in
    MulticastSocket multicastSocket;

    public MulticastSocketThread(MulticastSocket s) {
        multicastSocket = s;
    }

    public void run()
    {
        //Store the packet thats being sent
        DatagramPacket recvmsg;
        byte [] buf;

        //IP addess of whoever is sending a message
        String responseIP;
        InetAddress sendersAdd;
       
    //Listening to Multicast socket passed in and printing out messages
     while(true)
     {
         buf = new byte[1000];
         //Message will be stored in this
         recvmsg = new DatagramPacket(buf, buf.length);

        try 
	{
            multicastSocket.receive(recvmsg);
        }
	 catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("Thread Error");
        }

        //Printing message to console with there IP
        sendersAdd = recvmsg.getAddress();
        responseIP = sendersAdd.getHostAddress();
        System.out.println(responseIP + ": " + new String(recvmsg.getData()));
      }
    }
}
