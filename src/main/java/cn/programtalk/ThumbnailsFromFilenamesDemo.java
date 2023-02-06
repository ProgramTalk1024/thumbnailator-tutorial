package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ThumbnailsFromFilenamesDemo {
    /**
     * 使用FromFiles处理多个图片。
     */
    public static void main(String[] args) throws IOException {
        // 参数是本地文件路径，支持多参数
        File file = new File("input"); // input文件夹下有多个图片
        File[] files = file.listFiles();
        assert files != null;
        List<String> filenames = Arrays.stream(files).map(File::getPath).toList();
        Thumbnails.fromFilenames(filenames)
                .scale(1D)
                // 这里必须使用`toFiles`，因为处理的是多图
                .toFiles(new File("output"), Rename.SUFFIX_HYPHEN_THUMBNAIL);
    }
}
