let userIdx
function addPointEvent(){
    submitPoint(userIdx)
}
// 포인트 레이어 열기
function openPoint(idx){
    userIdx = idx
    fetch('/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            console.log(data.data)
            document.querySelector(".info").innerHTML=data.data.name;
        })
    document.querySelector('#point_layer').style.display="block"
    // 포인트 추가
    document.querySelector('#submit_point').addEventListener('click', addPointEvent)
}
// 포인트 레이어 닫기
function closePoint(){
    document.querySelector('#point_layer').style.display="none"
    document.querySelector('#submit_point').removeEventListener('click', addPointEvent)
}
// 포인트 create
function submitPoint(idx){
    if(document.querySelector('#insertedPoint').innerText != ''){
        let reason = document.querySelector('#select').value
        let point = Number(document.querySelector('#insertedPoint').innerText)
        let reasonNum
        switch(reason){
            case 'event':
                reasonNum=0
                break;
            case 'account':
                reasonNum=1
                break;
            case 'birthday':
                reasonNum=2
                break;
            case 'membership':
                reasonNum=3
                break;
        }

        fetch('http://localhost:8899/api/my/point/'+idx, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "transaction_time":`${new Date()}`,
                "resultCode":"ok",
                "description":"정상",
                "data":{
                    "reason" : `${reasonNum}`,
                    "point" : `${point}`
                }
            }),
        }).then((res) =>{
            alert('포인트 지급 완료');
            location.href='users';
            return;
        })
    }else {
        alert('지급이유를 선택하세요')
    }
}