package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ThumbnailsWatermarkDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                // 水印放到右下角
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("input/watermark.png")), 0.5f)
                .forceSize(100, 100)
                .toFile("output/o1.png");
    }
}
