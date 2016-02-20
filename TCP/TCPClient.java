import java.io.*; 
import java.net.*; 


public class TCPClient { 

    public static void main(String argv[]) throws Exception 
    { 
        String hostname = InetAddress.getLocalHost().getHostName();
        String sentence; 
        String modifiedSentence; 

        BufferedReader inFromUser = 
            new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket(hostname, 6791); 

        DataOutputStream outToServer = 
            new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = 
            new BufferedReader(new
                InputStreamReader(clientSocket.getInputStream())); 

        System.out.println("Enter request :");
        
        while(true)
        {   
            sentence = inFromUser.readLine();
            //没有必要初始化things为长度为1的字符串数组
            String[] things = new String[1];
            things=sentence.split(":");
            //字符是CHECK，ADD还是end已经在server端检查了，以下不需要再检查一次
             if(!sentence.equals("end"))
             {
                  System.out.println("Enter request:"+sentence);
             }
            else if (things[0].equals("CHECK"))
            {
              String getNum;
              //之前没有发送（writeBytes）就开始读取，无法读取到任何数据
              //逻辑上应当注意client和server端的readline()和writeByte()一一对应
              getNum= inFromServer.readLine(); 
              System.out.println("Name'number is: " + getNum); 
            }
           //字符是CHECK，ADD还是end已经在server端检查了，以上不需要再检查一次

            outToServer.writeBytes(sentence + '\n');
            
            if(sentence.equals("end"))
            {
              System.out.println("Disconnect with server ");
              break;
            }

            modifiedSentence = inFromServer.readLine(); 

            System.out.println("FROM SERVER: " + modifiedSentence); 
            
            //如果想再提示客户端输入request，此处应当同样加上 System.out.println("Enter request :");

        }
        clientSocket.close(); 

    } 
}
