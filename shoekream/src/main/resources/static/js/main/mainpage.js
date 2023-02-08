// 상단 배너 슬릭슬라이드
    $(function () {
    $('#slider-div').slick({
        slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li
        infinite: true,     //무한 반복 옵션
        slidesToShow: 1,        // 한 화면에 보여질 컨텐츠 개수
        slidesToScroll: 1,        //스크롤 한번에 움직일 컨텐츠 개수
        speed: 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
        arrows: true,         // 옆으로 이동하는 화살표 표시 여부
        dots: true,         // 스크롤바 아래 점으로 페이지네이션 여부
        autoplay: true,            // 자동 스크롤 사용 여부
        autoplaySpeed: 3000,         // 자동 스크롤시 다음으로 넘어가는데 걸리는 시간 (ms)
        pauseOnHover: true,        // 슬라이드 이동시 마우스 호버하면 슬라이더 멈추게 설정
        vertical: false,        // 세로 방향 슬라이드 옵션
        prevArrow: "<i class='prev_arrow fa-solid fa-chevron-left'></i>",        // 이전 화살표 모양 설정
        nextArrow: "<i class='next_arrow fa-solid fa-chevron-right'></i>",        // 다음 화살표 모양 설정
        dotsClass: "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
        draggable: true    //드래그 가능 여부

    });

        bannerResize();
});


    $(window).resize(function () {

        bannerResize();

});

    // 배너 resize
function bannerResize(){
    $(".bannerImg").width($(window).width());

    //$(".main_banner").width($(".bannerImg").width());

    //$(".main_banner").height($(".bannerImg").height());

}


    // 레이어창 열고 닫기 기능

//     function close_search(){
//     document.querySelector('.layer_search').style.display="none"
//     document.querySelector('body').classList.remove('test');
//     //큰애 스느롤 다시 보아가
// //.layer_search를 선택하고! 스타일을 줍니다! 안보이도록!
//
// }
//     function pop_search(){
//     document.querySelector('.layer_search').style.display="block"
//     document.querySelector('body').classList.add('test');
//     //큰애 스크롤 없애기 -
// //.layer_search를 선택하고! 스타일을 줍니다! 보이도록!
// }
    function close_auth_policy(){
    document.querySelector('.layer_auth_policy').style.display="none";
    //큰애 스느롤 다시 보아가
//.layer_auth_policy를 선택하고! 스타일을 줍니다! 안보이도록!

}
    function pop_auth_policy(){
    document.querySelector('.layer_auth_policy').style.display="block";
    //큰애 스크롤 없애기 -
//.layer_auth_policy를 선택하고! 스타일을 줍니다! 보이도록!
}
    function close_faq(){
    document.querySelector('.layer_faq').style.display="none";
    //큰애 스느롤 다시 보아가
//.layer_auth_policy를 선택하고! 스타일을 줍니다! 안보이도록!

}
    function pop_faq(){
    document.querySelector('.layer_faq').style.display="block";
    //큰애 스크롤 없애기 -
//.layer_auth_policy를 선택하고! 스타일을 줍니다! 보이도록!
}
    // 스타일 슬릭 슬라이드
    $(function () {
    $('#sc_slider_div').slick({
        slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li
        infinite: true,     //무한 반복 옵션
        slidesToShow: 6,        // 한 화면에 보여질 컨텐츠 개수
        slidesToScroll: 1,        //스크롤 한번에 움직일 컨텐츠 개수
        speed: 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
        arrows: true,         // 옆으로 이동하는 화살표 표시 여부
        dots: false,         // 스크롤바 아래 점으로 페이지네이션 여부
        autoplay: true,            // 자동 스크롤 사용 여부
        autoplaySpeed: 3000,         // 자동 스크롤시 다음으로 넘어가는데 걸리는 시간 (ms)
        pauseOnHover: true,        // 슬라이드 이동시 마우스 호버하면 슬라이더 멈추게 설정
        vertical: false,        // 세로 방향 슬라이드 옵션
        prevArrow: "<i class='sc_prev_arrow fa-solid fa-chevron-left'></i>",        // 이전 화살표 모양 설정
        nextArrow: "<i class='sc_next_arrow fa-solid fa-chevron-right'></i>",        // 다음 화살표 모양 설정
        draggable: true    //드래그 가능 여부

    });
});
    // 더보기 기능
    $(function(){
    $(".just_product").slice(0, 4).show(); // 초기갯수
        if($(".just_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load1").style.display="none";
        }
    $("#load1").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".just_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정
        if($(".just_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load1").style.display="none";
        }

});
    $(".pop_product").slice(0, 4).show(); // 초기갯수
        if($(".pop_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load2").style.display="none";
        }
    $("#load2").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".pop_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".pop_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load2").style.display="none";
}
});
    $(".newin_product").slice(0, 4).show(); // 초기갯수
        if($(".newin_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load3").style.display="none";
        }
    $("#load3").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".newin_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".newin_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load3").style.display="none";
}
});
    $(".wisely_product").slice(0, 4).show(); // 초기갯수
        if($(".wisely_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load4").style.display="none";
        }
    $("#load4").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".wisely_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".wisely_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load4").style.display="none";
}
});
    $(".brandmd_product").slice(0, 4).show(); // 초기갯수
        if($(".brandmd_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load5").style.display="none";
        }
    $("#load5").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".brandmd_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".brandmd_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load5").style.display="none";
}
});
    $(".simple_product").slice(0, 4).show(); // 초기갯수
        if($(".simple_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load6").style.display="none";
        }
    $("#load6").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".simple_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".simple_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load6").style.display="none";
}

});
    $(".collabs_product").slice(0, 4).show(); // 초기갯수
        if($(".collabs_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load7").style.display="none";
        }
    $("#load7").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".collabs_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".collabs_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load7").style.display="none";
}

});
    $(".newlowest_product").slice(0, 4).show(); // 초기갯수
        if($(".newlowest_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load8").style.display="none";
        }
    $("#load8").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".newlowest_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".newlowest_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load8").style.display="none";
}
});
    $(".newhighest_product").slice(0, 4).show(); // 초기갯수
        if($(".newhighest_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load9").style.display="none";
        }
    $("#load9").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".newhighest_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".newhighest_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load9").style.display="none";
}
});

    $(".coming").slice(0, 4).show(); // 초기갯수
        if($(".coming").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load10").style.display="none";
        }
    $("#load10").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".coming:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".coming:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load10").style.display="none";
}
});

    $(".bestcol_product").slice(0, 4).show(); // 초기갯수
        if($(".bestcol_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load11").style.display="none";
        }
    $("#load11").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".bestcol_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".bestcol_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load11").style.display="none";
}
});

    $(".jew_product").slice(0, 4).show(); // 초기갯수
        if($(".jew_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load12").style.display="none";
        }
    $("#load12").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".jew_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정

    if($(".jew_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
    document.querySelector("#load12").style.display="none";
}

});
    $(".camping_product").slice(0, 4).show(); // 초기갯수
        if($(".camping_product").length <= 4){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load13").style.display="none";
        }
    $("#load13").click(function(e){ // 클릭시 more
    e.preventDefault();
    $(".camping_product:hidden").slice(0, 4).show(); // 클릭시 more 갯수 지저정
        if($(".camping_product:hidden").length === 0){ // 컨텐츠 남아있는지 확인
            document.querySelector("#load13").style.display="none";
        }
});

});
    // 스크롤 상하단 버튼튼
    $('.btn_top').click(function(){
        $('html, body').animate({scrollTop:0},10);
        return false;
    });
    $(".btn_bottom").click(function() {
    $('html').animate({scrollTop : ($('.notice_area').offset().top)}, 10);
});


$(".product_item").click(function() {
    var idx =  $(this).find(".product_idx").val();
    location.href = "/product/"+idx;
});
$(".style_item1").click(function() {
    var idx =  $(this).find(".style_idx").val();
    location.href = "/social/details#"+idx;
});
function kakaoLogout() {
    if (!Kakao.Auth.getAccessToken()) {
        alert('Not logged in.');
        return;
    }
    Kakao.Auth.logout(function() {
        alert('logout ok\naccess token -> ' + Kakao.Auth.getAccessToken());
    });
}

