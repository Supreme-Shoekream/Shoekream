/**
 * Strp1: 가격/입찰기한 설정
 * price_box: 잘못된 가격을 입력했을 때 has_warning, has_danger 클래스 추가
 * errormsg: 그때 에러메세지 display설정
 * bid_input: 판매입찰시 가격 입력하는 input
 * price_now: 서버에서 받아온 즉시 판매가
 */
const pricebox = document.querySelector(".instant_group .price_now");
const errormsg = document.querySelector(".price_warning");
const bid_input = document.querySelector("#bid_input");
let price_now;
/**
 *서버에 보낼 데이터 선언
 * isNow
 * price = 핀매희망가
 * period
 * cardInfo
 * accountInfo
 * sender
 * senderHp
 * senderAddress
 * deliveryMemo
 */
let is_now = true;
let wish_price = 0;
let period = 0;
let card_info= '';
let delivery_memo ='';
let fees = 0;    //수수료
document.querySelector('.deadline_txt').innerHTML = calc_deadline(30)   //초기 deadline 세팅

let now_price = document.getElementById('now_price').innerHTML
/**
 * 🤍 기능1 입찰 <-> 즉시
 * .title_txt : 내용 "판매 입찰하기"<->"즉시 판매하기"
 * .tab_area.sell_tab .item : class on 추가/제거
 * .price_now : class active_input 추가/제거
 * .price_now_title : 내용 "판매 희망가"<->"즉시 판매가"
 * .deadline_info_area : class style="display=block or none"
 * .btn_confirm > a : 내용 "판매 입찰 계속" <->  "즉시 판매 계속"
 * 판매입찰누르면 가격 초기화 <-> 가격반영(즉시판매누르면 자동으로 값이 입력되도록!)
 */
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
  $(".price_total .amount").html((price_now+fees).toLocaleString("ko-KR"));
  $(".price_total .unit").html("원");
  $(".step-3 .main_title").html('즉시 판매가 완료되었습니다.')
  // 만약 에러메세지가 있을 때 없애기 위해
  pricebox.classList.remove("has_warning");
  pricebox.classList.remove("has_danger");
  errormsg.style.display = "none";
  is_now=true;
  period = 0;
}

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
  $(".price_total .unit").html("원");
  $(".step-3 .main_title").html('판매 입찰이 완료되었습니다.')
  document.getElementById("bid_input").value = ""; // bid_input value 값 초기화
  is_now=false;
  period=30;
}
/**
 * 🤍 기능2 즉시판매가가 없을 경우 : 판매입찰을 기본으로 설정하고, 즉시판매를 못누르도록 한다.
 */
if(document.querySelector('#now_price').innerHTML.trim() == '-'){
  console.log(document.querySelector('#now_price').innerHTML);
  sell_bid();
  const now = document.getElementById('now');
  now.innerHTML =`<a onclick="#" class="item_link">즉시 판매</a>`
  price_now = -1;   //즉시판매가로 넘어갈 수 없도록 설정
}else{
  price_now =  Number(document.querySelector('#now_price').innerHTML.replaceAll(',',''));
  fees = -Math.floor(price_now*0.015/100)*100
  document.querySelector('.price_total .amount').innerHTML=(price_now+fees).toLocaleString("ko-KR");
}
/**
 * 🤍 기능3: 입찰 선택시 input에 주는 이벤트 추가
 * input( value 속성의 값이 바뀔 때마다 발생하는 이벤트) -> 입찰 선택시 가격에 따라 경고
 * keyup( value가 업데이트 된 이후에 키보드에서 손을 떼면 발생하는 이벤트 ) -> 1000단위 콤마
 * blur(요소의 포커스가 해제되었을 때 발생하는 이벤트) -> 1000원단위, 입찰버튼 황성화
 */
document.querySelector(".fees").innerHTML = fees.toLocaleString("ko-KR") + "원";
// 입찰 선택시 가격에 따라 경고
bid_input.addEventListener("input", (e) => {
  let str_price = e.target.value;
  // 숫자 외 입력 불가 (숫자도 10글자까지)
  if (str_price.length > e.target.maxLength) {
    e.target.value = str_price.slice(0, e.target.maxLength);
  }
  str_price =  Number(str_price.replaceAll(',', ''));
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
    sell_now()
    return
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
  str_price=Math.floor(str_price/1000)*1000
  document.querySelector('.price_total .step1.amount').innerHTML=(str_price+fees).toLocaleString("ko-KR") ;
});

/**
 * 🤍 기능4: 마감기한 버튼 클릭시 날짜 계산해서 출력
 */
function calc_deadline(days){
  const today = new Date();
  let deadline = new Date(today);
  days = Number(days)
  deadline.setDate(today.getDate()+days);
  let dmonth = deadline.getMonth()+1
  return days + "일 ("+deadline.getFullYear()+"/"+dmonth+"/"+deadline.getDate()+" 마감)";
}
$(document).on("click", ".deadline_tab a", function () {
  if ($(".deadline_tab a").has(".is_active")) {
    // is_active 클래스가 존재하면 length 값은 1이상이 됨. -> true
    $(".deadline_tab a").removeClass("is_active");
  }
  this.className += " is_active";
  //🌈날짜 계산 후 반영 필요
  let periodtxt =this.innerHTML.replace('일','').trim();
  periodtxt = Number(periodtxt) //숫자로 변환하지 않으면 잘못 계산함
  period = periodtxt; //서버에 보낼 데이터와 연결
  document.querySelector(".deadline_txt").innerHTML = calc_deadline(period);
});
// 판매 입찰하기 입찰 마감기한 클릭시 버튼 활성화


/**
 * 🤍 기능5 : step2로 넘어가기전에 즉시판매가 or 판매희망가 선택된 것을 가져온다. + 입찰마감기한 저장
 * wish_price: 판매희망가
 * fees: 수수료 = -(가격*0.015 /100 )* 100 = 1.5% 100의자리수
 * 정산금액 = 즉시판매가(판매희망가) + 수수료
 */
function step2() {
  document.querySelector(".step-1").style.display = "none";
  document.querySelector(".step-2").style.display = "block";
  // step2로 넘어가기전에 즉시판매가 or 판매희망가 선택된 것을 가져온다.
  // console.log(document.querySelector('#now.on'))
  if (document.querySelector("#now.on") != null) {
    wish_price = price_now;
    period = 0;
    console.log("즉시판매가"+price_now);
    document.querySelector(".product_price").innerHTML = wish_price.toLocaleString("ko-KR") + "원";
  } else {
    if(Number(period) == 0) { period=30; }  // 설정 클릭 안하면 30일로 세팅!
    wish_price = Number(bid_input.value.replaceAll(",", ""));
    console.log("wish_price:" + wish_price);
    document.querySelector(".product_price").innerHTML = bid_input.value + "원";
  }
  fees = -Math.floor((wish_price * 0.015) / 100) * 100;
  console.log("fees:" + fees);
  document.querySelector(".fees").innerHTML = fees.toLocaleString("ko-KR") + "원";
  document.querySelector(".fees2").innerHTML = fees.toLocaleString("ko-KR") + "원";
  //정산금액 = 즉시판매가(판매희망가) + 수수료
  price_total = wish_price + fees;
  document.querySelector(".order_info .amount").innerHTML = price_total.toLocaleString("ko", "KR");
  document.querySelector(".price_total.order_info .amount").innerHTML = price_total.toLocaleString("ko-KR");
  document.querySelector(".step-2 .sell_total_confirm .amount").innerHTML = price_total.toLocaleString("ko-KR");
}

/**
 * 🤍 기능6 판매 정산 계좌 변경
 * 열고닫기
 * 드롭다운
 * 유효성검사
 * member update를 위한 fetch
 * */
//판매 정산 계좌 변경
function pop_payout_account(){
  document.querySelector('.layer_payout_account').style.display="block"
}
function close_payout_account(){
  document.querySelector('.layer_payout_account').style.display="none"
  document.querySelector('#input_acc').value =''
}
function dropdown(){
  // 클릭했을때 style.display==="none" => block 아니면 none
  const bankId = document.querySelector(".bank_dropdown_menu")
  if(bankId.style.display == "none"){
    bankId.style.display = "block"
  }else{
    bankId.style.display = "none"
  }
}
const bankId = document.querySelector(".bank_dropdown_menu")
const bankList = document.querySelectorAll('.bank_list_li')
const bankName = document.querySelector("#input_bank")
bankList.forEach((target) => {
  target.addEventListener("click", function(){
    bankName.placeholder = target.textContent
    bankList.forEach((e) => {
      e.classList.remove("select_bank")
      e.childNodes[1].style.display="none"
    })

    bankName.classList.add("input_txt_color")
    target.classList.add("select_bank")
    target.childNodes[1].style.display = "block"
    bankId.style.display = "none"
  })})
// 버튼활성화
const accountForm = document.querySelector('#input_acc')
const accountHolder = document.querySelector('#input_holder')
const submitButton = document.querySelector('#submit_btn_acc')
bankName.addEventListener('keyup', activeEvent);
accountForm.addEventListener('keyup', activeEvent);
let strAcc
function activeEvent() {
  switch(accountHolder.value && accountHolder.value && bankName.placeholder != '선택하세요' && validateAcc(strAcc)){
    case true : submitButton.disabled = true; submitButton.classList.remove('disabled'); submitButton.addEventListener('click',submit_account); break;
    case false : submitButton.disabled = false; submitButton.classList.add('disabled'); submitButton.removeEventListener('click',submit_account); break;
  }
}
// 버튼 submit
function submit_account() {
  if (!submitButton.classList.contains('disabled')) {
    let bankName = document.querySelector('#input_bank').placeholder
    let accNum = document.querySelector('#input_acc').value
    fetch('http://localhost:8889/api/my/account', {
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        "transaction_time": `${new Date()}`,
        "resultCode": "ok",
        "description": "정상",
        "data": {
          "bank": `${bankName}`,
          "accNumber": `${accNum}`
        }
      }),
    }).then((res) => {
      document.getElementById('accountInfo').innerHTML = bankName +" " + accNum
      document.getElementById('bank_name').innerHTML = bankName
      document.getElementById('acc_number').innerHTML= accNum
      document.querySelector('.member_name').innerHTML = document.getElementById('name').innerHTML
      close_payout_account()
      return;
    })
  }
}

// 계좌번호 정규 표현식
function validateAcc(strAcc){
  const reg_acc = /^[0-9]{2,}$/;
  if(!reg_acc.test(''+strAcc)){
    return false;
  }
  return true;
}
// 계좌번호 유효성 검사
document.querySelector('#input_acc').addEventListener('input', e => {
  strAcc = e.target.value;
  if(!validateAcc(strAcc)){
    document.querySelector('#input_acc_box').className='has_button input_box has_error';
    document.querySelector('#input_acc').setAttribute('validateresult',false);
  } else {
    document.querySelector('#input_acc_box').className='has_button input_box fill';
    document.querySelector('#input_acc').setAttribute('validateresult',true);

  }

})
/**
 * 🤍 기능7 새 주소 추가
 * 열고닫기
 * 유효성검사
 * create를 위한 fetch
 * */
const sender_dd = document.getElementById('sender')
const senderHp_dd = document.getElementById('senderHp')
const senderAddress_dd = document.getElementById('senderAddress')
function close_new_delivery() {
  document.querySelector(".layer_delivery").style.display = "none";
  document.querySelector('.layer_delivery').style.display="none"
  document.querySelector('#name_input').value=''
  document.querySelector('#hp_input').value=''
  document.querySelector('#sample6_postcode').value=''
  document.querySelector('#sample6_address').value=''
  document.querySelector('#sample6_detailAddress').value=''
  document.querySelector('#check1').checked = false
}
function pop_new_delivery() {
  document.querySelector(".layer_delivery").style.display = "block";
}
new_address_send_btn=document.querySelector('.layer_delivery #submit_btn')
new_address_send_btn.addEventListener('click',send_create)
function send_create(){
  const createName = document.querySelector('#name_input');
  const createHp = document.querySelector('#hp_input');
  const createZipcode = document.querySelector('#sample6_postcode');
  const createAddress1 = document.querySelector('#sample6_address');
  const createAddress2 = document.querySelector('#sample6_detailAddress')
  const createAddressBasic = document.querySelector('#check1').checked;
  fetch('/api/my/address', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      "transaction_time":`${new Date()}`,
      "resultCode":"ok",
      "description":"정상",
      "data":{
        "name": `${createName.value}`,
        "hp": `${createHp.value}`,
        "zipcode": `${createZipcode.value}`,
        "address1": `${createAddress1.value}`,
        "address2": `${createAddress2.value}`,
        "addressBasic": `${createAddressBasic}`
      }
    }),
  }).then((res)=>{
    // 주소가 아예 등록되지 않았더라면 주소창이 닫혀있기때문에, 열어주고, 빈주소창은 닫아준다.
    empty_delivery_info = document.querySelector('.empty_delivery_info')
    delivery_info = document.querySelector('.delivery_info')
    if(delivery_info.style.display=='none'){
      delivery_info.style.display='block'
      empty_delivery_info.style.display='none'
    }
    //레이어창 닫고 새로운 정보 반영
    sender_dd.innerHTML=createName.value
    senderHp_dd.innerHTML=hp_decode(createHp.value.toString())
    senderAddress_dd.innerHTML='('+createZipcode.value+') '+createAddress1.value+" "+createAddress2.value
    close_new_delivery()
    document.querySelector('#check1').checked = false
    return;
  })
}
function hp_decode(hp){
  return hp.substring(0,3)+"-"+hp.substring(4,5)+"***-*"+hp.substring(8,11)
}

function maxLengthCheck(object){
  if (object.value.length > object.maxLength){
    object.value = object.value.slice(0, object.maxLength);
  }
}

// 디바운스
let timer=false;//최초 false
const debounce=(e, callback)=> {
  if (timer) {
    clearTimeout(timer);
  }
  timer = setTimeout(function () {
    callback('' + e.target.value);
  }, 100); //200ms 이후 반응(디바운스)
}

// 이름 정규 표현식
function validateName(strName){
  // const reg_name =  /^[가-힣a-zA-Z]+$/;
  const reg_name = /^[가-힣]{2,6}$/;
  if(!reg_name.test(''+strName)){
    return false;
  }
  return true;
}

// 휴대폰 번호 정규 표현식
function validateHp(strHp){
  const reg_hp = /^01(?:0|1|6|7|8|9)(?:\d{3}|\d{4})\d{4}$/;
  if(!reg_hp.test(''+strHp)){
    return false;
  }
  return true;
}

// 이름 유효성 검사
document.querySelector('#name_input').addEventListener('input', e=>{
  let strName=e.target.value;
  let errorMsg='';
  if(!validateName(strName)){
    errorMsg='올바른 이름을 입력해주세요. (2 - 50자)';
    document.querySelector('#name_input_box').className='has_button input_box has_error';
    document.querySelector('#name_input').setAttribute('validateresult',false);
  } else {
    document.querySelector('#name_input_box').className='has_button input_box fill';
    document.querySelector('#name_input').setAttribute('validateresult',true);
  }
  document.querySelector('#name_input_error').innerHTML=errorMsg;
});

// 휴대폰 번호 유효성 검사
document.querySelector('#hp_input').addEventListener('input', e=>{
  debounce(e, strHp=>{
    let errorMsg='';
    if(!validateHp(strHp)){
      errorMsg='휴대폰 번호를 정확히 입력해주세요.';
      document.querySelector('#hp_input_box').className='input_box has_error';
      document.querySelector('#hp_input').setAttribute('validateresult',false);
    } else {
      document.querySelector('#hp_input_box').className='input_box fill';
      document.querySelector('#hp_input').setAttribute('validateresult',true);
    }
    document.querySelector('#hp_input_error').innerHTML=errorMsg;
  })
});

let strName
let strHp
document.querySelectorAll('#name_input').forEach((item) =>{
  item.addEventListener('blur', e=>{
    strName=e.target.value;
    if((validateName(strName))&&(validateHp(strHp))){
      $("#submit_btn").addClass("active");
      $("#submit_btn").removeClass("disabled")
    }else{
      $("#submit_btn").removeClass("active");
      $("#submit_btn").addClass("disabled")
    }
  })
})

document.querySelectorAll('#hp_input').forEach((item) =>{
  item.addEventListener('blur', e=>{
    strHp=e.target.value;
    if((validateName(strName))&&(validateHp(strHp))){
      $("#submit_btn").addClass("active");
      $("#submit_btn").removeClass("disabled")
    }else{
      $("#submit_btn").removeClass("active");
      $("#submit_btn").addClass("disabled")
    }
  })
});

/**
 * 🤍 기능8 주소 변경
 * 열고 닫기
 * 사용자의 주소 리스트를 불러오는 fetch
 * 주소리스트 클릭시 내용 반영 및 닫기
 */
function close_address() {
  document.querySelector(".layer_address").style.display = "none";
}
function pop_address() {
  address_list()
  document.querySelector(".layer_address").style.display = "block";
}
async function address_list(){
  let addressList="";
  try{
    const response_basic = await fetch(`/api/my/address/basic`);
    const data_basic = await response_basic.json();
    console.log(data_basic);
    data_basic.forEach(dto=>{
      let dto_hp = hp_decode(dto.hp)
      addressList+=`
                <div class="my_item is_active select">
                    <div class="info_bind">
                        <!---->
                        <div class="address_info">
                            <div class="name_box"><span class="name">${dto.name}</span><span class="mark">기본
                배송지</span></div>
                            <p class="phone">`+dto_hp+`</p>
                            <div class="address_box"><span class="zipcode">${'('+dto.zipcode+') '}</span><span
                                    class="address">${dto.address1+' '+dto.address2}</span></div>
                        </div>
                    </div>`
      if(senderHp_dd.innerHTML.trim()==dto_hp){
        addressList+=`<div class="btn_bind" style="display: block;"><img src="/img/wcheck.png"></div>
                </div>`
      }else{
        addressList+=`<div class="btn_bind" style="display: none;"><img src="/img/wcheck.png"></div>
                </div>`
      }
    })
    const response_other = await fetch(`/api/my/address/other`);
    const data_other = await response_other.json();
    console.log(data_other);
    data_other.forEach(dto=>{
      let dto_hp = hp_decode(dto.hp)
      addressList+=`
                <div class="my_item select">
                    <div class="info_bind">
                        <!---->
                        <div class="address_info">
                            <div class="name_box"><span class="name">${dto.name}</span><!----></div>
                            <p class="phone">`+dto_hp+`</p>
                            <div class="address_box"><span class="zipcode">${'('+dto.zipcode+') '}</span><span
                                    class="address">${dto.address1+' '+dto.address2}</span></div>
                        </div>
                    </div>`

      if(senderHp_dd.innerHTML.trim()==dto_hp){
        addressList+=`<div class="btn_bind" style="display: block;"><img src="/img/wcheck.png"></div>
                </div>`
      }else{
        addressList+=`<div class="btn_bind" style="display: none;"><img src="/img/wcheck.png"></div>
                </div>`
      }
    })
    console.log('addressList'+addressList)
    document.querySelector('.layer_address .other_list').innerHTML=addressList
    const address = document.querySelectorAll('.select');

    // 주소 리스트중 하나 클릭시 레이어창 닫고 내용 반영
    address.forEach((item) => {
      item.addEventListener('click', () => {
        address.forEach((e) => {
          const img = e.childNodes[2]
          img.style.display='none';
        })
        const address_info = item.childNodes[1].childNodes[3]
        let edit_name = address_info.childNodes[1].childNodes[0].innerHTML
        let edit_hp = address_info.childNodes[3].innerHTML
        let edit_address=address_info.childNodes[5].childNodes[1].innerHTML

        item.childNodes[2].style.display='block';
        close_address();
        sender_dd.innerHTML= edit_name;
        senderHp_dd.innerHTML = edit_hp;
        senderAddress_dd.innerHTML = edit_address;
      })
    })
  }catch(error){
    console.log(error)
  }
}

/**
 * 🤍 기능9 배송 요청사항
 * 열고 닫기
 * 배송 요청 리스트 선택시 효과
 * 직접 입력 선택시 효과주기
 * 직접 입력 선택시 직접 입력에 키를 입력할 때 버튼 활성화
 * 직접 입력 선택시 직접 입력에 내용이 없다면 비활성화
 * 배송 요청사항 내용 반영하기
 */
function close_layer_shipping_memo() {
  document.querySelector(".layer_shipping_memo").style.display = "none";
}
function pop_layer_shipping_memo() {
  document.querySelector(".layer_shipping_memo").style.display = "block";
}

//배송 요청 리스트 선택시 효과
const selectable = document.querySelectorAll(".button_shipping_memo_wrap.selectable");
const direct_input = document.querySelector(".button_shipping_memo_wrap.direct_input");
const memo_apply_btn = document.querySelector(".shipping_memo_buttons .btn_apply");
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
  let checkedtext = document.querySelector(".layer_shipping_memo .checked p").innerHTML;
  const input = document.querySelector(".button_shipping_memo_wrap .placeholder");
  //직접입력할 땐 textarea의 값을 전달
  if (checkedtext == "직접 입력") {
    checkedtext = document.querySelector(".layer_shipping_memo textarea").value;
    console.log(checkedtext);
  }
  input.innerHTML = checkedtext;
  delivery_memo = checkedtext;
  document.querySelector(".layer_shipping_memo").style.display = "none";
}


/**
 * 🤍 기능10 새 카드 추가
 * 열고 닫기
 * 정규식
 * 등록시 fetch로 card 등록
 */
function close_card() {
  document.querySelector(".layer_card").style.display = "none";
  document.querySelector('#cc-1').value=''
  document.querySelector('#cc-2').value=''
  document.querySelector('#cc-3').value=''
  document.querySelector('#cc-4').value=''
  document.querySelector('#birthday_input').value=''
  document.querySelector('#pin_input').value=''
}
function pop_card() {
  document.querySelector(".layer_card").style.display = "block";
}
function close_card_create_layer(){document.querySelector('.card_create_layer').style.display="none"}
function cardBasicOk(){document.querySelector('.card_create_layer').style.display="block"}
function createCard(isBasic) {
  let cardBasic = isBasic
  btnSave = document.querySelector('#submit_card_btn')
  if (btnSave.classList.contains('disabled')) {

  } else {
    let cardNumber = document.querySelector('#cc-1').value +
        document.querySelector('#cc-2').value +
        document.querySelector('#cc-3').value +
        document.querySelector('#cc-4').value
    console.log(cardNumber)
    let dateMm = document.querySelector('#expiration-month').value;
    let dateYy = document.querySelector('#expiration-year').value;
    let birthday = document.querySelector('#birthday_input').value;
    let pin = document.querySelector('#pin_input').value;
    console.log(cardNumber, dateMm, dateYy, birthday, pin)
    fetch('/api/my/payment', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        "transaction_time": `${new Date()}`,
        "resultCode": "ok",
        "description": "정상",
        "data": {
          "cardNumber": `${cardNumber}`,
          "cardMm": `${dateMm}`,
          "cardYy": `${dateYy}`,
          "birthDate": `${birthday}`,
          "cardPw": `${pin}`,
          "cardBasic": `${cardBasic}`
        }
      }),
    }).then((res) => {
      document.getElementById('cardInfo').innerHTML=document.querySelector('#cc-4').value;
      close_card_create_layer()
      close_card()
      document.querySelector('.regist_link').style.display='none'
      document.querySelector('.main_card').style.display='block'
      return;
    })
  }
}

// 카드 번호 정규 표현식
function validateCc1(strCc1){
  const reg_cc1 = /^[0-9]{4}$/;
  if(!reg_cc1.test(''+strCc1)){
    return false;
  }
  return true;
}
// 생년월일 정규 표현식
function validateBirthday(strBirthday){
  const reg_birthday = /([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))/;
  if(!reg_birthday.test(''+strBirthday)){
    return false;
  }
  return true;
}
//비밀번호 정규 표현식
function validatePin(strPin){
  const reg_pin = /^[0-9]{2}$/;
  if(!reg_pin.test(''+strPin)){
    return false;
  }
  return true;
};
// 카드 번호 유효성 검사
document.querySelectorAll('#cc-1').forEach((item) =>{
  item.addEventListener('input', e=>{
    let strCc1=e.target.value;
    let errorMsg='';
    if(!validateCc1(strCc1) && (item.length != 4)){
      errorMsg='올바른 카드 번호를 입력해주세요.(16자)';
      document.querySelector('#card_input_box').className='input_box has_error';
      document.querySelector('#cc-1').setAttribute('validateresult',false);
    } else {
      document.querySelector('#card_input_box').className='input_box fill';
      document.querySelector('#cc-1').setAttribute('validateresult',true);
    }
    document.querySelector('#card_input_error').innerHTML=errorMsg;
  })});

// 생년월일 유효성 검사
document.querySelector('#birthday_input').addEventListener('input', e=>{
  let strBirthday=e.target.value;
  let errorMsg='';
  if(!validateBirthday(strBirthday)){
    errorMsg='정확한 생년월일 6자를 입력해주세요';
    document.querySelector('#birthday_input_box').className='input_box has_error';
    document.querySelector('#birthday_input').setAttribute('validateresult',false);
  } else {
    document.querySelector('#birthday_input_box').className='input_box fill';
    document.querySelector('#birthday_input').setAttribute('validateresult',true);
  }
  document.querySelector('#birthday_input_error').innerHTML=errorMsg;
});

// 비밀번호 유효성 검사
document.querySelector('#pin_input').addEventListener('input', e=>{
  let strPin=e.target.value;
  let errorMsg='';
  if(!validatePin(strPin)){
    errorMsg='비밀번호 앞자리 2자 입력해주세요';
    document.querySelector('#pin_input_box').className='has_button input_box has_error';
    document.querySelector('#pin_input').setAttribute('validateresult',false);
  } else {
    document.querySelector('#pin_input_box').className='has_button input_box fill';
    document.querySelector('#pin_input').setAttribute('validateresult',true);
  }
  document.querySelector('#pin_input_error').innerHTML=errorMsg;
});

// 버튼 활성화
let strCc1
let strBirthday
let strPin
document.querySelectorAll('.input_card').forEach((item) =>{
  item.addEventListener('blur', e=>{
    strCc1=e.target.value;
    if((validateCc1(strCc1))&&(validateBirthday(strBirthday))&&(validatePin(strPin))){
      $("#submit_card_btn").addClass("active");
      $("#submit_card_btn").removeClass("disabled")
    }else{
      $("#submit_card_btn").removeClass("active");
      $("#submit_card_btn").addClass("disabled")
    }
  })
})
document.querySelectorAll('#birthday_input').forEach((item) =>{
  item.addEventListener('blur', e=>{
    strBirthday=e.target.value;
    if((validateCc1(strCc1))&&(validateBirthday(strBirthday))&&(validatePin(strPin))){
      $("#submit_card_btn").addClass("active");
      $("#submit_card_btn").removeClass("disabled")
    }else{
      $("#submit_card_btn").removeClass("active");
      $("#submit_card_btn").addClass("disabled")
    }
  })
})

document.querySelectorAll('#pin_input').forEach((item) =>{
  item.addEventListener('blur', e=>{
    strPin=e.target.value;
    if((validateCc1(strCc1))&&(validateBirthday(strBirthday))&&(validatePin(strPin))){
      $("#submit_card_btn").addClass("active");
      $("#submit_card_btn").removeClass("disabled")
    }else{
      $("#submit_card_btn").removeClass("active");
      $("#submit_card_btn").addClass("disabled")
    }
  })
});

/**
 * 🤍 기능11 카드 리스트 드롭다운
 * 클릭시 fetch 비동기 리스트 출력
 * 선택시 반영하고 닫기
 */
const card_drop_btn = document.querySelector('.clickable_card img')
const main_card = document.querySelector('.main_card .clickable_card')
console.log(card_drop_btn)
const card_drop_div = document.querySelector('.other_card')
card_drop_btn.addEventListener('click',()=>{
    if(card_drop_div.style.display=='none'){
        card_list()
        card_drop_div.style.display='block'
    }else{
        card_drop_div.style.display='none'
    }
})
async function card_list() {
  let cardList = "";
  try {
    const response_basic = await fetch(`/api/my/payment`);
    const data_basic = await response_basic.json();
    console.log(data_basic);
    data_basic.forEach(dto => {
      let last_num= dto.cardNumber.substring(12,16)
      cardList += `
                <li class="other_card_item">
                    <div class="card_info"><span class="card_name"> BC</span>
                        <div class="card_num">
                            <span class="num_bind">
                                <span class="dot"><span class="dot"></span></span><span class="hyphen"></span>
                                <span class="dot"><span class="dot"></span></span><span class="hyphen"></span>
                                <span class="dot"><span class="dot"></span></span><span class="hyphen"></span>
                                <div class="last_num_box"><span class="last_num">${last_num}</span></div>
                            </span><!---->
                        </div>
                    </div>
                </li>`
    })
    document.querySelector('.other_card .other_card_list').innerHTML = cardList
    const cards = document.querySelectorAll('.other_card_item')
    cards.forEach((card)=>{
      card.addEventListener('click',()=>{
        let last_number = card.childNodes[1].childNodes[2].childNodes[1].childNodes[10].childNodes[0].innerHTML
        document.getElementById('cardInfo').innerHTML=last_number
        card_drop_div.style.display='none'
      })
    })
  } catch (error) {
    console.log(error)
  }
}

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

/**
 * 🤍 기능12 체크 박스 모두 선택 시 결재하기 버튼 활성화
 */
const checks = document.querySelectorAll(".check");
console.log(checks)
checks.forEach((check)=>{
    check.addEventListener('click',getCheck)
})
function getCheck() {
    const query = 'input[class=check]:checked';
    const selectedElements = document.querySelectorAll(query);
    const cnt = selectedElements.length;
    // 조건 추가 카드랑 주소 + 계좌
    const delivery_empty = document.querySelector('.empty_delivery_info')
    const card_empty =document.querySelector('.regist_link')
    const account_empty = document.querySelector('#accountInfo').innerHTML
    if (cnt === 4 && delivery_empty.style.display==='none'&& card_empty.style.display==='none' &&
    account_empty !== '등록된 판매 정산 계좌가 없습니다.') {
        document.querySelector('#submit').classList.remove('disabled')
    } else {
        document.querySelector('#submit').className = 'btn full solid disabled';
    }
}



/**
 * 🤍 기능13 결재하기 버튼 클릭시 경고창 이후 결재완료페이지
 * fetch로 판매등록
 * 결재완료 페이지
 */
function pop_order_price_confirm(){
  document.querySelector('.layer_order_price_confirm .price').innerHTML=
      document.querySelector('.sell_check .sell_total_confirm .price .amount').innerHTML + "원"
  const btn_submit = document.getElementById('real_submit');
  btn_submit.addEventListener('click',sendit);
    document.querySelector('.layer_order_price_confirm').style.display="block"
}
function close_order_price_confirm(){
    document.querySelector('.layer_order_price_confirm').style.display="none"
}
function sendit() {
  //request로 필요한 DOM 객체 선택
  const productIdx = document.querySelector('.product_idx');
  const cardInfo = document.getElementById('cardInfo');
  const accountInfo = document.getElementById('accountInfo')
  const sender = document.getElementById('sender');
  const senderHp = document.getElementById('senderHp');
  const senderAddress = document.getElementById('senderAddress');
  card_info = "BC "+"****-****-****-"+ cardInfo.innerHTML
  fetch('http://localhost:8889/api/order/sell', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      //우리가 만든데이터
      "transaction_time":`${new Date()}`,
      "resultCode":"ok",
      "description":"정상",
      "data":{
        "productIdx": productIdx.innerHTML,
        "isNow":is_now,
        "price":wish_price,
        "period":period,
        "accountInfo":accountInfo.innerHTML,
        "cardInfo":card_info,
        "sender": sender.innerHTML,
        "senderHp":senderHp.innerHTML,
        "senderAddress":senderAddress.innerHTML,
        "deliveryMemo":delivery_memo
      }
    }),
  })
      .then((res) => {
        document.querySelector('.step-2').style.display="none"
        document.querySelector('.step-3').style.display="block"
        document.querySelector('.step-3 .wish_price').innerHTML=wish_price.toLocaleString('ko-KR') +" 원";
        document.querySelector('.step-3 .final_fees').innerHTML=fees.toLocaleString('ko-KR') +" 원";
        document.querySelector('.step-3 .final_price').innerHTML = (wish_price + fees ).toLocaleString('ko-KR') ;
        if(is_now != true){
          document.querySelector('.step-3 .deadline').innerHTML = calc_deadline(period);
        }else{
          document.querySelector('.step-3 .deadline_box').style.display= "none";
          document.querySelector('.step-3 .complete_title .main_title').innerHTML = "즉시판매가 완료되었습니다."
        }
        location.href="#" // 상단으로 올려준다.
        return; //리턴을 걸어서 진행하는 것을 막는다!
      })
      .then((data) => {
        console.log(data);
        return;
      })
      .catch((err)=>{
        alert(err);
      })
}
function close_order_price_confirm(){
  document.querySelector('.layer_order_price_confirm').style.display="none"
}

/**
 * 기능 14 : 검수기준 레이어창
 */
function pop_auth_policy(){
  document.querySelector('.layer_auth_policy').style.display="block"
}
function close_auth_policy(){
  document.querySelector('.layer_auth_policy').style.display="none"
}