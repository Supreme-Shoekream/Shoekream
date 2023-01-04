// 화면 스크롤 좌표값 구하는 이벤트
// window.addEventListener('scroll', function(){
//     console.log(window.scrollY)
// });


// 기능1. 스크롤시 왼쪽 상품 사진 이동후 멈춤
const is_fixed= document.querySelector(".is_fixed");
    // 왼쪽 상품 사진 

// const t = $(".meditaion_notice_product");
// const top = t.offset().top;
// const bottom = $("p.meditaion_notice_product").offset().top + $("p.meditaion_notice_product").outerHeight();
// console.log(bottom);

window.addEventListener('scroll', function(){
    // console.log(y2);
    // console.log(window.scrollY);
    if(window.scrollY >= 1240){ 

        // window.scrollY: 브라우저 최상단에서 현재까지 스크롤 된 좌표값 구함
        // 좌표값이 1240인 곳에서 멈춰야함
        is_fixed.classList.remove('is_fixed');
        is_fixed.classList.add('is_absolute');
            // css에서 position:absolute , top:1250px으로 지정해줬음
    }else{
        is_fixed.classList.remove('is_absolute');
        is_fixed.classList.add('is_fixed');
    }
});



// 기능2. 모든 사이즈 클릭시 팝업 레이어창 
function everysizePopup() {
    const layer = document.querySelector('.lg.layer_detail_size_select.select_only_size');
    layer.classList.add('layer');
        // class="lg layer_detail_size_select select_only_size"에 layer 추가
    
    const body = document.querySelector('body');
    body.style.overflow = 'hidden';

    layer.style.visibility ='visible';
        // popup시 요소 뜨게함
}

function everysizePopdown() {
    const layer = document.querySelector('.lg.layer_detail_size_select.select_only_size');
    layer.classList.remove('layer');
        // class="lg layer_detail_size_select select_only_size layer"에서 layer 제거
    
    const body = document.querySelector('body');
    body.style.overflow = '';

    layer.style.visibility ='hidden';
        // popdown시 요소 숨김(style은 유지=자리값은 유지됨)
}


// 기능3. 관심 상품 클릭시 팝업 레이어창 
function wishPopup() {
    const layer = document.querySelector('.layer_interest.lg');
    layer.classList.add('layer');
    
    const body = document.querySelector('body');
    body.style.overflow = 'hidden';

    layer.style.visibility ='visible';
}
function wishPopdown() {
    const layer = document.querySelector('.layer_interest.lg');
    layer.classList.remove('layer');
    layer.style.visibility ='hidden';
    
    const body = document.querySelector('body');
    body.style.overflow = "";
}



// 기능4. 시세 - 개월 버튼
const dates = document.querySelectorAll(".tab_list .date");
// console.log(items);
//배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
dates.forEach((date)=>{
    date.addEventListener('click',()=>{
        dates.forEach((e)=>{
        //하나만 선택되도록 기존의 효과를 지워준다.
            e.classList.remove('on');
            e.setAttribute('aria-selected','false');
        })
        
    // 선택한 그 아이만 효과를 추가해준다.
        date.classList.add('on');
        date.setAttribute('aria-selected','true');
        
        // const a = document.querySelector('.tab_list .graph_month');
        // const b = document.getElementById(a.getAttribute('aria-controls'));
        // console.log(b);

        // const ch = b.childNodes;

        // const canvas = ch[0].childNodes;

        // const k = canvas[0];
        // console.log(k.getAttribute('id'));
    })
});



// 기능5. 시세 - 그래프 띄우기
    // const a = document.querySelector('.tab_list .graph_month');
    // const b = document.getElementById(a.getAttribute('aria-controls'));
const sales_panel1 = document.getElementById("graph1m").getContext("2d");
const graph1m = new Chart(sales_panel1, {
    type: "line",
    data: {
        labels: [" ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        datasets: [{
            label: "시세",
            data: [784000, 784000, 789000, 858000, 792000, 790000, 100000, 200000, 320000, 9000],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        scales: {
            y: {
                beginAtZero: false,
                position: 'right' // 축이 왼쪽에 표시될지, 오른쪽에 표시될지 정할 수 있습니다.
            }
        },
        plugins: {
            legend: {
                display: false // 라벨 없애기(그래프제목)
            }
        }
    },
    
});


// 기능6. 시세 - 체결내역
const conclusiones = document.querySelectorAll(".tab_list .conclusion");
conclusiones.forEach((conclusion)=>{
    conclusion.addEventListener("click",() => {
        conclusiones.forEach((e) => {
            e.classList.remove("on");
            e.setAttribute("aria-selected","false");
        })
    conclusion.classList.add("on");
    conclusion.setAttribute("aria-selected","true");
    })
});



// 기능7. 시세 - 모든 사이즈
const filter_unit = document.querySelector(".filter_unit");
    
filter_unit.addEventListener("click", () => {
    const layer =document.querySelector(".filter_unit .layer_size_list")
    // const ch = filter_unit.childNodes;
    // const ch5 = ch[5];
    if(layer.style.display==="none"){
        document.querySelector(".filter_unit .btn.btn_select").classList.add("on");
        layer.style.display = "block";
    }else{
        document.querySelector(".filter_unit .btn.btn_select").classList.remove("on");
        layer.style.display = "none";
    }
});


// 기능8. 스크롤시 상단 고정 배너
const floating = document.querySelector(".floating_price.lg");
window.addEventListener("scroll", function(){
    
    if(window.scrollY >= 350){ 
        floating.classList.add("is_open");
    }else{
        floating.classList.remove("is_open");
    }
});


// 기능9. 구매전확인사항 드롭다운(3개 다 열수있음)
const dropdowns = document.querySelectorAll(".dropdown");
dropdowns.forEach((dropdown) => {

    dropdown.addEventListener("click",() => {

        const ch = dropdown.childNodes;
        console.log(ch);

        if(ch[3].getAttribute("class", "dc")){
            ch[3].removeAttribute("class", "dc");
        }else{
            ch[3].setAttribute("class", "dc");
        }

    })
});


// 기능10. 스타일 - 좋아요 버튼
$('span.like').click(function(e){
    e.preventDefault();
});

const items = document.querySelectorAll(".like");
    //배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
    items.forEach((item) => {

        item.addEventListener("click",()=>{

            const ch = item.childNodes;

            if(ch[1].getAttribute('src')=='../img/smile.png'){
                ch[1].setAttribute('src','../img/heart.png');
                // 숫자 ++1
            }else{
                ch[1].setAttribute('src','../img/smile.png');
            }
            
        })
    });


// 기능11. 슬릭슬라이더
$('picture.picture').click(function(e){
    e.preventDefault();
});

$(function(){
    $('#pro_slider-div').slick({
      slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li 
      infinite : true,     //무한 반복 옵션     
      slidesToShow : 1,        // 한 화면에 보여질 컨텐츠 개수
      slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
      speed : 100,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
      arrows : true,         // 옆으로 이동하는 화살표 표시 여부
      dots : true,         // 스크롤바 아래 점으로 페이지네이션 여부
      autoplay : false,            // 자동 스크롤 사용 여부
    //   autoplaySpeed : 3000,         // 자동 스크롤시 다음으로 넘어가는데 걸리는 시간 (ms)
      pauseOnHover : false,        // 슬라이드 이동시 마우스 호버하면 슬라이더 멈추게 설정
      vertical : false,        // 세로 방향 슬라이드 옵션
      prevArrow : "<button type='button' class='slick-prev'>Previous</button>",        // 이전 화살표 모양 설정
      nextArrow : "<button type='button' class='slick-next'>Next</button>",        // 다음 화살표 모양 설정
      dotsClass : "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
      draggable : true    //드래그 가능 여부 
    });
})

// 기능12. 체결 내역 더보기 - 열고닫기
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


// 기능13. 체결 내역 더보기 - 모든 사이즈
const size_select_wrap = document.querySelector(".size_select_wrap");
    
size_select_wrap.addEventListener("click", () => {
    const layer = document.querySelector(".size_select_wrap .layer_size_list");
    console.log(layer);

    if(layer.style.display == "none"){
        layer.style.display = "block";
    }else{
        layer.style.display = "none";
    }
});


// 기능14. 관심상품 클릭시 버튼 활성화
const wishes = document.querySelectorAll(".interest_btn_box .btn_interest");
    wishes.forEach((wish) => {
        wish.addEventListener("click", () => {

            const ch = wish.childNodes;
            // console.log(ch);

            if(ch[3].getAttribute("src") == "../img/select_mark_off.PNG"){
                ch[3].setAttribute("src","../img/select_mark_on.png");
            }else{
                ch[3].setAttribute("src","../img/select_mark_off.PNG");
            } 
        })
    });

const boxes = document.querySelectorAll(".interest_btn_box");
const wish_onoff = document.querySelector(".wish-onoff"); // 메인 관심상품
const wish_onoff2 = document.querySelector(".btn_wish_simple .wish-onoff"); // 상단 고정 배너 관심상품
let count = 0;

    boxes.forEach((box) => {
        box.addEventListener("click", () => {

            const ch = box.childNodes;
            // console.log(ch);
            
            if(ch[1].getAttribute("class") == "btn outlinegrey medium btn_interest"){
                ch[1].classList.add("wish_on");
                count++;
                // console.log("+:" + count);
            }else{
                ch[1].classList.remove("wish_on");
                count--;
                // console.log("-:" + count);
            }

            if(count > 0) {
                wish_onoff.setAttribute("src","../img/select_mark_on.png");
                wish_onoff2.setAttribute("src","../img/select_mark_on.png");
            }else{
                wish_onoff.setAttribute("src","../img/select_mark_off.PNG");
                wish_onoff2.setAttribute("src","../img/select_mark_off.png");
            }
        })
    });


// 사이즈 선택시 체크 표시
// const list = document.querySelector(".conclusion2_layer_content");
const item = document.querySelectorAll('.sl .size_item .size_link');
const input = document.querySelector(".input_text");
const checkImage = document.querySelector(".ico-check-s");

item.forEach((target) => {
    target.addEventListener("click", function(){
        // input.placeholder = event.target.textContent;
        item.forEach((e) => {
            e.classList.remove("item_on");
            e.childNodes[1].style.display="none";
            // console.log(e.childNodes[1]);
            // console.log(target.childNodes[0]);
        })


        // 사이즈값 찍어주기
        $('span.input_text').text(target.innerHTML.replace('<img src="../img/wcheck.png" class="ico-check-s icon sprite-icons" style="display: none;">',""));

        // 사이즈값 가져오기
        let size = $('span.it1').text(); // 앞에 찍어주는 사이즈값(메인 사이즈팝업창)
        let size2 = $('span.sss').text(); // 사이즈 팝업창에서의 사이즈값

        console.log("size:" + size);
        console.log("size2:" + size2);
        if(size == size2){
            
        }

        $('a.item_on').css("display", "block");
        
        // input.classList.add("input_txt_color");

        target.classList.add("item_on");
        target.childNodes[1].style.display = "block";
        

        // console.log(target.childNodes[1]);
        // list.style.display = "block";


    })

    // item.forEach((e) => {
    //     // console.log("e:" + e.childNodes[0]);
    //     // console.log(target.childNodes[0]);
    //     let ev = e.childNodes[0];

    //     let tv = target.childNodes[0];
        
    //     // if(e.childNodes[0].nodeValue == target.childNodes[0].nodeValue){
    //     if(ev == tv){
    //         console.log("=======")
    //         e.classList.add("item_on");
    //         e.childNodes[1].style.display = "block";
    //         // target.classList.add("item_on");
    //         // target.childNodes[1].style.display = "block";
    //     }
    // })
    
});








