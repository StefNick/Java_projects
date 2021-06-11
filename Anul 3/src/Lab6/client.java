package Lab6;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {

// declaration section:
// clientClient: our client socket
// os: output stream
// is: input stream

        Socket clientSocket = null;
        DataInputStream is = null;
        PrintStream os = null;
        DataInputStream inputLine =null;

// Initialization section:
// Try to open a socket on port 2222
// Try to open input and output streams

        try {
            clientSocket = new Socket("localhost", 2222);
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
            inputLine = new DataInputStream(new BufferedInputStream(System.in));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to host");
        }

// If everything has been initialized then we want to write some data
// to the socket we have opened a connection to on port 2222

        if (clientSocket != null && os != null && is != null) {
            try {

// keep on reading from/to the socket till we receive the "Ok" from the server,
// once we received that then we want to break.

                String responseLine;
                os.println(inputLine.readLine());
                while ((responseLine = is.readLine()) != null) {
                    System.out.println(responseLine);
                    if (responseLine.indexOf("Ok") != -1) {
                        break;
                    }
                    os.println(inputLine.readLine());
                }

// clean up:
// close the output stream
// close the input stream
// close the socket

                os.close();
                is.close();
                clientSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
}
