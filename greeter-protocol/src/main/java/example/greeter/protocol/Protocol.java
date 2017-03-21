package example.greeter.protocol;

import java.io.File;

public class Protocol {
    private boolean inProgress = true;

    public String startConversation() {
        return "What is your name?";
    }

    public String processInput(String input) {
        if (input.toLowerCase().contains("bye")) {
            inProgress = false;
            return "Bye";
        } else if (input.toLowerCase().contains("stats")) {
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

    public boolean isInProgress() {
        return inProgress;
    }
}
