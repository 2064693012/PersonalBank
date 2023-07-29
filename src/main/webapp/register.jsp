<%--
  Created by IntelliJ IDEA.
  User: Fault
  Date: 2023/7/22
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>注册</title>
    <link type="text/css" rel="stylesheet" href="css/zhuce.css" />
    <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var height=$(document).height();
            $('.main').css('height',height);
        })
    </script>
</head>

<body>
<div class="main">
    <div class="main0">
        <div class= "software_title blue">
            江江银行系统
        </div>
        <hr>
        <div class="main_left">
            <img src="images/zhuce-image-3.png" class="theimg"/>
            <img src="images/zhuce-image-2.png" class="secimg"/>
            <img src="images/zhuce-image-1.png" class="firimg"/>
        </div>
        <div class="main_right">

            <div class="sub"><p>已经注册？<a href="index.jsp"><span class="blue">请登录</span></a></p></div>
            <div class="container">
                <h2>用户注册</h2>
                <form action="${pageContext.request.contextPath}/manage" method="post">
                    <label for="username">用户名:</label>
                    <input type="text" id="username" name="username" required>
                    <br>

                    <label for="password">密码:</label>
                    <input type="password" id="password" name="password" required>
                    <br>

                    <label for="name">姓名:</label>
                    <input type="text" id="name" name="name" required>
                    <br>

                    <label for="tel">电话:</label>
                    <input type="text" id="tel" name="tel" required>
                    <br>

                    <label for="cardId">身份证号:</label>
                    <input type="text" id="cardId" name="cardId" required>
                    <br>

                    <label for="pid">户口所在地:</label>
                    <input type="text" id="pid" name="pid" required>
                    <br>

                    <label>性别:</label>
                    <input type="radio" id="male" name="gender" value="男" required>
                    <label for="male">男</label>
                    <input type="radio" id="female" name="gender" value="女" required>
                    <label for="female">女</label>
                    <br>

                    <input type="hidden" name="method" value="add"> <!-- 隐藏域，用于指定调用的方法 -->

                    <input type="submit" value="注册">
                </form>
            </div>

        </div>
    </div>
</div>

<div class="footer">
    <div class="footer0">
        <div class="footer_l">使用条款 | 隐私保护</div>
        <div class="footer_r">© 2023 江江银行系统 </div>
    </div>
</div>
</body>
</html>
