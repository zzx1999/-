  var formdata = {};//表单数据
  var isstuex = 0;//检查学生是否存在
  var submitflag = 1;//1 表示新增，2 表示修改
  var formid = 0;//每一行在数据库中的索引

  function cleanFrom(){
      $("input:text[id='sno']").attr("readonly",false);//将表单变为可编辑
      $("input:text[id='name']").attr("readonly",false);//将表单变为可编辑
      $("input:radio[name='optionsRadiosinline2']").removeAttr("disabled");//将表单变为不可编辑
      console.log("clean");
      var formcontent = new Array();
      formcontent = $("input");
      //清空表单
      for(var i=0;i<formcontent.length;i++){
        if(formcontent[i].type=="radio"){
          $(formcontent[i]).removeAttr("checked");//清空复选框
        }
        else{
          formcontent[i].value = '';//清空文本框
        }
      }
      $(".yearchoosed").text("2016");//回置下拉框
      //清空formdata
      formdata = {};
      submitflag = 1;//表示新增
      alert("请在下方表单填写相关信息");
  }
  //表单验证+结果获取
  function checkform(){
        console.log("check")
        //验证输入内容
        //验证单选框是否都有选中
      var re = /^\d{8}$/;
      var sno = $("input[id='sno']").val();
        //学号不为空
        if(sno==""){
          alert("请输入学号");
          return false;
        }
        //学号有6位
        if(!re.test(sno)){
          alert("请输入8位学号");
          return false;
        }
        else{
          formdata["sno"] = sno;
        }
        //验证姓名
        var re = /^[\u4e00-\u9fa5a-z]+$/gi;//只能输入汉字和英文字母
        var name = $("input[id='name']").val();
        //console.log(typeof(name))
        //console.log(name);
        //console.log(name.length);
        //姓名不为空
        if(name==""){
          alert("请输入姓名");
          return false;
        }
        if(!re.test(name)){      
          alert("姓名中包含特殊字符");
          return false;
        }
        else{
          formdata["name"] = name;
        }

        //验证科目 只能输入汉字和英文字母
        var subject = $("input:radio[name='optionsRadiosinline2']:checked").val();
        console.log(subject);
        //科目不为空
        /*if(subject==""){
          alert("请输入科目");
          return false;
        }
        var re = /^[\u4e00-\u9fa5a-z]+$/gi;//只能输入汉字和英文字母
        //科目为为中文或者英文
        if(!re.test(subject)){      
          alert("请输入合理科目");
          return false;
        }*/
        if(subject==null){
              alert("请选择科目");
              return false;
        }
        else
        {
          formdata["subject"] = subject;
        }
        //验证成绩
        var grade = $("input[id='grade']").val();
        //成绩不为空
        if(grade==""){
          alert("请输入成绩");
          return false;
        }
        var gradenum = parseInt(grade);
        if(gradenum>100||gradenum<0)
        {
          alert("无效成绩（请输入0-100之间的数字）");
          return false;
        }
        else
        {
          formdata["grade"] = grade;
        }
        console.log(gradenum);
        //alert("递交成功");
        return true;
      }
      //修改
     var tdnode;
     function modify(obj){
         isstuex = 1;
        alert("请在下方表单处做修改");
        var modifydata = new Array();
        tdnode = $(obj).parent().parent().children();
        console.log(tdnode.length);
        //获取要修改的那一行的值
        for(var i=0;i<tdnode.length-1;i++){
            console.log(tdnode[i].innerHTML)
            modifydata[i] = tdnode[i].innerHTML
        }
        //填充到下方表单


        $("input:text[id='sno']").val(modifydata[3]);
         $("input:text[id='sno']").attr("readonly",true);//将表单变为不可编辑
        $("input:text[id='name']").val(modifydata[4]);
         $("input:text[id='name']").attr("readonly",true);//将表单变为不可编辑
        $("input:radio[name='optionsRadiosinline2'][value='"+modifydata[6]+"']").prop('checked','true');
         $("input:radio[name='optionsRadiosinline2']").attr("disabled","disabled");//将表单变为不可编辑
        $("input:text[id='grade']").val(modifydata[7]);

        var modifynewdata = $("input:radio[name='optionsRadiosinline']:checked").val();
        submitflag = 2;//表示修改
         // var searchdata = {};
         // searchdata["stuNo"] = modifydata[3];
         // searchdata["subname"] = modifydata[6];
         // $.ajax({
         //     url: "/searchScoreId",
         //     type: "post",
         //     contentType: "application/json; charset=utf-8",
         //     data: JSON.stringify(searchdata),
         //     dataType: "text",//预期获取的数据格式
         //     success: function (data) {
         //         console.log(data);
         //         formid = data;
         //     }
         // })

      }
     //删除
      function deleteing(obj){
        
        var ttr = $(obj).parent().parent();
        var ttd = ttr.children()
        var deldata = {};
        deldata["stuNo"] = ttd[3].innerHTML;
        deldata["subname"] = ttd[6].innerHTML;
        if(confirm("确认删除？")){
            $.ajax({
                url: "/delet",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(deldata),
                dataType: "text",//预期获取的数据格式
                success: function (data) {
                    console.log(data);
                    $("button[name='refresh']").trigger("click");//表格刷新

                }
            })
        }

      }

      //查询
      var schway = "majorway";//默认查询方式
      $(".searchway").click(function(){
        //获取查询方式
        var searchway = $(this).text();
        schway = $(this).attr("id");
        $(".search").text(searchway);
        console.log(schway);
      })

      $(".search").click(function(){
        searnmmp();

      });
      //显示全部
      $(".searchall").click(function(){
        var hiddentr = $("tbody>tr");

        for(var i=0;i<hiddentr.length;i++){
          $(hiddentr[i]).removeAttr("style");
        }
      })

      function searnmmp(){

        //查找内容
        var searchkey = $(".form-control").val();
        //按专业
        if(schway=="majorway"){
          var alltr = $("tbody>tr");//获取所有tr
          for(var i=0;i<alltr.length;i++){
            if($(alltr[i]).children().eq(1).text()!=searchkey)
              //alltr[i].remove();
              alltr[i].style.display = "none";//隐藏不匹配内容
            else
              $(alltr[i]).removeAttr("style");//显示匹配内容
          }

        }
        //按年级
        else if(schway=="yearway"){
          var alltr = $("tbody>tr");
          for(var i=0;i<alltr.length;i++){
            if($(alltr[i]).children().eq(2).text()!=searchkey)
              alltr[i].style.display = "none";
            else
              $(alltr[i]).removeAttr("style");
          }


        }
        //按学号
        else if(schway=="snoway"){
          var alltr = $("tbody>tr");
          for(var i=0;i<alltr.length;i++){
            if($(alltr[i]).children().eq(3).text()!=searchkey)
              alltr[i].style.display = "none";
            else
              $(alltr[i]).removeAttr("style");
          }

        }
        //按姓名
        else if(schway=="nameway"){
          var alltr = $("tbody>tr");
          for(var i=0;i<alltr.length;i++){
            if($(alltr[i]).children().eq(4).text()!=searchkey)
              alltr[i].style.display = "none";
            else
              $(alltr[i]).removeAttr("style");
          }

        }
        //按科目
        else if(schway=="subway"){
          var alltr = $("tbody>tr");
          for(var i=0;i<alltr.length;i++){
            if($(alltr[i]).children().eq(5).text()!=searchkey)
              alltr[i].style.display = "none";
            else
              $(alltr[i]).removeAttr("style");
          }

        }
        else{
          alert("未查找到内容");
          //清空表
          $("tbody").empty();
        }
        


      }
      function searchName(obj) {
          var stuNo = $(obj).val();
          if(stuNo!=null){
              $.ajax({
                  url:"/searchstuName",//通过学号查找姓名，如果存在姓名自动出现在输入框，否则新增
                  type:"post",
                  contentType: "application/x-www-form-urlencoded; charset=utf-8",
                  data:{stuNo:stuNo},
                  dataType:"json",//预期获取的数据格式
                  success:function (data) {
                      console.log(data.stuName);
                      var stuname = data.stuName;
                      if(stuname!=null) {
                          $("#name").val(stuname);
                          isstuex = 1;
                      }
                      else {
                          $("#name").attr("placeholder","该用户不存在");
                          isstuex = 0;

                      }

                  }
              })

          }

      }
      $("#submit1").click(function () {

          var ischecked = checkform()
          var adddata = {};
          adddata["studentNo"] = $("#sno").val();
          adddata["subjectName"] = $("input:radio[name='optionsRadiosinline2']:checked").val();
          adddata["stuscore"] = $("#grade").val();
          adddata["scoreId"] = formid;
          var nowtime = new Date();
          adddata["addtime"] = nowtime.toLocaleDateString();
          console.log(adddata["studentNo"]);
          console.log(adddata["subjectName"]);
          console.log(adddata["addtime"]);
          console.log(isstuex)
          //添加操作
          //学生存在。新增score表
              if (isstuex == 1&&ischecked) {
                  console.log("addnewScore")
                  $.ajax({
                      url: "/addscore",
                      type: "post",
                      contentType: "application/json; charset=utf-8",
                      data: JSON.stringify(adddata),
                      dataType: "text",//预期获取的数据格式
                      success: function (data) {
                          console.log(data);
                          $("button[name='refresh']").trigger("click");
                          //window.location.reload();
                          if(submitflag==1)
                              alert("新增成功");
                          if(submitflag==2)
                              alert("修改成功");
                      }
                  })
              }
              if(isstuex==0){
                  //searchName(document.getElementById("name"));
                  alert("该用户不存在");
              }
      })
  function getGradeSum() {
      var sumNo = prompt("请输入学生学号，不输入即求所有学生总成绩 ");
      console.log(sumNo);
      if(sumNo==''){
          $.ajax({
              url: "/getAllGradeSum",
              type: "post",
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              dataType: "text",//预期获取的数据格式
              success: function (data) {
                  alert("所有学生总成绩为"+data);
              },
              error:function () {
                  console.log(sumNo);
              }
          })
      }
      else {
          //先查找学生是否存在
          $.ajax({
              url:"/searchstuName",//通过学号查找姓名，如果存在姓名自动出现在输入框，否则新增
              type:"post",
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              data:{stuNo:sumNo},
              dataType:"json",//预期获取的数据格式
              success:function (data) {
                  console.log(data.stuName);
                  var stuname = data.stuName;
                  if(stuname!=null) {
                      isstuex = 1;
                      //学生存在，获取总成绩
                      $.ajax({
                          url: "/getGradeSum",
                          type: "post",
                          contentType: "application/x-www-form-urlencoded; charset=utf-8",
                          data: {sumno: sumNo},
                          dataType: "text",//预期获取的数据格式
                          success: function (data) {
                              alert("该学生总成绩为" + data);
                          },
                          error: function () {
                              console.log(sumNo);
                          }
                      });
                  }
                  else {
                      alert("该用户不存在");
                      isstuex = 0;

                  }

              }
          })

      }

  }
  
  function getavgGrade() {
      var avgNo = prompt("请输入学生学号，不输入即求所有学生平均成绩 ");
      console.log(avgNo);
      if(avgNo==''){
          $.ajax({
              url: "/getAllGradeAvg",
              type: "post",
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              dataType: "text",//预期获取的数据格式
              success: function (data) {
                  alert("所有学生平均成绩为"+data);
              },
              error:function () {
                  console.log(avgNo);
              }
          })
      }
      else {
          //先查找学生是否存在
          $.ajax({
              url:"/searchstuName",//通过学号查找姓名，如果存在姓名自动出现在输入框，否则新增
              type:"post",
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              data:{stuNo:avgNo},
              dataType:"json",//预期获取的数据格式
              success:function (data) {
                  console.log(data.stuName);
                  var stuname = data.stuName;
                  if(stuname!=null) {
                      isstuex = 1;
                      //学生存在，获取总成绩
                      $.ajax({
                          url: "/getGradeAvg",
                          type: "post",
                          contentType: "application/x-www-form-urlencoded; charset=utf-8",
                          data: {avgno: avgNo},
                          dataType: "text",//预期获取的数据格式
                          success: function (data) {
                              alert("该学生平均成绩为" + data);
                          },
                          error: function () {
                              console.log(avgNo);
                          }
                      });
                  }
                  else {
                      alert("该用户不存在");
                      isstuex = 0;

                  }

              }
          })

      }

      
  }