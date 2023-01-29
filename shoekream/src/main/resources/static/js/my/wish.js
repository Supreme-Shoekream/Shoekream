// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// delete
// function productdelete_popup(idx){
//     document.querySelector(".product_delete").style.display = "block";
//     const btn_delete = document.querySelector(".btn_delete");
//     btn_delete.addEventListener("click",()=>{
//         admindelete(idx)
//     });
// }

// let idx = document.getElementById("wish_proIdx").value;
function wishDelete(idx){
    fetch('http://localhost:8889/api/product/'+idx, {
        method: "DELETE"
    })
        .then((res) => {
            alert("관심상품 삭제 성공!")
            // location.href="/admin/products";
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 관심상품 삭제 실패!");
            location.reload();
            return;
        })
}