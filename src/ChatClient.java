import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

class ChatClient
{

    public static void main(String[] args) throws IOException {
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
        String msg; String responseIP; String reponsemsg;
        InetAddress sendersAdd;
        boolean live =true;
        while(live)
        {
            //Getting what user typed in console & Sending it
            userInput = new BufferedReader(new InputStreamReader(System.in));
            msg = userInput.readLine();
            message = new DatagramPacket(msg.getBytes(), msg.length(), group, 40202);
            s.send(message);

            //Getting what other useres responses
            buf = new byte[1000];
            recvmsg = new DatagramPacket(buf, buf.length);
            s.receive(recvmsg);

            //Printing response to console with there IP
            sendersAdd = recvmsg.getAddress();
            responseIP = sendersAdd.getHostAddress();
            reponsemsg = new String(recvmsg.getData());
            System.out.println(responseIP + ": " + reponsemsg);

            if(reponsemsg.equals("STOP"))
                live = false;
        }

    }




}