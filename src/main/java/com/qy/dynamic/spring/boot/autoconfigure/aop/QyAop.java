package com.qy.dynamic.spring.boot.autoconfigure.aop;

import com.qy.dynamic.spring.boot.autoconfigure.QyDataSource;
import com.qy.dynamic.spring.boot.autoconfigure.annotation.QySource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Date:   2018/6/29 17:51
 * Author: huangxincheng
 * 请用一句话描述这个类:
 *
 * <author>              <time>            <version>          <desc>
 * huangxincheng     2018/6/29 17:51         1.0.0
 * <p>
 * 春风十里不如你
 **/

@Aspect
@Order(-9999)
@Component
public class QyAop {

    private static final Logger logger = LoggerFactory.getLogger(QyAop.class);

//    @Pointcut("@annotation(com.qy.manydsexample.ds.annotation.QySource)")
//    public void aspectMethod() {}
//    @Around(value="aspectMethod()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        Object result;
//        MethodSignature methodSignature = (MethodSignature) point.getSignature();
//        Method method = methodSignature.getMethod();
//        this.handler(method, point.getTarget().getClass());
//        result = point.proceed();
//        //处理ThreadLocal值
//        QyDataSource.putSourceKey(null);
//        return result;
//    }


    /**
     * 和上面一样 只不过减少配置切面
     * @param point
     * @param qySource
     * @return
     * @throws Throwable
     */
    @Around(value = "@annotation(qySource)")
    public Object around(ProceedingJoinPoint point, QySource qySource) throws Throwable {
        Object result;
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        this.handler(method, point.getTarget().getClass());
        result = point.proceed();
        logger.info("joinPoint dataSourceKey : " + QyDataSource.getSourceKey());
        //处理ThreadLocal值
        QyDataSource.putSourceKey(null);
        return result;
    }



    private void handler(Method method, Class<?> clazz) throws NoSuchMethodException, SecurityException {
        String key = null;
        //优先判断method是否有注解 否则判断class
        method = clazz.getDeclaredMethod(method.getName(), method.getParameterTypes());
        QySource source = AnnotationUtils.findAnnotation(method, QySource.class);
        if (source != null) {
            key = source.source();
        } else {
            source = AnnotationUtils.findAnnotation(clazz, QySource.class);
            if (source != null) {
                key = source.source();
            }
        }
        logger.info("当前使用数据源别名:{}", key);
        QyDataSource.putSourceKey(key);
    }

}
