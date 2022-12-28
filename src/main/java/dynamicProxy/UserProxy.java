package dynamicProxy;

public class UserProxy implements IUser {
    IUser user;

    public UserProxy() {

    }

    public UserProxy(IUser user) {
        this.user = user;
    }

//  静态代理类
    @Override
    public void show() {
        System.out.println("调用了show");
    }

    @Override
    public void create() {
        System.out.println("调用了create");
    }

    @Override
    public void update() {
        System.out.println("调用了update");
    }
}
