package com.kotall.learn.dynamicds.ds;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * desc:
 *
 * @author zpwang
 * @date 2019/3/29 11:33
 * @since 1.0.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getDatabaseType();
    }
}
