package zzx.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zzx.entity.subject;

import java.util.List;
@Repository
public interface SubjectMapper {
    public List<subject> findAllsub();//获取所有课程
    public List<subject> findstubject(@Param("subid") int subid);//根据课程id查找课程
    public int searchId(@Param("subname")String subname);//根据课程名查找课程id
}
