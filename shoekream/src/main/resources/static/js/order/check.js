// 체크 개수 따라 구매계속 버튼 색 바뀌기
function getCheck() {
    const query = 'input[class=check]:checked';
    const selectedElements = document.querySelectorAll(query);
    const cnt = selectedElements.length;
    const next = document.querySelector("#next")
    if (cnt == 5) {
        $("#next").css("background-color", "black");
        next.disabled = false;
        next.classList.remove('disabled');

    } else {
        $("#next").css("background-color", "#ebebeb");
        next.disabled = true;
        if(!(next.classList.contains('disabled'))) next.classList.add('disabled');
    }
}
document.querySelector('#input0').addEventListener('click',getCheck)

function pop_auth_policy(){
    document.querySelector('.layer_auth_policy').style.display="block"
}
function close_auth_policy(){
    document.querySelector('.layer_auth_policy').style.display="none"
}
function pop_notice(){
    document.querySelector('.layer_order_notice').style.display="block"
}
function close_notice(){
    document.querySelector('.layer_order_notice').style.display="none"
}
function pop_faq(){
    document.querySelector('.layer_faq').style.display="block"
}
function close_faq(){
    document.querySelector('.layer_faq').style.display="none"
}