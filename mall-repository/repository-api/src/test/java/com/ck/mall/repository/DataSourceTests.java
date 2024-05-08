package com.ck.mall.repository;

import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Qnxy
 */
public class DataSourceTests {

    @Test
    public void getDataSource() throws SQLException {
        DataSource dataSource = MallProjectDataSource.getDataSource();
        assert dataSource != null;
        assert dataSource.getConnection() != null;
    }
}
