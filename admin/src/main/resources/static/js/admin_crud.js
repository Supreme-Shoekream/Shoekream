function pop_admin_register(){
    document.querySelector(".layer_admin_register").style.display = "block";
    const btn_save = document.querySelector('.btn_save');
    btn_save.addEventListener('click',sendit);
}

function sendit() {
    //request로 필요한 DOM 객체 선택
    const adminid = document.getElementById('id_input');
    const adminpw = document.getElementById('password_input');
    const name = document.getElementById('name_input');
    const hp = document.getElementById('hp_input');

    fetch('http://localhost:8899/api/admin', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "adminid":`${adminid.value}`,
                "adminpw":`${adminpw.value}`,
                "name":`${name.value}`,
                "hp":`${hp.value}`
            }
        }),
    })
        .then((res) => {
            alert('등록성공')
            location.reload();
            return; //리턴을 걸어서 진행하는 것을 막는다!
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err)=>{
            alert(err);
        })
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
document.querySelector('#password_input').addEventListener('blur', e=>{
        strPassword=e.target.value;
        if((validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $(".btn_save").removeClass("active");
            $(".btn_save").removeClass("disabled")
        }else{
            $(".btn_save").addClass("active");
            $(".btn_save").addClass("disabled")
        }
    })


document.querySelector('#name_input').addEventListener('blur', e=>{
        strName=e.target.value;
        if((validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $(".btn_save").removeClass("active");
            $(".btn_save").removeClass("disabled")
        }else{
            $(".btn_save").addClass("active");
            $(".btn_save").addClass("disabled")
        }
    })


document.querySelector('#hp_input').addEventListener('blur', e=>{
        strHp=e.target.value;
        if((validatePassword(strPassword))&&(validateName(strName))&&(validateHp(strHp))){
            $(".btn_save").removeClass("active");
            $(".btn_save").removeClass("disabled")
        }else{
            $(".btn_save").addClass("active");
            $(".btn_save").addClass("disabled")
        }
    })

function close_admin_register(){
    document.querySelector(".layer_admin_register").style.display = "none";
}


//edit 시작 ✅✅✅✅✅✅
function pop_admin_edit(idx){
    document.querySelector(".layer_admin_edit").style.display = "block";
    //adminid를 찍어줘야함
    const adminid2 =document.getElementById('adminid_edit')
    fetch('http://localhost:8899/api/admin/'+idx)
        .then((response) => response.json())
        .then((data) => {
            adminid2.innerHTML=data.data.adminid;
        })
    const btn_edit = document.querySelector('.btn_edit');
    btn_edit.addEventListener('click',()=>{
        sendedit(idx)
    });
}
function sendedit(idx) {
    //request로 필요한 DOM 객체 선택
    const adminid2 =document.getElementById('adminid_edit')
    const adminpw2 = document.getElementById('password_edit');
    const name2 = document.getElementById('name_edit')
    const hp2 = document.getElementById('hp_edit')

    fetch('http://localhost:8899/api/admin', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "adminid" : adminid2.innerHTML,
                "adminpw":`${adminpw2.value}`,
                "name":`${name2.value}`,
                "hp":`${hp2.value}`
            }
        }),
    })
        .then((res) => {
            alert('수정성공')
            location.reload();
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err)=>{
            alert(err);
        })
}
// 비밀번호 유효성 검사
document.querySelector('#password_edit').addEventListener('input', e=>{
    let strPassword=e.target.value;
    let errorMsg='';
    if(!validatePassword(strPassword)){
        errorMsg='영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)';
        document.querySelector('#password_input_box2').className='has_button input_box has_error';
        document.querySelector('#password_edit').setAttribute('validateresult',false);
    } else {
        document.querySelector('#password_input_box2').className='has_button input_box fill';
        document.querySelector('#password_edit').setAttribute('validateresult',true);
    }
    document.querySelector('#password_input_error').innerHTML=errorMsg;
});

// 이름 유효성 검사
document.querySelector('#name_edit').addEventListener('input', e=>{
    let strName=e.target.value;
    let errorMsg='';
    if(!validateName(strName)){
        errorMsg='이름을 정확히 입력해주세요.';
        document.querySelector('#name_input_box2').className='has_button input_box has_error';
        document.querySelector('#name_edit').setAttribute('validateresult',false);
    } else {
        document.querySelector('#name_input_box2').className='has_button input_box fill';
        document.querySelector('#name_edit').setAttribute('validateresult',true);
    }
    document.querySelector('#name_input_error').innerHTML=errorMsg;
});

// 휴대폰 번호 유효성 검사
document.querySelector('#hp_edit').addEventListener('input', e=>{
    debounce(e, strHp=>{
        let errorMsg='';
        if(!validateHp(strHp)){
            errorMsg='휴대폰 번호를 정확히 입력해주세요.';
            document.querySelector('#hp_input_box2').className='input_box has_error';
            document.querySelector('#hp_edit').setAttribute('validateresult',false);
        } else {
            document.querySelector('#hp_input_box2').className='input_box fill';
            document.querySelector('#hp_edit').setAttribute('validateresult',true);
        }
        document.querySelector('#hp_input_error').innerHTML=errorMsg;
    })
});
//버튼 활성화 조건
let editPassword
let editName
let editHp
document.querySelector('#password_edit').addEventListener('blur', e=>{
    editPassword=e.target.value;
        if((validatePassword(editPassword))&&(validateName(editName))&&(validateHp(editHp))){
            $(".btn_edit").removeClass("active");
            $(".btn_edit").removeClass("disabled")
        }else{
            $(".btn_edit").addClass("active");
            $(".btn_edit").addClass("disabled")
        }
    })


document.querySelector('#name_edit').addEventListener('blur', e=>{
    editName=e.target.value;
        if((validatePassword(editPassword))&&(validateName(editName))&&(validateHp(editHp))){
            $(".btn_edit").removeClass("active");
            $(".btn_edit").removeClass("disabled")
        }else{
            $(".btn_edit").addClass("active");
            $(".btn_edit").addClass("disabled")
        }
    })

document.querySelector('#hp_edit').addEventListener('blur', e=>{
    editHp=e.target.value;
        if((validatePassword(editPassword))&&(validateName(editName))&&(validateHp(editHp))){
            $(".btn_edit").removeClass("active");
            $(".btn_edit").removeClass("disabled")
        }else{
            $(".btn_edit").addClass("active");
            $(".btn_edit").addClass("disabled")
        }
    })

function close_admin_edit() {
    document.querySelector(".layer_admin_edit").style.display = "none";

}
////관리자 조회👀👀👀👀👀👀👀👀
function pop_admin_view(idx){

    fetch('http://localhost:8899/api/admin/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            console.log(data.data.adminid)
            document.querySelector(".layer_admin_view .adminid").innerHTML=data.data.adminid;
            document.querySelector(".layer_admin_view .status").innerHTML=data.data.status;
            document.querySelector(".layer_admin_view .name").innerHTML=data.data.name;
            document.querySelector(".layer_admin_view .hp").innerHTML=data.data.hp;
        })
    //미리 내용 채우고나서
    document.querySelector(".layer_admin_view").style.display = "block";
}
function close_admin_view(){
    document.querySelector(".layer_admin_view").style.display = "none";
}

///삭제 시작!❗❗❌❌❌❗❗❌❌❌
function pop_admin_delete(idx){
    document.querySelector(".layer_admin_delete").style.display = "block";
    const btn_delete = document.querySelector('.btn_delete');
    btn_delete.addEventListener('click',()=>{
        admindelete(idx)
    });
}
function admindelete(idx){
    fetch('http://localhost:8899/api/admin/'+idx, {
        method: "DELETE",

    })
        .then((res) => {
            alert('삭제 완료')
            location.reload();
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err)=>{
            alert(err);
        })
}
function close_admin_delete(){
    document.querySelector(".layer_admin_delete").style.display = "none";
}
const searchInput = document.getElementById('search_box')
searchInput.addEventListener('blur',search_admin)
function search_admin(){
    location.href="/admin?page=0&searchKeyword="+searchInput.value;
}