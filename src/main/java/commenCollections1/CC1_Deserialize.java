package commenCollections1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CC1_Deserialize {

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Object obj = ois.readObject();
        return obj;
    }

}
