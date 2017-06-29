<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<head>  
<meta charset="utf-8">  
<title>维修报表</title>  
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<link href="../css/font-awesome.min.css" rel="stylesheet">
<script src="../js/esl.js"></script>  
<script src="../js/jquery.min.js"></script>  
</head>  
<body>  
<div class="row">
		<div class="col-lg-12">
			<ol class="breadcrumb">
				<li><a href="home.jsp">主页</a></li>
				<li class="active">报表管理</li>
				<li class="active">投诉信息报表</li>
			</ol>
		</div>
	</div>
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->  
    <div id="main1" style="height:400px"></div>  
    <button type="button" id="button1">显示/隐藏</button>  
    <script type="text/javascript">  
        $(function() {  
            $("#button1").click(function() {  
                $("#main1").slideToggle(999);  
            });  
        });  
        var complaint = new Array();  
        var complaintCount = new Array();  
        $.ajax({  
            url : 'Complaint.action',  
            type : 'GET',  
            dataType : 'json',  
            async : false,  
            success : function(jsonArray) {  
                for (x in jsonArray[0]) {  
                    complaint[x] = jsonArray[0][x];  
                }  
                for (x in jsonArray[0]) {  
                    complaintCount[x] = jsonArray[1][x];  
                }  
            }  
        });  
        // 路径配置  
        require.config({  
            paths : {  
                'echarts' : 'http://echarts.baidu.com/build/echarts',  
                'echarts/chart/bar' : 'http://echarts.baidu.com/build/echarts'  
            }  
        });  
  
        // 使用  
        require([ 'echarts', 'echarts/chart/line' // 使用柱状图就加载bar模块，按需加载  
        ], function(ec) {  
            // 基于准备好的dom，初始化echarts图表  
            var myChart = ec.init(document.getElementById('main1'));  
  
            var option = {  
                title : {  
                    text : '投诉用户与投诉次数对比',  
                },  
                tooltip : {  
                    trigger : 'axis'  
                },  
                legend : {  
                    data : ['投诉次数'] 
                },  
                toolbox : {  
                    show : true,  
                    feature : {  
                        mark : {  
                            show : true  
                        },  
                        dataView : {  
                            show : true,  
                            readOnly : false  
                        },  
                        magicType : {  
                            show : true,  
                            type : [ 'line', 'bar' ]  
                        },  
                        restore : {  
                            show : true  
                        },  
                        saveAsImage : {  
                            show : true  
                        }  
                    }  
                },  
                calculable : true,  
                xAxis : [ {  
                    type : 'category',  
                    data : complaint ,
                } ],  
                yAxis : [ {  
                    type : 'value'  
                } ],  
                series : [{  
                    name : '投诉次数',  
                    type : 'line',  
                    data : complaintCount,  
                    markPoint : {  
                        data : [ {  
                            type : 'max',  
                            name : '最大值'  
                        }, {  
                            type : 'min',  
                            name : '最小值'  
                        } ]  
                    },  
                    markLine : {  
                        data : [ {  
                            type : 'average',  
                            name : '平均值'  
                        } ]  
                    }  
                }]  
            };  
  
            // 为echarts对象加载数据   
            myChart.setOption(option);  
        });  
    </script>  
</body>  