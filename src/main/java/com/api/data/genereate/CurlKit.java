package com.api.data.genereate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * curl下载
 * @author sikj
 *
 */
class CurlKit {
    private static Pattern p = Pattern.compile(".*?state=(\\d+)size=(.*?)end.*?");

    public static String DEFAULT_UA = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:11.0) Gecko/20100101 Firefox/11.0";

    public static String TEMP_SUFFIX = ".curltemp";

    // 连接超时时间
    private static final int CONNECT_TIME_OUT = 1*60;

    // 下载超时时间
    private static final int DOWNLOAD_TIME_OUT = 10 * 60;

    /**
     * curl下载
     * @param url 下载地址
     * @param extraMap 下载附加信息
     * @param savePath 下载保存路径，包含文件名称
     * @param errSb 错误信息Buffer
     * @return 1表示下载成功， 0表示下载失败, -1表示下载超时, -2下载返回结果中没有找到Http状态和文件大小
     * @throws IOException
     * @throws InterruptedException
     */
    public static int curl(String url, Map<String, String> extraMap,Map<String, String> headerMap,Map<String, String> paramsMap,
                           String savePath, StringBuffer errSb) throws IOException, InterruptedException{
        int state = 0;
        url = encodeUrl(url);
//        遍历map
//        for (String key : paramsMap.keySet()) {
//            url += "?"+key+"="+paramsMap.get(key)+"\"";
//        }
//        System.out.println("url"+url);

        String headerMapStr="";

                //遍历map
        for (String key : headerMap.keySet()) {
            headerMapStr += " -H  \""+key+":"+headerMap.get(key)+"\" ";
        }



        String user_agent = DEFAULT_UA;

        if(null!=extraMap){
            String ua = extraMap.get("user-agent");
            if(null!=ua && !"".equals(ua)){
                user_agent = ua;
            }
        }

        String tempFilePath = savePath + ".curltemp";

        // 拼写下载命令
        String[] cmdString = {"/bin/bash", "-c", "/usr/bin/curl -X GET "
                + " --connect-timeout " + CONNECT_TIME_OUT + "  -m " + DOWNLOAD_TIME_OUT
                + " -L -w state=%{http_code}size=%{size_download}end"
                + " -A " + "\"" + user_agent + "\""+headerMapStr
                + " -o " + tempFilePath +" "
                + url};

        String resultStr = exec(Arrays.asList(cmdString));

        if(resultStr.contains("timed out")){
            errSb.append("下载超时");
            return -1;
        }


        // 匹配下载输出结果中的 Http状态和文件大小
        Matcher match = p.matcher(resultStr);
        if(match.find()){
            String stateStr = match.group(1);
            String sizeStr = match.group(2);

            // http状态为200 Ok
            if("200".equals(stateStr)){
                File downFile = new File(tempFilePath);
                if(downFile.exists()){
                    try{
                        long fileSize = Long.parseLong(sizeStr);

                        // 比较下载下来的文件和Get请求返回的真实文件大小
                        if(fileSize!=downFile.length()){
                            errSb.append("文件下载中断，下载未完成！");
                        }
                        else{
                            // 下载的文件大小和真实文件大小相符合，下载成功
                            state = 1;
                            downFile.renameTo(new File(savePath));
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        errSb.append("下载失败" + ", Http_code:" + stateStr + ";size:" + sizeStr
                                +";Excpetion:" + e.getMessage());
                    }
                }else{
                    errSb.append("下载已完成，但文件丢失！");
                }
            }
            else{
                errSb.append("下载失败" + ", Http_code:" + stateStr);
            }
        }
        else{
            state=-2;
        }

        return state;
    }

    private static String encodeUrl(String url){
        url = url.replace("&", "\\&");
        url = url.replace("(", "%28");
        url = url.replace(")", "%29");

        return url;
    }


    private static String exec(List<String> commandList) {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command(commandList);
        System.out.println("curl命令行"+commandList);
        builder.redirectErrorStream(true);
        Process p;
        StringBuffer sb = new StringBuffer();
        try {
            p = builder.start();
            BufferedReader inputBuf = null;
            String line = null;
            inputBuf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = inputBuf.readLine()) != null) {
                sb.append(line);
                continue;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException("Curl下载过程被打断！");
        }
        return sb.toString();
    }

    /**
     * curl下载文件
     * @param curlPath
     * @param destFilePath
     * @param url
     * @param headerMap
     * @param paramsMap
     */
    public static void dowloadFileCurl(String curlPath ,String destFilePath, String url,Map<String, String> headerMap,Map<String, String> paramsMap) {
        url = encodeUrl(url);
        //遍历map
        for (String key : paramsMap.keySet()) {
            url += "?"+key+"="+paramsMap.get(key);
        }
        System.out.println("url"+url);

        String headerMapStr="";
        //遍历map
        for (String key : headerMap.keySet()) {
            headerMapStr += " -H  \""+key+":"+headerMap.get(key)+"\" ";
        }

        String cmdString =curlPath + " -o "+ destFilePath +" \"" + url +"\""
                + headerMapStr+ " -X GET";
        System.out.println("执行的命令---》"+cmdString);

        long start = System.currentTimeMillis();
        try {
            Runtime.getRuntime().exec(cmdString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("下载成功，耗时(ms)："+(System.currentTimeMillis()-start));
    }

    /**
     * 本地测试
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
//        String url = "http://47.97.154.153:8201/file-manager/file/attach/view";
        String url = "https://view.xdocin.com/view-xdocin-com_unkwvl.htm";
        StringBuffer errSb = new StringBuffer();
        String saveFilePath="/media/zgq/data/code/git.youlu.com/dev-test-team-project/api-test/swaggerTest/wanma-cuke-bdd-test/shixing/new-cuke/wm_cuke/src/test/resources/upload/temp.docx";
        Map<String, String> HeaderMap = new HashMap();
        Map<String, String> ParamMap = new HashMap();
        HeaderMap.put("Accept","*/*");
        HeaderMap.put("Request-Origion","Knife4j");
        HeaderMap.put("Content-Type","application/x-www-form-urlencoded");
        HeaderMap.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiVVNFUjIwMTgwMTAxMDEwMDAwMDAwMDAxIiwidXNlcl9uYW1lIjoiMTM0MzMxNTY5MjEiLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjExMzg4MjY1LCJhdXRob3JpdGllcyI6WyIxX-i2hee6p-euoeeQhuWRmCJdLCJqdGkiOiI2ZjE1ZWMwMi0yMmE5LTRkYWUtYmNlNC1jNTM4Y2IxZWEwNDEiLCJjbGllbnRfaWQiOiJhZG1pbi1hcHAifQ.XwfKcTHlRgpc2fjfFcnjoGBUWZscffQQrlnn7lgFcUA0ZYqTG9oR4htIEjGfhtjBY_AjMkAIsx2oIHOKxtEW-iRvq-aBJCCic9HqCMB4QjiVCC66LJEnhSJ_r65_mEfpaRwhS6xKHVxaMqvsXbj17RjGPbJ_-QtZXU2FNJUARqA");
        ParamMap.put("fileId", "FILE1351013005235720192");

//        int result = CurlKit.curl(url,null, HeaderMap,ParamMap,saveFilePath, errSb);
//        System.out.println("result:" + result + " error: " + errSb.toString());
//
        String curlPath = "/usr/bin/curl";
        String destPath="/media/zgq/data/code/git.youlu.com/dev-test-team-project/api-test/swaggerTest/wanma-cuke-bdd-test/shixing/new-cuke/wm_cuke/src/test/resources/upload/temp.docx";
        String fileUrl = "http://i0.hdslb.com/bfs/archive/5a08e413f479508ab78bb562ac81f40ad28a4245.jpg";
//        dowloadFileCurl(curlPath,saveFilePath,url,HeaderMap,ParamMap);
        dowloadFileCurl(curlPath,destPath,fileUrl,HeaderMap,ParamMap);

    }
}
