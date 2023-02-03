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

// 아이디 정규 표현식
function validateId(strId){
    const reg_id = /^[a-zA-Z0-9]{2,10}$/;
    if(!reg_id.test(''+strId)){
        return false;
    }
    return true;
}

// 비밀번호 정규 표현식
function validatePassword(strPassword){
    const reg_password = /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    if(!reg_password.test(''+strPassword)){
        return false;
    }
    return true;
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

// 휴대폰 번호 자동 하이픈(-) 정규식
const autoHyphen = (target) => {
    target.value = target.value
    .replace(/[^0-9]/g, '')
    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}

// 휴대폰 번호 정규 표현식
function validateHp(strHp){
    const reg_hp = /^01(?:0|1|6|7|8|9)-(?:\d{3}|\d{4})-\d{4}$/;
    if(!reg_hp.test(''+strHp)){
        return false;
    }
    return true;
}

// 아이디 유효성 검사
document.querySelector('#id_input').addEventListener('input', e=>{
    let strId=e.target.value;
    let errorMsg='';
    if(!validateId(strId)){
        errorMsg='영문과 숫자만 입력해주세요. (2-10자)';
        document.querySelector('#id_input_box').className='has_button input_box has_error';
        document.querySelector('#id_input').setAttribute('validateresult',false);
    } else {
        document.querySelector('#id_input_box').className='has_button input_box fill';
        document.querySelector('#id_input').setAttribute('validateresult',true);
    }
    document.querySelector('#id_input_error').innerHTML=errorMsg;
});

// 비밀번호 유효성 검사
document.querySelector('#password_input').addEventListener('input', e=>{
    let strPassword=e.target.value;
    let errorMsg='';
    if(!validatePassword(strPassword)){
        errorMsg='영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)';
        document.querySelector('#password_input_box').className='has_button input_box has_error';
        document.querySelector('#password_input').setAttribute('validateresult',false);
    } else {
        document.querySelector('#password_input_box').className='has_button input_box fill';
        document.querySelector('#password_input').setAttribute('validateresult',true);
    }
    document.querySelector('#password_input_error').innerHTML=errorMsg;
});

// 이름 유효성 검사
document.querySelector('#name_input').addEventListener('input', e=>{
    let strName=e.target.value;
    let errorMsg='';
    if(!validateName(strName)){
        errorMsg='이름을 정확히 입력해주세요.';
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

// 버튼 활성화
let strPassword
let strName
let strHp
document.querySelectorAll('#password_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strPassword=e.target.value;
        if((validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $(".btn_save").removeClass("active");
            $(".btn_save").removeClass("disabled")
        }else{
            $(".btn_save").addClass("active");
            $(".btn_save").addClass("disabled")
        }
    })
})

document.querySelectorAll('#name_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strName=e.target.value;
        if((validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $(".btn_save").removeClass("active");
            $(".btn_save").removeClass("disabled")
        }else{
            $(".btn_save").addClass("active");
            $(".btn_save").addClass("disabled")
        }
    })
})

document.querySelectorAll('#hp_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strHp=e.target.value;
        if((validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $(".btn_save").removeClass("active");
            $(".btn_save").removeClass("disabled")
        }else{
            $(".btn_save").addClass("active");
            $(".btn_save").addClass("disabled")
        }
    })
});