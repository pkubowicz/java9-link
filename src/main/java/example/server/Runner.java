package example.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Runner {
    private static class Protocol {
        String processInput(String input) {
            if (input.toLowerCase().contains("stats")) {
                return "I have " + Runtime.getRuntime().availableProcessors() + " processors";
            } else if (input.toLowerCase().contains("get")) {
                File readme = new File("README.md");
                if (readme.exists()) {
                    return Long.toString(readme.getTotalSpace()); // TODO read from file
                } else {
                    return "No readme";
                }
            }
            return "Hello " + input;
        }
    }

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
                    out.println("What is your name?");
                    String inputLine, outputLine;
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.toLowerCase().contains("bye")) {
                            out.println("Bye");
                            break;
                        }
                        outputLine = protocol.processInput(inputLine);
                        out.println(outputLine);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
