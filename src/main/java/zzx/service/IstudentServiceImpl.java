package zzx.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zzx.entity.score;
import zzx.entity.student;
import zzx.entity.studentAllAttr;
import zzx.entity.subject;
import zzx.mapper.ScoreMapper;
import zzx.mapper.StudentMapper;
import zzx.mapper.SubjectMapper;
import zzx.service.IstudentService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class IstudentServiceImpl implements IstudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private ScoreMapper scoreMapper;
    @Override
    public List<student> findAllStudent(){
        return studentMapper.findAllStudent();
    }
    @Override
    public List<studentAllAttr> findStudentAllAttr() {
        return studentMapper.findStudentAllAttr();
    }
    @Override
    public String searchName(int stuNo){System.out.println(stuNo);return studentMapper.searchName(stuNo);}
    @Override
    public int searchStuId(int stuNo){return studentMapper.searchStuId(stuNo);}

    public StudentMapper getSdao(){
        return studentMapper;
    }
    public void setSdao(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    @Override
    public List<subject> findstubject(int subid){return subjectMapper.findstubject(subid);}
    @Override
    public List<subject> findAllsub(){return subjectMapper.findAllsub();}
    @Override
    public int searchId(String subname){System.out.println(subjectMapper.searchId(subname));return subjectMapper.searchId(subname);}

    public SubjectMapper getSubjectMapper() {
        return subjectMapper;
    }
    public void setSubjectMapper(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<score> findAllScore() {
        return null;
    }
    @Override
    public List<score> findstuScore(int subint, int stuid) {
        return null;
    }
    @Override
    public int addScore(int subjectId, int studentId, float studentScore, Date modifyTime){return scoreMapper.addScore(subjectId,studentId,studentScore,modifyTime);};
    @Override
    public int searchalex(int subId,int stuId){return scoreMapper.searchalex(subId,stuId);}

    @Override
    public void updateScore(int soreId,int subjectId, int studentId, float studentScore,Date modifyTime){
        //Date now = new Date();
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        scoreMapper.updateScore(soreId,subjectId,studentId,studentScore,modifyTime);
    }
    @Override
    public float getGradeSum(int sumNo){return scoreMapper.getGradeSum(sumNo);}
    @Override
    public Float getAllGradeSum(){return scoreMapper.getAllGradeSum();}
    @Override
    public Float getGradeAvg(int avgno){return scoreMapper.getGradeAvg(avgno);}
    @Override
    public Float getAllGradeAvg(){return scoreMapper.getAllGradeAvg();}
    public void delScore(int scoreId){scoreMapper.delScore(scoreId);}
    public ScoreMapper getScoreMapper() {
        return scoreMapper;
    }
    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }



}
