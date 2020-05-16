/**
 * Created by DELL on 2019/11/15.
 */
$(function(){
    <!--登录-->
    $("#login").on("click", function () {
        window.location.href = "http://localhost:8090/general/login";
    });
});
$(function(){
    <!--注册-->
    $("#register").on("click", function () {
        window.location.href = "http://localhost:8090/general/getotp";
    });
});
/*$("#search").on("click",function(){
    var goodsname = $("#goodsname").val();
    if(goodsname ==null||goodsname==""){
        alert("搜索商品名不能为空");
        return false;
    }
    $.ajax({
        type:"POST",
        url:"http://localhost:8090/goods/getsearchGoods",
        contentType:"application/x-www-form-urlencoded",
        data:{
            "goodsname":goodsname,
        },
        xhrFields:{withCredentials:true},
        success:function(data){
            if(data.status == "success"){
                alert("搜索成功");
            }else{
                alert("搜索失败");
            }
        },
        error:function(data){
            alert("搜索失败");
        }
    });
    return false;
});*/



    /*$.ajax({
        type:"GET",
        url:"http://localhost:8090/goods/getAllGoods",
        dateType:"json",
        //跨域请求共享
        xhrFields:{withCredentials:true},

        success:function(data){
            if(data.status == "success"){
                console.log(data.data);
                alert("获取成功")
                list(data.data);
            }else{
                alert("获取商品信息失败");
            }
        },
        error:function(data){
            alert("获取商品信息失败");
        }
    });
function list(data){
	var str = "";
		str += '<div class="container-fluid">';
        str += '<div class="row">';
    for(var i =0;i<data.length;i++){
        str += '<div class=" col-md-3 " >';
        str += '<div class="thumbnail">';
        str += '<img src="' + data[i].image+'" alt="商品图片">';
        str += '<div class="caption">';
        str += '<h3>' + data[i].goodsname+'</h3>'
        str += '<p>' + data[i].price+'</p>';
        str += '<p><a href="localhost:8090/goods/selectonegoods?id=' + data[i].id+'" class="btn btn-primary" role="button">查看详情</a></p>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
        str += '</div>';
	}
    $("#goodslist").html(str);
}*/