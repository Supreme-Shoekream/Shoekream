// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// delete
function wishDelete(idx){
    // let idx = document.getElementById("wish_proIdx").getAttribute("value");
        fetch('http://localhost:8889/api/product/'+idx, {
            method: "DELETE"
        })
            .then((res) => {
                alert("관심상품 삭제 성공!")
                location.reload();
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