// 팝업창
// function layer_card(){
//     document.querySelector('.layer_card').style.display="block"
// }
//
// function closeLayer(){
//     document.querySelector('.layer_card').style.display="none"
// }

// input 타입 넘버에 maxlength 설정
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
    item.addEventListener('keyup', e=>{
        strCc1=e.target.value;
        if((validateCc1(strCc1))&&(validateBirthday(strBirthday))&&(validatePin(strPin))){
            $("#submit_btn").removeClass("active");
            $("#submit_btn").removeClass("disabled")
        }else{
            $("#submit_btn").addClass("active");
            $("#submit_btn").addClass("disabled")
        }
    })
})

document.querySelectorAll('#birthday_input').forEach((item) =>{
    item.addEventListener('keyup', e=>{
        strBirthday=e.target.value;
        if((validateCc1(strCc1))&&(validateBirthday(strBirthday))&&(validatePin(strPin))){
            $("#submit_btn").removeClass("active");
            $("#submit_btn").removeClass("disabled")
        }else{
            $("#submit_btn").addClass("active");
            $("#submit_btn").addClass("disabled")
        }
    })
})

document.querySelectorAll('#pin_input').forEach((item) =>{
    item.addEventListener('keyup', e=>{
        strPin=e.target.value;
        if((validateCc1(strCc1))&&(validateBirthday(strBirthday))&&(validatePin(strPin))){
            $("#submit_btn").removeClass("active");
            $("#submit_btn").removeClass("disabled")
        }else{
            $("#submit_btn").addClass("active");
            $("#submit_btn").addClass("disabled")
        }
    })
});