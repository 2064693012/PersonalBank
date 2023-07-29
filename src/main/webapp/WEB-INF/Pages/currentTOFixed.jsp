<%--
  Created by IntelliJ IDEA.
  User: Fault
  Date: 2023/7/25
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/fixedDeposit?action=transfer" method="post">
  <label for="transferType">转账类型:</label>
  <select id="transferType" name="transferType">
    <option value="toFixed">活期转定期</option>
    <option value="toCurrent">定期转活期</option>
  </select>
  <br>
  <label for="timeDiff">存款时间:</label>
  <select id="timeDiff" name="timeDiff">
    <option value="1">1年</option>
    <option value="2">2年</option>
    <option value="3">3年</option>
    <option value="5">5年</option>
  </select>
  <br>
  <input type="submit" value="确认转账">
</form>

</body>
</html>
