// *** 판매 희망가 ***
const pricebox = document.querySelector(".instant_group .price_now");
const errormsg = document.querySelector(".price_warning");
const price_now = 540000; //서버에서 가져옴(즉시판매가)
const bid_input = document.querySelector("#bid_input");
let fees = -8100; //수수료
document.querySelector(".fees").innerHTML = fees.toLocaleString("ko-KR") + "원";
// 입찰 선택시 가격에 따라 경고
bid_input.addEventListener("input", (e) => {
  let str_price = e.target.value;
  // 숫자 외 입력 불가 (숫자도 10글자까지)
  if (str_price.length > e.target.maxLength) {
    e.target.value = str_price.slice(0, e.target.maxLength);
  }
  // .price_now에 has_danger has_warning추가
  if (str_price < 30000) {
    pricebox.classList.add("has_warning");
    pricebox.classList.add("has_danger");
    errormsg.style.display = "block";
  } else {
    pricebox.classList.remove("has_warning");
    pricebox.classList.remove("has_danger");
    errormsg.style.display = "none";
  }
});
// 1000단위 콤마
bid_input.addEventListener("keyup", function (e) {
  let str_price = e.target.value;
  str_price = Number(str_price.replaceAll(",", ""));
  if (isNaN(str_price)) {
    bid_input.value = "";
  } else {
    const form_price = str_price.toLocaleString("ko-KR");
    bid_input.value = form_price;
  }
});
bid_input.addEventListener("blur", (e) => {
  let str_price = e.target.value;
  str_price = Number(str_price.replaceAll(",", ""));
  // 30000원 미만 입력시 내용이 지워진다.
  if (str_price < 30000) {
    e.target.value = "";
  }
  // 즉시 판매값보다 싸게 부르면 즉시판매로 넘어간다.
  if (price_now >= str_price) {
    $(".header_main .title_txt").html("즉시 판매하기");
    $("#bid").removeClass("on");
    $("#now").addClass("on");
    $(".price_now").removeClass("active_input");
    $("#bid_input").hide();
    $("#now_price").show();
    $(".price_now_title").html("즉시 판매가");
    $(".deadline_info_area").hide();
    $(".step-1 .btn_confirm a").html("즉시 판매 계속");
    $(".step-1 .btn_confirm a").removeClass("disabled");
  }
  //1000원 단위로만 입력 가능하다.
  if (str_price != 0 && str_price % 1000 != 0) {
    const form_price = str_price.toLocaleString("ko-KR");
    bid_input.value = form_price.slice(0, form_price.length - 3) + "000";
  }
  //판매입찰계속 버튼 활성화 조건
  if (str_price >= 30000 && price_now < str_price) {
    $(".step-1 .btn_confirm a").removeClass("disabled");
  } else {
    $(".step-1 .btn_confirm a").addClass("disabled");
  }
  //수수료 반영
  fees = -Math.floor((str_price * 0.015) / 100) * 100;
  document.querySelector(".fees").innerHTML =
    fees.toLocaleString("ko-KR") + "원";
  //정산금액 반영
  document.querySelector('.price_total .amount').innerHTML=(str_price-fees).toLocaleString("ko-KR") + "원";
});
// ***입찰 <-> 즉시***

// .title_txt : 내용 "판매 입찰하기"<->"즉시 판매하기"
// .tab_area.sell_tab .item : class on 추가/제거
// .price_now : class active_input 추가/제거
// .price_now_title : 내용 "판매 희망가"<->"즉시 판매가"
// .deadline_info_area : class style="display=block or none"
// .btn_confirm > a : 내용 "판매 입찰 계속" <->  "즉시 판매 계속"
// 판매입찰누르면 가격 초기화 <-> 가격반영(즉시판매누르면 자동으로 값이 입력되도록!)
function sell_now() {
  //즉시 판매 버튼 클릭
  $(".header_main .title_txt").html("즉시 판매하기");
  $("#bid").removeClass("on");
  $("#now").addClass("on");
  $(".price_now").removeClass("active_input");
  $("#bid_input").hide();
  $("#now_price").show();
  $(".price_now_title").html("즉시 판매가");
  $(".deadline_info_area").hide();
  $(".step-1 .btn_confirm a").html("즉시 판매 계속");
  $(".step-1 .btn_confirm a").removeClass("disabled");
  $(".is_dark span").html("즉시 판매가");
  $(".price_total .amount").html("531,900");
  $(".price_total .unit").html("원");
  // 만약 에러메세지가 있을 때 없애기 위해
  pricebox.classList.remove("has_warning");
  pricebox.classList.remove("has_danger");
  errormsg.style.display = "none";
}
console.log(document.querySelector(".is_dark span"));
function sell_bid() {
  // 판매 입찰 버튼 클릭
  $(".header_main .title_txt").html("판매 입찰하기");
  $("#now").removeClass("on");
  $("#bid").addClass("on");
  $(".price_now").addClass("active_input");
  $("#bid_input").show();
  $("#now_price").hide();
  $(".price_now_title").html("판매 희망가");
  $(".deadline_info_area").show();
  $(".step-1 .btn_confirm a").html("판매 입찰 계속");
  $(".step-1 .btn_confirm a").addClass("disabled");
  $(".is_dark span").html("판매 희망가");
  $(".price_total .amount").html("-");
  $(".price_total .unit").html("");
  document.getElementById("bid_input").value = ""; // bid_input value 값 초기화
}
// 마감기한 버튼 클릭
$(document).on("click", ".deadline_tab a", function () {
  if ($(".deadline_tab a").has(".is_active")) {
    // is_active 클래스가 존재하면 length 값은 1이상이 됨. -> true
    $(".deadline_tab a").removeClass("is_active");
  }
  this.className += " is_active";
  //🌈날짜 계산 후 반영 필요
  const today = new Date();
  let deadline = new Date(today);
  let period = this.innerHTML.replace("일", "").trim();
  period = Number(period); //숫자로 변환하지 않으면 잘못 계산함
  deadline.setDate(today.getDate() + period);
  console.log("deadline:" + deadline);
  let dmonth = deadline.getMonth() + 1;
  let deadline_txt =
    period +
    "일 (" +
    deadline.getFullYear() +
    "/" +
    dmonth +
    "/" +
    deadline.getDate() +
    " 마감)";
  document.querySelector(".deadline_txt").innerHTML = deadline_txt;
});
// 판매 입찰하기 입찰 마감기한 클릭시 버튼 활성화
let wish_price = 0;
//총 결재금액
const span_price_total = document.querySelector(
  ".price_total.order_info .amount"
);
function step2() {
  document.querySelector(".step-1").style.display = "none";
  document.querySelector(".step-2").style.display = "block";
  // step2로 넘어가기전에 즉시판매가 or 판매희망가 선택된 것을 가져온다.
  // console.log(document.querySelector('#now.on'))
  if (document.querySelector("#now.on") != null) {
    wish_price = price_now;
    console.log(price_now);
    document.querySelector(".product_price").innerHTML =
      wish_price.toLocaleString("ko-KR") + "원";
  } else {
    wish_price = Number(bid_input.value.replaceAll(",", ""));
    console.log("wish_price:" + wish_price);
    document.querySelector(".product_price").innerHTML = bid_input.value + "원";
  }
  //수수료 = -(가격*0.015 /100 )* 100 = 1.5% 100의자리수
  fees = -Math.floor((wish_price * 0.015) / 100) * 100;
  console.log("fees:" + fees);
  document.querySelector(".fees").innerHTML =
    fees.toLocaleString("ko-KR") + "원";
  document.querySelector(".fees2").innerHTML =
    fees.toLocaleString("ko-KR") + "원";
  //정산금액 = 즉시판매가(판매희망가) + 수수료
  price_total = wish_price + fees;
  document.querySelector(".order_info .amount").innerHTML =
    price_total.toLocaleString("ko", "KR");
  span_price_total.innerHTML = price_total.toLocaleString("ko-KR");
}

/***^^^^^^*****step2*^^^^^^******/
//판매 정산 계좌 변경
function pop_payout_account(){
  document.querySelector('.layer_payout_account').style.display="block"
}
function close_payout_account(){
  document.querySelector('.layer_payout_account').style.display="none"
}
// 새 주소 추가 레이어 열고 닫기
function close_new_delivery() {
  document.querySelector(".layer_delivery").style.display = "none";
}
function pop_new_delivery() {
  document.querySelector(".layer_delivery").style.display = "block";
}

// 주소 변경 레이어 열고 닫기
function close_address() {
  document.querySelector(".layer_address").style.display = "none";
}
function pop_address() {
  document.querySelector(".layer_address").style.display = "block";
}

// 주소 리스트중 하나 클릭시 레이어창 닫고 내용 반영
const address = document.querySelectorAll(".select");
address.forEach((item) => {
  item.addEventListener("click", () => {
    address.forEach((e) => {
      // console.log(e.childNodes[3].childNodes[0]);
      e.childNodes[3].style.display = "none";
    });
    let edit_address =
      item.childNodes[1].childNodes[3].childNodes[5].childNodes[1].innerHTML;
    // console.log(edit_address)
    item.childNodes[3].style.display = "block";
    close_address();
    document.querySelector(".address_info .address_txt").innerHTML =
      edit_address;
  });
});
// 배송 요청사항 열고 닫기
function close_layer_shipping_memo() {
  document.querySelector(".layer_shipping_memo").style.display = "none";
}
function pop_layer_shipping_memo() {
  document.querySelector(".layer_shipping_memo").style.display = "block";
}

//배송 요청 리스트 선택시 효과
const selectable = document.querySelectorAll(
  ".button_shipping_memo_wrap.selectable"
);
const direct_input = document.querySelector(
  ".button_shipping_memo_wrap.direct_input"
);
const memo_apply_btn = document.querySelector(
  ".shipping_memo_buttons .btn_apply"
);
selectable.forEach((item) => {
  item.addEventListener("click", () => {
    selectable.forEach((e) => {
      e.classList.remove("checked");
      e.childNodes[2].style.display = "none";
    });
    //직접입력부분도 효과 없애준다.
    direct_input.classList.remove("checked");
    document.querySelector(".direct_input img").style.display = "none";
    document.querySelector(".textarea_shipping_memo").style.display = "none";
    //클릭한 애만 효과준다.
    item.classList.add("checked");
    item.childNodes[2].style.display = "block";
    //직접입력 갔다가 돌아왔을때 방지용
    memo_apply_btn.classList.remove("disabled");
  });
});

//직접 입력 선택시 효과주기
direct_input.addEventListener("click", (e) => {
  document.querySelector(".textarea_shipping_memo").style.display = "block";
  //위에 selectable 체크 그림 없애주기
  selectable.forEach((e) => {
    e.classList.remove("checked");
    e.childNodes[2].style.display = "none";
  });
  direct_input.classList.add("checked");
  document.querySelector(".direct_input img").style.display = "block";
  // 적용하기 버튼 비활성화
  memo_apply_btn.classList.add("disabled");
});

//직접 입력 선택시 직접 입력에 키를 입력할 때 버튼 활성화
//직접 입력 선택시 직접 입력에 내용이 없다면 비활성화
let text = document.querySelector(".shipping_memo textarea");
text.addEventListener("input", () => {
  if (text.value != "") {
    memo_apply_btn.classList.remove("disabled");
  } else {
    memo_apply_btn.classList.add("disabled");
  }
});

//배송 요청사항 내용 반영하기
function update_layer_shipping_memo() {
  let checkedtext = document.querySelector(
    ".layer_shipping_memo .checked p"
  ).innerHTML;
  const input = document.querySelector(
    ".button_shipping_memo_wrap .placeholder"
  );
  //직접입력할 땐 textarea의 값을 전달
  if (checkedtext == "직접 입력") {
    checkedtext = document.querySelector(".layer_shipping_memo textarea").value;
    console.log(checkedtext);
  }
  input.innerHTML = checkedtext;
  document.querySelector(".layer_shipping_memo").style.display = "none";
}
// 현금 영수증 정보 열고 닫기
function pop_processing_fee(){
  document.querySelector('.layer_processing_fee').style.display="block"
}
function close_processing_fee(){
  document.querySelector('.layer_processing_fee').style.display="none"
}
// 현금 영수증 레이어창
  //선택시 내용 셀렉트 div 바뀌는거 .drop_item".item_on"

  //receipt_config_form childnodes이용해서 input_box receipt_config_wrap display설정

  // input_box input_title 내용 변경 휴대폰번호<->카드번호

  // 버튼활성화 disabled고려
// 새 카드추가 열고 닫기
function close_card() {
  document.querySelector(".layer_card").style.display = "none";
}
function pop_card() {
  document.querySelector(".layer_card").style.display = "block";
}

// 새 카드 추가 레이어창 저장시 div 추가 other_card에

// 새 카드 저장 시 레이어 창

// 카드 드롭다운 클릭시 카드리스트 나오고 선택시 반영하고 닫기
const card_drop_btn = document.querySelector('.clickable_card img')
const main_card = document.querySelector('.main_card .clickable_card')
console.log(card_drop_btn)
const card_drop_div = document.querySelector('.other_card')
card_drop_btn.addEventListener('click',()=>{
    if(card_drop_div.style.display=='none'){
        card_drop_div.style.display='block'
    }else{
        card_drop_div.style.display='none'
    }
})
const cards = document.querySelectorAll('.other_card_item')
cards.forEach((card)=>{
    card.addEventListener('click',()=>{
        main_card.childNodes[1].innerHTML = card.childNodes[1].innerHTML
        card_drop_div.style.display='none'
    })
})
//결재 방법 선택시 테두리 효과
const items = document.querySelectorAll(".method");
items.forEach((item) => {
  item.addEventListener("click", () => {
    items.forEach((e) => {
      e.classList.remove("selected");
    });
    item.classList.add("selected");
  });
});

//체크 박스 모두 선택 시 결재하기 버튼 활성화

const checks = document.querySelectorAll(".check");
console.log(checks)
checks.forEach((check)=>{
    check.addEventListener('click',getCheck)
})
function getCheck() {
    const query = 'input[class=check]:checked';
    const selectedElements = document.querySelectorAll(query);
    const cnt = selectedElements.length;
    if (cnt == 4) {
        document.querySelector('#submit').classList.remove('disabled')
    } else {
        document.querySelector('#submit').className = 'btn full solid disabled';
    }
}


//결재하기 버튼 클릭시 경고창 이후 결재완료페이지
function pop_order_price_confirm(){
    document.querySelector('.layer_order_price_confirm').style.display="block"
}
function close_order_price_confirm(){
    document.querySelector('.layer_order_price_confirm').style.display="none"
}