<html>
<head>
    <title>gradeList</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!--bootstraptable组件以及中文包-->
    <script src="js/bootstrap-table.min.js"></script>
    <link href="css/bootstrap-table.min.css" rel="stylesheet" />
    <script src="js/bootstrap-table-zh-CN.min.js"></script>
    <style type="text/css">
        #gradetable{
            margin: 2%;
        }
        .checkboxdiv{
            float: left;
        }
        p{
            float: left;
            margin-right:10px;
        }
        label{
            float: left;
            margin-left:10px;
            margin-right:10px;
        }

        #year span{
            float: left;

        }
        input[type='radio']{
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div id="gradetable">

    <table class="table" id="stutable"></table>
</div>

<button type="button" class="btn btn-primary" style="float:right;margin-right:2%" onclick="cleanFrom();" >新增</button>
<button type="button" class="btn btn-primary" style="float:right;margin-right:2%" onclick="getGradeSum()" >获取总成绩</button>
<button type="button" class="btn btn-primary" style="float:right;margin-right:2%" onclick="getavgGrade()" >获取平均成绩</button>
<form class="bs-example bs-example-form" role="form">
    <div class="row">
        <div class="col-lg-6">
            <div class="input-group">
                <div class="input-group-btn">
                    <button type="button" class="btn btn-default search" tabindex="-1">按专业查询</button>
                    <button type="button" class="btn btn-default searchall" tabindex="-1">查看全部</button>
                    <button type="button" class="btn btn-default
                            dropdown-toggle" data-toggle="dropdown" tabindex="-1">
                        <span class="caret"></span>
                        <span class="sr-only">切换查询</span>
                    </button>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#" class="searchway" id="majorway">按专业查询</a>
                        </li>
                        <li>
                            <a href="#" class="searchway" id="yearway">按年级查询</a>
                        </li>
                        <li>
                            <a href="#" class="searchway" id="snoway">按学号查询</a>
                        </li>
                        <li>
                            <a href="#" class="searchway" id="nameway">按姓名查询</a>
                        </li>
                        <li>
                            <a href="#" class="searchway" id="subway">按科目查询</a>
                        </li>
                    </ul>
                </div><!-- /btn-group -->
                <input type="text" class="form-control" id="search_text">
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->
    </div>
</form>
<br>
<br>
<div class="inputtitle">
    <h1 class="display-5">学生信息录入</h1>
    <hr class="my-4">
</div>
<div id="gradeformdiv">
    <from id="gradeform" method="post">
        <div id="major" style="display: none;">
            <p>专业：</p>
            <span style="margin-left:30px;"></span>   <!--万能空格-->
            <label class="radio-inline">计算机：<input type="radio" name="optionsRadiosinline" id="computer" value="计算机"></label>
            <label class="radio-inline">物联网：<input type="radio" name="optionsRadiosinline" id="wlw" value="物联网"></label>
            <label class="radio-inline">通信：<input type="radio" name="optionsRadiosinline" id="tx" value="通信"></label>
        </div>
        <br>
        <br>
        <div id="year" style="display: none;">
            <p>年级：</p>
            <span style="margin-left:30px;"></span>   <!--万能空格-->
            <div class="input-group-prepend">
                <button class="btn btn-outline-secondary dropdown-toggle yearchoosed" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">2016一班</button>
                <div class="dropdown-menu">
                    <a class="dropdown-item yearchoose" href="#">2016一班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2016二班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2016三班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2017一班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2017二班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2017三班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2018一班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2018二班</a>
                    <div role="separator" class="dropdown-divider"></div>
                    <a class="dropdown-item yearchoose" href="#">2018三班</a>
                    <div role="separator" class="dropdown-divider"></div>
                </div>
            </div>
            </p>
        </div>
        <br>
        <div id="snodiv"><p>学号：</p><input type="text" class="form-control" id="sno" placeholder="请输入学号" onblur="searchName(this)"></div>
        <br>
        <div id="sname"><p>姓名：</p><input type="text" class="form-control" id="name" placeholder="请输入姓名"></div>
        <br>
        <div id="sex" style="display: none;">
            <p>性别:</p>
            <span style="margin-left:50px;"></span>   <!--万能空格-->
            <label><input type="radio" name="optionsRadios" id="men" value="男">男</label>
            <label><input type="radio" name="optionsRadios" id="women" value="女">女</label>
        </div>
        <br>
        <br>
        <div id="subject">
            <p>科目:</p>
            <label class="radio-inline">高等数学<input type="radio" name="optionsRadiosinline2" id="Math" value="高等数学"></label>
            <label class="radio-inline">大学英语<input type="radio" name="optionsRadiosinline2" id="Eng" value="大学英语"></label>
            <label class="radio-inline">Java语言程序设计<input type="radio" name="optionsRadiosinline2" id="Java" value="Java语言程序设计"></label>
        </div>
        <br>
        <br>
        <div id="gradediv"><p>成绩:</p><input type="text" class="form-control" id="grade" placeholder="请输入成绩"></div>
        <br>
        <button type="button" class="btn btn-primary" style="float:left;margin-right:2%" id="submit1">提交</button>
    </from>
</div>
<script src="js/gradelist.js"></script>
<script type="text/javascript">
    $(function () {

        //1.初始化Table
        var oTable = new TableInit();
        oTable.Init();

        //2.初始化Button的点击事件
        var oButtonInit = new ButtonInit();
        oButtonInit.Init();

    });
    function tableHeight(){
        //可以根据自己页面情况进行调整
        return $(window).height() -280;
    }


    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#stutable').bootstrapTable({
                method: 'post',
                contentType: "application/x-www-form-urlencoded",//必须要有！！！！
                url:"/student",//要请求数据的文件路径
                toolbar: '#toolbar',//指定工具栏
                striped: true, //是否显示行间隔色
                height:tableHeight(),//高度调整
                dataType:"json",//bootstrap table 可以前端分页也可以后端分页，这里
                //我们使用的是后端分页，后端分页时需返回含有total：总记录数,这个键值好像是固定的
                //rows： 记录集合 键值可以修改  dataField 自己定义成自己想要的就好
                smartDisplay:false,
                pageNumber: 1, //初始化加载第一页，默认第一页
                pagination:true,//是否分页
                queryParams:oTableInit.queryParams,//请求服务器时所传的参数
                sidePagination:'server',//指定服务器端分页
                pageSize:10,//单页记录数
                pageList:[3,10,20,30],//分页步进值
                showRefresh:true,//刷新按钮
                showColumns:true,
                clickToSelect: true,//是否启用点击选中行
                toolbarAlign:'right',//工具栏对齐方式
                buttonsAlign:'right',//按钮对齐方式
                toolbar:'#toolbar',//指定工作栏
                columns: [{
                    checkbox: true
                }, {
                    field: 'specialty',
                    title: '专业'
                }, {
                    field: 'grade',
                    title: '年级'
                }, {
                    field: 'studentNo',
                    title: '学号'
                }, {
                    field: 'studentname',
                    title: '姓名'
                }, {
                    field: 'studentsex',
                    title: '性别'
                }, {
                    field: 'subjectName',
                    title: '科目'
                }, {
                    field: 'studentScore',
                    title: '成绩'
                }, {
                    field: 'peration',
                    title: '操作',
                    width: 120,
                    align: 'center',
                    valign: 'middle',
                    formatter: actionFormatter
                },
                ],
                locale:'zh-CN',//中文支持,
                responseHandler: function (res) {
                    //如果没有错误则返回数据，渲染表格
                    console.log(res);
                    return res;
                },
            });
            //操作栏的格式化
            function actionFormatter(value, row, index) {
                var id = value;
                var result = "";
                result += "<a href='javascript:;'  onclick=\"modify(this)\" title='编辑'><span class='glyphicon glyphicon-pencil'>编辑</span></a>";
                result += "<a href='javascript:;'  onclick=\"deleteing(this)\" title='删除'><span class='glyphicon glyphicon-remove'>删除</span></a>";
                return result;
            }
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {
            var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                //每页多少条数据
                pageSize: params.limit,
                //请求第几页
                pageIndex:params.pageNumber,
                //searchtext: $("#search_text").val(),
                //searchkey: $(".search").text()
            };
            return temp;
        };
        return oTableInit;
    };


    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};

        oInit.Init = function () {
            //初始化页面上面的按钮事件
        };

        return oInit;
    };
</script>

</body>
</html>