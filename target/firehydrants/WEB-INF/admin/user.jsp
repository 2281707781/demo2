<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2020/5/17
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户添加界面</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/admin/uploads" method="post" enctype="multipart/form-data">
        <input type="file" name="fileUpload" />
        <input type="submit" value="上传文件" />
    </form>
</body>
</html>
