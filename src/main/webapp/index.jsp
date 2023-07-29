
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>登录</title>
  <link type="text/css" rel="stylesheet" href="css/login.css" />
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript">
      $(document).ready(function () {
          var height=$(document).height();
          $('.main').css('height',height);
      })
  </script>

  <script>

      //取出传回来的参数error并与yes比较
      var errori ='<%=request.getParameter("error")%>';
      if(errori=='yes'){
          alert("登录失败,用户名或者密码错误!");
      }
  </script>

</head>

<body>
<div class="main">
  <div class="main0">
    <div class= "software_title blue">
      江江个人网上银行系统
    </div>
    <hr>
    <div class="main_left">
      <img src="images/login-image-3.png" class="theimg"/>
      <img src="images/login-image-2.png" class="secimg"/>
      <img src="images/login-image-1.png" class="firimg"/>
    </div>
    <div class="main_right">
      <div class="main_r_up">
        <img src="images/user.png" />
        <div class="pp">登录</div>
      </div>
      <div class="sub"><p>还没有账号？<a href="register.jsp"><span class="blue">立即注册</span></a></p></div>
      <form action="${pageContext.request.contextPath}/login" method="post">
      <div class="txt">
        <span style="letter-spacing:8px;">用户名:</span>
        <input name="username" type="text" class="txtphone" placeholder="请输入注册手机号或管理员名称"/>
      </div>
      <div class="txt">
        <span style="letter-spacing:4px;">登录密码:</span>
        <input name="password" type="password" class="txtphone" placeholder="请输入登录密码"/>
      </div>

      <div class="xieyi">
         <select name="user">
            <option value ="depositor">用户</option>
            <option value ="admin">管理员</option>
        </select>
        <a href="password.html"><span class="blue" style=" padding-left:100px; cursor:pointer">忘记密码?</span></a>            </div>
      <input class="xiayibu button" type="submit" value="登录">
      </form>
    </div>
  </div>
</div>

<div class="footer">
  <div class="footer0">
    <div class="footer_l">使用条款 | 隐私保护</div>
    <div class="footer_r">© 2023 江江 银行系统 </div>
  </div>
</div>
</body>
</html>
