window.onload = function(){
    const btn = document.getElementById('loginBtn');
    btn.addEventListener('click',sendit);
}

function sendit(){
    const userid = document.getElementById('userid');
    const userpw = document.getElementById('userpw');

    if(userid.value==''){
        alert('아이디를 입력하세요');
        userid.focus()
        return false;
    }

    if(userpw.value ==''){
        alert('비밀번호 입력하세요');
        userpw.focus()
        return false;
    }
    document.getElementById('frm').submit();
}
/*
function sendit(){
    const userid = document.getElementById('userid');
    const userpw = document.getElementById('userpw');

    if(userid.value==''){
        alert('아이디를 입력하세요');
        userid.focus()
        return false;
    }

    if(userpw.value ==''){
        alert('비밀번호 입력하세요');
        userpw.focus()
        return false;
    }
    fetch('http://localhost:8890/api/admin/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "userid":`${userid.value}`,
                "userpw":`${userpw.value}`
            }
        }),
    })
        .then((res) => {
            alert('로그인 성공')
            location.href='';
            return; //리턴을 걸어서 진행하는 것을 막는다!
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err)=>{
            alert('에러!!');
            location.reload();
        })

}

 */