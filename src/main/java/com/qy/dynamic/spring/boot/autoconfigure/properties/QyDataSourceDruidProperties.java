package com.qy.dynamic.spring.boot.autoconfigure.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * Date:   2018/6/30 10:33
 * Author: huangxincheng
 * 请用一句话描述这个类:
 *
 * <author>              <time>            <version>          <desc>
 * huangxincheng     2018/6/30 10:33         1.0.0
 * <p>
 * 春风十里不如你
 **/
@ConfigurationProperties(QyDataSourceDruidProperties.CP)
public class QyDataSourceDruidProperties {

    private Map<String,DruidDataSource> druidDataSourceMap;

    public Map<String, DruidDataSource> getDruidDataSourceMap() {
        return druidDataSourceMap;
    }

    public void setDruidDataSourceMap(Map<String, DruidDataSource> druidDataSourceMap) {
        this.druidDataSourceMap = druidDataSourceMap;
    }

    public static final String CP =  "spring.datasource.qy.druid";
}
