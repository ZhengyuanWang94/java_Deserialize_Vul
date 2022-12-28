package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserInvocationHandler implements InvocationHandler {
    IUser user;

    public UserInvocationHandler() {

    }

    public UserInvocationHandler(IUser user) {
        this.user = user;
    }

//    与readObject类似，invoke在有函数调用的时候自动执行
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用了：" + method.getName());
        method.invoke(user, args);
        return null;
    }
}
