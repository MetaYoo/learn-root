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

import com.kotall.learn.mybatis.mapper.BlogMapper;
import com.kotall.learn.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author MetaYoo
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        String path = "com.kotall.learn.mybatis.mapper";
        MybatisMapperScanner scanner = new MybatisMapperScanner(beanDefinitionRegistry);
        scanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
        scanner.scan(path);

//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        beanDefinition.setBeanClass(MyFactoryBean.class);
//        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(BlogMapper.class);
//        beanDefinitionRegistry.registerBeanDefinition("blogMapper", beanDefinition);
//
//        AbstractBeanDefinition userBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        userBeanDefinition.setBeanClass(MyFactoryBean.class);
//        userBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
//        beanDefinitionRegistry.registerBeanDefinition("userMapper", userBeanDefinition);
    }
}
