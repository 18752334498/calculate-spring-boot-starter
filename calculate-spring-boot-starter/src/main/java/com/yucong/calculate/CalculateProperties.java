package com.yucong.calculate;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties 加载属性文件内的配置
 * prefix 指定配置属性的前缀
 */
@ConfigurationProperties(prefix = "calculate")
public class CalculateProperties {

    private String scale;

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
