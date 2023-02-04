// nav 선택
$(".menu_item").click(function(){
    $(".menu_item").removeClass("active");
    $(this).addClass("active");
    $(".menu_item").removeClass("menu_on")
    }); 

// 테이블 선택
$(".category_txt").click(function(){
    $(".category_txt").removeClass("active");
    $(this).addClass("active");
    $(".category").removeClass("category_on")
    }); 