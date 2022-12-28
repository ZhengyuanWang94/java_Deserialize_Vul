package dynamicProxy;

public class UserImplement implements IUser{
    public UserImplement() {

    }

    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public void create() {
        System.out.println("create");
    }

    @Override
    public void update() {
        System.out.println("update");
    }
}
