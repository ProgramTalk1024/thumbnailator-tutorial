package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsToFileDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/logo.jpg")
                .scale(1.75)
                // 默认如果文件已经存在，则不覆盖，可以设置allowOverwrite=true。
                .allowOverwrite(true)
                // 可以不指定文件后缀，默认是png
                .toFile("output/logo-new");
    }
}
