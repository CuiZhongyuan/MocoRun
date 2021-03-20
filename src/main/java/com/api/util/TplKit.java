package com.api.util;



import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TplKit {

    private static final Pattern p = Pattern.compile("\\$\\{(.+?)\\}");

    public static String parse(String content, Map<String, Object> kvs){
        // 检查是否存在Random参数
        content = DataKit.randomDataStringInspect(content);
        // 开始进行缓存检查替换
        Matcher m = p.matcher(content);
        StringBuffer sr = new StringBuffer();
        while(m.find()){
            String group = m.group();
            group = group.replaceAll("\\$", "").replaceAll("\\{", "").replaceAll("\\}", "");
            Object o = kvs.get(group);
            if(o != null) {
                if (o instanceof String || o instanceof Long) {
                    if(group.equals("batchParams") || group.equals("batchUcKey")){
                        m.appendReplacement(sr, kvs.get(group).toString());
                    }else{
                        m.appendReplacement(sr, "\"" + kvs.get(group).toString() + "\"");
                    }
                } else {
                    // YAML 处理
                    Yaml yaml = new Yaml();
                    String dumpData = yaml.dump(kvs.get(group));
                    m.appendReplacement(sr, dumpData);
                }
            }

        }
        m.appendTail(sr);
        return sr.toString();
    }

    /**
     * @Author zgq
     * @Description  将字符串中的所有${Var} 替换为缓存中的Var-->EnvParameters.mongoCache.get(Var)
     * @Date 20-10-12 上午10:29
     * @param sqlFragment
     * @return java.lang.String
     **/
    public static String parseStringByReplaceCache(String sqlFragment){
        // 检查是否需要Random替换
        sqlFragment = DataKit.randomDataStringInspect(sqlFragment);
        Matcher sqlVar = p.matcher(sqlFragment);
        //循环
        while(sqlVar.find()){
            //每一个符合正则的字符串
            String e = sqlVar.group();
//            //截取出括号中的内容  ${xxxx}
            String substring = e.substring(2, e.length()-1);
//            //字符串截取
//            CharSequence subSequence = sqlFragment.subSequence(sqlVar.start(0), sqlVar.end(0));
//            System.out.println("正则匹配的结果"+e);
            //进行替换
            sqlFragment = sqlFragment.replace(e, EnvParameters.mongoCache.getOrDefault(substring,e).toString());
        }
        return  sqlFragment;
    }


    /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
    public static List<String> parseStringSub(String soap, String rgex){
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 1;
            list.add(m.group(i));
            i++;
        }
        return list;
    }

    /**
     *
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap
     * @param rgex
     * @return
     */
    public static String parseStringSubSimple(String soap,String rgex){
        String subStr="";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            subStr = m.group();
        }
        return subStr;
    }

    /**
     * 根据正则匹配提取
     * @param soap
     * @param rgex
     * @return
     */
    public static String getStringSub(String soap,String rgex){
        String subStr="";
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            subStr = m.group(0).substring(1, m.group().length() - 1);
        }
        return subStr;
    }

    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     * @param str
     * @param strStart
     * @param strEnd
     * @return
     */
    public static String subString(String str, String strStart, String strEnd) {
        //找出指定的2个字符在该字符串里面的位置
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);
        // index 为负数 即表示该字符串中 没有该字符
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "----- 中不存在 " + strStart + "   无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "----- 中不存在 " + strEnd + "  无法截取目标字符串";
        }
        //开始截取
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

    /**
     * 判断字符串是否以字符开始
     * @param s
     * @return
     */
    public static boolean startWithChar(String s) {
        if (s != null && s.length() > 0) {
            String start = s.trim().substring(0, 1);
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            return pattern.matcher(start).matches();
        } else {
            return false;
        }
    }

    /**
     * 判断是否包含文件协议头
     * @param str
     * @return
     */
    public static boolean checkContainURL(String str) {
        Boolean result=false;
        //文件头协议
        if(str.contains("http://")||str.contains("https://")||str.contains("ftp://")||str.contains("samba://")){
            result =true;
        }
        return result;
    }

    /**
     * 检查html是否包含超链接
     * @param str
     * @return
     */
    public static boolean checkHtmlByHref(String str) {
        Boolean result=false;
        if(!str.contains("<html")){
            System.out.println("输入的非html");
        }
        //超链接匹配
        String regex="<a.*?/a>";
        //String regex = "<a.*>(.*)</a>";
        Pattern pt=Pattern.compile(regex);
        Matcher mt=pt.matcher(str);
        while(mt.find())
        {
            String s3="href=.*?>";
            Pattern pt3=Pattern.compile(s3);
            Matcher mt3=pt3.matcher(mt.group());
            while(mt3.find())
            {
                if(mt3.group().replaceAll("href=|>","").length()>0) {
                    result =true;
                    System.out.println("网址："+mt3.group().replaceAll("href=|>",""));
                }
            }
        }
        return  result;

    }
}

