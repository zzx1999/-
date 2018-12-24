package zzx.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zzx.entity.student;
import zzx.entity.studentAllAttr;

import java.util.List;
@Repository
public interface StudentMapper {
    //获取全部学生
    public List<student> findAllStudent();
    //获取所有学生属性
    public List<studentAllAttr> findStudentAllAttr();
    //通过学号找姓名
    public String searchName(@Param("stuNo") int stuNo);
    //通过学号找ID
    public int searchStuId(@Param("stuNo")int stuNo);
}
