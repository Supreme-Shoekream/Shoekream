/**
 * 🤍 기능 1: 유저 이메일로 검색하기
 */
const searchInput = document.getElementById('search_box')
searchInput.addEventListener('blur',search_buy)
function search_buy(){
    location.href="/admin/buy?page=0&searchKeyword="+searchInput.value;
}


/**
 * 🤍 기능 2: 레이어창을 통해 상세
 */
function pop_buy_view(idx){

    fetch('http://localhost:8889/api/order/buy/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            let matching_idx = data.sellIdx;
            if(matching_idx == null) matching_idx = "x"
            document.querySelector('.layer_buy_view .idx').innerHTML=data.idx;
            document.querySelector('.layer_buy_view .productName').innerHTML=data.productName;
            document.querySelector('.layer_buy_view .productSize').innerHTML=data.productSize;
            document.querySelector('.layer_buy_view .productImg').src=data.productImg;
            document.querySelector('.layer_buy_view .memberEmail').innerHTML=data.memberEmail;
            document.querySelector('.layer_buy_view .type').innerHTML=data.type;
            document.querySelector('.layer_buy_view .price').innerHTML=data.price;
            document.querySelector('.layer_buy_view .period').innerHTML=data.period;
            document.querySelector('.layer_buy_view .createdAt').innerHTML=data.createdAt;
            document.querySelector('.layer_buy_view .deadline').innerHTML=data.deadline;
            document.querySelector('.layer_buy_view .progress_class').innerHTML=data.progress;
            document.querySelector('.layer_buy_view .sellIdx').innerHTML=matching_idx
            document.querySelector('.layer_buy_view .cardInfo').innerHTML=data.cardInfo;
            document.querySelector('.layer_buy_view .receiver').innerHTML=data.receiver;
            document.querySelector('.layer_buy_view .receiverHp').innerHTML=data.receiverHp;
            document.querySelector('.layer_buy_view .receiverAddress').innerHTML=data.receiverAddress;
        })
    //미리 내용 채우고나서
    document.querySelector(".layer_buy_view").style.display = "block";
}
function close_buy_view(){
    document.querySelector(".layer_buy_view").style.display = "none";
}

/**
 * 🤍 기능 3: 레이어창을 통해 수정
 */
function pop_buy_edit(idx){
    document.querySelector(".layer_buy_edit").style.display = "block";
    //미리 찍어줘야함
    fetch('http://localhost:8889/api/order/buy/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            let matching_idx = data.sellIdx;
            if(matching_idx == null) matching_idx = "x"
            document.querySelector('.layer_buy_edit .idx').innerHTML=data.idx;
            document.querySelector('.layer_buy_edit .productName').innerHTML=data.productName;
            document.querySelector('.layer_buy_edit .productSize').innerHTML=data.productSize;
            document.querySelector('.layer_buy_edit .productImg').src=data.productImg;
            document.querySelector('.layer_buy_edit .memberEmail').innerHTML=data.memberEmail;
            document.querySelector('.layer_buy_edit .type').innerHTML=data.type;
            document.querySelector('.layer_buy_edit .price').innerHTML=data.price;
            document.querySelector('.layer_buy_edit .period').innerHTML=data.period;
            document.querySelector('.layer_buy_edit .createdAt').innerHTML=data.createdAt;
            document.querySelector('.layer_buy_edit .deadline').innerHTML=data.deadline;
            document.querySelector('.layer_buy_edit .sellIdx').innerHTML=matching_idx;
            document.querySelector('.layer_buy_edit .cardInfo').innerHTML=data.cardInfo;
            document.querySelector('.layer_buy_edit .receiver').innerHTML=data.receiver;
            document.querySelector('.layer_buy_edit .receiverHp').innerHTML=data.receiverHp;
            document.querySelector('.layer_buy_edit .receiverAddress').innerHTML=data.receiverAddress;
        })
    const btn_edit = document.querySelector('.layer_buy_edit .btn_edit');
    btn_edit.addEventListener('click',()=>{
        sendedit(idx)
    });
}
function sendedit(idx) {
    //request로 필요한 DOM 객체 선택
    const ship_status = document.getElementById('ship_status')
    console.log(ship_status);
    console.log(ship_status.value);
    fetch('http://localhost:8889/api/order/buy/'+idx, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time": `${new Date()}`,
            "resultCode": "ok",
            "description": "정상",
            "data": {
                "progressNum": ship_status.value,
            }
        }),
    })
        .then((res) => {
            console.log(res);
            location.reload();
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert(err);
        })
}
function close_buy_edit() {
    document.querySelector(".layer_buy_edit").style.display = "none";

}
/**
 * 🤍 기능 4: 레이어창을 통해 삭제
 */
function pop_buy_delete(idx){
    document.querySelector(".buy_delete").style.display = "block";
    const btn_delete = document.querySelector('.btn_delete');
    btn_delete.addEventListener('click',()=>{
        buydelete(idx)
    });
}
function buydelete(idx){
    fetch('http://localhost:8889/api/order/buy/'+idx, {
        method: "DELETE",
    })
        .then((res) => {
            location.href='/admin/buy';
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
function close_buy_delete(){
    document.querySelector(".buy_delete").style.display = "none";
}
/**
 * 🤍 기능 5: 검색 size 바궈보기
 */