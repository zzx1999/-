package zzx.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zzx.entity.score;
import zzx.entity.student;
import zzx.entity.studentAllAttr;
import zzx.entity.subject;

import java.util.Date;
import java.util.List;
@Service
public interface IstudentService {
    //studentMapper
    public List<student> findAllStudent();//获取所有学生
    public List<studentAllAttr> findStudentAllAttr();//获取专业、年级、学号、姓名、性别、科目、成绩
    public String searchName(int stuNo);//通过学号查找姓名
    public int searchStuId(int stuNo);//通过学号查找id

    //SubjectMapper
    public List<subject> findstubject(int subid);//通过课程id找课程
    public List<subject> findAllsub();//查找所有课程
    public int searchId(String subname);//通过课程名查找课程id

    //ScoreMapper
    public List<score> findAllScore();//查找所有成绩记录
    public List<score> findstuScore(int subint,int stuid);//通过课程id，学生id查找成绩信息
    public int searchalex(int subId,int stuId);//通过课程id，学生id查找成绩信息是否存在
    public int addScore(int subjectId, int studentId, float studentScore, Date modifyTime);//添加成绩
    public void updateScore(int soreId,int subjectId, int studentId, float studentScore, Date modifyTime);//更新
    public void delScore(int scoreId);//删除
    public float getGradeSum(int sumNo);//根据学号获取某学生总成绩
    public Float getAllGradeSum();//获取所有学生总成绩
    public Float getGradeAvg(int avgno);//根据学号获取某学生平均成绩
    public Float getAllGradeAvg();//获取所有学生平均成绩




}
