package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ThumbnailsOfDemo1 {
    public static void main(String[] args) throws IOException {
        // 参数是本地文件路径，支持多参数
        Thumbnails.of("input/logo.png")
                .scale(1D)
                .toFile("output/o1.png");

        // File类型的参数，支持多参数
        Thumbnails.of(new File("input/logo.png"))
                .scale(1D)
                .toFile("output/o2.png");

        // BufferedImage类型参数
        BufferedImage bufferedImage = ImageIO.read(new File("input/logo.png"));
        Thumbnails.of(bufferedImage)
                .scale(1D)
                .toFile("output/o3.png");

        // FileInputStream类型
        File file = new File("input/logo.png");
        FileInputStream fis = new FileInputStream(file);
        Thumbnails.of(fis)
                .scale(1D)
                .toFile("output/o4.png");
    }
}
