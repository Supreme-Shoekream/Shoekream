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
            document.querySelector('#find_btn').className='btn full solid';
            document.querySelector('#find_btn').disabled=false;
        }
        document.querySelector('#hp_input_error').innerHTML=errorMsg;
    })
});

// 버튼 활성화
document.querySelectorAll('#hp_input').forEach((item) =>{
    item.addEventListener('input', e=>{
    let strHp=e.target.value;
        if(validateHp(strHp)){
                $("#find_btn").removeClass("active");
                $(this).addClass("active");
                $("#find_btn").removeClass("disabled")
            }
                })
            });