package com.alibaba.common;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.alibaba.BaseDbTest;
import com.alibaba.common.db.DataSourceFactory;
import junit.framework.Assert;

import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author agapple 2014年2月25日 下午11:38:06
 * @since 1.0.0
 */
public class DataSourceFactoryTest extends BaseDbTest {

    @Test
    public void testOracle() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        dataSourceFactory.start();

        DataSource oracle1 = dataSourceFactory.getDataSource(getOracleConfig());
        DataSource oracle2 = dataSourceFactory.getDataSource(getOracleConfig());
        Assert.assertTrue(oracle1 == oracle2);
        testConnection(oracle1);

        dataSourceFactory.stop();
    }

    @Test
    public void testMysql() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        dataSourceFactory.start();
        DataSource mysql1 = dataSourceFactory.getDataSource(getMysqlConfig());
        DataSource mysql2 = dataSourceFactory.getDataSource(getMysqlConfig());
        Assert.assertTrue(mysql1 == mysql2);
        testConnection(mysql1);

        dataSourceFactory.stop();
    }

    private void testConnection(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String version = (String) jdbcTemplate.execute(new ConnectionCallback() {

            public Object doInConnection(Connection con) throws SQLException, DataAccessException {
                DatabaseMetaData metaData = con.getMetaData();
                return metaData.getDatabaseProductName() + "-" + metaData.getDatabaseProductVersion();
            }
        });

        System.out.println(version);
        Assert.assertNotNull(version);
    }
}
