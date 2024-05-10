package com.ck.mall.repository.transaction;

import com.ck.mall.repository.JdbcTransaction;
import com.ck.mall.repository.SpringJdbcSupport;
import com.ck.mall.repository.TestTable;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring JdbcTransaction 实现测试
 *
 * @author Qnxy
 */
public class SpringJdbcTransactionTests {

    private final JdbcTransaction jdbcTransaction = JdbcTransaction.INSTANCE;

    @Test
    public void insertTest() {
        final List<Number> ids = new ArrayList<>();

        this.jdbcTransaction.executeTransaction(() -> {

            final KeyHolder keyHolder = new GeneratedKeyHolder();
            SpringJdbcSupport.jdbcClient()
                    .sql("insert into  test_table(username, age) values (?, ?)")
                    .params("ck", 27)
                    .update(keyHolder);
            ids.add(keyHolder.getKey());

//            int i = 1 /0;

            SpringJdbcSupport.jdbcClient()
                    .sql("insert into  test_table(username, age) values (?, ?)")
                    .params("ck2", 27)
                    .update(keyHolder);
            ids.add(keyHolder.getKey());

        });


        final List<TestTable> list = SpringJdbcSupport.jdbcClient()
                .sql("select * from test_table where id in(:ids)")
                .param("ids", ids)
                .query(TestTable.class)
                .list();


        assert list.size() == ids.size();
    }
}
