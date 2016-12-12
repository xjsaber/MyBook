package book.spring3.chapter4.unit;

import org.springframework.core.io.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by xjsaber on 3/15/2016.
 */
public class ResourceTest {
    @Test
    public void testByteArrayResource() {
        Resource resource = new ByteArrayResource("Hello World!".getBytes());
        if (resource.exists()) {
            dumpStream(resource);
        }
    }

    private void dumpStream(Resource resource) {
        java.io.InputStream inputStream = null;
        try {
            //1.获取文件资源
            inputStream = resource.getInputStream();
            //2.读取资源
            byte[] descBytes = new byte[inputStream.available()];
            inputStream.read();
            System.out.println(new String(descBytes));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                //3.关闭资源
                inputStream.close();
            } catch (IOException ex) {
            }
        }
    }

    @Test
    public void testInputStreamResource() {
        ByteArrayInputStream baiSteam = new ByteArrayInputStream("Hello World!".getBytes());
        Resource resource = new InputStreamResource(baiSteam);
        if (resource.exists()) {
            dumpStream(resource);
        }
        Assert.assertEquals(true, resource.isOpen());
    }

    @Test
    public void testFileResource() {
        File file = new File("classpath:text.txt");
        Resource resource = new FileSystemResource(file);
        if (resource.exists()) {
            dumpStream(resource);
        }
        Assert.assertEquals(false, resource.isOpen());
    }

    //
    @Test
    public void testClasspathResourceByDefaultClassLoader() throws IOException {
        Resource resource = new ClassPathResource("classpath:book/spring3/chapter4/test1.properties");
        if (resource.exists()) {
            dumpStream(resource);
        }
        System.out.println("path:" + resource.getFile().getAbsolutePath());
        Assert.assertEquals(false, resource.isOpen());
    }
}
