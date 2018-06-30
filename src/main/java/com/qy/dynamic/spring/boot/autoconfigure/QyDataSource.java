package com.qy.dynamic.spring.boot.autoconfigure;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Date:   2018/6/29 17:42
 * Author: huangxincheng
 * 请用一句话描述这个类:
 *
 * <author>              <time>            <version>          <desc>
 * huangxincheng     2018/6/29 17:42         1.0.0
 * <p>
 * 春风十里不如你
 **/
public class QyDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> SOURCELOCAL = new ThreadLocal<>();

    public static final void putSourceKey(String key) {
        SOURCELOCAL.set(key);
    }

    public static final String getSourceKey() {
        return SOURCELOCAL.get();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getSourceKey();
    }
}
