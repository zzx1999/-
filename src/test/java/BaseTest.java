import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/java")
@ContextConfiguration({"classpath:spring/springmvc.xml","classpath:spring/applicationContext-dao.xml","classpath:spring/applicationContext-service.xml"})
public class BaseTest {
    protected Logger log = LoggerFactory.getLogger(getClass());
    @Test
    public void test(){
        System.out.println("aaa");
    }

}
