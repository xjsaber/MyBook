package readinglist;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xjsaber on 2017/4/27.
 * Enable component-scanning and auto-configuration
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReadingListApplication.class)
public class ReadingListApplicationTest {

    public static void main(String[] args) {
        //Test that the context loads
        SpringApplication.run(ReadingListApplicationTest.class, args);
    }
}
