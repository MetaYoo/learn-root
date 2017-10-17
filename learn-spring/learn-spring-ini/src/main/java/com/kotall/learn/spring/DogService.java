package com.kotall.learn.spring;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: aracwong
 * @email: aracwong@163.com
 * @datetime: 2017/9/25 0025 下午 1:57
 * @version: 1.0.0
 */
@Service("dogService")
public class DogService {

    @Resource(name = "animalService")
    private AnimalService animalService;

    DogService() {
        System.out.println("constructor Dog");
    }
}
