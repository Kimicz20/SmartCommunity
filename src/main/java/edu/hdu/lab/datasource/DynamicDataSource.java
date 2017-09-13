package edu.hdu.lab.datasource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态配置数据源类
 * dao层调用数据库时会自动配置
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private Logger logger = Logger.getLogger(this.getClass());
    protected Object determineCurrentLookupKey() {
        logger.debug("@@@@ determineCurrentLookupKey database is = "+ DynamicDataSourceHolder.getDataSource());
        return DynamicDataSourceHolder.getDataSource();
    }
}
