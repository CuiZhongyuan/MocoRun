import org.testng.annotations.Test;

import java.io.*;

public class TotalLine {

    int sumFile = 0;
    long sumLine =0;
    @Test
    public void runTest(){
        try {
            //读取文件路径
            File file = new File("D:\\work\\MocoRun\\src\\main\\java\\com\\api\\test");
            //统计结果输出到文本text
            FileOutputStream fileOutputStream =  new FileOutputStream("D:\\work\\sum.text");
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream,"utf-8"));
            bufferedWriter.write("类名\t\t行数");
            //自动根据操作系统选择对应的换行符newLine()
            bufferedWriter.newLine();
            //递归统计代码行数
            sumTotal(file,bufferedWriter);
            bufferedWriter.newLine();
            bufferedWriter.write("一共："+sumFile+"个类\t\t"+sumLine+"代码行数");
            //关流前要先刷新流
            bufferedWriter.flush();
            //关流
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sumTotal(File file,BufferedWriter bufferedWriter){
        //for循环遍历---按文件大小、名称、日期排序方法
        for (File f : file.listFiles()){
            //判断结尾是Java文件
            if (f.isFile()&&f.getName().endsWith(".java")){
                try {
                    int line = 0;
                    BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(f),"utf-8"));
                    String str = null;
                    while ((str=br.readLine())!= null){
                        //\\s表示 空格,回车,换行等空白符,将空白符替换为空字符""
                        str = str.replace("\\s","");
                        //过滤注释
                        if ("".equals(str) || str.startsWith("//")|| str.startsWith("/**")
                                ||str.startsWith("**/")||str.startsWith(" *")){
                        }else {
                            line++;
                            bufferedWriter.write(line+""+str);
                            bufferedWriter.newLine();
                            System.out.println(line+":"+str);
                        }
                    }
                    br.close();
                    System.out.println(f.getName()+"\t\t"+line);
                    //写入换行符
                    bufferedWriter.newLine();
                    //写入类名
                    bufferedWriter.write(f.getName()+"\t\t"+line);
                    bufferedWriter.newLine();
                    //把缓冲区的数据强行写出
                    bufferedWriter.flush();
                    sumFile++;
                    sumLine += line;
                    System.out.println("统计："+sumFile+"\t\t"+sumLine +"行");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (file.isDirectory()){
                //当file为目录时，递归遍历
                sumTotal(file,bufferedWriter);
            }
        }

    }
}
