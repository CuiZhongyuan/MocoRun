package com.api.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @program: cuke
 * @description: 万马平台
 * @author: shixing
 * @create: 2020-12-31 17:14
 **/

public class WM {
    //    _id:String ,api: String, param: String, in: String, method: String
    private String api;
    private String apiName;
    private String param;
    private String mongoParam;
    private String in;
    private String method;
    private WMCache wmCache;
    private WMAssert wmAssert;
    private String res;

    public WM(){
        this.setWmAssert(new WMAssert());
        this.setWmCache(new WMCache());
    }

   /*
     geteer and seteer
    */

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMongoParam() {
        return mongoParam;
    }

    public void setMongoParam(String mongoParam) {
        this.mongoParam = mongoParam;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public WMCache getWmCache() {
        return wmCache;
    }

    public void setWmCache(WMCache wmCache) {
        this.wmCache = wmCache;
    }

    public WMAssert getWmAssert() {
        return wmAssert;
    }

    public void setWmAssert(WMAssert wmAssert) {
        this.wmAssert = wmAssert;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    /**
     * 自定义的  将map 转为 jsonString
     * @param mongoParam
     */
    public void setMongoParam(Map mongoParam) {
        try {
            this.mongoParam = new ObjectMapper().writeValueAsString(mongoParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    /**
     * 自定义的 将map 转为 jsonString
     * @param param
     */
    public void setParam(Map param) {
        try {
            this.param = new ObjectMapper().writeValueAsString(param);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
