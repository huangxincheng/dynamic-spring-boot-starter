package com.qy.dynamic.spring.boot.autoconfigure;

import com.alibaba.druid.pool.DruidDataSource;
import com.qy.dynamic.spring.boot.autoconfigure.annotation.QySource;
import com.qy.dynamic.spring.boot.autoconfigure.properties.QyDataSourceDruidProperties;
import com.qy.dynamic.spring.boot.autoconfigure.properties.QyDataSourceProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Date:   2018/6/29 19:01
 * Author: huangxincheng
 * 请用一句话描述这个类:
 *
 * <author>              <time>            <version>          <desc>
 * huangxincheng     2018/6/29 19:01         1.0.0
 * <p>
 * 春风十里不如你
 **/
@Configuration
@ConditionalOnClass(value = {QySource.class})
@ConditionalOnProperty(name="spring.datasource.type",  havingValue = "com.qy.dynamic.spring.boot.autoconfigure.QyDataSource", matchIfMissing = true)
@EnableConfigurationProperties(value = {QyDataSourceProperties.class})
public class QyDataSouceAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(QyDataSouceAutoConfiguration.class);



    @EnableConfigurationProperties(value = {QyDataSourceDruidProperties.class})
    @ConditionalOnProperty(name="spring.datasource.qy.source-class-name",  havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
    static class QyDruid extends QyDataSouceAutoConfiguration {

        @Bean
        public DataSource dataSource(QyDataSourceDruidProperties qyDataSourceDruidProperties, QyDataSourceProperties qyDataSourceProperties) {
            Map<Object,Object> sourceMap = new HashMap<>();
            Map<String, DruidDataSource> druidDataSourceMap = qyDataSourceDruidProperties.getDruidDataSourceMap();
            druidDataSourceMap.entrySet().forEach(entry -> {
                sourceMap.put(entry.getKey(),entry.getValue());
                logger.info("注入数据源 :" + entry.getKey());
            });
            QyDataSource qyDataSource =  new QyDataSource();
            qyDataSource.setTargetDataSources(sourceMap);
            qyDataSource.setDefaultTargetDataSource(sourceMap.get(qyDataSourceProperties.getDefaultSourceKey()));
            logger.info("注入数据源 SUCCESS");
            return qyDataSource;
        }
    }




}
