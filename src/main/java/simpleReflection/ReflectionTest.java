package simpleReflection;

import simpleSerialize.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Person person = new Person();
        Class c =  person.getClass();
//      反射就是操作Class

//        从原型class中实例化对象
//        c.newInstance();
        Constructor personConstructor = c.getConstructor(String.class, int.class);
        Person p = (Person) personConstructor.newInstance("wzy", 28);
        System.out.println(p);

//        获取类中的属性
        Field[] personFields = c.getFields();
        Field[] personDeclareFields = c.getDeclaredFields();

        for (Field f : personFields) {
            System.out.println(f);
        }

        for (Field f : personDeclareFields) {
            System.out.println(f);
        }

        Field nameField = c.getField("name");
        nameField.set(p, "bingo");
        System.out.println(p);

        Field ageField = c.getDeclaredField("age");
        ageField.setAccessible(true );
        ageField.set(p, 1);
        System.out.println(p);

//        调用类里面的方法
        Method[] personMethods = c.getMethods();
        for (Method m : personMethods) {
            System.out.println(m);
        }

        Method personAction = c.getMethod("action", String.class );
        personAction.invoke(p, "reflection");

    }
}
