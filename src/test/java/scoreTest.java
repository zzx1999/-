import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import zzx.mapper.ScoreMapper;
import zzx.service.IstudentService;

import java.util.Date;

public class scoreTest extends BaseTest {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private IstudentService istudentService;
    @Test
    public void testScore(){

        System.out.println(scoreMapper.addScore(1,1,98,new Date()));
    }
    @Test
    public void testsearchalex(){
        int stuNo = istudentService.searchalex(1,1);
        System.out.println(stuNo);
    }
    @Test
    public void testupdate(){
        istudentService.updateScore(2,3,2,99,new Date());
    }

    @Test
    public void testdel(){
        istudentService.delScore(3);
    }

    @Test
    public void testgradeSum(){
        System.out.println(istudentService.getGradeSum(1));
    }

}
