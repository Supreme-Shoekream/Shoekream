// 드롭다운
const dropdowns = document.querySelectorAll(".dropdown");
dropdowns.forEach((dropdown) => {

    dropdown.addEventListener("click",() => {

        const ch = dropdown.childNodes;
        console.log(ch);

        if(ch[3].style.display=="none"){
            ch[3].style.display="block";
        }else{
            ch[3].style.display="none";
        }

    })
});

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
$(".category.policy").click(function(){
    $(".dropdown.policy").css("display","block");
    $(".dropdown.both").css("display","none");
    $(".dropdown.buy").css("display","none");
    $(".dropdown.sell").css("display","none");
    });

$(".category.both").click(function(){
    $(".dropdown.both").css("display","block");
    $(".dropdown.policy").css("display","none");
    $(".dropdown.buy").css("display","none");
    $(".dropdown.sell").css("display","none");
});

$(".category.buy").click(function(){
    $(".dropdown.buy").css("display","block");
    $(".dropdown.both").css("display","none");
    $(".dropdown.policy").css("display","none");
    $(".dropdown.sell").css("display","none");
});

$(".category.sell").click(function(){
    $(".dropdown.sell").css("display","block");
    $(".dropdown.buy").css("display","none");
    $(".dropdown.both").css("display","none");
    $(".dropdown.policy").css("display","none");
});

$(".category.total").click(function(){
    $(".dropdown.buy").css("display","block");
    $(".dropdown.both").css("display","block");
    $(".dropdown.policy").css("display","block");
});