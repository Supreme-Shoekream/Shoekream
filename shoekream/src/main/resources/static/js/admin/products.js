// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// create
function productcreate_popup(){
    document.querySelector(".product_create").style.display = "block";
    const btn_save = document.querySelector('.btn_save');
    btn_save.addEventListener("click",sendit);
}

function sendit(){
    const brand = document.getElementById("brand"); // 브랜드
    const name = document.getElementById("name"); // 상품명
    const nameKor = document.getElementById("nameKor"); // 상품명(kor)
    const size = document.getElementById("size"); // 사이즈
    const img = document.getElementById("previewImg"); // 상품사진
    const modelNum = document.getElementById("modelNum"); // 모델번호
    const releaseDate = document.getElementById("releaseDate"); // 출시일
    const color = document.getElementById("color"); // 컬러
    const firstPrice = document.getElementById("firstPrice"); // 발매가
    const category = document.getElementById("category"); // 카테고리
    const gender = document.getElementById("gender"); // 성별
    const collection = document.getElementById("collection"); // 컬렉션

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
    // if(size.value === ""){
    //     alert("사이즈를 작성해주세요");
    //     size.focus();
    //     return false;
    // }
    if(firstPrice.value === ""){
        alert("발매가를 작성해주세요");
        firstPrice.focus();
        return false;
    }
    // if(category.value === ""){
    //     alert("카테고리를 선택해주세요");
    //     category.focus();
    //     return false;
    // }

    // fetch("http://localhost:8888/api/admin/products", {
    fetch("http://localhost:8888/api/admin/products", {
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
                "category":`${category.value}`,
                "gender":`${gender.value}`,
                "collection":`${collection.value}`
            }
        }),
    })
        .then((res) => {
            alert("상품 등록 성공!")
            location.href="http://localhost:8888/admin/products";
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

function productcreate_popdown(){
    document.querySelector(".product_create").style.display = "none";
}




// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// view
function productview_popup(idx){
    fetch("http://localhost:8888/api/admin/products/" + idx)
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




// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// edit
function productedit_popup(idx){

    fetch('http://localhost:8888/api/admin/products/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            document.querySelector("#edit_idx").innerHTML=data.data.idx;
            document.querySelector("#edit_brand").value=data.data.brand;
            document.querySelector("#edit_name").value=data.data.name;
            document.querySelector("#edit_nameKor").value=data.data.nameKor;
            document.getElementsByClassName("edit_img").src=data.data.img;
            // 상품 생성시 받아온 이미지 url을 layer_product_view의 img태그 class명을 찍어 src에 넣어줌
            document.querySelector("#edit_size").value=data.data.size;
            document.querySelector("#edit_category").value=data.data.category;
            document.querySelector("#edit_modelNum").value=data.data.modelNum;
            document.querySelector("#edit_releaseDate").value=data.data.releaseDate;
            document.querySelector("#edit_color").value=data.data.color;
            document.querySelector("#edit_firstPrice").value=data.data.firstPrice;
            document.querySelector("#edit_category").value=data.data.category;
            document.querySelector("#edit_gender").value=data.data.gender;
            document.querySelector("#edit_collection").value=data.data.collection;
        })
    document.querySelector(".product_edit").style.display = "block";

    const btn_edit = document.querySelector(".btn_edit");
    btn_edit.addEventListener("click",()=>{
        sendedit(idx)
    });
}

function sendedit(idx) {
    //request로 필요한 DOM 객체 선택
    const idx2 =document.getElementById('edit_idx').innerHTML; // 수정 안된 idx
    const brand2 = document.getElementById('edit_brand').value;
    const name2 = document.getElementById('edit_name').value;
    const nameKor2 = document.getElementById('edit_nameKor').value;
    const size2 = document.getElementById('edit_size').value;
    const img2 = document.getElementsByClassName('edit_img').src;
    const modelNum2 = document.getElementById('edit_modelNum').value;
    const releaseDate2 = document.getElementById('edit_releaseDate').value;
    const color2 = document.getElementById('edit_color').value;
    const firstPrice2 = document.getElementById('edit_firstPrice').value;
    const category2 = document.getElementById('edit_category').value;
    const gender2 = document.getElementById('edit_gender').value;
    const collection2 = document.getElementById('edit_collection').value;

    fetch('http://localhost:8888/api/admin/products' + idx, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "idx" : `${idx2}`,
                "brand":`${brand2}`,
                "name":`${name2}`,
                "nameKor":`${nameKor2}`,
                "size":`${size2}`,
                "img":`${img2}`,
                "modelNum":`${modelNum2}`,
                "releaseDate":`${releaseDate2}`,
                "color":`${color2}`,
                "firstPrice":`${firstPrice2}`,
                "category":`${category2}`,
                "gender":`${gender2}`,
                "collection":`${collection2}`
            }
        }),
    })
        .then((res) => {
            alert("상품 수정 성공!")
            location.href="http://localhost:8888/admin/products";
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 상품 수정 실패!");
            location.reload();
            return;
        })
}

function productedit_popdown() {
    document.querySelector(".product_edit").style.display = "none";
}




// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// delete
function productdelete_popup(idx){
    document.querySelector(".product_delete").style.display = "block";
    const btn_delete = document.querySelector(".btn_delete");
    btn_delete.addEventListener("click",()=>{
        admindelete(idx)
    });
}

function admindelete(idx){
    console.log("111111");
    fetch('http://localhost:8888/api/admin/products/'+idx, {
        method: "DELETE"
    })
        .then((res) => {
            console.log("222222");
            alert("상품 삭제 성공!")
            location.href="/admin/products";
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 상품 삭제 실패!");
            location.reload();
            return;
        })
}

function productdelete_popdown() {
    document.querySelector(".product_delete").style.display = "none";
}


// 파일첨부
$(document).ready(function(){
//파일첨부 이벤트
$('.filebox .upload-hidden').on('change', function(){
    if(window.FileReader){
        var filename = $(this)[0].files[0].name;
        if(!validFileType(filename)){
            alert("허용하지 않는 확장자 파일입니다.");
            return false;
        }else{
            if(!validFileSize($(this)[0].files[0])){
                alert("파일 사이즈가 10MB를 초과합니다.");
                return false;
            }else{
                if(!validFileNameSize(filename)){
                    alert("파일명이 100자를 초과합니다.");
                    return false;
                }
            }
        }
    } else {
        var filename = $(this).val().split('/').pop().split('\\').pop();
    }
    $(this).prev().val(filename); //input upload-name 에 파일명 설정해주기

    readImage($(this)[0]); //미리보기
});
});

function validFileType(filename) {
    const fileTypes = ["png", "jpg", "jpeg"];
    return fileTypes.indexOf(filename.substring(filename.lastIndexOf(".")+1, filename.length).toLowerCase()) >= 0;
}
function validFileSize(file){
    if(file.size > 10000000){ //10MB
    return false;
    }else{
    return true;
    }
}

function validFileNameSize(filename){
    if(filename.length > 100){ //100자
    return false;
    }else{
    return true;
    }
}

//이미지 띄우기
function readImage(input) {
    if(input.files && input.files[0]) {
    const reader = new FileReader();
    reader.onload = function(e){
    const previewImage = document.getElementById("previewImg"); // create img
    previewImage.src = e.target.result;

    // const previewImage2 = document.getElementsByClassName("edit_img"); // edit img
    // previewImage2.src = e.target.result;
    }
    // reader가 이미지 읽도록 하기
    reader.readAsDataURL(input.files[0]);
    }
}
//이미지 원본 팝업 띄우기
function popImage(url) {
    var img = new Image();
    img.src = url;
    var img_width = img.width;
    var win_width = img.width + 25;
    var img_height = img.height;
    var win = img.height + 30;
    var popup = window.open('', '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto');
    popup.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
}


// edit 이미지 띄우기
function readImage2(input) {
    if(input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e){
            // const previewImage = document.getElementById("previewImg"); // create img
            // previewImage.src = e.target.result;

            const previewImage2 = document.getElementsByClassName("edit_img"); // edit img
            previewImage2.src = e.target.result;
        }
        // reader가 이미지 읽도록 하기
        reader.readAsDataURL(input.files[0]);
    }
}
// edit 이미지 원본 팝업 띄우기
function popImage2(url) {
    var img = new Image();
    img.src = url;
    var img_width = img.width;
    var win_width = img.width + 25;
    var img_height = img.height;
    var win = img.height + 30;
    var popup = window.open('', '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto');
    popup.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
}


