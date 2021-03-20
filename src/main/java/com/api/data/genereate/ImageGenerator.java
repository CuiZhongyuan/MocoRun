package com.api.data.genereate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class ImageGenerator {

    //合成图片
    public static File overlapImage(String backgroundPath, String gzhPath, String qrCodePath, String text, String outPutPath) {
        try {
            // 设置图片大小
            //设置图片大小
            BufferedImage background = resizeImage(848,1018, ImageIO.read(new File(backgroundPath)));
            BufferedImage qrCode = resizeImage(80,80,ImageIO.read(new File(qrCodePath)));
            BufferedImage gzh = resizeImage(80,80,ImageIO.read(new File(gzhPath)));
            Graphics2D g = background.createGraphics();
            g.setColor(Color.black);
            g.setFont(new Font("微软雅黑",Font.PLAIN,40));
            g.drawString(text,436 ,630);
            //在背景图片上添加二维码图片
            g.drawImage(qrCode, 760, 920, qrCode.getWidth(), qrCode.getHeight(), null);
            g.drawImage(gzh, 660, 920, gzh.getWidth(), gzh.getHeight(), null);
            g.dispose();
            File out = new File(outPutPath + System.currentTimeMillis()+".jpg");
            ImageIO.write(background, "jpg", out);
            return out;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi) {
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }

    //批量生成生成图片
    public static void main(String[] args) {
        String imagesPath ="";
        //背景图片
         String background_img ="";
        //二维码
         String qrcode_img ="";
        //正面图片
         String gzh_img ="";
        //输出目录
         String outputdir ="";
        for(int i = 0; i <= 10; i ++) {
            String imageName = i + ".jpg";
            overlapImage(background_img,qrcode_img,gzh_img,imagesPath,outputdir);
            System.out.println("生成的图片" + imageName);
        }
    }
}
