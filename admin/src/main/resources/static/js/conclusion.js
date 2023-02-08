// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 키워드 검색 -> 상품명, 모델번호
const searchInput = document.getElementById('search_box')
searchInput.addEventListener('blur', () => {
    search_conclusion();
});

function search_conclusion(){
    location.href="/conclusion?page=0&searchKeyword="+searchInput.value;
}