package example.greeter.files;

import com.google.common.io.Files;

import java.io.File;
import java.security.MessageDigest;

public class FileReader {
    public static String read() {
        File readme = new File("README.md");
        if (readme.exists()) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                return toString(md.digest(Files.toByteArray(readme)));
            } catch (Exception e) {
                return "Cannot read readme";
            }
        } else {
            return "No readme";
        }
    }

    private static String toString(byte[] hash) {

        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hash) {
            if ((0xff & hashByte) < 0x10) {
                hexString.append("0").append(Integer.toHexString((0xFF & hashByte)));
            } else {
                hexString.append(Integer.toHexString(0xFF & hashByte));
            }
        }
        return hexString.toString();
    }
}
