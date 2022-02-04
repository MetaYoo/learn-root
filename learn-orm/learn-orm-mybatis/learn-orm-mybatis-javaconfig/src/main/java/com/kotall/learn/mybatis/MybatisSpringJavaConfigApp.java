package com.kotall.learn.mybatis;
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

import com.kotall.learn.mybatis.mapper.UserMapper;
import com.kotall.learn.mybatis.service.BlogService;
import com.kotall.learn.mybatis.spring.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author MetaYoo
 */
@ComponentScan("com.kotall.learn.mybatis")
@Import(MyImportBeanDefinitionRegistrar.class)
public class MybatisSpringJavaConfigApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(MybatisSpringJavaConfigApp.class, AppConfig.class);

        applicationContext.refresh();


        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        System.out.println(userMapper.getById());

        BlogService blogService = applicationContext.getBean(BlogService.class);
        System.out.println(blogService);
        blogService.showBlog(1L);

    }

}
