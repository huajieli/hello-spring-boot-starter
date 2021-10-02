package cn.huajieli.service;

/**
 * @author huajieli
 * @create 2021-10-02 10:47
 */
public class HelloService {
    private String name;
    private String address;

    public HelloService(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String sayHello(){
        return "你好！我的名字叫 " + name + "，我来自 " + address;
    }
}
