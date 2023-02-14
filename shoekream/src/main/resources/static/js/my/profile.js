function validateName(strName){
    // const reg_name =  /^[가-힣a-zA-Z]+$/;
    const reg_name = /^[가-힣]{2,50}$/;
    if(!reg_name.test(''+strName)){
        return false;
    }
    return true;
}
let strName=document.querySelector(".unit_name .desc").innerHTML;
document.querySelector('#name_input').addEventListener('input', e=>{
    strName=e.target.value;
    let errorMsg='';
    if(!validateName(strName)){
        errorMsg='올바른 이름을 입력해주세요. (2-50자)';
        document.querySelector('#name_input_box').className='has_button input_box has_error';
        document.querySelector('#name_input').setAttribute('validateresult',false);
        document.querySelector('#name_save').classList.add('disabled');
        document.querySelector('#name_save').disabled = true;

    } else {
        document.querySelector('#name_input_box').className='has_button input_box fill';
        document.querySelector('#name_input').setAttribute('validateresult',true);
        document.querySelector('#name_save').classList.remove('disabled');
        document.querySelector('#name_save').disabled = false;
    }
    document.querySelector('#name_input_error').innerHTML=errorMsg;
});
function pop_email(){
    document.querySelector('.unit_email').style.display="none"
    document.querySelector('.modify_email').style.display="block"
}
function close_email(){
    document.querySelector('.unit_email').style.display="block"
    document.querySelector('.modify_email').style.display="none"
}
function pop_password(){
    document.querySelector('.unit_password').style.display="none"
    document.querySelector('.modify_password').style.display="block"
}
function close_password(){
    document.querySelector('.unit_password').style.display="block"
    document.querySelector('.modify_password').style.display="none"
}
function pop_name(){
    document.querySelector('.unit_name').style.display="none"
    document.querySelector('.modify_name').style.display="block"
}
function pop_hp(){
    document.querySelector('.unit_hp').style.display="none"
    document.querySelector('.modify_hp').style.display="block"
}
function close_hp(){
    document.querySelector('.unit_hp').style.display="block"
    document.querySelector('.modify_hp').style.display="none"
}

const closename =document.querySelectorAll('.close_name')
const layer1 = document.querySelector('.modify_name')
const layer2 = document.querySelector('.unit_name')
closename.forEach((target) => {
    target.addEventListener('click', () => {

        layer1.style.display="none"
        layer2.style.display="block"
        if(validateName(strName)){
            let name=document.querySelector(".unit_name .desc")
            let name1=document.querySelector(".profile_detail .name")
            name.innerHTML=strName
            name1.innerHTML=strName
        }

    })
})

function close_layer(){
    document.querySelector('.layer_size').style.display="none"
    let size=document.querySelector(".size.desc");
    size.innerHTML= document.querySelector(".size_item .btn.on span").innerHTML
}
function pop_layer() {
    document.querySelector('.layer_size').style.display = "block"
}
let shoe2
const boxes = document.querySelectorAll(".size_item .btn");
boxes.forEach((box) => {
    box.addEventListener("click", () => {
        boxes.forEach((e)=>{
            e.classList.remove("on")
        })
        box.classList.add("on")
        shoe2 = box.innerText
    })
});


function delete_img(){
    fetch("http://localhost:8889/api/my/profile/delete", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
        }),
    })
        .then((res) => {
            //alert("프로필 사진 삭제 완료")
            location.href="/my/profile";
            return;
        })
        .catch((err) => {
            //alert("프로필 사진 삭제 실패");
            location.reload();
            return;
        })
}



// 첨부파일 파일 저장
$("input[type='file']").on("change", function(e){
    let formData = new FormData();
    let fileInput = $('input[name="modify_img"]');
    //console.log(fileInput)
    let fileList = fileInput[0].files;
    let fileObj = fileList[0];
    //console.log("fileName : " + fileObj.name); // 파일 이름 확인
    //console.log("fileSize : " + fileObj.size); // 파일 사이즈 확인
    formData.append("modify_img", fileObj);


    // ajax를 사용하여 서버로 전송
    $.ajax({
        url: "/api/my/profile/modify_img",
        processData : false,
        contentType : false,
        data : formData,
        type : 'POST',
        enctype:'multipart/form-data',
        dataType : 'text',
        success : function (data){
            //alert(data);
            document.getElementById("profile_img").value = "/images/" + data;
        },
        error: function(e) {
            //alert("값을 가져오지 못했습니다.");
        }
    });

});

// 프로필 이미지 미리보기
function readImage(input){
    if(input.files && input.files[0]){
        const fileReader = new FileReader();
        fileReader.onload = function (e) {
            document.getElementById("profile_img").src = e.target.result;
        }
        fileReader.readAsDataURL(input.files[0]);
    }
    else{
        document.getElementById("profile_img").src = "";
    }
}

function profileImgSend(){
    const img = document.getElementById("profile_img").value;
    // const img = img2.replaceAll("http://localhost:8889", "");

    fetch("http://localhost:8889/api/my/changeUpload", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "imgUrl": img
            }
        }),
    })
        .then((res) => {
            alert("프로필 사진 등록 완료")
            location.href="/my/profile";
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            //alert("프로필 사진 등록 실패");
            location.reload();
            return;
        })
}

const update = document.getElementById("img_update");
const save = document.getElementById("img_save");
update.addEventListener("click", () => {
    save.style.display = "flex";
});
save.style.display = "none";



// const modifyImg = document.getElementById('modify_img');
// modifyImg.addEventListener("change", e => {
//     // console.log(e.target)
//     readImage(e.target);
//     alert('이미지가 수정되었습니다')
//     // location.href='/my/profile'
// })
// 업데이트

// 이메일
function emailSendIt() {
    if (!document.querySelector('#button_email').classList.contains('disabled')) {
        let email2 = document.querySelector('#change_email').value
        sendIt(email2, "nickname")
    }
}

// 닉네임
function nicknameSendIt() {
    let nickname2 = document.querySelector('#name_input').value
    sendIt(nickname2, "name")
}

// 비밀번호
function pwSendIt() {
    if (!document.querySelector('#button_password').classList.contains('disabled')) {
        let pw1 = document.querySelector('#pw1').value
        let pw2 = document.querySelector('#pw2').value
        fetch('/api/my/profile', {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                "transaction_time": `${new Date()}`,
                "resultCode": "ok",
                "description": "정상",
                "data": {
                    "memberPw": pw1 + "," + pw2
                }
            }),
        })
            .then((response) => response.json())
            .then((data) => {
                    console.log(data)
                    if (data == true) {
                        alert('비밀번호 수정 완료')
                        location.href = '/my/profile';
                        return;
                    } else {
                        alert('비밀번호가 일치하지 않습니다');
                        return;
                    }
                }
            )
    }
}

// 전화번호
function hpSendIt() {
    if(!document.querySelector('#hp_save').classList.contains('disabled')){
        let hp2 = document.querySelector('#hp_input')
        sendIt(hp2.value, 'hp')
    }
}

// 신발
function shoeSendIt() {
    console.log(shoe2)
    fetch('http://localhost:8889/api/my/profile', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "shoeSize" : shoe2
            }
        }),
    }).then((res)=>{
        alert('프로필수정 완료');
        location.href='/my/profile';
        return;
    })
    close_layer()
}
// 업데이트 - sendIt - 이메일/비밀번호/전화번호
function sendIt(data, type){
    console.log('2')
    let email = null;
    let nickname = null;
    let hp = null;
    let name = null;
    switch (type){
        case 'name' : name = data; break;
        case 'nickname' : nickname = data; break;
        case 'hp' : hp = data; break;
    }
    fetch('http://localhost:8889/api/my/profile', {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "name": name,
                "nickname" : nickname,
                "hp" : hp
            }
        }),
    }).then((res)=>{
        alert('프로필수정 완료');
        location.href='/my/profile';
        return;
    })
}

// 정규식

// 휴대폰 번호 정규 표현식
function validateHp(strHp){
    const reg_hp = /^01(?:0|1|6|7|8|9)(?:\d{3}|\d{4})\d{4}$/;
    if(reg_hp.test(''+strHp)){
        return true;
    }
    return false;
}
// 휴대폰 번호 유효성 검사
document.querySelector('#hp_input').addEventListener('input', e=>{
    strHp=e.target.value
    let errorMsg='';
    if(!validateHp(strHp)){
        errorMsg='휴대폰 번호를 정확히 입력해주세요.';
        document.querySelector('#hp_input_box').className='input_box has_error';
        document.querySelector('#hp_input').setAttribute('validateresult',false);
        document.querySelector('#hp_input').classList.remove('input_border')
        document.querySelector('#hp_save').classList.add('disabled')
    } else {
        document.querySelector('#hp_input_box').className='input_box fill';
        document.querySelector('#hp_input').setAttribute('validateresult',true);
        document.querySelector('#hp_input').classList.add('input_border')
        document.querySelector('#hp_save').classList.remove('disabled')
    }

});
// 이메일 정규 표현식
function validateEmail(strEmail){
    const reg_email =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    if(!reg_email.test(''+strEmail)){
        return false;
    }
    return true;
}
// 이메일 유효성 검사
// document.querySelector('#change_email').addEventListener('input', e=>{
//     strEmail = e.target.value;
//     let errorMsg='';
//     if(!validateEmail(strEmail)){
//         errorMsg='이메일 주소를 정확히 입력해주세요.';
//         document.querySelector('#email_input_box').className='input_box has_error';
//         document.querySelector('#change_email').setAttribute('validateresult', false);
//         document.querySelector('#change_email').classList.remove('input_border')
//         document.querySelector('#button_email').classList.add('disabled')
//     } else {
//         document.querySelector('#email_input_box').className='input_box fill';
//         document.querySelector('#change_email').setAttribute('validateresult', true);
//         document.querySelector('#change_email').classList.add('input_border')
//         document.querySelector('#button_email').classList.remove('disabled')
//     }
//
// });
// 닉네임 버튼활성화
let nicknameInput = document.querySelector('#change_email')
nicknameInput.addEventListener('keyup', () => {
    if(nicknameInput.value != ''){
        document.querySelector('#button_email').classList.remove('disabled')
    }else {
        document.querySelector('#button_email').classList.add('disabled')
    }
})
// 비밀번호 정규 표현식
function validatePassword(strPassword){
    const reg_password = /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
    if(!reg_password.test(''+strPassword)){
        return false;
    }
    return true;
}
// 비밀번호 유효성 검사
document.querySelector('#pw2').addEventListener('input', e=>{
    console.log('앙')
    let strPassword=e.target.value;
    let errorMsg='';
    if(!validatePassword(strPassword)){
        errorMsg='영문, 숫자, 특수문자를 조합해서 입력해주세요. (8-16자)';
        document.querySelector('#password_input_box').className='has_button input_box has_error';
        document.querySelector('#pw2').setAttribute('validateresult',false);
        document.querySelector('#pw2').classList.remove('input_border')
        document.querySelector('#button_password').classList.add('disabled')
    } else {
        document.querySelector('#password_input_box').className='has_button input_box fill';
        document.querySelector('#pw2').setAttribute('validateresult',true);
        document.querySelector('#pw2').classList.add('input_border')
        document.querySelector('#button_password').classList.remove('disabled')
    }
    document.querySelector('#password_input_error').innerHTML=errorMsg;
});