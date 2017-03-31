<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="${pageContext.request.contextPath}/static/avalon.js/2.0s/avalon.min.js"></script>
        <script>
            var vm = avalon.define({
                $id: "test",
                a: '${pageContext.request.contextPath}'
            });
        </script>
        <style>
            .ms-controller{
                display:none;
            }
        </style>
    </head>
    <body>
        <div ms-controller="test">
            <input ms-duplex="@a" />
            <p>{{@a}}</p>
        </div>
    </body>
</html>
