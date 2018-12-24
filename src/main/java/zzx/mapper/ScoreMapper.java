package zzx.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zzx.entity.score;

import java.util.Date;
import java.util.List;
@Repository
public interface ScoreMapper {
    public List<score> findstuScore(@Param("stuid") int stuid,@Param("subid") int subid);
    public List<score> findAllScore();
    //查找某学生成绩信息是否已经存在 already exist 返回已存在记录ID
    public int searchalex(@Param("subId")int subId,@Param("stuId") int stuId);
    //添加成绩信息
    public int addScore(@Param("subjectId") int subjectId, @Param("studentId") int studentId, @Param("studentScore") float studentScore, @Param("motime") Date modifyTime);
    //更新成绩信息
    public void updateScore(@Param("scoreId") int scoreId,@Param("subjectId") int subjectId, @Param("studentId") int studentId, @Param("studentScore") float studentScore, @Param("motime") Date modifyTime);
    //删除成绩信息
    public void delScore(@Param("scoreId") int scoreId);
    //获取某学生的总成绩
    public Float getGradeSum(@Param("sumNo") int sumNo);
    //获取所有学生总成绩
    public Float getAllGradeSum();
    //获取学生平均成绩
    public Float getGradeAvg(@Param("avgno") int avgno);
    //获取所有学生平均成绩
    public Float getAllGradeAvg();
}
