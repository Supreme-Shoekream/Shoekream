// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// view

    fetch("http://localhost:8889/product/" + idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            console.log(data.data.idx);
            document.querySelector(".idx").innerHTML=data.data.idx;
            document.querySelector(".name").innerHTML=data.data.name;
            document.querySelector(".nameKor").innerHTML=data.data.nameKor;
            document.getElementById("previewImg2").src=data.data.img;
            // 상품 생성시 받아온 이미지 url을 layer_product_view의 img태그 class명을 찍어 src에 넣어줌
            document.querySelector(".brand").innerHTML=data.data.brand;
            document.querySelector(".size").innerHTML=data.data.size;
            document.querySelector(".category").innerHTML=data.data.category;
            document.querySelector(".modelNum").innerHTML=data.data.modelNum;
            document.querySelector(".releaseDate").innerHTML=data.data.releaseDate;
            document.querySelector(".color").innerHTML=data.data.color;
            document.querySelector(".firstPrice").innerHTML=data.data.firstPrice;
            document.querySelector(".category").innerHTML=data.data.category;
            document.querySelector(".gender").innerHTML=data.data.gender;
            document.querySelector(".collection").innerHTML=data.data.collection;
        })

    document.querySelector(".product_view").style.display = "block";
}

function productview_popdown() {
    document.querySelector(".product_view").style.display = "none";
}