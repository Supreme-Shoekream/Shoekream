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
    $(".size_layer").css('display', 'none'); //팝업닫고
    $("#input_shoesize").val(document.querySelector('.size_item.active .btn').text.trim());
});


// 정규식 조건

// input 타입 넘버에 maxlength 설정
function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }    
}










//디바운스
let timer=false;//최초 false
const filterByDebounce=(e, callback)=> {
    if (timer) {
        clearTimeout(timer);
    }
    timer = setTimeout(function () {
        callback('' + e.target.value);
    }, 200); //200ms 이후 반응(디바운스)
}

//이메일 유효성검사
function validateEmail(strEmail){
    const reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if(!reg_email.test(''+strEmail)){
        return false;
    }
    return true;
}


//oninput -> 함수(유효성) -> 아니면! #error추가!
on off 기능
 


// 이메일 유효성 검사
document.querySelector('#email_input').addEventListener('input', e=>{
    filterByDebounce(e, strEmail=>{
        let errorMsg='';
        if(!validateEmail(strEmail)){
            errorMsg='이메일 주소를 정확히 입력해주세요.';
            document.querySelector('#email_input_box').className='input_box has_error';
            document.querySelector('#email_input').setAttribute('validateresult',false);
        } else {
            document.querySelector('#email_input_box').className='input_box fill';
            document.querySelector('#email_input').setAttribute('validateresult',true);
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

// 전화번호 유효성 검사
document.querySelector('#hp_input').addEventListener('input', e=>{
    filterByDebounce(e, strHp=>{
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