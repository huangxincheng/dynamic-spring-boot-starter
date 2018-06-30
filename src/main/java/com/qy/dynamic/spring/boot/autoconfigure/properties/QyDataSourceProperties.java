package com.qy.dynamic.spring.boot.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Date:   2018/6/29 19:06
 * Author: huangxincheng
 * 请用一句话描述这个类:
 *
 * <author>              <time>            <version>          <desc>
 * huangxincheng     2018/6/29 19:06         1.0.0
 * <p>
 * 春风十里不如你
 **/
@ConfigurationProperties(QyDataSourceProperties.CP)
public class QyDataSourceProperties {

    private String sourceClassName;

    private String defaultSourceKey;

    public static final String CP = "spring.datasource.qy";

    public String getSourceClassName() {
        return sourceClassName;
    }

    public void setSourceClassName(String sourceClassName) {
        this.sourceClassName = sourceClassName;
    }

    public String getDefaultSourceKey() {
        return defaultSourceKey;
    }

    public void setDefaultSourceKey(String defaultSourceKey) {
        this.defaultSourceKey = defaultSourceKey;
    }
}
