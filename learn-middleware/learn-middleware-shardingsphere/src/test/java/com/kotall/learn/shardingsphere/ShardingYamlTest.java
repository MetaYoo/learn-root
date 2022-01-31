package com.kotall.learn.shardingsphere;
/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author MetaYoo
 */
public class ShardingYamlTest {


//    @Test
//    public void test_sharding() throws Exception {
//        String file = ShardingYamlTest.class.getClassLoader().getSystemResource("shardingsphere_cfg.yml").getFile();
//
//        DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(new File(file));
//
//        String sql = "SELECT t.* FROM t_order t WHERE t.order_id=?";
//        try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, "123456");
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    // ...
//                }
//            }
//        }
//    }
}
