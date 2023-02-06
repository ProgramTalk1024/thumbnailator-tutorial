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

# 入手
`要使用thumbnailtor`，主要使用`Thumbnails`类，该类采用建造者模式设计，类中提供了很多返回`Builder`的静态方法。
![](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061455602.png)

正如上图所示，`Thumbnails`提供了通过字符串图片地址、File对象、URI对象等方式创建`Thumbnails`对象的方式。
## of方法
of方法提供了多种参数，当然参数虽然不同，功能却是相同的。

```java
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
```
代码执行完毕后，会生成四个图片。
![](https://programtalk-1256529903.cos.ap-beijing.myqcloud.com/202302061518740.png)

