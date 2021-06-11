package Lab6M;

import java.net.*;
import java.io.*;

public class ServerMM {
    public static void main(String[] args) throws IOException {
        Socket[] mysocks=new Socket[10];
        String[] names = new String[10];
        Socket temp_sock = null;
        int count=0;
        for(int i=0;i<10;i++){
            mysocks[i] = null;
            names[i] = null;
        }
        ServerSocket serverSocket = null;
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8888.");
            System.exit(-1);
        }

        while (listening){
            if(count<10 &&((temp_sock=serverSocket.accept())!=null)){
                for(int i=0;i<10;i++){
                    if(mysocks[i]==null){
                        count=i;
                        mysocks[i]=temp_sock;
                        temp_sock=null;
                        new ServerThread(mysocks[count],mysocks,count,names).start();
                        break;
                    }
                }
                count=0;
                for(int i=0;i<10;i++)
                    if(mysocks[i]!=null) count++;
                System.out.println(count);
            }else System.out.println("Server is full");
        }
        serverSocket.close();
    }
}

class ServerThread extends Thread{
    String names[] = null;
    String name = null;
    public int own_num;
    private Socket socket = null;
    private Socket[] mys=null;
    PrintWriter outAll = null;
    public ServerThread(Socket socket,Socket[] my,int num,String names[]) {
        super("ServerThread");
        this.socket = socket;
        this.mys = my;
        own_num = num;
        this.names = names;
    }
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String inputLine, outputLine;
            while ((inputLine = in.readLine())!= null) {
                if (inputLine.equalsIgnoreCase("2112333")){			for(int i = 0;i<10;i++){
                    if(names[i] != null)
                        if(name.compareTo(names[i])==0){
                            names[i] = null;
                            break;
                        }
                }
                    for(int i=0;i<10;i++){
                        if(mys[i]!=null){
                            outAll = new PrintWriter(mys[i].getOutputStream(), true);
                            outAll.println("^#");				for(int j=0;j<10;j++){
                                if(names[j] != null)
                                    outAll.println(names[j]);
                            }
                            outAll = null;
                        }
                    }
                    break;
                }
                if(inputLine.charAt(0)=='^'){
                    for(int i = 0;i < 10; i++){
                        if(names[i] == null){
                            name = inputLine;
                            names[i] = inputLine;
                            break;
                        }
                    }
                    for(int i=0;i<10;i++){
                        if(mys[i]!=null){
                            outAll = new PrintWriter(mys[i].getOutputStream(), true);
                            outAll.println("^#");				for(int j=0;j<10;j++){
                                if(names[j] != null)
                                    outAll.println(names[j]);
                            }
                            outAll = null;
                        }
                    }
                }else
                    for(int i=0;i<10;i++){
                        if(mys[i]!=null){
                            outAll = new PrintWriter(mys[i].getOutputStream(), true);
                            outAll.println(inputLine);
                            outAll = null;
                        }
                    }
            }
            socket.close();
            mys[own_num]=null;
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
