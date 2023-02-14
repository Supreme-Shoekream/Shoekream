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

// 이메일 정규 표현식
function validateEmail(strEmail){
    const reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if(!reg_email.test(''+strEmail)){
        return false;
    }
    return true;
}

// 이름 유효성 검사
let strName;
document.querySelector('#name_input').addEventListener('input', e=>{
    strName=e.target.value;
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

//이메일 유효성 검사
document.querySelector('#email_input').addEventListener('input', e=>{
    strName=e.target.value;
    debounce(e, strEmail =>{
        let errorMsg='';
        if(!validateEmail(strEmail)){
            errorMsg='이메일 주소를 정확히 입력해주세요.';
            document.querySelector('#email_input_box').className='input_box has_error';
            document.querySelector('#find_btn').className='btn full solid disabled';
        } else {
            document.querySelector('#email_input_box').className='input_box fill';
            if(document.querySelector('#name_input_box').classList.contains('fill')){
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

let strEmail
document.querySelectorAll('#email_input').forEach((item) =>{
    item.addEventListener('input', e=>{
        strEmail=e.target.value;
        if((validateEmail(strEmail))&&(validateName(strName))){
            $("#find_btn").addClass("active");
            $("#find_btn").removeClass("disabled")
        }else{
            $("#find_btn").removeClass("active");
            $("#find_btn").addClass("disabled")
        }
    })
})