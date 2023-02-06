package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class ThumbnailsFromInputStreamsDemo {
    /**
     * 使用fromInputStreams处理多个图片。
     */
    public static void main(String[] args) throws IOException {
        Thumbnails.fromInputStreams(List.of(new FileInputStream("input/logo.png")
                        , new FileInputStream("input/logo.png")))
                .scale(1D)
                .toFiles(List.of(new File("output/fromInputStreams.jpg"), new File("output/fromInputStreams.jpg")));
    }
}
