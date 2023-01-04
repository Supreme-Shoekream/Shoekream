window.onload = function(){
    const btn = document.getElementById('registBtn');
    btn.addEventListener('click',sendit);
}

function sendit(){
    const userid = document.getElementById('userid');
    const userpw = document.getElementById('userpw');
    const name = document.getElementById('name')

    if(userid.value ==''){
        alert('아이디를 입력하세요');
        userid.focus()
        return false;
    }

    if(userpw.value ==''){
        alert('비밀번호 입력하세요');
        userpw.focus()
        return false;
    }

    if(name.value ==''){
        alert('이름을 입력하세요');
        name.focus()
        return false;
    }

    //fetch
    fetch('http://localhost:8890/api/admin', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "userid":`${userid.value}`,
                "userpw":`${userpw.value}`,
                "name":`${name.value}`
            }
        }),
    })
        .then((res) => {
            alert('등록성공')
            location.href='/login';
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