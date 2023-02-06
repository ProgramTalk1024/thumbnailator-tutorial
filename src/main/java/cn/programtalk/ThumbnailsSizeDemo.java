package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsSizeDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                .scale(0.8)
                .toFile("output/o1.png");
    }
}
