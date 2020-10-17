package com.company;
import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) throws Exception {

        //Configure Message
        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        String message;
        System.out.println("Please enter a message to send. Once finished, press enter");
        message = inFromUser.readLine();

        // Establish a TCP connection with the mail server.


        //Note: Something is wrong with socket or port*
        Socket socket = new Socket("alt2.gmail-smtp-in.l.google.com", 25);




        DataOutputStream outToServer =
                new DataOutputStream(socket.getOutputStream());

        // Create a BufferedReader to read a line at a time.
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        // Read greeting from the server.
        String response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("220")) {
            throw new Exception("220 reply not received from server.");
        }

        // Get a reference to the socket's output stream.
        OutputStream os = socket.getOutputStream();

        // Send HELO command and get server response.
        String command = "HELO google\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send MAIL FROM command.
			//Enter your email <here> below
        command = "MAIL FROM: <?>\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }


        // Send RCPT TO command.
			//Enter your email <here> below
        command = "RCPT TO: <?>\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send DATA command.
        command = "DATA\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("354")) {
            throw new Exception("354 reply not received from server.");
        }

        // Send from command.
			//Enter your email <here> below
        command = "from: <?>\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }


        // Send to TO command.
			//Enter your email <here> below
        command = "to: <?>\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send message data.
        command = message + "\r\n";
        System.out.print(command);

        // End with line with a single period.
        command = ".\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("250")) {
            throw new Exception("250 reply not received from server.");
        }

        // Send QUIT command.
        command = "QUIT\r\n";
        System.out.print(command);
        os.write(command.getBytes("US-ASCII"));
        response = br.readLine();
        System.out.println(response);
        if (!response.startsWith("221")) {
            throw new Exception("221 close connection encountered error");
        }
        else
            System.out.println("Closed Program");
    }
}
