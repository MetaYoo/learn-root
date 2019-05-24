var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
})

layui.use(['element', 'layer', 'form', 'table','laypage'], function () {
    var element = layui.element;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var laypage = layui.laypage;

    //第一个实例
    table.render({
        elem: '#demo'
        , height: 312
        , url: '/demo/users/' //数据接口
        , page: true //开启分页
        , cols: [[ //表头
            {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
            , {field: 'username', title: '用户名', width: 80}
            , {field: 'sex', title: '性别', width: 80, sort: true}
            , {field: 'city', title: '城市', width: 80}
            , {field: 'sign', title: '签名', width: 177}
            , {field: 'experience', title: '积分', width: 80, sort: true}
            , {field: 'score', title: '评分', width: 80, sort: true}
            , {field: 'classify', title: '职业', width: 80}
            , {field: 'wealth', title: '财富', width: 135, sort: true}
        ]]
    });

    //执行一个laypage实例
    // laypage.render({
    //     elem: 'demo' //注意，这里的 test1 是 ID，不用加 # 号
    //     ,count: 50 //数据总数，从服务端得到
    // });

});