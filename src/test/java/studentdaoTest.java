import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zzx.entity.student;
import zzx.entity.studentAllAttr;
import zzx.mapper.StudentMapper;
import zzx.service.IstudentService;

import javax.annotation.Resource;
import java.util.List;

public class studentdaoTest extends BaseTest{
    @Autowired
    private StudentMapper studentmapper;
    @Autowired
    private IstudentService istudentService;
    @Test
    public void testshow() {
        List<studentAllAttr> studentList = studentmapper.findStudentAllAttr();
        System.out.print(studentList.size());
        System.out.print(studentList.size());
    }

    @Test
    public void testsearchIdByNo(){
        int testtemp = istudentService.searchStuId(20160101);
        System.out.println(testtemp);
    }

}
