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

// 이메일 유효성 검사
document.querySelector('#email_input').addEventListener('input', e=>{
    debounce(e, strEmail=>{
        let errorMsg='';
        if(!validateEmail(strEmail)){
            errorMsg='이메일 주소를 정확히 입력해주세요.';
            document.querySelector('#email_input_box').className='has_button input_box has_error';
            document.querySelector('#login_btn').className='btn full solid disabled';
        } else {
            document.querySelector('#email_input_box').className='has_button input_box fill';
            if(document.querySelector('#password_input_box').classList.contains('fill')){
                document.querySelector('#login_btn').className='btn full solid';
                document.querySelector('#login_btn').disabled=false;
            } else {
                document.querySelector('#login_btn').className='btn full solid disabled';
            }
        }
        document.querySelector('#email_input_error').innerHTML=errorMsg;
    })
});

// 비밀번호 유효성 검사
document.querySelector('#password_input').addEventListener('input', e=>{
    debounce(e, strPassword=>{
        let errorMsg='';
        if(!validatePassword(strPassword)){
            errorMsg='영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)';
            document.querySelector('#password_input_box').className='has_button input_box has_error';
            document.querySelector('#login_btn').className='btn full solid disabled';
        } else {
            document.querySelector('#password_input_box').className='has_button input_box fill';
            if(document.querySelector('#email_input_box').classList.contains('fill')){
                document.querySelector('#login_btn').className='btn full solid';
                document.querySelector('#login_btn').disabled=false;
            } else {
                document.querySelector('#login_btn').className='btn full solid disabled';
            }
        }
        document.querySelector('#password_input_error').innerHTML=errorMsg;
    })
});


window.onload = function(){
    const btn = document.getElementById('login_btn');
    btn.addEventListener('click',sendit);
}

function sendit(){
    const email = document.getElementById('email_input');
    const memberPw = document.getElementById('password_input');

    // if(email.value==''){
    //     alert('아이디를 입력하세요');
    //     email.focus()
    //     return false;
    // }
    //
    // if(memberPw.value ==''){
    //     alert('비밀번호 입력하세요');
    //     memberPw.focus()
    //     return false;
    // }
    document.getElementById('frm').submit();
}