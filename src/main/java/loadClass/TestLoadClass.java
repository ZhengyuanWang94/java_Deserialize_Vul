package loadClass;

import java.io.IOException;

public class TestLoadClass {
    static {
        try {
            Runtime.getRuntime().exec("open .");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
