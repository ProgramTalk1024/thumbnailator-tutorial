package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsRotateDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                .rotate(30) // 旋转30度
                // 此处size必须设置
                .size(100, 10)
                .toFile("output/o1.png");
    }
}
