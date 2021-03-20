package com.api.data.genereate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: data-generate
 * @description: url
 * @author: shixing
 * @create: 2020-04-14 10:35
 **/
public class UrlGenerator {

    /**
     * 协议
     */
    public static String[] PROTOCOL_ARRY = {"http://","https://","ftp://","rtsp://","mms://"};


    /**
     *
     * @param protocol "http","https","ftp","rtsp","mms"
     * @param host www.baidu.com
     * @return
     */
    public static String getUrl(String protocol , String host){
        StringBuffer url = new StringBuffer();
        url.append(protocol).append("://")
                .append(host)
                .append("?")
                .append(CharNumberGenerator.generateLetterAndInt(9));
        return url.toString();
    }
    public static String getUrl(){
        StringBuffer url = new StringBuffer();
         url.append(PROTOCOL_ARRY[NumberGenerator.randomInt(PROTOCOL_ARRY.length-1)])
            .append("www.")
            .append(CharNumberGenerator.generateLetterAndInt(9))
            .append(".com")
            .append("?")
            .append(CharNumberGenerator.generateLetterAndInt(9));
       return url.toString();
    }

    /**
     *
     * @param host www.baidu.com
     * @return
     */
    public static String getUrl(String host){
        StringBuffer url = new StringBuffer();
        url.append(PROTOCOL_ARRY[NumberGenerator.randomInt(PROTOCOL_ARRY.length-1)])
                .append(host)
                .append("?")
                .append(CharNumberGenerator.generateLetterAndInt(9));
        return url.toString();
    }

    /**
     * 检查是否Email
     * @param str
     * @return
     */
    public static boolean isEmail( String str ) {
        String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;
        return match( regex ,str );
    }

    public static boolean isURL( String str ){
        String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*" ;
        return match( regex ,str );
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    public static boolean match( String regex ,String str ){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher( str );
        return matcher.matches();
    }

    /**
     * 解析文本是否包含URL
     * @param content
     * @return
     */
    public static boolean parseStrContainsUrl(String content) {
        boolean url_b = false;
        if (content.indexOf("http:")>-1 || content.indexOf("https:")>-1) {
            url_b = true;
        }else if (content.indexOf("HTTP:")>-1 || content.indexOf("HTTPS:")>-1) {
            url_b = true;
        }else if(content.indexOf(".") > 0) {
            Map<Character, String> keywordMindMap = new HashMap<Character, String>();
//			char[] teshus = "-_.~!*'();:@&=+$,/?#[]".toCharArray();
            char[] teshus = "-.:/".toCharArray();
            for (int i = 0; i < teshus.length; i++) {
                Character one = teshus[i];
                keywordMindMap.put(one, "");
            }
            List<String> countryList = new ArrayList<>();
            countryList.add(".hk");//中国香港
            countryList.add(".tw");//中国台湾
            countryList.add(".mo");//中国澳门
            countryList.add(".edu");//教育机构
            countryList.add(".gov");//政府部门
            countryList.add(".int");//国际组织
            countryList.add(".mil");//美国军事部门
            countryList.add(".net");//网络组织
            countryList.add(".org");//非盈利组织
            countryList.add(".biz");//商业
            countryList.add(".info");//网络信息服务组织
            countryList.add(".pro");//用于会计、律师和医生
            countryList.add(".name");//用于个人
            countryList.add(".museum");//用于博物馆
            countryList.add(".coop");//用于商业合作团体
            countryList.add(".aero");//用于航空工业
            countryList.add(".idv");//用于个人
// 更多 todo
            char[] contents = content.toCharArray(); //把字符中转换为字符数组
            int dian_index = content.indexOf(".");
            if (dian_index>0) {
                all:
                for (int i = dian_index,len=contents.length; i < len; i++) {
                    char one = contents[i];
                    if (one == '.') {
                        String url = ".";
                        boolean email_b = false;//是否为邮箱  true:是
                        //前
                        for (int beforeI = i-1; beforeI>=0; beforeI--) {
                            char beforeOne = contents[beforeI];
                            if((beforeOne >='a' && beforeOne<='z')
                                    || (beforeOne >='A' && beforeOne<='Z')
                                    || (beforeOne >='0' && beforeOne<='9')
                                    || keywordMindMap.get(beforeOne) != null
                            ){
                                url = beforeOne+url;
                            }else if(beforeOne =='@'){//邮箱
                                email_b = true;
                                break;
                            }else {
                                break;
                            }
                        }

                        if (!email_b) {
                            for (int lastI = i+1; lastI<len; lastI++) {
                                char lastOne = contents[lastI];
                                if((lastOne >='a' && lastOne<='z')
                                        || (lastOne >='A' && lastOne<='Z')
                                        || (lastOne >='0' && lastOne<='9')
                                        || keywordMindMap.get(lastOne) != null
                                ){
                                    url = url+lastOne;

                                    i = lastI;//不遍历多次
                                }else {
                                    break;
                                }
                            }

                            url = url.toLowerCase();//都变小写
                            if (url.indexOf("http:")>-1 || url.indexOf("https:")>-1) {
                                url_b = true;
                                break;
                            }else {
                                if ((url.indexOf(".com/")>0 || url.endsWith(".com")) && url.length()>4) {
                                    url_b = true;
                                    break;
                                }
                                if ((url.indexOf(".cn/")>0 || url.endsWith(".cn")) && url.length()>3) {
                                    url_b = true;
                                    break;
                                }
                                for (int j = 0; j < countryList.size(); j++) {
                                    String countryNet = countryList.get(j);
                                    if ((url.indexOf(countryNet+"/")>0 || url.endsWith(countryNet)) && url.length()>countryNet.length()) {
                                        url_b = true;
                                        break all;
                                    }
                                }
//								System.out.println("url="+url);
                                String[] xie_gangArray = {url};
                                if (url.indexOf("/")>-1) {
                                    xie_gangArray = url.split("/");
                                }

                                for (int j2 = 0; j2 < xie_gangArray.length; j2++) {
                                    String urlNew = xie_gangArray[j2];
                                    if (urlNew.indexOf(".")>-1) {//判断是否为ip
                                        int two = urlNew.indexOf(":");
                                        if (two>-1) {
                                            urlNew = urlNew.substring(0,two);
                                        }
                                        urlNew = urlNew.trim();
                                        //判断一个字符串是不是IP地址
                                        boolean isIP_b = isIPAddress(urlNew);
                                        if (isIP_b) {
                                            url_b = true;
                                            break all;
                                        }

                                    }
                                }

                            }
                        }



                    }
                }
            }

        }
        return url_b;
    }

    /**
     * 判断一个字符串是不是IP地址
     *    字符串的长度 0.0.0.0 7位 ~ 000.000.000.000 15位
     *    将字符串拆分成四段
     *    检查每段是否都是纯数字
     *    检查每段是否都在0-255之间
     *    以上条件都满足的话返回true
     */
    public static boolean isIPAddress(String str) {
        // 如果长度不符合条件 返回false
        if(str.length()<7 || str.length() >15) {
            return false;
        }
        String[] arr = str.split("\\.");
        //如果拆分结果不是4个字串 返回false
        if( arr.length != 4 ) {
            return false;
        }
        for(int i = 0 ; i <4 ; i++ ){
            for(int j = 0; j<arr[i].length();j++){
                char temp = arr[i].charAt(j);
                //如果某个字符不是数字就返回false
                if(temp>='0' && temp <= '9') {

                }else {
                    return false;
                }
            }
        }
        for(int i = 0 ; i<4;i++){
            int temp = Integer.parseInt(arr[i]);
            //如果某个数字不是0到255之间的数 就返回false
            if( temp<0 || temp >255) {
                return false;
            }
        }
        return true;
    }

}
