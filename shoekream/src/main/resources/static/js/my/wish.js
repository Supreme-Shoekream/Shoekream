// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// delete
function Delete(idx){
    fetch('http://localhost:8889/api/product/'+idx, {
        method: "DELETE"
    })
        .then((res) => {
            // alert("관심상품 삭제 성공!");
            location.reload();
        })
        .then((data) => {
            console.log(data);
        })
        .catch((err) => {
            // alert("에러! 에러! 관심상품 삭제 실패!");
            location.reload();
        })
}