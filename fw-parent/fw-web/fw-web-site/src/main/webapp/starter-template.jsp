<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <!--bootstrap不支持IE兼容模式，为了让IE浏览器运行最新的渲染模式，将添加以下标签在页面中-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
        <title>Bootstrap, from Twitter</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- Le styles -->
        <link href="${pageContext.request.contextPath}/static/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
            }
        </style>
        <link href="${pageContext.request.contextPath}/static/bootstrap/2.3.2/css/bootstrap-responsive.min.css" rel="stylesheet">
        <!-- bsie css -->
        <!--[if lte IE 6]>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bsie/1.05/css/bootstrap-ie6.css" />
        <![endif]-->
        <!--[if lte IE 7]>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bsie/1.05/css/ie.css" />
        <![endif]-->
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="${pageContext.request.contextPath}/static/html5shiv/r29/html5shiv.min.js"></script>
        <![endif]-->

        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${pageContext.request.contextPath}/img/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${pageContext.request.contextPath}/img/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${pageContext.request.contextPath}/img/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/img/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.png">
        <!--
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="icon" />
        <link href="${pageContext.request.contextPath}/img/favicon.ico" rel="bookmark" />
        -->
    </head>

    <body>

        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="#">Project name</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="#about">About</a></li>
                            <li><a href="#contact">Contact</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>

        <div class="container">

            <h1>Bootstrap starter template</h1>
            <p>Use this document as a way to quick start any new project.<br> All you get is this message and a barebones HTML document.</p>

        </div> <!-- /container -->

        <!-- Le javascript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/1.12.4/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/2.3.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/bsie/1.05/js/bootstrap-ie.js"></script>

    </body>
</html>

