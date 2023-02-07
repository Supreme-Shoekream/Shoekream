function pop_penalty_view(idx){
    document.querySelector('.pop_penalty_view').style.display="block";
    fetch('http://localhost:8899/api/penalty/'+idx)
        .then((res)=>res.json())
        .then(data=>{
            console.log(data.productName)

            document.querySelector('.penalty_nickname').innerHTML = data.nickname;
            document.querySelector('.penalty_account').innerHTML = data.memAcc;
            document.querySelector('.penalty_reason').innerHTML = data.reason;
            document.querySelector('.product_cd').innerHTML = data.productModelNum;
            document.querySelector('.penalty_name').innerHTML = data.memberName;
            document.querySelector('.penalty_email').innerHTML = data.memberEmail;
            document.querySelector('.product_nameKor').innerHTML = data.productNameKor;

        })
}

function pop_penalty_view_down(){
    document.querySelector('.pop_penalty_view').style.display='none';
}
function pop_penalty_delete_down(){
    document.querySelector('.pop_penalty_delete').style.display='none';
}
function delete_penalty(idx){
    document.querySelector('.pop_penalty_delete').style.display='block';
    const del = document.querySelector('.dlt');
    del.addEventListener('click', ()=>{
        delete_func(idx);
    })
}
function delete_func(idx){
    fetch('http://localhost:8899/api/penalty/'+idx,{
        method:"DELETE"
    }).then((res)=>{
        alert('삭제 완료');
        location.reload();
        return;
    })
        .catch((err)=>{
            alert(err);
        })
}