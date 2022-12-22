package simpleReflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class UnserializeReflection {
    public static Object unserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Object obj = ois.readObject();
        return obj;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        unserialize("ser.bin");
    }
}
