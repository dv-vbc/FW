<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="${pageContext.request.contextPath}/static/avalon.js/2.0s/avalon.min.js"></script>
        <script>
            var vm1 = avalon.define({
                $id: "test",
                a: 111
            })
            vm1.$watch('a', function () {
                console.log('vm1.a change')
            })
            var vm2 = avalon.define({
                $id: 'test2',
                b: 222
            })
            vm2.$watch('b', function () {
                console.log('vm2.b change')
            })
            var vm3 = avalon.mediatorFactory(vm1, vm2)
            //这个回调其实放在vm1.$events中
            vm3.$watch('a', function () {
                console.log('vm3.a change')
            })
            //这个回调其实放在vm2.$events中
            vm3.$watch('b', function () {
                console.log('vm3.b change')
            })
            console.log('------')
            vm3.a = 22
            vm3.b = 44
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