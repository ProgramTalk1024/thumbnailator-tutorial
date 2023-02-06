package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsOutputQualityDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                .scale(1.75)
                // 设置质量
                .outputQuality(1F)
                .toFile("output/o1");
    }
}
