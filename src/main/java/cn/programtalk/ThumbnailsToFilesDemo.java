package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ThumbnailsToFilesDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/logo.jpg", "input/logo.png")
                .scale(1.75)
                // 默认如果文件已经存在，则不覆盖，可以设置allowOverwrite=true。
                .allowOverwrite(true)
                // 可以不指定文件后缀，默认是png
                //.toFiles(new File("output"), Rename.NO_CHANGE);
                //.toFiles(Rename.NO_CHANGE);
                .toFiles(List.of(new File("output/1"), new File("output/2")));
    }
}
