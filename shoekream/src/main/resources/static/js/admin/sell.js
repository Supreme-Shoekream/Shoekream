// search
const searchInput = document.getElementById('search_box')
searchInput.addEventListener('blur',search_sell)
function search_sell(){
    location.href="/admin/sell?page=0&searchKeyword="+searchInput.value;
}

// view

function layer_sell_view(idx){

    fetch('http://localhost:8889/api/order/sell/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            let member_idx = data.memberId;
            if(member_idx == null) BuyIdx = "x"
            document.querySelector('.layer_sell_view .idx').innerHTML=data.idx;
            document.querySelector('.layer_sell_view .productIdx').innerHTML=data.productIdx;
            document.querySelector('.layer_sell_view .productName').innerHTML=data.productName;
            document.querySelector('.layer_sell_view .price').innerHTML=data.price;
            document.querySelector('.layer_sell_view .productImg').src=data.productImg;
            document.querySelector('.layer_sell_view .productSize').innerHTML=data.productSize;
            document.querySelector('.layer_sell_view .sender').innerHTML=data.sender;
            document.querySelector('.layer_sell_view .type').innerHTML=data.type;
            document.querySelector('.layer_sell_view .createdAt').innerHTML=data.createdAt;
            document.querySelector('.layer_sell_view .status').innerHTML=data.status;
            document.querySelector('.layer_sell_view .period').innerHTML=data.period;
            document.querySelector('.layer_sell_view .name').innerHTML=data.name;
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
    fetch('http://localhost:8889/api/order/sell/'+idx, {
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