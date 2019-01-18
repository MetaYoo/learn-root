package com.kotall.learn;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author zpwang
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testExtractor() {
        NativeJdbcExtractor jdbcExtractor = this.jdbcTemplate.getNativeJdbcExtractor();
        Assert.assertNull(jdbcExtractor);
    }

    /**
     * 不带查询条件的 StatementCallback
     */
    @Test
    public void testQuery() {
        this.jdbcTemplate.execute(new StatementCallback<Object>() {
            @Override
            public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
                ResultSet rs = stmt.executeQuery("select * from t_blog");
                while (rs.next()) {
                    System.out.print(rs.getInt("id") + "=");
                    System.out.println(rs.getString("title"));
                }
                rs.close();
                return null;
            }
        });
    }

    /**
     * 带查询条件的 PreparedStatementCallback
     */
    @Test
    public void testQuery1() {
        this.jdbcTemplate.execute("select * from t_blog", new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.print(rs.getInt("id") + "=");
                    System.out.println(rs.getString("title"));
                }
                rs.close();
                return null;
            }
        });
    }

    @Test
    public void testQuery2() {
        this.jdbcTemplate.execute(new ConnectionCallback<Object>() {
            @Override
            public Object doInConnection(Connection con) throws SQLException, DataAccessException {
                return null;
            }
        });
    }

    @Test
    public void testQuery3() {
        String callDef = "call in_param(1)";
        this.jdbcTemplate.execute(callDef, new CallableStatementCallback<Object>() {
            @Override
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                }
                rs.close();
                return null;
            }
        });
    }


    @Test
    public void testQuery4() {
        String stsef = "select * from t_blog";
        this.jdbcTemplate.execute(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(stsef);
            }
        }, new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.print(rs.getInt("id") + "=");
                    System.out.println(rs.getString("title"));
                }
                rs.close();
                return null;
            }
        });
    }


    @Test
    public void testQuery5() {
        String callDef = "call in_param(0)";
        this.jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                return con.prepareCall(callDef, ResultSet.CONCUR_READ_ONLY, 2);
            }
        }, new CallableStatementCallback<Object>() {
            @Override
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                ResultSet rs = cs.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1));
                }
                rs.close();
                return null;
            }
        });
    }

    @Test
    public void testQuery6() {
        String sql = "select * from t_blog limit 1";
        Map<String, Object> result = this.jdbcTemplate.queryForMap(sql);
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }


    @Test
    public void testQuery7() {
        String sql = "select * from t_blog";
        List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
        for (Map<String, Object> row : list) {
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.print(key + "=" + value + ",");
            }
            System.out.println();
        }
    }

    @Test
    public void testQuery8() {
        String sql = "select * from t_blog";
        SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sql);
    }






}
