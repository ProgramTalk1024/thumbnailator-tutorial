package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ThumbnailsToOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        OutputStream os = new FileOutputStream("output/logo.png");
        Thumbnails.of("input/logo.jpg")
                .scale(1.75)
                .toOutputStream(os);
    }
}
