package Lab6M;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
class Interface extends Frame{
    Dialog form = null;
    TextArea wnd_msg = null,wnd_names=null;
    Button b_send = null;
    TextField to_send = null;
    Interface(String window_name){
        super(window_name);
        setFont(new Font("Comic",Font.BOLD,12));
        setBackground(Color.green);
        wnd_msg = new TextArea("",14,50,TextArea.SCROLLBARS_VERTICAL_ONLY);
        wnd_msg.setEditable(false);
        wnd_names = new TextArea("",14,10,TextArea.SCROLLBARS_VERTICAL_ONLY);
        wnd_names.setEditable(false);
        to_send = new TextField(50);
        b_send = new Button("Send");
        Panel for_sends = new Panel();
        Panel for_wnds = new Panel();
        for_sends.add(to_send);
        for_sends.add(b_send);
        for_wnds.add(wnd_names);
        for_wnds.add(wnd_msg);
        add(for_sends,BorderLayout.SOUTH);
        add(for_wnds,BorderLayout.CENTER);
        setSize(500,340);
        setVisible(true);
    }
}
class Form extends Dialog implements ActionListener{
    public TextField for_host=null,for_name=null;
    public Button begin = null;
    public String[] host = null;
    public String[] name = null;
    Label l_host = new Label("Host:   ");
    Label l_name = new Label("Name:");
    Form(Interface chat,String[] host,String[] name){
        super(chat , "Regestration:",true);
        setFont(new Font("Comic",Font.BOLD,12));
        this.host = host;
        this.name = name;
        for_host = new TextField(10);
        for_name = new TextField(10);
        Panel for_texts = new Panel();
        for_texts.add(l_host);
        for_texts.add(for_host);
        for_texts.add(l_name);
        for_texts.add(for_name);
        begin = new Button("Begin");
        begin.addActionListener(this);
        Panel for_button = new Panel();
        for_button.add(begin);
        add(for_texts,BorderLayout.CENTER);
        add(for_button,BorderLayout.SOUTH);
        setBackground(Color.green);
        setSize(180,115);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        host[0] = for_host.getText();
        name[0] = for_name.getText();
        dispose();
    }
}
class Read implements ActionListener{
    // Transmiterea mesajului
    Interface wnd;
    String name="not :";
    Socket ss;
    PrintWriter out = null;
    String fromUser="";
    Read(Socket s,Interface w){
        wnd=w;
        ss=s;
        try{
            out = new PrintWriter(ss.getOutputStream(), true);
        }catch(IOException io){}
    }
    public void actionPerformed(ActionEvent ae){
        fromUser=wnd.to_send.getText();
        wnd.to_send.setText("");
        out.println(name+fromUser);
        fromUser="";
    }
    public void setName(String name){
        this.name=name+" : ";
    }
}
public class ClientMM{
    public static void main(String[] args) throws IOException {
        String[] name = new String[1] , host = new String[1];
        Interface wnd = new Interface("Chat");
        new Form(wnd,host,name);
        Read reader = null;
        Socket kkSocket = null;
        BufferedReader in = null;
        try {
            kkSocket = new Socket(host[0], 8888);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection.");
            System.exit(1);
        }
        String fromServer;
        reader=new Read(kkSocket,wnd);
        reader.setName(name[0]);
        wnd.setTitle(name[0]);
        final PrintWriter out = reader.out;
        // mesaje de sistem
        out.println("^"+name[0]);
        wnd.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                out.println("2112333");
                System.exit(0);
            }
        });
        wnd.b_send.addActionListener(reader);
        wnd.to_send.addActionListener(reader);
        while(((fromServer = in.readLine()) != null) && (reader!=null)) {
            if((fromServer.charAt(0)=='^') && (fromServer.charAt(1)=='#')){
                wnd.wnd_names.setText("");
                continue;
            }
            if(fromServer.charAt(0)=='^'){
                wnd.wnd_names.append(fromServer.substring(1)+'\n');
            }else
                wnd.wnd_msg.append(fromServer+"\n");
        }
        in.close();
        kkSocket.close();
    }
}
