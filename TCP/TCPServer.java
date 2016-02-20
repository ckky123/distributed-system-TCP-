import java.io.*; import java.net.*;import java.util.HashMap;import java.lang.*;public class TCPServer {     public static void main(String argv[]) throws Exception     {         String clientSentence;         String capitalizedSentence;         HashMap<String,String> directory = new HashMap<String,String>();        ServerSocket welcomeSocket = new ServerSocket(6791);         Socket connectionSocket = welcomeSocket.accept();         BufferedReader inFromClient =             new BufferedReader(new                InputStreamReader(connectionSocket.getInputStream()));        DataOutputStream  outToClient =             new DataOutputStream(connectionSocket.getOutputStream());                                 while(true) {        	//你发送的是以:作为分隔符的，这里也应该是冒号        	//.split()返回的是一个数组（比如“ABC:DEF:GHI”会返回ABC,DEF,GHI三个字符串）,clientSentence不应该是String而应该是String数组            clientSentence = inFromClient.readLine().split(" ");             if (clientSentence[0].equals("CHECK:"))            {                String getNum = directory.get(clientSentence[1]);                outToClient.writeBytes(clientSentence[1] + "'s number is " + getNum + "\n");                         }            else if (clientSentence[0].equals("ADD:"))            {            	//应当加入判断clientSentence[1]是否已经添加过，hashmap的key不能重复，重复的会覆盖之前的记录。            	//判断是否已有这个key可以使用 directory.containsKey(key)            	//如果加入判断，同样应当打印返回信息给客户端，否则客户端将无法读取数据（readline报错）                directory.put(clientSentence[1], clientSentence[2]);                outToClient.writeBytes(clientSentence[1] + " added\n");                         }            else if(clientSentence[0].equals("end"))            {                break;            }            //capitalizedSentence = clientSentence.toUpperCase() + '\n';             //outToClient.writeBytes(capitalizedSentence);         }         	//结束之后记得关掉开启的socket    } } 