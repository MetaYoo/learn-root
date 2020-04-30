package com.kotall.learn.springboot.mystarter;

public class GetHashCodeClass {
    private String target;

    public GetHashCodeClass(String target) {
        this.target = target;
    }

    public String getHashCode() {
        return String.valueOf(this.target.hashCode());
    }
}
