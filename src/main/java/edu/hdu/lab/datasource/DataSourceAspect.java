package edu.hdu.lab.datasource;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 拦截@DataSource注解
 * 指定当前的数据源
 */
@Aspect
@Component
public class DataSourceAspect {

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     *  切点定义
     *  对DataSource注解进行拦截
     */
    @Pointcut("@annotation(edu.hdu.lab.datasource.DataSource)")
    public void DataSourceConfig(){}

    /**
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     * @param point
     * @throws Exception
     */
    @Before("DataSourceConfig()")
    public void intercept(JoinPoint point) throws Exception {

        //1.获取注解所在的类全类名
        Class<?> target = point.getTarget().getClass();
        //2.获取被注解的方法名称
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 3.默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
        logger.debug("@@@@ after intercept database is "+DynamicDataSourceHolder.getDataSource());
    }

    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource source = clazz.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource source = m.getAnnotation(DataSource.class);
                DynamicDataSourceHolder.setDataSource(source.value());
            }
        } catch (Exception e) {
            System.out.println(clazz + ":" + e.getMessage());
        }
    }

}
