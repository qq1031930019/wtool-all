package com.wtool.img;

import com.wtool.img.filter.AlphaImageFilter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

/**
 * 图片转换
 * @author wanglicheng
 * @Version v1.0
 * @ClassName com.wtool.img.ImgConvert
 * @Date 2023/3/10 14:40
 * @Description <请输入一句话描述该类功能>
 * @Copyright <酷得少年（北京）文化传播有限公司>
 */
public class ImgConvert {


    /**
     * 转成灰图
     * @param srcFile
     * @param distFile
     */
    public static void gary(File srcFile, File distFile) throws IOException {
        if(srcFile == null || distFile  == null){
            throw new IllegalArgumentException("parameter cannot be empty ！");
        }
        BufferedImage bufferedImage = ImageIO.read(srcFile);
        FilteredImageSource fis = new FilteredImageSource(bufferedImage.getSource(), new AlphaImageFilter());
        Image im = Toolkit.getDefaultToolkit().createImage(fis);
        im.flush();
        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(im, 0, 0, null);
        g.dispose();
        newImage.flush();
        ImageIO.write(newImage, "png", distFile);
        bufferedImage.flush();
    }
}
