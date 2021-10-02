package cn.huajieli.config;

import cn.huajieli.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huajieli
 * @create 2021-10-02 10:50
 * <p>
 * 这是一个自定配置类，用于配置我们的HelloService对象
 */

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    private HelloProperties helloProperties;

    /**
     * 通过构造方法注入配置属性对象HelloPropertiers
     */
    public HelloServiceAutoConfiguration(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    /**
     * @Bean: 自动创建HelloService对象
     * @ConditionalOnMissingBean：避免重复创建，只有不存在时才创建
     * @return
     */
    @ConditionalOnMissingBean
    @Bean
    public HelloService helleService(){
        return new HelloService(helloProperties.getName(),helloProperties.getAddress());
    }

}




























