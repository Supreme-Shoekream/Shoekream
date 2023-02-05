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
