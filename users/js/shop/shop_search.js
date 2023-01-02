
function dd_menu1() {
    let click = document.getElementById("drop-content1");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

function dd_cm1() {
    let click = document.getElementById("dd-content1");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

const items = document.querySelectorAll(".side_btn1");
//배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
items.forEach((item) => {

    item.addEventListener('click', () => {

        const ch = item.childNodes;

        if (ch[1].getAttribute('src') == '../img/shopimg/side_btn1.png') {
            ch[1].setAttribute('src', '../img/shopimg/side_btn0.png');
            // 숫자 ++1
        } else {
            ch[1].setAttribute('src', '../img/shopimg/side_btn1.png');
        }

    })
})


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

function dd_menu8() {
    let click = document.getElementById("drop-content8");
    if (click.style.display === "none") {
        click.style.display = "block";

    } else {
        click.style.display = "none";

    }
}

// 탑 메뉴 클릭시 효과
const quickbtn = document.querySelectorAll(".btn_quick1");

function handleClick(event) {
// console.log(event.target.classList);
  // console.log(this);
  // 콘솔창을 보면 둘다 동일한 값이 나온다


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


const receiptList = document.querySelectorAll(".sorting_item")
// 리스트 main_desc
        const Sortingtit = document.querySelector(".main_desc") 
        // 글자바뀌는곳 sorting_title
        receiptList.forEach((target) => {
            target.addEventListener("click", function() {
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