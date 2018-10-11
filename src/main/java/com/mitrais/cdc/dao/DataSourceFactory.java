package com.mitrais.cdc.dao;

import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceFactory {

    private final DataSource dataSource;

    public DataSourceFactory() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("rmsdb");
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setUser("webstudent");
        ds.setPassword("webstudent");
        this.dataSource = ds;
    }

    public static Connection getConnection() throws SQLException
    {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }

}
