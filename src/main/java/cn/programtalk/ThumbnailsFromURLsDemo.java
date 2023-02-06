package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ThumbnailsFromURLsDemo {
    /**
     * 使用fromURLs处理多个图片。
     */
    public static void main(String[] args) throws IOException {
        Thumbnails.fromURLs(List.of(new URL("https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061502561.png")
                        , new URL("https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202301311404082.png")))
                .scale(1D)
                .toFiles(List.of(new File("output/fromURLs1.jpg"), new File("output/fromURLs2.jpg")));
    }
}
