package loadClass;

import java.io.IOException;

public class Test {
    static {
        try {
            Runtime.getRuntime().exec("open .");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
