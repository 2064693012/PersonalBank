
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>江江银行个人网上银行系统</title>
    <link href="css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css?v=2.2.0" rel="stylesheet">
</head>

<body class="fixed-navigation">
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <!-- Sidebar content goes here -->
                <li class="nav-header">
                    <div class="dropdown profile-element">
            <span>
              <img alt="image" class="img-circle" src="img/a5.jpg" />
            </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="main.jsp">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${depositor.name}</strong>
                             </span>  <span class="text-muted text-xs block">用户 <b class="caret"></b></span> </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="form_avatar.html">修改头像</a>
                            </li>
                            <li><a href="profile.html">个人资料</a>
                            </li>
                            <li><a href="contacts.html">联系我们</a>
                            </li>
                            <li><a href="mailbox.html">信箱</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="index.jsp">安全退出</a>
                            </li>
                        </ul>
                    </div>
                    <div class="logo-element">
                        JJ
                    </div>

                </li>
                <li class="active">
                    <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">主页</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li class="bg0"><a href="${pageContext.request.contextPath}/service?method=findAllOfInfo">个人信息</a></li>
                        <li class="bg2"><a href="${pageContext.request.contextPath}/service?method=findAccount">财富总览</a></li>
                        <li class="bg4"><a href="${pageContext.request.contextPath}/service?method=aboutUs">关于我们</a></li>
                        </li>
                    </ul>
                </li>

                <li class="active">
                    <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">业务办理</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="${pageContext.request.contextPath}/business?method=deposit">存款</a></li>
                        <li><a href="${pageContext.request.contextPath}/business?method=withdrawal">取款</a></li>
                        <li><a href="${pageContext.request.contextPath}/business?method=transfer">转账</a></li>
                        <li class="active"><a href="${pageContext.request.contextPath}/business?method=fixedDeposit">定期存款</a></li>
                        <li><a href="${pageContext.request.contextPath}/business?method=currentDeposit">活期存款</a></li>
                        <li><a href="${pageContext.request.contextPath}/fixedDeposit">活期定期记录</a></li>
                        <li><a href="${pageContext.request.contextPath}/business?method=transferLog">交易明细</a></li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">账号维护</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="${pageContext.request.contextPath}/modify?method=modifyInfo">修改个人信息</a></li>
                        <li><a href="${pageContext.request.contextPath}/modify?method=modifyPassword">修改密码</a></li>
                        <li><a href="${pageContext.request.contextPath}/modify?method=resetPassword">重置密码</a></li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">生活缴费</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="${pageContext.request.contextPath}/payment?method=gas">天然气费</a></li>
                        <li><a href="${pageContext.request.contextPath}/payment?method=waterAndElectricity">水电费</a></li>
                    </ul>
                </li>

                <li><a href="${pageContext.request.contextPath}/log?page=1&cardID=${depositor.cardid}">查看日志</a></li>
            </ul>
        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <!-- Top navigation bar content goes here -->
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message"><a href="index.html" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用江江银行</span>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/logout?cardId=${depositor.cardid}">
                            <i class="fa fa-sign-out"></i> 退出
                        </a>
                    </li>


                </ul>
            </nav>
        </div>

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <ol class="breadcrumb">
                    <!-- Breadcrumb content goes here -->
                    <li>
                        <a href="main.jsp">主页</a>
                    </li>
                    <li class="active">
                        <strong>定期存款</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">
                JJ
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content text-center p-md">
                            <h2><span class="text-navy"> <h1>添加定期存款</h1></span></h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="wrapper wrapper-content animated fadeInRight">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content text-center p-md">
                                <form action="${pageContext.request.contextPath}/fixedDeposit?action=add" method="post">
                                    <label for="cardID">银行卡号:</label>
                                    <input type="text" id="cardID" name="cardID" placeholder="" value="${depositor.cardid}" readonly/>
                                    <br>
                                    <label for="depositAmount">转入金额:</label>
                                    <input type="text" id="depositAmount" name="depositAmount" required>
                                    <br>
                                    <label for="startDate">开始时间:</label>
                                    <input type="date" id="startDate" name="startDate" required>
                                    <br>
                                    <label for="timeDiff">存款时间:</label>
                                    <select id="timeDiff" name="timeDiff" required>
                                        <option value="1">1年利率1.75%</option>
                                        <option value="2">2年利率2.25%</option>
                                        <option value="3">3年利率2.75%</option>
                                        <option value="5">5年利率2.75%</option>
                                    </select>
                                    <br>
                                    <br>
                                    <label for="endDate">结束时间:</label>
                                    <input type="date" id="endDate" name="endDate" readonly>
                                    <br>
                                    <label for="depositType">类型:</label>
                                    <input type="text" id="depositType" name="depositType" placeholder="" value="定期" readonly>
                                    <br>
                                    <label for="interestRate">年利率:</label>
                                    <input type="text" id="interestRate" name="interestRate" placeholder="" value="" readonly>
                                    <br>
                                    <label for="maturityAmount">本金+利息:</label>
                                    <input type="text" id="maturityAmount" name="maturityAmount" placeholder="" value="" readonly>
                                    <br>
                                    <input type="submit" value="存款">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>




        </div>
        <div class="footer">
            <div class="pull-right">
                By：<a href="#" target="_blank">江江</a>
            </div>
            <div>
                <strong>江江</strong> JJ &copy; 2023
            </div>
        </div>
    </div>
</div>


<!-- Mainly scripts -->

<script>
    // 获取开始时间、存款时间、金额、年利率和本息+利息输入框
    var startDateInput = document.getElementById("startDate");
    var timeDiffSelect = document.getElementById("timeDiff");
    var endDateInput = document.getElementById("endDate");
    var depositAmountInput = document.getElementById("depositAmount");
    var interestRateInput = document.getElementById("interestRate");
    var maturityAmountInput = document.getElementById("maturityAmount");

    // 监听存款时间选择框的变化
    timeDiffSelect.addEventListener("change", calculateEndDateAndInterestRate);

    // 定义计算结束时间和年利率的函数
    function calculateEndDateAndInterestRate() {
        // 获取开始时间、存款时间和金额的值
        var startDate = new Date(startDateInput.value);
        var timeDiff = parseInt(timeDiffSelect.value);
        var depositAmount = parseFloat(depositAmountInput.value);

        // 计算结束时间
        var endDate = new Date(startDate);
        endDate.setFullYear(startDate.getFullYear() + timeDiff);
        endDateInput.value = endDate.toISOString().split("T")[0];

        // 根据不同的存款时间设置对应的年利率
        if (timeDiff == 1) {
            interestRateInput.value = "1.75";
        } else if (timeDiff == 2) {
            interestRateInput.value = "2.25";
        } else if (timeDiff == 3 || timeDiff == 5) {
            interestRateInput.value = "2.75";
        }

        // 计算本息+利息的值
        var maturityAmount = depositAmount + depositAmount * timeDiff * parseFloat(interestRateInput.value) / 100;
        maturityAmountInput.value = maturityAmount.toFixed(2);
    }

    // 初始时计算一次结束时间和年利率
    calculateEndDateAndInterestRate();
</script>


<script src="js/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js?v=3.4.0"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Flot -->
<script src="js/plugins/flot/jquery.flot.js"></script>
<script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/plugins/flot/jquery.flot.spline.js"></script>
<script src="js/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/plugins/flot/jquery.flot.pie.js"></script>
<script src="js/plugins/flot/jquery.flot.symbol.js"></script>
<script src="js/plugins/flot/curvedLines.js"></script>

<!-- Peity -->
<script src="js/plugins/peity/jquery.peity.min.js"></script>
<script src="js/demo/peity-demo.js"></script>

<!-- Custom and plugin javascript -->
<script src="js/hplus.js?v=2.2.0"></script>
<script src="js/plugins/pace/pace.min.js"></script>

<!-- jQuery UI -->
<script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- Jvectormap -->
<script src="js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>

<!-- EayPIE -->
<script src="js/plugins/easypiechart/jquery.easypiechart.js"></script>

<!-- Sparkline -->
<script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>

<!-- Sparkline demo data  -->
<script src="js/demo/sparkline-demo.js"></script>

<script>
    $(document).ready(function () {
        $('.chart').easyPieChart({
            barColor: '#f8ac59',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        $('.chart2').easyPieChart({
            barColor: '#1c84c6',
            //                scaleColor: false,
            scaleLength: 5,
            lineWidth: 4,
            size: 80
        });

        var d1 = [
            [1262304000000, 1],
            [1264982400000, 100],
            [1267401600000, 1],
            [1270080000000, 200],
            [1272672000000, 1],
            [1275350400000, 100],
            [1277942400000, 1],
            [1280620800000, 300]
        ];
        var d2 = [
            [1262304000000, 100],
            [1264982400000, 1],
            [1267401600000, 150],
            [1270080000000, 1],
            [1272672000000, 230],
            [1275350400000, 1],
            [1277942400000, 150],
            [1280620800000, 10]
        ];

        var data3 = [
            {
                label: "Data 1",
                data: d1,
                color: '#23c6c8'
            },
            {
                label: "Data 2",
                data: d2,
                color: '#1ab394'
            }
        ];
        $.plot($("#flot-chart3"), data3, {
            xaxis: {
                tickDecimals: 0
            },
            series: {
                lines: {
                    show: true,
                    fill: true,
                    fillColor: {
                        colors: [{
                            opacity: 1
                        }, {
                            opacity: 1
                        }]
                    },
                },
                curvedLines: {
                    active: true,
                    fit: true,
                    apply: true
                },
                points: {
                    width: 0.1,
                    show: false
                },
            },
            grid: {
                show: false,
                borderWidth: 0
            },
            legend: {
                show: false,
            }
        });


        var mapData = {
            "US": 298,
            "SA": 200,
            "DE": 220,
            "FR": 540,
            "CN": 120,
            "AU": 760,
            "BR": 550,
            "IN": 200,
            "GB": 120,
        };

        $('#world-map').vectorMap({
            map: 'world_mill_en',
            backgroundColor: "transparent",
            regionStyle: {
                initial: {
                    fill: '#e4e4e4',
                    "fill-opacity": 0.9,
                    stroke: 'none',
                    "stroke-width": 0,
                    "stroke-opacity": 0
                }
            },

            series: {
                regions: [{
                    values: mapData,
                    scale: ["#1ab394", "#22d6b1"],
                    normalizeFunction: 'polynomial'
                }]
            },
        });
    });
</script>

</body>

</html>
