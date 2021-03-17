package com.api.util;

/**
 * @program: cuke
 * @description:
 * @author: shixing
 * @create: 2021-01-13 16:49
 **/
public enum ContentType {
    /**
     * 可根据请求类型进行添加，默认为 JSON
     */
    JSON("application/json"),
    FILE("multipart/form-data")
    ;
    private String type;

    ContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
