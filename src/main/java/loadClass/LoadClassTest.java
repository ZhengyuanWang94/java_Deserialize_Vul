package loadClass;

import simpleSerialize.Person;

import java.net.URL;
import java.net.URLClassLoader;

public class LoadClassTest {
    public static void main(String[] args) throws Exception {
//        new Person();

//        new Person("wzy", 28);

//        Person.staticAction();

//        Person.id = 1;

//        Class c = Person.class; //不会加载

//        Class.forName("simpleSerialize.Person"); // 默认初始化，会加载调用静态代码块

//        设置初始化参数为false，不会进行类加载，所以就不会调用静态代码块
//        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//        Class<?> c = Class.forName("simpleSerialize.Person", false, systemClassLoader);
//        c.newInstance(); //这里继续实例化就会去调用静态代码块，构造代码块等

//        System.out.println(systemClassLoader);
//        Class<?> personClass = systemClassLoader.loadClass("simpleSerialize.Person"); //只loadClass是不会进行初始化的
//        personClass.newInstance();

//        继承关系：classLoader-> SecureClassLoader->UrlClassLoader->AppClassLoader
//        调用关系：loadClass->findClass(overwirte的方法)->defineClass(从字节码加载类)
//        而URLClassLoader是可以利用的

//        URLClassLoader 可以实现任意类加载 (file/http/jar)
//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:/Users/zhengyuan.wang/projects/temp")});
//        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("http://localhost:9999/")});
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("jar:file:/Users/zhengyuan.wang/projects/temp/unserializableDemo-1.0-SNAPSHOT.jar!/")});
        Class<?> c = urlClassLoader.loadClass("loadClass.Test");
        c.newInstance();

    }
}
