package com.api.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: cuke
 * @description: 断言
 * @author: shixing
 * @create: 2021-01-10 17:01
 **/
public class WMAssert {
    private String flag;
    private Map<String,Object> assertMap;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Map<String,Object> getAssertMap() {
        return assertMap;
    }

    public void setAssertMap(Map<String,Object> assertMap) {
        this.assertMap = assertMap;
    }


    public void setAssertMap(String key,String val){
        Map<String,Object> map = this.getAssertMap();
        if(map == null){
            map = new HashMap<String,Object>();
            map.put(key,val);
            this.setAssertMap(map);
        }else {
            map.put(key,val);
        }
    }
}
