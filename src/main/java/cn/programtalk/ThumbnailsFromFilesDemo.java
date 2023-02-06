package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ThumbnailsFromFilesDemo {
    /**
     * 使用fromFiles处理多个图片。
     */
    public static void main(String[] args) throws IOException {
        File file = new File("input");
        File[] files = file.listFiles();
        assert files != null;
        Thumbnails.fromFiles(Arrays.asList(files))
                .scale(1D)
                // 这里必须使用`toFiles`，因为处理的是多图
                .toFiles(new File("output"), Rename.SUFFIX_HYPHEN_THUMBNAIL);
    }
}
