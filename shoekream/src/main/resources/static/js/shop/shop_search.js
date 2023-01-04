
// function dd_menu1() {
//     let click = document.getElementById("drop-content1");
//     if (click.style.display === "none") {
//         click.style.display = "block";
//     } else {
//         click.style.display = "none";

//     }
// }

function dd_cm1() {
    let click = document.getElementById("dd-content1");
    if (click.style.display === "none") {
        click.style.display = "block";
    } else {
        click.style.display = "none";

    }
}

// ======================================================================
//  +버튼 클릭시  ( + ↔ - ) 버튼 변경
// ======================================================================

const items = document.querySelectorAll(".side_btn1");
//배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
items.forEach((item) => {

    item.addEventListener('click', () => {

        const ch = item.childNodes;

        if (ch[1].getAttribute('src') == '/img/shopimg/side_btn1.png') {
            ch[1].setAttribute('src', '/img/shopimg/side_btn0.png');
            // 숫자 ++1
        } else {
            ch[1].setAttribute('src', '/img/shopimg/side_btn1.png');
        }

    })
})

// ======================================================================

function dd_menu2() {
    let click = document.getElementById("drop-content2");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

function dd_menu3() {
    let click = document.getElementById("drop-content3");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

function dd_menu4() {
    let click = document.getElementById("drop-content4");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}


function dd_menu5() {
    let click = document.getElementById("drop-content5");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

function dd_menu6() {
    let click = document.getElementById("drop-content6");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

function dd_menu7() {
    let click = document.getElementById("drop-content7");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

const tes1 = document.querySelector('.sorting_link')
const tes2 = document.querySelector('.filter_menu')

function dd_menu8() {
    let click = document.getElementById("drop-content8");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

// ======================================================================
// 탑 메뉴 필터 클릭 시 색상 효과
// ======================================================================

const quickbtn = document.querySelectorAll(".btn_quick1");

function handleClick(event) {



if (event.target.classList[2] == "clicked") {
    event.target.classList.remove("clicked");
} else {
    for (let i = 0; i < quickbtn.length; i++) {
    quickbtn[i].classList.remove("clicked");
    }

    event.target.classList.add("clicked");
}
}

function init() {
for (let i = 0; i < quickbtn.length; i++) {
    quickbtn[i].addEventListener("click", handleClick);
}
}

init();

// ======================================================================

// ======================================================================
// desc sorting 실패1
// ======================================================================
// 
const receiptList = document.querySelectorAll(".sorting_item")
// 리스트 main_desc
        const Sortingtit = document.querySelector(".main_desc") 
        // 글자바뀌는곳 sorting_title
        receiptList.forEach((target) => {
            target.addEventListener("click", function(event) {
                Sortingtit.sortingtit = event.target.textContent // 글자바꾸기
                receiptList.forEach((e) => {
                    // e.classList.remove("select_receipt") 
                    // 글씨강조지우기
                    e.childNodes[1].style.display="none" 
                    // 체크표시지우기
                })
                // Sortingtit.classList.add("input_txt_color") 
                // 글자바뀌는곳의 글씨색 변경
                // target.classList.add("select_receipt") // 글씨강조추가
                target.childNodes[1].style.display = "block" // 체크표시추가
                dd_menu8.style.display="none" // 리스트창 닫기
            })
        })
// 
// ======================================================================


// ======================================================================
// desc sorting 실패2
// ======================================================================
// const receiptList = document.querySelectorAll(".sorting_item")
// // 리스트 main_desc
//         const Sortingtit = document.querySelector(".main_desc") 
//         // 글자바뀌는곳 sorting_title
//         receiptList.forEach((target) => {
//             target.addEventListener("click", function(event) {
//                 Sortingtit.sortingtit = event.target.textContent // 글자바꾸기
//                 receiptList.forEach((e) => {
//                     e.childNodes[1].style.display="none" 
//                     // div 사라지는 부분 ,, 체크표시제거
//                 })
//                 // target.classList.add("select_receipt") // 추가
//                 target.childNodes[1].style.display = "none" // 체크표시추가
//                 dd_menu8.style.display="none" // 리스트창 닫기
//             })
//         })
// ======================================================================

// ======================================================================
// desc sorting 3트
// ======================================================================

// const sortbtn = document.querySelectorAll('.sorting_link')   // 버튼
// const dd_list = document.querySelector('.filter_menu') // 리스트, 클릭시 none시킬거
// const dd_sortitem = document.querySelector('.sorting_item') // 리스트 none
// const dd_checkbtn = document.querySelector('.sorting_img') // 체크img, 클릭시 block시킬거
// // const dd_sortlink = document.querySelector('.sorting_link')
// const dd_listmenu = document.querySelector('.sorting_list') // if 클릭됐을 때
// const sorting_title = document.getElementsByClassName('.sorting_title') //문구 출력

// sortbtn.forEach((target) => {
//     target.addEventListener('click',() =>{
//         if (true) {
//             dd_checkbtn.style.display="none";
//             dd_sortitem.style.display="none";
//             dd_list.style.display="none";
//             sorting_title=(main_desc);
//         } else {
//             layer1.style.display="block";
//             layer2.style.display="none";
//         }
//     })
// })

// ======================================================================



// ======================================================================
// desc sorting 4트
// ======================================================================


rdd1 = function rdd_menu1() {
    const rdd1 = document.getElementsByClassName('.main_desc');
    rdd1.innerText 
    = '인기순';
} 

rdd2 = function rdd_menu2() {
    const rdd2 = document.getElementsByClassName('.main_desc');
    rdd2.innerText 
    = '프리미엄순';
}

rdd3 = function rdd_menu3() {
    const rdd3 = document.getElementsByClassName('.main_desc');
    rdd3.innerText 
    = '즉시 구매가순';
} 

rdd4 = function rdd_menu4() {
    const rdd4 = document.getElementsByClassName('.main_desc');
    rdd4.innerText 
    = '즉시 판매가순';
} 

rdd5 = function rdd_menu5() {
    const rdd5 = document.getElementsByClassName('.main_desc');
    rdd5.innerText 
    = '발매일순';
} 

// const sortbtn = document.querySelectorAll('.sorting_link')   // 버튼
// const dd_list = document.querySelector('.filter_menu') // 리스트, 클릭시 none시킬거
// const dd_sortitem = document.querySelector('.sorting_item') // 리스트 none
// const dd_checkbtn = document.querySelector('.sorting_img') // 체크img, 클릭시 block시킬거
// const dd_sortlink = document.querySelector('.sorting_link')
// const dd_listmenu = document.querySelector('.sorting_list') // if 클릭됐을 때
// const sorting_title = document.getElementsByClassName('.sorting_title') //문구 출력

// sorting_title.forEach((target) => {
//     //sorting_title 버튼을 클릭했을 때
//     target.addEventListener('click',() =>{
//         if (true) {
//             dd_listmenu.style.display="block";
//         } else {
//             sorting_link.style.display="block";
//         }
//     })
// })

// ======================================================================


// sorting_title을 클릭하면 sorting_list를 block
// sorting_link를 클릭하면 sorting_list를 none


// function dd_menu8() {
//     let click = document.getElementById("drop-content8");
//     if (click.style.display === "none") {
//         click.style.display = "block";

//     } else {
//         click.style.display = "none";

//     }
// }

// catebtn.forEach((target) => {
//     target.addEventListener('click',() =>{
//         if (catebtn2.getAttribute('src') == '/img/shopimg/side_btn0.png') {
//             layer1.style.display="none";
//             layer2.style.display="block";
//         } else {
//             layer1.style.display="block";
//             layer2.style.display="none";
//         }
//     })
// })




// ======================================================================

// ======================================================================
// addEvenetListener 1개이상 사용 예제
// ======================================================================

// const closename = coumnet.querySelectorAll('.close_name')
// const layer1 = document.querySelector('지울거')
// const layer2 = document.querySelector('띄울거')
// closename.forEach((target) => {
//     target.addEventListener('click',() =>{
//         layer1.style.display="none"
//         layer2.style.display="block"
//     })
// })

// ======================================================================


// ======================================================================
// addEvenetListener 1트
// ======================================================================

// const catebtn = document.querySelectorAll('.side_btn1')   // 버튼
// const layer1 = document.querySelector('#placehod')   // none 시킬거
// const layer2 = document.querySelector('#drop-content1')   // block시킬거 

// catebtn.forEach((target) => {
//     target.addEventListener('click',() =>{
        
//         layer1.style.display="none"
//         layer2.style.display="block"
//     })
// })

// ======================================================================

// ======================================================================
//  왼쪽 메뉴 클릭하면 placeholder 사라지고 나타나게 하는 코드
// ======================================================================
const catebtn = document.querySelectorAll('.side_btn1')   // 버튼
const layer1 = document.querySelector('#placehod')   // none 시킬거
const layer2 = document.querySelector('#drop-content1')   // block시킬거
const catebtn2 = document.querySelector('.side_btn')    //사진 비교를 위해 호출

catebtn.forEach((target) => {
    target.addEventListener('click',() =>{
        if (catebtn2.getAttribute('src') == '/img/shopimg/side_btn0.png') {
            layer1.style.display="none";
            layer2.style.display="block";
        } else {
            layer1.style.display="block";
            layer2.style.display="none";
        }
    })
})

//만약 none== 열어라 else 닫아리 글자보여줘

// if (click.style.display === "none") {
//     click.style.display = "block";

// } else {
//     click.style.display = "none";


// ======================================================================

// 탑 고정 메뉴
// document.getElementById("top_menu01").style.backgroundColor = "red";

// document.getElementById("top_menu01").onclick = function(){
//     this.style.backgroundColor ="yellow";
// };

// // = = = = = = = = = = = = = = = = = =

// document.getElementById("top_menu06").style.backgroundColor ="gray";
// document.getElementById("top_menu07").style.backgroundColor ="gray";


// document.getElementById("top_menu06").onclick = function(){
//             this.style.backgroundColor ="black";
//             document.getElementById("top_menu07").style.backgroundColor ="gray";
//         };

// document.getElementById("top_menu07").onclick = function(){
//             this.style.backgroundColor ="black";
//             document.getElementById("top_menu06").style.backgroundColor ="gray";
//         };

// https://liufeier.tistory.com/22 참고해서 할 것 !!


// var div2 = document.getElementsById("top_menu01");

//     function handleClick(event) {
//         console.log(event.target);
//         // console.log(this);
//         // 콘솔창을 보면 둘다 동일한 값이 나온다

//         console.log(event.target.classList);

//         if (event.target.classList[1] === "clicked") {
//         event.target.classList.remove("clicked");
//         } else {
//         for (var i = 0; i < div2.length; i++) {
//             div2[i].classList.remove("clicked");
//         }

//         event.target.classList.add("clicked");
//         }
//     }

//     function init() {
//         for (var i = 0; i < div2.length; i++) {
//         div2[i].addEventListener("click", handleClick);
//         }
//     }

//     init();

// ===========================================
// 버튼 클릭시 왔다 갔다 하는거
// ===========================================

// const nonClick = document.querySelectorAll(".non-click");
// // .non-click의 선택자를 모두 호출

// function handleClick(event) {
//   // div에서 모든 "click" 클래스 제거
//   nonClick.forEach((e) => {         // nonClick을 호출해서 forEach 돌리기
//     e.classList.remove("click");
//   });
//   // 클릭한 div만 "click"클래스 추가
//   event.target.classList.add("click");
// }
// ;

// nonClick.forEach((e) => {
//   e.addEventListener("click", handleClick);
// });

// ===========================================

// 버리는 코드 (필터)

// console.log(document.querySelectorAll('.btn_quick1'))



// let catecheck2 = document.querySelectorAll('.checkInput');
// let checkImg = document.querySelectorAll(".checkImg");
// $(document).on('click', '.btn_quick_filter_js', function (e) {
//     e.preventDefault();
//     let index = $(".btn_quick_filter_js").index(this);
//     if (catecheck2[index].checked == false) {
//         catecheck2[index].checked = true;
//         $('.top_box').eq(index + 1).css({"display": "block"});
//         $('.top_box').not($('.top_box').eq(index + 1)).css({"display": "none"});
//         checkImg[index].src = "/lib/img/check_box_b.png";
//         for (let i in checkImg) {
//             if (i != index) {
//                 checkImg[i].src = "/lib/img/check_box.png";
//                 catecheck2[i].checked = false;
//             }
//         }
//         $(this).css({"background-color": "rgb(250, 221, 221)", "color": "rgb(252, 121, 121)"});

//         $('.btn_quick_filter_js').not($(this)).css({
//             "background-color": "rgb(240, 240, 240)",
//             "color": "black"
//         });

//         console.log(catecheck2[index])
//         let $save_check = catecheck2[index]
//         let $cate_sell = $save_check.value;
//         click_you = $cate_sell;


//     } else if (catecheck2[index].checked == true) {
//         catecheck2[index].checked = false;
//         $('.top_box').eq(0).css({"display": "block"});
//         $('.top_box').not($('.top_box').eq(0)).css({"display": "none"});
//         checkImg[index].src = "/lib/img/check_box.png";
//         $(this).css({"background-color": "rgb(240, 240, 240)", "color": "black"});
//         click_you= "카테취소";

//     }



//     sendit()
// });