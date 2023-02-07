let userIdx2
function addBlackList(){
    submitBlackList(userIdx2)
}
// 레이어 열기
function pop_black_list(idx){
    userIdx2 = idx
    fetch('/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            console.log(data.data)
            document.querySelector("#black_list_name").innerHTML=data.data.name;
        })
    document.querySelector('#black_list_layer').style.display="block"
    // 포인트 추가
    document.querySelector('#black_list_submit').addEventListener('click', addBlackList)
}

// 레이어 닫기
function pop_black_list_close(){
    document.querySelector('#black_list_layer').style.display="none"
    document.querySelector('#black_list_submit').removeEventListener('click', addBlackList)
}

// 블랙리스트 create
function submitBlackList(idx){
    let reason = document.querySelector('#select2')
    console.log(reason)
    let reasonText = reason.options[reason.selectedIndex].text
    console.log(reasonText)
    fetch('http://localhost:8889/api/blacklist/'+idx, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "reason" : `${reasonText}`
            }
        }),
    }).then((res)=>{
        alert('블랙리스트 처리 완료');
        location.href='/admin/users';
        return;
    })
}
