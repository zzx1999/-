import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import zzx.mapper.SubjectMapper;
import zzx.service.IstudentService;
import zzx.service.IstudentServiceImpl;

import javax.annotation.Resource;
import java.util.logging.Logger;

public class subjectdaoTest extends BaseTest{
    @Autowired
    public IstudentService istudentService;
    @Autowired
    private SubjectMapper subjectMapper;
    @Test
    public void testsearchid(){
        System.out.println("begintest");
        //int isok = istudentService.searchId("高等数学");
        System.out.println(istudentService.searchId("高等数学"));
        //System.out.println(subjectMapper.searchId("高等数学"));
    }
}
