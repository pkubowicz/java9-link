package example.greeter.server;

import example.greeter.protocol.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Runner {

    public static void main(String[] args) {
        int portNumber = args.length > 0 ? Integer.parseInt(args[0]) : 8000;
        Protocol protocol = new Protocol();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Shutting down")));

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Listening to connections on port " + portNumber);
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out =
                             new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(clientSocket.getInputStream()))) {
                    out.println(protocol.startConversation());
                    String inputLine, outputLine;
                    while ((inputLine = in.readLine()) != null) {
                        outputLine = protocol.processInput(inputLine);
                        out.println(outputLine);
                        if (!protocol.isInProgress()) {
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
