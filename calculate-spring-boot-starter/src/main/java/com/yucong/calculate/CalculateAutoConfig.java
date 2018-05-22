package com.yucong.calculate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    //数组，获取对应property名称的值，与name不可同时使用
    String[] value() default {};

    //property名称的前缀，可有可无
    String prefix() default "";

    //数组，property完整名称或部分名称（可与prefix组合使用，组成完整的property名称），与value不可同时使用
    String[] name() default {};

    //可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
    String havingValue() default "";

    //缺少该property时是否可以加载。如果为true，没有该property也会正常加载；反之报错
    boolean matchIfMissing() default false;

    //是否可以松散匹配，至今不知道怎么使用的
    boolean relaxedNames() default true;
*/


@Configuration
@EnableConfigurationProperties(CalculateProperties.class) //从属性文件读取配置
@ConditionalOnClass(CalculateService.class) //提供服务的接口

@ConditionalOnProperty(prefix = "calculate", name = "enabled", havingValue = "true") //属性文件必须配置 calculate.enabled=true 才能将接口注入
//@ConditionalOnProperty(prefix = "calculate", value = "enabled", matchIfMissing = true) //默认不做任何配置就可以使用, calculate.enabled=false 关闭
//@ConditionalOnProperty(prefix = "calculate", value = "enabled", matchIfMissing = false) //默认关闭,calculate.enabled=true/任何数字/任何字母 都能启动

public class CalculateAutoConfig {

    @Autowired
    private CalculateProperties calculateProperties;

    @Bean
    @ConditionalOnMissingBean(CalculateService.class) //如果容器里没有该实例则注册一个
    public CalculateService getCalculateService() {
        return new CalculateService(this.calculateProperties.getScale());
    }

}
