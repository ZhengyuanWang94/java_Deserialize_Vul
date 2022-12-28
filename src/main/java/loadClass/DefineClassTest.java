package loadClass;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;



public class DefineClassTest {
    private Test test;
    public static void main(String[] args) throws Exception{
//        ClassLoader.defineClass 字节码加载任意类
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Method defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);
        defineClassMethod.setAccessible(true);
        byte[] code = Files.readAllBytes(Paths.get(
                "/Users/zhengyuan.wang/projects/temp/Test.class"));
        Class c = (Class) defineClassMethod.invoke(cl, "Test", code, 0, code.length);
        c.newInstance();

//        Unsafe.defineClass加载任意类(Unsafe是private的，但是其中的defineClass是public的)
//        但是Spring中可以直接生成Unsafe类
    }
}
