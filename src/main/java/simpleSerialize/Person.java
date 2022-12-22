package simpleSerialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Person implements Serializable {
    public String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "simpleSerialize.Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void action(String action) {
        System.out.println(action);
    }

//    可能的形式：
//    1. 入口类的readObject直接调用危险方法
//    2. 入口类参数中包含可控类，该类包含危险方法，readObject时调用
//    3. 入口类参数中包含可控类，该类又调用其他有危险方法的类，readObject时调用
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Runtime.getRuntime().exec("open .");
    }
}
