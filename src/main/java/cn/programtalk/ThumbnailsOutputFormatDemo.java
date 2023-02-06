package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ThumbnailsOutputFormatDemo {
    public static void main(String[] args) throws IOException {
        String[] writerFormatNames = ImageIO.getWriterFormatNames();
        for (String writerFormatName : writerFormatNames) {
            System.out.print(writerFormatName + " "); // JPG jpg tiff bmp BMP gif GIF WBMP png PNG JPEG tif TIF TIFF jpeg wbmp
        }
        Thumbnails.of("input/wx.png")
                .scale(1.75)
                // 如果不设置默认跟原图片一致
                .outputFormat("JPG")
                // 设置质量
                .outputQuality(1F)
                .toFile("output/o1");
    }
}
