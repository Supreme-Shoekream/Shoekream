// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 스크롤시 왼쪽 상품 사진 이동후 멈춤
const feed_area = document.getElementById("confirm_wrap");
const fa = feed_area.offsetTop;
const is_fixed = document.querySelector(".is_fixed"); // 왼쪽 상품 사진
window.addEventListener('scroll', function () {
    console.log(window.scrollY);
    if (window.scrollY >= fa) {
        // window.scrollY: 브라우저 최상단에서 현재까지 스크롤 된 좌표값 구함
        is_fixed.classList.remove('is_fixed');
        is_fixed.classList.add('is_absolute');
    } else {
        is_fixed.classList.remove('is_absolute');
        is_fixed.classList.add('is_fixed');
    }
});




// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 시세 - 개월 버튼
const dates = document.querySelectorAll(".tab_list .date");
const tces = document.querySelectorAll(".tc");
//배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
dates.forEach((date) => {
    date.addEventListener('click', () => {
        dates.forEach((e) => {
            //하나만 선택되도록 기존의 효과를 지워준다.
            e.classList.remove('on');
            e.setAttribute('aria-selected', 'false');
        });
        tces.forEach((e)=>{
            e.setAttribute("width", "0");
            e.setAttribute("height", "0");
        });

        // 선택한 그 아이만 효과를 추가해준다.
        date.classList.add('on');
        date.setAttribute('aria-selected', 'true');

        let month = date.childNodes[1].innerHTML;
        // console.log(months);
        switch (month){
            case "1개월":
                document.getElementById("graph1m").setAttribute("width", "560");
                document.getElementById("graph1m").setAttribute("height", "200");
                break;
            case "3개월":
                document.getElementById("graph3m").setAttribute("width", "560");
                document.getElementById("graph3m").setAttribute("height", "200");
                break;
            case "6개월":
                document.getElementById("graph6m").setAttribute("width", "560");
                document.getElementById("graph6m").setAttribute("height", "200");
                break;
            case "1년":
                document.getElementById("graph1y").setAttribute("width", "560");
                document.getElementById("graph1y").setAttribute("height", "200");
                break;
            case "전체":
                document.getElementById("graphall").setAttribute("width", "560");
                document.getElementById("graphall").setAttribute("height", "200");
                break;
        }
    })
});



// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 시세 - 체결내역
const conclusiones = document.querySelectorAll(".tab_list .conclusion");
const twes = document.querySelectorAll(".tw");
conclusiones.forEach((conclusion) => {
    conclusion.addEventListener("click", () => {
        conclusiones.forEach((e) => {
            e.classList.remove("on");
            e.setAttribute("aria-selected", "false");
        });
        twes.forEach((e) => {
            e.classList.remove("show");
        });


        conclusion.classList.add("on");
        conclusion.setAttribute("aria-selected", "true");

        let con = conclusion.childNodes[1].innerHTML;
        console.log(con);
        switch (con){
            case "체결 거래":
                document.getElementById("panel1").classList.add("show");
                // document.getElementById("panel11").classList.add("show");
                break;
            case "판매 입찰":
                document.getElementById("panel2").classList.add("show");
                // document.getElementById("panel22").classList.add("show");
                break;
            case "구매 입찰":
                document.getElementById("panel3").classList.add("show");
                // document.getElementById("panel33").classList.add("show");
                break;
        }
    });
});

// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 체결 내역 더보기 - 열고닫기
const plusList = document.querySelectorAll(".plus"); // 팝업창 안에 있는 체결내역 버튼
const plustbtn = document.querySelectorAll(".plusbtn"); // 체결 내역 더보기 버튼
const tcl = document.querySelectorAll(".tcl"); // 팝업창 안에 리스트 출력되는 영역
plustbtn.forEach((pb) => {
    pb.addEventListener("click", () => {
        const layer = document.querySelector(".layer_market_price");
        layer.style.display = "block";
        const body = document.querySelector("body");
        body.style.overflow = "hidden";

        plusList.forEach((e) => {
            e.classList.remove("on");
        });

        tcl.forEach((e) => {
            e.classList.remove("show");
        });


        let plus = pb.innerHTML;
        switch (plus){
            case "체결 내역 더보기":
                document.getElementById("plus1").classList.add("on"); // 팝업창 안에 있는 체결내역 버튼 on
                document.getElementById("panel11").classList.add("show");
                break;
            case "판매 입찰 내역 더보기":
                document.getElementById("plus2").classList.add("on"); // 팝업창 안에 있는 판매입찰 버튼 on
                document.getElementById("panel22").classList.add("show");
                break;
            case "구매 입찰 내역 더보기":
                document.getElementById("plus3").classList.add("on"); // 팝업창 안에 있는 구매입찰 버튼 on
                document.getElementById("panel33").classList.add("show");
                break;
            }
        });
    });

plusList.forEach((pL) => {
    pL.addEventListener("click", () => {
        plusList.forEach((e) => {
            e.classList.remove("on");
        });
        tcl.forEach((e) => {
            e.classList.remove("show");
        });


        let pluslist = pL.childNodes[1].innerHTML;
        switch (pluslist){
            case "체결 거래":
                document.getElementById("plus1").classList.add("on"); // 팝업창 안에 있는 체결내역 버튼 on
                document.getElementById("panel11").classList.add("show");
                break;
            case "판매 입찰":
                document.getElementById("plus2").classList.add("on"); // 팝업창 안에 있는 판매입찰 버튼 on
                document.getElementById("panel22").classList.add("show");
                break;
            case "구매 입찰":
                document.getElementById("plus3").classList.add("on"); // 팝업창 안에 있는 구매입찰 버튼 on
                document.getElementById("panel33").classList.add("show");
                break;
        }

    })
})




function conPopdown() {
    const layer = document.querySelector(".layer_market_price");
    layer.style.display = "none";

    const body = document.querySelector("body");
    body.style.overflow = "";
}


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 스크롤시 상단 고정 배너
const product_info_wrap = document.getElementById("product_info_wrap");
const piw = product_info_wrap.offsetTop;
const floating = document.querySelector(".floating_price.lg");
window.addEventListener("scroll", function () {
    if (window.scrollY >= piw) {
        floating.classList.add("is_open");
    } else {
        floating.classList.remove("is_open");
    }
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 구매전확인사항 드롭다운(3개 동시에 열수있음)
const dropdowns = document.querySelectorAll(".dropdown");
dropdowns.forEach((dropdown) => {
    dropdown.addEventListener("click", () => {
        const ch = dropdown.childNodes;
        console.log(ch);

        if (ch[3].getAttribute("class", "dc")) {
            ch[3].removeAttribute("class", "dc");
        } else {
            ch[3].setAttribute("class", "dc");
        }
    });
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 스타일 - 좋아요 버튼
$('span.like').click(function (e) {
    e.preventDefault();
});

const items = document.querySelectorAll(".like");
//배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
items.forEach((item) => {
    item.addEventListener("click", () => {
        const ch = item.childNodes;

        if (ch[1].getAttribute('src') == '/img/smile.png') {
            ch[1].setAttribute('src', '/img/heart.png');
            // 숫자 ++1
        } else {
            ch[1].setAttribute('src', '/img/smile.png');
        }
    });
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 상세페이지 로드시 관심상품 북마크 상태 처리
const wishes = document.querySelectorAll(".btn_wish");
const wish_onoff = document.querySelector(".wish-onoff"); // 메인 관심상품
const wish_onoff2 = document.querySelector(".btn_wish_simple .wish-onoff"); // 상단 고정 배너 관심상품
let isWish = document.getElementById("wishCount").getAttribute("value");
wishes.forEach((wish) => {

    const ch = wish.childNodes;
    console.log(ch);

    if(isWish === "true") {
        if (ch[1].getAttribute("src") == "/img/select_mark_off.PNG") {
            wish_onoff.setAttribute("src", "/img/select_mark_on.png");
            wish_onoff2.setAttribute("src", "/img/select_mark_on.png");
        }else {
            wish_onoff.setAttribute("src", "/img/select_mark_on.png");
            wish_onoff2.setAttribute("src", "/img/select_mark_on.png");
        }
    }else{
        if (ch[1].getAttribute("src") == "/img/select_mark_off.PNG") {
            wish_onoff.setAttribute("src", "/img/select_mark_off.PNG");
            wish_onoff2.setAttribute("src", "/img/select_mark_off.PNG");
        }else {
            wish_onoff.setAttribute("src", "/img/select_mark_on.png");
            wish_onoff2.setAttribute("src", "/img/select_mark_on.png");
        }
    }
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 관심상품 클릭시 카운트
wishes.forEach((wish) => {
    wish.addEventListener("click", () => {
        const ch = wish.childNodes;
        // console.log(ch);
        if(isWish === ""){
            location.href = "http://localhost:8889/login";
        }else {
            if (ch[1].getAttribute("src") == "/img/select_mark_off.PNG") {
                wish_onoff.setAttribute("src", "/img/select_mark_on.png");
                wish_onoff2.setAttribute("src", "/img/select_mark_on.png");
                // let count = 0;
                // count = count + 1;
                // $(".wish_count .wishCount").html(count);
                wishCreate();
            } else {
                wish_onoff.setAttribute("src", "/img/select_mark_off.PNG");
                wish_onoff2.setAttribute("src", "/img/select_mark_off.PNG");
                // let totcount = $(".wish_count .wishCount").text();
                // totcount = totcount - 1;
                // $(".wish_count .wishCount").html(totcount);
                wishDelete();
            }
        }
    })
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 관심상품 create
function wishCreate() {
    const proIdx = document.getElementById("wish_proIdx").getAttribute("value");
    // console.log(proIdx);
    fetch("http://localhost:8889/api/product", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            "productIdx": proIdx
        }),
    })
    .then((res) => {
        // alert("관심상품 등록 성공!");
        location.reload();
    })
    .then((data) => {
        console.log(data);
    })
    .catch((err) => {
        alert("에러! 에러! 관심상품 등록 실패!");
        location.reload();
    })
}

// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 관심상품 delete
function wishDelete() {
    let idx = document.getElementById("wish_proIdx").getAttribute("value");
    fetch('http://localhost:8889/api/product/' + idx, {
        method: "DELETE"
    })
    .then((res) => {
        // alert("관심상품 삭제 성공!");
        location.reload();
    })
    .then((data) => {
        console.log(data);
    })
    .catch((err) => {
        alert("에러! 에러! 관심상품 삭제 실패!");
        location.reload();
    })
}

















