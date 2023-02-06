# 什么是Thumbnailator？
![](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061124918.png)

Thumbnailator是Google开源的优秀图片处理的第三方Java类库，比JDK自带的库要好用的多。

[官网Github地址](https://github.com/coobird/thumbnailator)
# Maven依赖
目前最新版本是0.4.19
```xml
<dependency>
    <groupId>net.coobird</groupId>
    <artifactId>thumbnailator</artifactId>
    <version>0.4.19</version>
</dependency>
```

# 图片处理步骤
`Thumbnailtor`处理图片的步骤可以看我绘制的下图来说明。
![](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061556838.png)

主要分为三个步骤。

# 构建Thumbnails.Builder
`要使用thumbnailtor`，主要使用`Thumbnails`类，该类采用建造者模式设计，类中提供了很多返回`Builder`的静态方法。
![](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061455602.png)

正如上图所示，`Thumbnails`提供了通过字符串图片地址、File对象、URI对象等方式创建`Thumbnails`对象的方式。
## of方法
of方法提供了多种参数，当然参数虽然不同，功能却是相同的。并且of支持多个参数，可以同时处理多个图片，不过要特别注意，处理多个图片的时候，输出方法也要使用支持输出多个图片的方法。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class ThumbnailsOfDemo {
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

        // 网络图片
        Thumbnails.of(new URL("https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061502561.png"))
                .scale(1D)
                .toFile("output/o4.png");
    }
}

```

`scale`代表缩放，`toFile`是输出图片的方法，先不要管，后面会讲到。代码执行完毕后，会生成五个图片。

## fromFilenames方法
`Thumbnails.fromFilenames(Iterable<String> files)`方法支持接入多个图片，也就是说能处理多个图片。
```java
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
```
`toFiles`的用法后面会讲解。

## fromFiles方法
`Thumbnails.fromFiles方法(Iterable<File> files)`方法支持接入多个图片，也就是说能处理多个图片。不同于`fromFilenames`的是它的参数是`Iterable<File>`类型。
```java
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
```
## fromURLs方法 

`Thumbnails.fromURLs`用于批量处理网络图片。

```java
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
```

## fromImages方法
`Thumbnails.fromImages`批量处理`BufferedImage`类型的图片。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ThumbnailsFromImagesDemo {
    /**
     * 使用fromImages处理多个图片。
     */
    public static void main(String[] args) throws IOException {
        Thumbnails.fromImages(List.of(ImageIO.read(new URL("https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061502561.png"))
                        , ImageIO.read(new URL("https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202301311404082.png"))))
                .scale(1D)
                .toFiles(List.of(new File("output/fromImages1.jpg"), new File("output/fromImages2.jpg")));
    }
}
```

## fromInputStreams方法

`Thumbnails.fromInputStreams`批量处理`InputStream`类型的图片。

```java
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
```



# 图片处理

讲解图片处理前，先准备一张图片（wx.png）。图片属性信息如下：

![image-20230206163350487](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061633592.png)

## 图片尺寸

### 基本说明

图片尺寸控制可以通过`width`、`height`，`size`， `forceSize`，`scale`方法控制。

`width`、`height`，`size`方法会默认保证原始图片比例（可以通过设置`keepAspectRatio(false)`取消，如果取消则等价于`forceSize`方法。），因为收到原始图片宽高比例的限制，即便设置了宽高可能也不会引起图片的尺寸（像素）变化（可能会导致图片大小变化）。

`forceSize`则会强制保证宽高，可能会导致图片拉伸。

`scale`是设置图片宽高比例，不大于1的数参数。

有以下异常情况要注意：

* 调用`width`和`height`方法后不能再使用` size`或者`scale`方法，否则会抛出异常。
* 调用`size`方法后不能再使用`scale`方法，否则会抛出异常。
* 调用`scale`方法后不能调用`size`、 `scale`或`keepAspectRatio(boolean)` 方法，否则会抛出异常。



### 代码示例

原图是宽=`1710`，高=`624`，宽高比`≈2.74`，那么目标的宽度是100，高度就会被计算为`100 / 2.74 ≈ 36`。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsSizeDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                .size(100, 100)
                .toFile("output/o1.png");
    }
}
```



处理完毕后图片信息如下：

![size处理结果](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061635967.png)

可以看到确实是`100X36`的分辨率。



如果想不保证宽高比例，可以使用`forceSize`方法。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsSizeDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                .forceSize(100, 100)
                .toFile("output/o1.png");
    }
}
```



效果如下：



![forceSize](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061738225.png)





通过scale，设置宽高比例来修改尺寸。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class ThumbnailsSizeDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                .scale(0.8)
                .toFile("output/o1.png");
    }
}
```

代码中设置了scale=0.8

效果图如下：

![scale](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061752224.png)



## 图片旋转

可以通过`rotate(double angle)`，来旋转图片，参数是度，比如30代表30度。

```java
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
```



![image-20230206175947372](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061759466.png)

## 图片水印

通过`watermark`设置水印，有多个多态方法。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ThumbnailsWatermarkDemo {
    public static void main(String[] args) throws IOException {
        Thumbnails.of("input/wx.png")
                // 水印放到右下角
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("input/watermark.png")), 0.5f)
                .scale(1.74)
                .toFile("output/o1.png");
    }
}
```



![image-20230206182020651](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061820754.png)



# 输出图片

## 输出格式

`outputFormat(String format)`方法用于设置输出文件格式。支持的格式可以通过`ImageIO.getWriterFormatNames()`获取。

我这里打印的格式列表是`JPG jpg tiff bmp BMP gif GIF WBMP png PNG JPEG tif TIF TIFF jpeg wbmp`。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ThumbnailsOutputFormatDemo {
    public static void main(String[] args) throws IOException {
        String[] writerFormatNames = ImageIO.getWriterFormatNames();
        for (String writerFormatName : writerFormatNames) {
            System.out.print(writerFormatName + " "); // JPG jpg tiff bmp BMP gif GIF WBMP png PNG JPEG tif TIF TIFF jpeg wbmp
        }
        Thumbnails.of("input/wx.png")
                .scale(1.75)
                // 如果不设置默认跟原图片一致
                .outputFormat("JPG")
                .toFile("output/o1");
    }
}
```

输出结果：

![outputFormat](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061839691.png)

## 输出质量

通过`outputQuality(float quality)`设置输出质量，参数介于0和1之间，支持小数，数字越小质量越差。

```java
package cn.programtalk;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ThumbnailsOutputFormatDemo {
    public static void main(String[] args) throws IOException {
        String[] writerFormatNames = ImageIO.getWriterFormatNames();
        for (String writerFormatName : writerFormatNames) {
            System.out.print(writerFormatName + " "); // JPG jpg tiff bmp BMP gif GIF WBMP png PNG JPEG tif TIF TIFF jpeg wbmp
        }
        Thumbnails.of("input/wx.png")
                .scale(1.75)
                // 如果不设置默认跟原图片一致
                .outputFormat("JPG")
                // 设置质量
                .outputQuality(1F)
                .toFile("output/o1");
    }
}
```

