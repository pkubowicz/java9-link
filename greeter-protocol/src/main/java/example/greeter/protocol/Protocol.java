package example.greeter.protocol;

import example.greeter.files.FileReader;

public class Protocol {
    private boolean inProgress;

    public String startConversation() {
        inProgress = true;
        return "What is your name?";
    }

    public String processInput(String input) {
        if (input.toLowerCase().contains("bye")) {
            inProgress = false;
            return "Bye";
        } else if (input.toLowerCase().contains("stats")) {
            return "I have " + Runtime.getRuntime().availableProcessors() + " processors";
        } else if (input.toLowerCase().contains("get")) {
            return FileReader.read();
        }
        return "Hello " + input;
    }

    public boolean isInProgress() {
        return inProgress;
    }
}
