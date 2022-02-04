package com.kotall.learn.mybatis.spring;
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

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author MetaYoo
 */
public class MyFactoryBean implements FactoryBean {

    private Class className;
    private SqlSessionFactory sqlSessionFactory;

    public MyFactoryBean(Class className) {
        this.className = className;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public Object getObject() throws Exception {
//        Object instance = Proxy.newProxyInstance(className.getClassLoader(), new Class[]{className}, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println(method.getName());
//                return null;
//            }
//        });
        sqlSessionFactory.getConfiguration().addMapper(className);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.getMapper(className);
    }

    @Override
    public Class<?> getObjectType() {
        return className;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
