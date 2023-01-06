// 상품 생성
window.onload = function (){
    const btn = document.getElementById("productCreateBtn");
    btn.addEventListener("click", sendit);
}

function sendit(){
    const brand = document.getElementById("brand"); // 브랜드
    const name = document.getElementById("name"); // 상품명
    const nameKor = document.getElementById("nameKor"); // 상품명(kor)
    const size = document.getElementById("size"); // 사이즈
    const img = document.getElementById("img"); // 상품사진
    const modelNum = document.getElementById("modelNum"); // 모델번호
    const releaseDate = document.getElementById("releaseDate"); // 출시일
    const color = document.getElementById("color"); // 컬러
    const firstPrice = document.getElementById("firstPrice"); // 발매가
    const category = document.getElementById("category"); // 카테고리

    if(img.value === ""){
        alert("상품 이미지를 넣어주세요");
        img.focus();
        return false;
    }
    if(name.value === ""){
        alert("상품명을 작성해주세요");
        name.focus();
        return false;
    }
    if(nameKor.value === ""){
        alert("상품명(kor) 작성해주세요");
        nameKor.focus();
        return false;
    }
    if(brand.value === ""){
        alert("브랜드명을 작성해주세요");
        brand.focus();
        return false;
    }
    if(size.value === ""){
        alert("사이즈를 작성해주세요");
        size.focus();
        return false;
    }
    if(firstPrice.value === ""){
        alert("발매가를 작성해주세요");
        firstPrice.focus();
        return false;
    }
    if(category.value === ""){
        alert("카테고리를 선택해주세요");
        category.focus();
        return false;
    }

    fetch("http://localhost:8888/admin/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "brand":`${brand.value}`,
                "name":`${name.value}`,
                "nameKor":`${nameKor.value}`,
                "size":`${size.value}`,
                "img":`${img.value}`,
                "modelNum":`${modelNum.value}`,
                "releaseDate":`${releaseDate.value}`,
                "color":`${color.value}`,
                "firstPrice":`${firstPrice.value}`,
                "category":`${category.value}`
            }
        }),
    })
        .then((res) => {
            alert("상품 등록 성공!")
            location.href="/products";
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 상품 등록 실패!");
            location.reload();
            return;
        })
}

//









