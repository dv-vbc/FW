<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8"> 
        <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
        <title>Bootstrap 模板</title>
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon" />
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="shortcut icon" />
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="bookmark" />
        <!-- 引入 Bootstrap -->
        <link href="${pageContext.request.contextPath}/static/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
        <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
        <!--[if lt IE 9]>
           <script src="${pageContext.request.contextPath}/static/html5shiv/r29/html5.min.js"></script>
           <script src="${pageContext.request.contextPath}/static/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
        <script src="${pageContext.request.contextPath}/static/jquery/1.12.4/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/static/tether/1.4.0/js/tether.min.js"></script>
        <!-- 包括所有已编译的插件 -->
        <script src="${pageContext.request.contextPath}/static/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
    </head>
    <body>
        <h1>Hello World!</h1>

        <div><a href="${pageContext.request.contextPath}/kb/wechat/setup/fb.html">fb兼容问题</a></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </body>
</html>

