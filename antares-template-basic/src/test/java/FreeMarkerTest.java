import java.io.File;
import java.io.IOException;

import org.junit.Test;

import freemarker.template.Configuration;

public class FreeMarkerTest {
    @Test
    void testFreeMarker() throws IOException{
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
    }
    
}
