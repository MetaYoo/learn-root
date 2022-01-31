//package com.kotall.learn.shardingsphere;
///*
// * Copyright 2002-2020 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
//import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
//import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author MetaYoo
// */
//public class ShardingJavaTest {
//
//    private DataSource dataSource;
//
//    @Before
//    public void test_sharding() throws Exception {
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//// Configure the first data source
//        DruidDataSource dataSource1 = new DruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/db_order_01");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("123456");
//        dataSourceMap.put("ds0", dataSource1);
//
//// Configure the second data source
//        DruidDataSource dataSource2 = new DruidDataSource();
//        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://localhost:3307/db_order_01");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("123456");
//        dataSourceMap.put("ds1", dataSource2);
//
//// Configure order table rule
//        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration("t_order", "ds${0..1}.t_order_0${0..9}");
//
//// Configure database sharding strategy
//        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("user_id", "dbShardingAlgorithm"));
//
//// Configure table sharding strategy
//        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "tableShardingAlgorithm"));
//
//// Omit t_order_item table rule configuration ...
//// ...
//
//// Configure sharding rule
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTables().add(orderTableRuleConfig);
//
//// Configure database sharding algorithm
//        Properties dbShardingAlgorithmrProps = new Properties();
//        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
//        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));
//
//// Configure table sharding algorithm
//        Properties tableShardingAlgorithmrProps = new Properties();
//        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order_0${order_id % 10}");
//        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm", new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps));
//
//        /* 其他配置 */
//        Properties otherProperties = new Properties();
//        otherProperties.setProperty("sql-show", "true");
//        otherProperties.setProperty("sql.show", "true");
//// Create ShardingSphereDataSource
//        dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singleton(shardingRuleConfig), otherProperties);
//
//
//    }
//
//    @After
//    public void shutdown() {
//    }
//
//    @Test
//    public void test_query() throws Exception {
//        Connection connection = dataSource.getConnection();
//        String sql = "SELECT t.* FROM t_order t WHERE t.order_id=?";
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, 717);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    System.out.print(rs.getInt(1));
//                    System.out.print(" ");
//                    System.out.print(rs.getString(3));
//                    System.out.println();
//                }
//            }
//        }
//    }
//
//    @Test
//    public void test_insert() throws Exception {
//        Connection connection = dataSource.getConnection();
//        String sql = "insert t_order(order_id, order_desc, user_id) values (?,?,?)";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        for (int i = 0; i < 1000; i++) {
//            ps.setInt(1, i);
//            ps.setString(2, "desc-" + i);
//            ps.setInt(3, i);
//            ps.execute();
//        }
//        ps.close();
//        connection.close();
//
//    }
//}
