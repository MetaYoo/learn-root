package com.kotall.learn.spring;

import org.springframework.stereotype.Service;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/9/25 0025 下午 1:55
 * @version: 1.0.0
 */
@Service("animalService")
public class AnimalService {
    AnimalService() {
        System.out.println("constructor Animal");
    }
}
