// 사이즈 팝업창
function layer_delivery(){
    document.querySelector('.layer_delivery').style.display="block"
}

function closeLayer(){
    document.querySelector('.layer_delivery').style.display="none"
}

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
            $("#submit_btn").removeClass("active");
            $("#submit_btn").removeClass("disabled")
        }else{
            $("#submit_btn").addClass("active");
            $("#submit_btn").addClass("disabled")
        }
    })
})

document.querySelectorAll('#hp_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strHp=e.target.value;
        if((validateName(strName))&&(validateHp(strHp))){
            $("#submit_btn").removeClass("active");
            $("#submit_btn").removeClass("disabled")
        }else{
            $("#submit_btn").addClass("active");
            $("#submit_btn").addClass("disabled")
        }
    })
});
