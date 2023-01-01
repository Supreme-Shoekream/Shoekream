
function dd_menu1() {
    let click = document.getElementById("drop-content1");
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


var div2 = document.getElementsByIdName("top_menu01");

    function handleClick(event) {
        console.log(event.target);
        // console.log(this);
        // 콘솔창을 보면 둘다 동일한 값이 나온다

        console.log(event.target.classList);

        if (event.target.classList[1] === "clicked") {
        event.target.classList.remove("clicked");
        } else {
        for (var i = 0; i < div2.length; i++) {
            div2[i].classList.remove("clicked");
        }

        event.target.classList.add("clicked");
        }
    }

    function init() {
        for (var i = 0; i < div2.length; i++) {
        div2[i].addEventListener("click", handleClick);
        }
    }

    init();