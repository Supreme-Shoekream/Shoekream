// // input 타입 넘버에 maxlength 설정
function maxLengthCheck(object){
    if (object.value.length > object.maxLength){
        object.value = object.value.slice(0, object.maxLength);
    }
}
//
// // 디바운스
let timer=false;//최초 false
const debounce=(e, callback)=> {
    if (timer) {
        clearTimeout(timer);
    }
    timer = setTimeout(function () {
        callback('' + e.target.value);
    }, 100); //200ms 이후 반응(디바운스)
}

const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
}
// // 휴대폰 번호 정규 표현식
function validateHp(strHp){
    // const reg_hp = /^01(?:0|1|6|7|8|9)(?:\d{3}|\d{4})\d{4}$/;
    const reg_hp = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    if(!reg_hp.test(''+strHp)){
        return false;
    }
    return true;
}
//
// // 휴대폰 번호 유효성 검사
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

// // 버튼 활성화
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
// const item = document.querySelector('#hp_input');
//     item.addEventListener('input', e=>{
//         let strHp=e.target.value;
//         if(validateHp(strHp)){
//             $("#find_btn").removeClass("active");
//             $(this).addClass("active");
//             $("#find_btn").removeClass("disabled")
//         }
//
// });

//
function idCheck(){
    var hp = $("#hp_input").val();


    $.ajax({
    type: "GET",
    url: "/check/findId",
    data: {
    "hp": hp
    },
    success: function (res) {
        // if (res['check']!=null) {
            console.log(res);
            var resultEmail = res['resultEmail'];

            if(resultEmail == null) {
                // 일치하는 휴대폰 없음

            }else{
                // 일치하는 휴대폰 있음
                document.querySelector('.help_area').style.display="none";
                document.querySelector('.email_area').style.display="block";
                $("#result_email").text(resultEmail);

            }
        // }
    }});


}
//
