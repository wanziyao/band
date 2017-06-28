package com.alibaba;

import com.alibaba.common.model.DbType;
import com.alibaba.common.model.DataSourceConfig;

/**
 * @author agapple 2014年2月25日 下午11:38:06
 * @since 1.0.0
 */
public class BaseDbTest {

    public DataSourceConfig getOracleConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setUsername("test");
        config.setPassword("test");
        config.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:test");
        config.setEncode("UTF-8");
        config.setType(DbType.ORACLE);

        return config;
    }

    public DataSourceConfig getMysqlConfig() {
        DataSourceConfig config = new DataSourceConfig();
        config.setUsername("test");
        config.setPassword("test");
        config.setUrl("jdbc:mysql://127.0.0.1:3306");
        config.setEncode("UTF-8");
        config.setType(DbType.MYSQL);

        return config;
    }
}
