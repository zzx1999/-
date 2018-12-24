package zzx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zzx.entity.student;
import zzx.entity.studentAllAttr;
import zzx.entity.subject;
import zzx.service.IstudentService;
import zzx.service.IstudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class studentController {
    @Autowired
    private IstudentService istudentService;
    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }
    //从数据库中加载数据
    @RequestMapping(value = "/student",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject showStudentALL(HttpServletRequest request){
         List<studentAllAttr> listStudent = istudentService.findStudentAllAttr();
        JSONObject stujson = new JSONObject();
        System.out.println(request.getParameter("pageIndex"));
        int rows = Integer.parseInt(request.getParameter("pageSize"));//每一页的最大记录数
        int page = Integer.parseInt(request.getParameter("pageIndex"));//页码
        //System.out.print(page+"   "+rows);
        int PageIndexbegin = (page-1)*rows;//每一页起始点位置
        int PageIndexend = PageIndexbegin+rows>listStudent.size()-1?listStudent.size():PageIndexbegin+rows;

        stujson.put("total",listStudent.size());
        stujson.put("rows",listStudent.subList(PageIndexbegin,PageIndexend));//一页的条数
        System.out.println(stujson.toString());
        return stujson;

    }
    //通过学号查找学生姓名
    @RequestMapping(value = "/searchstuName",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject searchstuName(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8"); //当然如果是json数据,需要设置为("text/javascript;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String stuNo = request.getParameter("stuNo");
        System.out.println(stuNo);
        String stuName = istudentService.searchName(Integer.parseInt(stuNo));
        System.out.println(stuName);
        JSONObject stuNamejson = new JSONObject();
        stuNamejson.put("stuName",stuName);
        return stuNamejson;
    }
    //添加成绩信息
    @RequestMapping(value = "/addscore",method = RequestMethod.POST)
    @ResponseBody   //@RequestBody转换接受的JSON为对象，用@ResponseBody转换返回的对象为JSON。有两种接受前端数据的方式，一种使用Map接受，一种使用实体类进行接收
    public String addscore(@RequestBody Map<String, String> addmap){
        int stuNo = Integer.parseInt(addmap.get("studentNo"));//获取学号，并进行类型转换
        //System.out.println(addmap.get("subjectName"));
        int stuId = istudentService.searchStuId(stuNo);//通过学号获取学生id
        int subId = istudentService.searchId(addmap.get("subjectName"));//通过课程名获取课程id
        float stuscore = Float.parseFloat(addmap.get("stuscore"));//获取学生成绩
        System.out.println(stuNo+"  "+subId+"  "+stuscore+"  ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            System.out.println(addmap.get("addtime"));
            Date modifytime = sdf.parse(addmap.get("addtime"));
            int hasfoundinScore = istudentService.searchalex(subId,stuId);//查找该学生课程信息，
            System.out.println(hasfoundinScore);
            //如果该条记录存在就更新数据库
            if(hasfoundinScore!=0){
                istudentService.updateScore(hasfoundinScore,subId,stuId,stuscore,new Date());
                return "更新成功";
            }//不存在就插入
            else{
                int isadd = 0;
                isadd = istudentService.addScore(subId,stuId,stuscore,new Date());
                if(isadd!=0)
                    return "新增成功";
                else
                    return "新增失败";


            }

        }catch (ParseException e){
            e.printStackTrace();
            return "失败";
        }

    }
    //删除学生成绩信息
    @RequestMapping(value = "/delet",method = RequestMethod.POST)
    @ResponseBody
    public String delet(@RequestBody Map<String, String> deletemap){
        int stuid = istudentService.searchStuId(Integer.parseInt(deletemap.get("stuNo")));//根据学号找stuid
        int subid = istudentService.searchId(deletemap.get("subname"));//根据课程名找subid
        int scoreid = istudentService.searchalex(subid,stuid);//查找是否存在该成绩信息记录
        System.out.println(scoreid);
        if(scoreid!=0) {
            istudentService.delScore(scoreid);//存在即删除
            return "del ok";
        }
        return "del false";

    }

    //根据学号获得学生总成绩
    @RequestMapping(value="/getGradeSum",method = RequestMethod.POST)
    @ResponseBody
    public String getGradeSum(HttpServletRequest request){
        System.out.println(request.getParameter("sumno")+"");
        int stuNo = Integer.parseInt(request.getParameter("sumno"));
        int stuId = istudentService.searchStuId(stuNo);
        System.out.println(stuId+"getsum");
        float scoresum = istudentService.getGradeSum(stuId);


        return scoresum+"";
    }
    //获取所有学生总成绩
    @RequestMapping(value = "/getAllGradeSum",method = RequestMethod.POST)
    @ResponseBody
    public String getAllGradeSum(){
        return istudentService.getAllGradeSum()+"";
    }

    //根据学号获得学生平均成绩
    @RequestMapping(value="/getGradeAvg",method = RequestMethod.POST)
    @ResponseBody
    public String getGradeAvg(HttpServletRequest request){
        System.out.println(request.getParameter("avgno")+"");
        int stuNo = Integer.parseInt(request.getParameter("avgno"));
        int stuId = istudentService.searchStuId(stuNo);
        System.out.println(stuId+"getsum");
        float scoreavg = istudentService.getGradeAvg(stuId);


        return scoreavg+"";
    }

    //获得所有学生平均成绩
    @RequestMapping(value = "/getAllGradeAvg",method = RequestMethod.POST)
    @ResponseBody
    public String getAllGradeAvg(){
        return istudentService.getAllGradeAvg()+"";
    }
}
