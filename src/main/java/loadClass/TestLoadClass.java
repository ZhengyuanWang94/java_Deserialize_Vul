package loadClass;

import java.io.IOException;

public class TestLoadClass {
    static {
        try {
            Runtime.getRuntime().exec("open /System/Applications/Calculator.app/Contents/MacOS/Calculator");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
