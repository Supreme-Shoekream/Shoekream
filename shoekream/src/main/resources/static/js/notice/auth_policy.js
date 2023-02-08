// nav 선택
$(".menu_item").click(function(){
    $(".menu_item").removeClass("active");
    $(this).addClass("active");
    $(".menu_item").removeClass("menu_on")
    }); 

// 테이블 선택
$(".category").click(function(){
    $(".category").removeClass("active");
    $(this).addClass("active");
    $(".category").removeClass("category_on")
    });

// 필터링
$(".category.shoe").click(function(){
    $(".wrap_auth.shoe").css("display","block");
    $(".wrap_auth.clothes").css("display","none");
    $(".wrap_auth.acc").css("display","none");
    $(".wrap_auth.tech").css("display","none");
    $(".wrap_auth.life").css("display","none");
    $(".wrap_auth.watch").css("display","none");
    $(".wrap_auth.bag").css("display","none");
});

$(".category.clothes").click(function(){
    $(".wrap_auth.shoe").css("display","none");
    $(".wrap_auth.clothes").css("display","block");
    $(".wrap_auth.acc").css("display","none");
    $(".wrap_auth.tech").css("display","none");
    $(".wrap_auth.life").css("display","none");
    $(".wrap_auth.watch").css("display","none");
    $(".wrap_auth.bag").css("display","none");
});

$(".category.acc").click(function(){
    $(".wrap_auth.shoe").css("display","none");
    $(".wrap_auth.clothes").css("display","none");
    $(".wrap_auth.acc").css("display","block");
    $(".wrap_auth.tech").css("display","none");
    $(".wrap_auth.life").css("display","none");
    $(".wrap_auth.watch").css("display","none");
    $(".wrap_auth.bag").css("display","none");
});

$(".category.tech").click(function(){
    $(".wrap_auth.shoe").css("display","none");
    $(".wrap_auth.clothes").css("display","none");
    $(".wrap_auth.acc").css("display","none");
    $(".wrap_auth.tech").css("display","block");
    $(".wrap_auth.life").css("display","none");
    $(".wrap_auth.watch").css("display","none");
    $(".wrap_auth.bag").css("display","none");
});

$(".category.life").click(function(){
    $(".wrap_auth.shoe").css("display","none");
    $(".wrap_auth.clothes").css("display","none");
    $(".wrap_auth.acc").css("display","none");
    $(".wrap_auth.tech").css("display","none");
    $(".wrap_auth.life").css("display","block");
    $(".wrap_auth.watch").css("display","none");
    $(".wrap_auth.bag").css("display","none");
});

$(".category.watch").click(function(){
    $(".wrap_auth.shoe").css("display","none");
    $(".wrap_auth.clothes").css("display","none");
    $(".wrap_auth.acc").css("display","none");
    $(".wrap_auth.tech").css("display","none");
    $(".wrap_auth.life").css("display","none");
    $(".wrap_auth.watch").css("display","block");
    $(".wrap_auth.bag").css("display","none");
});

$(".category.bag").click(function(){
    $(".wrap_auth.shoe").css("display","none");
    $(".wrap_auth.clothes").css("display","none");
    $(".wrap_auth.acc").css("display","none");
    $(".wrap_auth.tech").css("display","none");
    $(".wrap_auth.life").css("display","none");
    $(".wrap_auth.watch").css("display","none");
    $(".wrap_auth.bag").css("display","block");
});