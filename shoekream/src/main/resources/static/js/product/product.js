// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 스크롤시 왼쪽 상품 사진 이동후 멈춤
const is_fixed = document.querySelector(".is_fixed"); // 왼쪽 상품 사진
window.addEventListener('scroll', function () {
    // console.log(window.scrollY);
    if (window.scrollY >= 1240) {
        // window.scrollY: 브라우저 최상단에서 현재까지 스크롤 된 좌표값 구함
        is_fixed.classList.remove('is_fixed');
        is_fixed.classList.add('is_absolute'); // 좌표값이 1245인 곳에서 멈춰야함
    } else {
        is_fixed.classList.remove('is_absolute');
        is_fixed.classList.add('is_fixed');
    }
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 시세 - 개월 버튼
const dates = document.querySelectorAll(".tab_list .date");
// console.log(items);
//배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
dates.forEach((date) => {
    date.addEventListener('click', () => {
        dates.forEach((e) => {
            //하나만 선택되도록 기존의 효과를 지워준다.
            e.classList.remove('on');
            e.setAttribute('aria-selected', 'false');
        })

        // 선택한 그 아이만 효과를 추가해준다.
        date.classList.add('on');
        date.setAttribute('aria-selected', 'true');
    })
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 시세 - 체결내역
const conclusiones = document.querySelectorAll(".tab_list .conclusion");
conclusiones.forEach((conclusion) => {
    conclusion.addEventListener("click", () => {
        conclusiones.forEach((e) => {
            e.classList.remove("on");
            e.setAttribute("aria-selected", "false");
        });
        conclusion.classList.add("on");
        conclusion.setAttribute("aria-selected", "true");
    });
});


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 스크롤시 상단 고정 배너
const floating = document.querySelector(".floating_price.lg");
window.addEventListener("scroll", function () {
    if (window.scrollY >= 350) {
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
// 체결 내역 더보기 - 열고닫기
function conPopup() {
    const layer = document.querySelector(".layer_market_price");
    layer.style.display = "block";

    const body = document.querySelector("body");
    body.style.overflow = "hidden";
}

function conPopdown() {
    const layer = document.querySelector(".layer_market_price");
    layer.style.display = "none";

    const body = document.querySelector("body");
    body.style.overflow = "";
}


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
        // alert("에러! 에러! 관심상품 등록 실패!");
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
        // alert("에러! 에러! 관심상품 삭제 실패!");
        location.reload();
    })
}

















