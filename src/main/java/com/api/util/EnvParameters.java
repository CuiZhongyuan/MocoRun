package com.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnvParameters {

    public static String getHostEnv(){
        String host = System.getenv("API_HOST");
        if(host != null){
            return host;
        }else {
            return "";
        }
    }

    //wanma数据库地址：47.114.174.96:3306
    public static String getDBEnv(){
        return System.getenv("DB_HOST");
    }

    /**
     * 接口请求代理配置
     * @return
     */
    public static String getProxyServerHostEnv(){
        return System.getenv("PROXY_HOST");
    }
    public static String getProxyServerHostPortEnv(){
        return  System.getenv("PROXY_PORT");
    }

    // 缓存 json assert
    public static Map<String, Object> mongoCache = new HashMap<>();
    public static List<WM> mongoList = new ArrayList<>();
    /**
     * 环境编码
     * @return
     */
    public static String getEnvCode() {
        return System.getenv("ENV_CODE");
    }

    private static ContentType CONTENT_TYPE = ContentType.JSON;

    public static ContentType getContentType() {
        return CONTENT_TYPE;
    }

    public static void setContentType(ContentType type) {
        CONTENT_TYPE = type;
    }
}
