// search
const searchInput = document.getElementById('search_box')
searchInput.addEventListener('blur',search_buy)
function search_buy(){
    location.href="/sell?page=0&searchKeyword="+searchInput.value;
}

// view

function layer_sell_view(idx){

    fetch('http://localhost:8899/api/order/sell/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            let matching_idx = data.buyIdx;
            if(matching_idx == null) {
                matching_idx = "x"
                document.querySelector('.layer_sell_view .buyer').innerHTML=matching_idx;
            }else{
                fetch('http://localhost:8899/api/order/buy/'+ matching_idx)
                    .then((response) => response.json())
                    .then((data2) => {
                        matching_idx = data2.memberEmail;
                        document.querySelector('.layer_sell_view .buyer').innerHTML=
                            `<a href="/buy?page=0&searchKeyword=${matching_idx}">${matching_idx}</a>`
                    })
            }
            document.querySelector('.layer_sell_view .idx').innerHTML=data.idx;
            document.querySelector('.layer_sell_view .productIdx').innerHTML=data.productIdx;
            document.querySelector('.layer_sell_view .productName').innerHTML=data.productName;
            document.querySelector('.layer_sell_view .price').innerHTML=data.price.toLocaleString('ko-KR')+"원";
            document.querySelector('.layer_sell_view .productImg').src=data.productImg;
            document.querySelector('.layer_sell_view .productSize').innerHTML=data.productSize;
            document.querySelector('.layer_sell_view .seller').innerHTML=data.memberEmail;
            document.querySelector('.layer_sell_view .type').innerHTML=data.type;
            document.querySelector('.layer_sell_view .createdAt').innerHTML=data.createdAt;
            document.querySelector('.layer_sell_view .status').innerHTML=data.status;
            document.querySelector('.layer_sell_view .period').innerHTML=data.period;

            document.querySelector('.layer_sell_view .totalPrice').innerHTML=data.totalPrice;
            document.querySelector('.layer_sell_view .fees').innerHTML=data.fees;
            document.querySelector('.layer_sell_view .account_idx').innerHTML=data.cardInfo;

        })
    //미리 내용 채우고나서
    document.querySelector(".layer_sell_view").style.display = "block";
}
function close_sell_view(){
    document.querySelector(".layer_sell_view").style.display = "none";
}


// delete

function pop_sell_delete(idx){
    document.querySelector(".sell_delete").style.display = "block";
    const btn_delete = document.querySelector('.btn_delete');
    btn_delete.addEventListener('click',()=>{
        selldelete(idx)
    });
}
function selldelete(idx){
    fetch('http://localhost:8899/api/order/sell/'+idx, {
        method: "DELETE",
    })
        .then((res) => {
            location.href='/admin/sell';
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
function close_sell_delete() {
    document.querySelector(".sell_delete").style.display = "none";
}


let sellIdx
function addPenalty(){
    send_penalty(sellIdx)
}
// 패널티
function pop_penalty(idx){
    sellIdx =idx;
    document.querySelector(".layer_penalty").style.display = "block";
    fetch('http://localhost:8899/api/order/sell/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            document.querySelector('.layer_penalty #panalty_email').innerHTML=data.memberEmail;
            document.querySelector('.layer_penalty #panalty_productIdx').innerHTML=data.productIdx;
            document.querySelector('.layer_penalty #panalty_productName').innerHTML=data.productName;
            let penalrt_price = Math.floor(data.price*0.15/1000) * 1000;
            console.log(penalrt_price)
            document.querySelector('.layer_penalty #panalty_price').innerHTML=penalrt_price;

        })
    const btn_save = document.querySelector('.btn_save_penalty');
    btn_save.addEventListener('click',addPenalty);
}
function close_penalty(){
    document.querySelector(".layer_penalty").style.display = "none";
}
function send_penalty(sellIdx){
    const reason = document.getElementById('panalty_reson');

    fetch('http://localhost:8899/api/penalty', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "reasonIdx":`${reason.value}`,
                "sellIdx":`${sellIdx}`
            }
        }),
    })
        .then((res) => {
            alert('등록성공')
            location.href='/penalty';
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