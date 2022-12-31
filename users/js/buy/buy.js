// *** 구매 희망가 ***
pricebox = document.querySelector('.instant_group .price_now');
errormsg = document.querySelector('.price_warning')
price_now = 540000; //서버에서 가져옴(즉시구매가)

// 입찰 선택시 가격에 따라 경고
document.querySelector('#bid_input').addEventListener('input', e=>{
    let str_price=e.target.value;
    // 숫자 외 입력 불가 (숫자도 10글자까지)
    if (str_price.length > e.target.maxLength){
        e.target.value = str_price.slice(0, e.target.maxLength);
    }
    // .price_now에 has_danger has_warning추가
    if(str_price < 30000 ){
        pricebox.classList.add('has_warning')
        pricebox.classList.add('has_danger')
        errormsg.style.display="block"
    } else {
        pricebox.classList.remove('has_warning')
        pricebox.classList.remove('has_danger')
        errormsg.style.display="none"
    }
});
document.querySelector('#bid_input').addEventListener('blur', e=>{
    let str_price=e.target.value;
    // 30000원 미만 입력시 내용이 지워진다.
    if(str_price < 30000 ){
        e.target.value='';
    }
    // 1000원 단위가 아닐 시 다른 곳 클릭할때 1000원 미만단위 버림
    if(str_price%1000 !=0){
        e.target.value=Math.floor(str_price/1000)*1000;
    }
    // 즉시 구매값보다 비싸게 부르면 즉시구매로 넘어간다.
    if(price_now < str_price){
        $(".header_main .title_txt").html("즉시 구매하기");
        $("#bid").removeClass("on");
        $("#now").addClass("on");
        $(".price_now").removeClass("active_input");
        $("#bid_input").hide();
        $("#now_price").show();
        $(".price_now_title").html("즉시 구매가");
        $(".deadline_info_area").hide();
        $(".step-1 .btn_confirm a").html("즉시 구매 계속");
        $(".step-1 .btn_confirm a").removeClass("disabled")
    }
    if((str_price >= 30000)  &&(price_now > str_price) ){
        $(".step-1 .btn_confirm a").removeClass("disabled")
    }else{
        $(".step-1 .btn_confirm a").addClass("disabled")
    }
})
// ***입찰 <-> 즉시***

    // .title_txt : 내용 "구매 입찰하기"<->"즉시 구매하기"
    // .tab_area.buy_tab .item : class on 추가/제거
    // .price_now : class active_input 추가/제거
    // .price_now_title : 내용 "구매 희망가"<->"즉시 구매가"
    // .deadline_info_area : class style="display=block or none"
    // .btn_confirm > a : 내용 "구매 입찰 계속" <->  "즉시 구매 계속"
    // 구매입찰누르면 가격 초기화 <-> 가격반영(즉시구매누르면 자동으로 값이 입력되도록!)
    function buy_now() {    //즉시 구매 버튼 클릭
        $(".header_main .title_txt").html("즉시 구매하기");
        $("#bid").removeClass("on");
        $("#now").addClass("on");
        $(".price_now").removeClass("active_input");
        $("#bid_input").hide();
        $("#now_price").show();
        $(".price_now_title").html("즉시 구매가");
        $(".deadline_info_area").hide();
        $(".step-1 .btn_confirm a").html("즉시 구매 계속");
        $(".step-1 .btn_confirm a").removeClass("disabled")
        // 만약 에러메세지가 있을 때 없애기 위해
        pricebox.classList.remove('has_warning')
        pricebox.classList.remove('has_danger')
        errormsg.style.display="none"
    }

    function buy_bid() {  // 구매 입찰 버튼 클릭
        $(".header_main .title_txt").html("구매 입찰하기");
        $("#now").removeClass("on");
        $("#bid").addClass("on");
        $(".price_now").addClass("active_input");
        $("#bid_input").show();
        $("#now_price").hide();
        $(".price_now_title").html("구매 희망가");
        $(".deadline_info_area").show();
        $(".step-1 .btn_confirm a").html("구매 입찰 계속");
        $(".step-1 .btn_confirm a").addClass("disabled")
        document.getElementById("bid_input").value=''; // bid_input value 값 초기화

    }
// 마감기한 버튼 클릭
$(document).on('click', '.deadline_tab a', function(){
    if($(".deadline_tab a").has('.is_active')){
        // is_active 클래스가 존재하면 length 값은 1이상이 됨. -> true
       $(".deadline_tab a").removeClass("is_active");
    }
    this.className+=" is_active";
    // setExpire();
});


/*
//천단위 콤마 생성
function addComma(data) {
    return data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//천단위 콤마 제거 -- 서버에 보낼 때
function minusComma(data){
    return data.replace(/[^\d]+/g, "");
}
// 천단위 콤마 생성 실행부분
in_amount.addEventListener('input', function(e) {
    let value = e.target.value;
    value = Number(value.replaceAll(',', ''));
    if(isNaN(value)) {
        in_amount.value = '';
    }else {
        const formatValue = value.toLocaleString('ko-KR');
        in_amount.value = formatValue;
    }
});

*/
// 구매 입찰하기 입찰 마감기한 클릭시
function step2(){
    document.querySelector('.step-1').style.display="none"
    document.querySelector('.step-2').style.display="block"
}



/***^^^^^^*****step2*^^^^^^******/

// 새 주소 추가 레이어 옇고 닫기
function close_new_delivery(){
    document.querySelector('.layer_new_delivery').style.display="none"
}
function pop_new_delivery(){
    document.querySelector('.layer_new_delivery').style.display="block"
}

// 주소 변경 레이어 열고 닫기
function close_address(){
    document.querySelector('.layer_address').style.display="none"
}
function pop_address(){
    document.querySelector('.layer_address').style.display="block"
}

// 주소 리스트중 하나 클릭시 레이어창 닫고 내용 반영

// 배송 요청사항 열고 닫기
function close_layer_shipping_memo(){
    document.querySelector('.layer_shipping_memo').style.display="none"
}
function pop_layer_shipping_memo(){
    document.querySelector('.layer_shipping_memo').style.display="block"
}

//배송 요청 리스트 선택시 효과
const selectable = document.querySelectorAll(".button_shipping_memo_wrap.selectable");
const direct_input=document.querySelector(".button_shipping_memo_wrap.direct_input");
const memo_apply_btn=document.querySelector(".shipping_memo_buttons .btn_apply");
selectable.forEach((item)=>{
    item.addEventListener('click',()=>{
        selectable.forEach((e)=>{
            e.classList.remove('checked');
            e.childNodes[2].style.display="none";
        })
        //직접입력부분도 효과 없애준다.
        direct_input.classList.remove('checked')
        document.querySelector(".direct_input img").style.display="none"
        document.querySelector(".textarea_shipping_memo").style.display="none"
        //클릭한 애만 효과준다.
        item.classList.add('checked');
        item.childNodes[2].style.display="block";
        //직접입력 갔다가 돌아왔을때 방지용
        memo_apply_btn.classList.remove('disabled')

    })
})

//직접 입력 선택시 효과주기
direct_input.addEventListener('click',(e)=>{
    document.querySelector(".textarea_shipping_memo").style.display="block"
    //위에 selectable 체크 그림 없애주기
    selectable.forEach((e)=>{
        e.classList.remove('checked');
        e.childNodes[2].style.display="none";
    })
    direct_input.classList.add('checked')
    document.querySelector(".direct_input img").style.display="block"
    // 적용하기 버튼 비활성화
    memo_apply_btn.classList.add('disabled')
})
//직접 입력 선택시 직접 입력에 키를 입력할 때 버튼 활성화
//직접 입력 선택시 직접 입력에 내용이 없다면 비활성화
let text = document.querySelector('.shipping_memo textarea')
text.addEventListener('input',()=>{
    if(text.value!=""){
        memo_apply_btn.classList.remove('disabled')
    }else{
        memo_apply_btn.classList.add('disabled')
    }
})
//배송 요청사항 내용 반영하기
function update_layer_shipping_memo(){
    let checkedtext =document.querySelector('.layer_shipping_memo .checked p').innerHTML
    const input = document.querySelector('.button_shipping_memo_wrap .placeholder')
    //직접입력할 땐 textarea의 값을 전달
    if(checkedtext=="직접 입력"){
        checkedtext = document.querySelector('.layer_shipping_memo textarea').value
        console.log(checkedtext)    }
    input.innerHTML = checkedtext
    document.querySelector('.layer_shipping_memo').style.display="none"
}
// 포인트 ? 열고 닫기
function close_point(){
    document.querySelector('.layer_point').style.display="none"
}
function pop_point(){
    document.querySelector('.layer_point').style.display="block"
}

//모두사용 클릭시 input에 value
$(document).on('click', '.btn_use_credit', function(){
    console.log(document.querySelector('.value_current').text)
    $(".input_credit").val(3,000);
    //서버에서 받아온 총 포인트 값
    //입찰시 -> 최종 주문 정보에 "결제 시점에 최대 사용"
    //즉시구매시 -> "-3,000P"
});


// 새 계좌 추가 레이어 창 열고 닫기 -- 계좌 간편결재 없애는 걸로!!

// 새 카드추가 열고 닫기

//결재 방법 선택시 테두리 효과

//체크박스 4개 선택시 버튼 활성화