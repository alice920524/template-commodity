package com.duia.commodity.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zhenghui on 2018/3/7.
 * 动态生成图片：已有图片插入文字
 */
public class ImageUtils {

    protected static Log logger = LogFactory.getLog(ImageUtils.class);
    //NTCE 分享模版图片 存放项目image目录下
    private final static String FILE_PATH = "image/ntce_share.jpg";
    private final static String FONT_TTC = "image/simsunb.ttf";




    /**
     *
     * @param params
     */
    public static String exportImg(String[] params) {
        String shareFileName = "";
        try {
            //1.jpg是你的 主图片的路径
            String fullPath = Thread.currentThread().getContextClassLoader().getResource(FILE_PATH).getPath();
            InputStream is = new FileInputStream(fullPath);

            //解码当前JPEG数据流，返回BufferedImage对象
            BufferedImage buffImg = ImageIO.read(is);
            //得到画笔对象
            Graphics g = buffImg.getGraphics();

            //最后一个参数用来设置字体的大小
            Color mycolor = Color.black;//new Color(0, 0, 255);
            g.setColor(mycolor);
            g.setFont(new Font("楷体", Font.BOLD, 38));
//            g.setFont(loadFont(Font.BOLD, 38));
            //200,95 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g.drawString(params[0], 200, 536);

            //第二个参数用来设置字体的大小
            g.setColor(mycolor);
            g.setFont(new Font("楷体", Font.PLAIN, 27));
//            g.setFont(loadFont(Font.PLAIN, 27));
            //200,95 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g.drawString(params[1], 253, 578);

            //最后一个参数用来设置字体的大小
            g.setColor(mycolor);
            g.setFont(new Font("楷体", Font.PLAIN, 27));
//            g.setFont(loadFont(Font.PLAIN, 27));
            //200,95 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。
            g.drawString(params[2], 580, 578);

            g.dispose();

            OutputStream os;

            //os = new FileOutputStream("d:/union.jpg");
            String dateDIR = DateFormatUtils.format(new Date(), "yyyyMMdd");
            String path = params[4] + dateDIR + "/" + UUID.randomUUID().toString() + ".jpg";

            File file = new File(params[3] + path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            os = new FileOutputStream(params[3] + path);
            //创键编码器，用于编码内存中的图象数据。
            ImageIO.write(buffImg, "jpeg", os);

            shareFileName = "/" + path;

            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            logger.error("FileNotFoundException：" + e);
        } catch(Exception e) {
            // TODO Auto-generated catch block
            logger.error("Exception：" + e);
        }
        return shareFileName;
    }

    /**
     * 应用新样式和大小，创建一个新 Font 对象。
     * @param fontStyle
     * @param fontSize
     * @return
     * @throws IOException
     * @throws FontFormatException
     */
    public static Font loadFont(int fontStyle, int fontSize) throws IOException, FontFormatException {
        String fullPath = Thread.currentThread().getContextClassLoader().getResource(FONT_TTC).getPath();
        Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, new File(fullPath));
        Font actionJsonBase = dynamicFont.deriveFont(fontStyle, fontSize);
        //register the font
        return actionJsonBase;
    }
}
