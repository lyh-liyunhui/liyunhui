/**
 * Created by DELL on 2019/12/3.
 */
<!--单选-->
$(function(){
$(".check1").click(function() {
   /* var totalPrice=0;*/
    var check = $(".check1").length;
    var checked = $(".check1:checked").length;
    if (check == checked) {
        $(".selectAll").prop("checked", true);
       /* $(".sum").each(function(){
            /!*var s=$(".sum").html();*!/
            totalPrice+=Number($(this).html());
        });*/
    } else {
        $(".selectAll").prop("checked", false);
    }
    allPrice();
    checkNum();
   /* $("#totalPrice").html(totalPrice);*/
});
});
<!--全选-->
$(function(){
    $(".selectAll").on("click", function () {
   /*     var totalPrice=0;*/
       if(this.checked==true){
           $(".check1").prop("checked",true);
          /* $(".sum").each(function(){
               /!*var s=$(".sum").html();*!/
               totalPrice+=Number($(this).html());
           });*/
       }else{
           $(".check1").prop("checked",false);
       }
        allPrice();
        checkNum();
       /* $("#totalPrice").html(totalPrice);*/
    });
});
<!--计算+小计价格-->
$(function(){
    $(".adder").on("click", function () {
        var num=$(this).prev().html();
        $(this).prev().html(Number(num)+1);
        var buynum=$(this).prev().html();
        var price=$(this).parent().prev().children().html();
        $(this).parent().next().children().html(parseFloat(buynum*price).toFixed(2));
        allPrice();
        checkNum();
    });
});
<!--计算-小计价格-->
$(function(){
    $(".miner").on("click", function () {
        var num=$(this).next().html();
        if(num>1){
            $(this).next().html(Number(num)-1);
            var buynum=$(this).next().html();
            var price=$(this).parent().prev().children().html();
            $(this).parent().next().children().html(parseFloat(buynum*price).toFixed(2));
        }else {
            alert("数量不能小于0");
        }
        allPrice();
        checkNum();
    });
});
function checkNum(){
    var num=0;
    $(".check1").each(function(){
        if(this.checked==true){
            var b=$($(this).parent().next().next().next().next().children()[1]).html();
            num+=Number(b);
        }
    });
    $(".fontColor1").html(num);
}
function allPrice(){
    var totalPrice=0;
    $(".check1").each(function(){
        if(this.checked==true){
            var a=$($(this).parent().next().next().next().next().next().children()[0]).html();    //单类商品数量
              /*console.log($($(this).parent().next().next().next().next().next().children().children()[0]).html());*/
            totalPrice+=Number(a);
        }
    });
    $(".totalPrice").html(totalPrice);
}



$(function(){
    $(".del").on("click", function () {
        var goodsId=$(this).parent().next().val();
        $.ajax({
            type:"POST",
            contentType:"application/x-www-form-urlencoded",
            url:"http://localhost:8090/goods/delgoods",
            data:{
                "goodsId":goodsId
            },
            xhrFields:{withCredentials:true},
            success:function(data){
                if(data.status == "success"){
                    alert("删除商品成功");
                    window.location.reload();
                }else{
                    alert("删除商品失败");
                }
            },
            error:function(data){
                alert("删除商品失败");
            }
        });
        return false;
    });
});
/*$(miner).click(function(){
    var count=$("#countnum").text();
    var price=$("#price").text();
    var sum=$("#sum").text();
    if(count<=1){
        count=1;
        sum=count*price;
        $("#sum").text(sum);
    }else{
        count=parseInt(count)-1;
        sum=count*price;
        $("#sum").text(sum);
    }
    $("#countnum").text(count);
});*/

