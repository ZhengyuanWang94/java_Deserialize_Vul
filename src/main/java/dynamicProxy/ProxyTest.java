package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        IUser user = new UserImplement();
        user.show();

//        静态代理
//        IUser userProxy = new UserProxy(user);
//        userProxy.show();

//        动态代理
//        要代理的接口，要做的操作，classloader
        InvocationHandler userInvocationHandler = new UserInvocationHandler(user);
        IUser userProxy = (IUser) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(), userInvocationHandler);
        userProxy.show();
        userProxy.create();
        userProxy.update();
    }
}
