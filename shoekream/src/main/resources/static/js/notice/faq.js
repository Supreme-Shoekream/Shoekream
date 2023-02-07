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