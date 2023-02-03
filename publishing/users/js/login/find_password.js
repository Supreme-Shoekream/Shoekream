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

// 휴대폰 번호 정규 표현식
function validateHp(strHp){
    const reg_hp = /^01(?:0|1|6|7|8|9)(?:\d{3}|\d{4})\d{4}$/;
    if(!reg_hp.test(''+strHp)){
        return false;
    }
    return true;
}

// 이메일 정규 표현식
function validateEmail(strEmail){
    const reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if(!reg_email.test(''+strEmail)){
        return false;
    }
    return true;
}

// 휴대폰 번호 유효성 검사
document.querySelector('#hp_input').addEventListener('input', e=>{
    debounce(e, strHp=>{
        let errorMsg='';
        if(!validateHp(strHp)){
            errorMsg='휴대폰 번호를 정확히 입력해주세요.';
            document.querySelector('#hp_input_box').className='input_box has_error';
            document.querySelector('#find_btn').className='btn full solid disabled';
        } else {
            document.querySelector('#hp_input_box').className='input_box fill';
            if(document.querySelector('#email_input_box').classList.contains('fill')){
                document.querySelector('#find_btn').className='btn full solid';
                document.querySelector('#find_btn').disabled=false;
            } else {
                document.querySelector('#find_btn').className='btn full solid disabled';
            }
        }
        document.querySelector('#hp_input_error').innerHTML=errorMsg;
    })
});

//이메일 유효성 검사
document.querySelector('#email_input').addEventListener('input', e=>{
    debounce(e, strEmail=>{
        let errorMsg='';
        if(!validateEmail(strEmail)){
            errorMsg='이메일 주소를 정확히 입력해주세요.';
            document.querySelector('#email_input_box').className='input_box has_error';
            document.querySelector('#find_btn').className='btn full solid disabled';
        } else {
            document.querySelector('#email_input_box').className='input_box fill';
            if(document.querySelector('#hp_input_box').classList.contains('fill')){
                document.querySelector('#find_btn').className='btn full solid';
                document.querySelector('#find_btn').disabled=false;
            } else {
                document.querySelector('#find_btn').className='btn full solid disabled';
            }
        }
        document.querySelector('#email_input_error').innerHTML=errorMsg;
    })
});

// 버튼 활성화
let strHp
let strEmail
document.querySelectorAll('#hp_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strHp=e.target.value;
        if((validateHp(strHp))&&(validateHp(strHp))){
            $("#submit_btn").removeClass("active");
            $("#submit_btn").removeClass("disabled")
        }else{
            $("#submit_btn").addClass("active");
            $("#submit_btn").addClass("disabled")
        }
    })
})

document.querySelectorAll('#email_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strHp=e.target.value;
        if((validateEmail(strEmail))&&(validateHp(strHp))){
            $("#submit_btn").removeClass("active");
            $("#submit_btn").removeClass("disabled")
        }else{
            $("#submit_btn").addClass("active");
            $("#submit_btn").addClass("disabled")
        }
    })
});
