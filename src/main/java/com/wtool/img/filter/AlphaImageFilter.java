package com.wtool.img.filter;

import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.RGBImageFilter;

/**
 * @author wanglicheng
 * @Version v1.0
 * @ClassName com.wtool.img.filter.AlphaImageFilter
 * @Date 2023/3/10 14:41
 * @Description <请输入一句话描述该类功能>
 * @Copyright <酷得少年（北京）文化传播有限公司>
 */
public class AlphaImageFilter extends RGBImageFilter {

    private static final DirectColorModel DIRECT_COLOR_MODEL = (DirectColorModel)ColorModel.getRGBdefault();

    private int alpha = 10;

    public AlphaImageFilter(){
        super();
        super.canFilterIndexColorModel = true;
    }
    public AlphaImageFilter(int alpha){
        super();
        super.canFilterIndexColorModel = true;
        this.alpha = Math.max(0, alpha);
    }
    /**
     * Subclasses must specify a method to convert a single input pixel
     * in the default RGB ColorModel to a single output pixel.
     *
     * @param x   the X coordinate of the pixel
     * @param y   the Y coordinate of the pixel
     * @param rgb the integer pixel representation in the default RGB
     *            color model
     * @return a filtered pixel in the default RGB color model.
     * @see ColorModel#getRGBdefault
     * @see #filterRGBPixels
     */
    public int filterRGB(int x, int y, int rgb) {
        if(DIRECT_COLOR_MODEL.getAlpha(rgb) < this.alpha){
            return rgb;
        }
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        int gray = (r * 299 + g * 587 + b * 114 + 500) / 1000;
        return (255 & 0xff) << 24 | (gray & 0xff) << 16 | (gray & 0xff) << 8 | gray & 0xff;
    }
}
