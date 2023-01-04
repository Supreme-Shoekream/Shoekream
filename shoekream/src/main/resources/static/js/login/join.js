// 사이즈 팝업창
function size_layer(){
    document.querySelector('.size_layer').style.display="block"
}

function closeLayer(){
    document.querySelector('.size_layer').style.display="none"
}

// 사이즈 선택
$(".size_item").click(function(){
    $(".size_item").removeClass("active");
    $(this).addClass("active");
    $("#size_submit_btn").removeClass("disabled")
    }); 

// input_area에 사이즈 value 값으로 전달
$(document).on('click', '#size_submit_btn', function(){
    $(".size_layer").css('display', 'none'); // 팝업 닫고
    $("#input_shoesize").val(document.querySelector('.size_item.active .btn').text.trim());
});


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

// 이메일 정규 표현식
function validateEmail(strEmail){
    const reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if(!reg_email.test(''+strEmail)){
        return false;
    }
    return true;
}

//비밀번호 정규 표현식
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

// ssn1 정규 표현식
function validateBirthdate1(strBirthdate1){
    const reg_birthdate1 =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/;
    if(!reg_birthdate1.test(''+strBirthdate1)){
        return false;
    }
    return true;
}

// ssn2 정규 표현식
function validateBirthdate(strBirthdate){
    const reg_birthdate =  /^[1-4]{1}$/;
    if(!reg_birthdate.test(''+strBirthdate)){
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

// 이메일 유효성 검사
document.querySelector('#email_input').addEventListener('input', e=>{
    debounce(e, strEmail=>{
        let errorMsg='';
        if(!validateEmail(strEmail)){
            errorMsg='이메일 주소를 정확히 입력해주세요.';
            document.querySelector('#email_input_box').className='input_box has_error';
            document.querySelector('#email_input').setAttribute('validateresult', false);
        } else {
            document.querySelector('#email_input_box').className='input_box fill';
            document.querySelector('#email_input').setAttribute('validateresult', true);
        }
        document.querySelector('#email_input_error').innerHTML=errorMsg;
    })
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
// document.querySelectorAll('#email_input').forEach((item) =>{
//     item.addEventListener('input', e=>{
//     let strEmail=e.target.value;
//     if(validateEmail(strEmail)){
//         document.querySelectorAll('#password_input').forEach((item) =>{
//             item.addEventListener('input', e=>{
//             let strPassword=e.target.value;
//         if(validatePassword(strPassword)){
//             document.querySelectorAll('#name_input').forEach((item) =>{
//                 item.addEventListener('input', e=>{
//                 let strName=e.target.value;
//             if(validateName(strName)){
//                 document.querySelectorAll('#hp_input').forEach((item) =>{
//                     item.addEventListener('input', e=>{
//                     let strHp=e.target.value;
//                 if(validateHp(strHp)){
//                     $("#btn_submitSignIn").removeClass("active");
//                     $(this).addClass("active");
//                     $("#btn_submitSignIn").removeClass("disabled")
//                 }
//                     })
//             })
//         }
//     })
// })
// }
// })
// })
// }})});




let strEmail
let strPassword
let strName
let strHp
document.querySelectorAll('#email_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strEmail=e.target.value;
        if((validateEmail(strEmail))&&(validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $("#btn_submitSignIn").removeClass("active");
            $("#btn_submitSignIn").removeClass("disabled")
        }else{
            $("#btn_submitSignIn").addClass("active");
            $("#btn_submitSignIn").addClass("disabled")
        }
    })
})

document.querySelectorAll('#password_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strPassword=e.target.value;
        if((validateEmail(strEmail))&&(validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $("#btn_submitSignIn").removeClass("active");
            $("#btn_submitSignIn").removeClass("disabled")
        }else{
            $("#btn_submitSignIn").addClass("active");
            $("#btn_submitSignIn").addClass("disabled")
        }
    })
})

document.querySelectorAll('#name_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strName=e.target.value;
        if((validateEmail(strEmail))&&(validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $("#btn_submitSignIn").removeClass("active");
            $("#btn_submitSignIn").removeClass("disabled")
        }else{
            $("#btn_submitSignIn").addClass("active");
            $("#btn_submitSignIn").addClass("disabled")
        }
    })
})

document.querySelectorAll('#hp_input').forEach((item) =>{
    item.addEventListener('blur', e=>{
        strHp=e.target.value;
        if((validateEmail(strEmail))&&(validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $("#btn_submitSignIn").removeClass("active");
            $("#btn_submitSignIn").removeClass("disabled")
        }else{
            $("#btn_submitSignIn").addClass("active");
            $("#btn_submitSignIn").addClass("disabled")
        }
    })
});