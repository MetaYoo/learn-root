package com.kotall.learn.httpclient;

import java.io.Serializable;

/**
 * @author : zpwang
 * @version : 1.0.0
 * @date : 2018/5/7
 */
public class HttpResult implements Serializable {
    private Integer statusCode;
    private String content;

    public HttpResult(Integer statusCode, String content) {
        this.statusCode = statusCode;
        this.content = content;
    }
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "statusCode=" + statusCode +
                ", content='" + content + '\'' +
                '}';
    }
}
