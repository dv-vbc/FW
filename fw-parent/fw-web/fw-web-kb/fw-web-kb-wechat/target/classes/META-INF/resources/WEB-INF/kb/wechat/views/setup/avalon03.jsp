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
                aaa: 111,
                ddd: 444
            })
            var vm2 = avalon.define({
                $id: "test2",
                ddd: 555
            })
            var vm3 = avalon.define({
                $id: "test3",
                aaa: 333
            })
        </script>
    </head>
    <body ms-controller="test">
        <p>{{@aaa}}</p>
        <div ms-controller="test2">
            {{@aaa}}::{{@ddd}}
        </div>
        <div ms-important="test3">
            {{@aaa}}::{{@ddd}}
        </div>
    </body>
</html>