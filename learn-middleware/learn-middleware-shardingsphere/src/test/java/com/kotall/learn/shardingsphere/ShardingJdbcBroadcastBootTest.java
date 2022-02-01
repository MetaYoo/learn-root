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

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kotall.learn.shardingsphere.entity.Dict;
import com.kotall.learn.shardingsphere.mapper.DictMapper;
import com.kotall.learn.shardingsphere.mapper.DictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author MetaYoo
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
public class ShardingJdbcBroadcastBootTest {

    @Autowired
    private DictMapper dictMapper;

    @Test
    public void test_addDict() {
        for (int i = 0; i < 10; i++) {
            Dict dict = new Dict();
            dict.setDictKey("测试配置key" + i);
            dict.setDictValue("测试配置value" + i);
            dictMapper.insert(dict);
        }
    }

    @Test
    public void test_getDict() {
        Dict Dict = dictMapper.selectById(1488493725775634434L);
        System.out.println(Dict);
    }

    @Test
    public void test_updateDict() {
        Dict Dict = dictMapper.selectById(1488493728095084545L);
        Dict.setDictValue("测试配置-更新");
        dictMapper.updateById(Dict);
    }

    @Test
    public void test_deleteDict() {
        Wrapper<Dict> wrapper = new LambdaQueryWrapper<Dict>().eq(Dict::getDictId, 1488493728095084545L);
        int count = dictMapper.delete(wrapper);
        System.out.println(count);
    }

    @Test
    public void test_queryDict() {
        Wrapper<Dict> wrapper = new LambdaQueryWrapper<Dict>();
        List<Dict> dictList = dictMapper.selectList(wrapper);
        System.out.println(dictList.size());
    }

    @Test
    public void test_queryDictByPage() {
        Wrapper<Dict> wrapper = new LambdaQueryWrapper<Dict>();
        Page<Dict> page = new Page<>();
        page.setCurrent(1L);
        page.setSize(5L);
        Page<Dict> dictPage = dictMapper.selectPage(page, wrapper);
        System.out.println(dictPage.getTotal());
        System.out.println(dictPage.getPages());
        System.out.println(dictPage.getRecords());
    }
}
