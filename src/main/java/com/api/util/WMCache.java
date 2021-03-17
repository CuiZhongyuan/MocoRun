package com.api.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cuke
 * @description: 缓存
 * @author: shixing
 * @create: 2021-01-10 16:59
 **/
public class WMCache {
    private String flag;
    private Map<String,Object> cacheMap;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Map<String,Object> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<String,Object> cacheMap) {
        this.cacheMap = cacheMap;
    }

    public void setCacheMap(String key,Object val){
        Map<String,Object> map = this.getCacheMap();
        if(map == null){
            map = new HashMap<String,Object>();
            map.put(key,val);
            this.setCacheMap(map);
        }else {
            map.put(key,val);
        }
    }
}
