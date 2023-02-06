package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ThumbnailsToOutputStreamsDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/logo.jpg", "input/logo.png")
                .scale(1.75)
                // 这里文件后缀最好指定，写入到磁盘的文件没有文件后缀。
                .toOutputStreams(List.of(new FileOutputStream("output/1.jpg"), new FileOutputStream("output/2.png")));
    }
}
