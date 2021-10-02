package cn.huajieli.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huajieli
 * @create 2021-10-02 10:41
 *
 * 读取配置文件转换为bean
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "HelloProperties{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
