// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 파일첨부
// 이미지 업로드
$("input[type='file']").on("change", function(e){
    //alert("동작"); // 파일 속성에 접근되는지 확인

    let formData = new FormData();
    // 화면 이동없이 첨부파일을 서버로 전송해야함
    // 폼태그와 같은 역할을 해주는 formData 객체를 생성해 첨부파일을 formData에 저장하고 formData 자체를 서버로 전송해야함

    let fileInput = $('input[name="uploadFile"]');
    let fileList = fileInput[0].files;
    //console.log("fileList : " + fileList); // 해당 객체가 파일리스트인지 확인

    let fileObj = fileList[0]; // file 객체를 담기 위한 변수를 선언하여 file 객체로 초기화해줌
    //console.log("fileObj : " + fileObj);  // 해당 객체가 어떠한 객체인지 확인

    // console.log("fileName : " + fileObj.name); // 파일 이름 확인
    // console.log("fileSize : " + fileObj.size); // 파일 사이즈 확인
    // console.log("fileType(MimeType) : " + fileObj.type); // 파일 타입 확인

    if(!fileCheck(fileObj.name, fileObj.size)){
        return false;
    }
    //alert("통과"); // 파일 타입, 크기 조건 둘 다 만족하면 뜸

    formData.append("uploadFile", "fileObj");
    // 첨부한 파일을 폼데이터에 업로드파일이라는 이름으로 추가해줌 (input name과 같아야함)
    // <input name="uploadFile" value="fileObj"> 와 같은 뜻

    // let resultData = "";

    // ajax를 사용하여 서버로 전송
    // $.ajax({
    //     url: '/admin/products', // 서버로 요청을 보낼 url
    //     processData : false, // 서버로 전송할 데이터를 queryStirng 형태로 변환할지 여부
    //     contentType : false, // 서버로 전송되는 데이터의 content-type
    //     data : formData, // 서버로 전송할 데이터
    //     type : 'POST', // 서버 요청 타입(GET, POST)
    //     dataType : 'json' // 서버로부터 반환받을 데이터 타입
    //     // async : false,
    //     // success : function (data){
    //     //     alert(data);
    //     //     resultData = data;
    //     //     alert(resultData);
    //     // },
    //     // error: function(e) {
    //     //     alert("값을 가져오지 못했습니다.");
    //     // }
    // });

    fetch("http://localhost:8889/admin/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data": formData
        }),
    })
        .then((res) => {
            alert("성공!")
            res.json()
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 실패!");
            location.reload();
            return;
        })

    // return resultData;
    // console.log(resultData);

    const previewImg = document.querySelector("#previewImg");
    console.log(fileObj.name.replace(" ", "_"));
    console.log("/img/product/" +fileObj.name.replace(" ", "_"));

    previewImg.src = "/img/product/" + fileObj.name;
    previewImg.style.display = "block";
});

// 파일 타입, 크기 제한
let regex = new RegExp("(.*?)\.(jpg|png)$"); // 이미지 파일 타입 png, jpg만 허용
let maxSize = 1048576; // 1MB 제한
let blankPattern = /[\s]/g;
function fileCheck(fileName, fileSize){
    if(fileSize >= maxSize){
        alert("파일 업로드 가능한 사이즈를 초과하였습니다.");
        return false;
    }
    if(!regex.test(fileName)){
        alert("해당 종류의 파일은 업로드할 수 없습니다.");
        return false;
    }
    if(blankPattern.test(fileName)){
        alert("파일명에는 공백이 포함될 수 없습니다.");
        // return fileName.replaceAll(" ", "_");
        return false;
    }
    return true;
}



// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// create
function productcreate_popup(){
    document.querySelector(".product_create").style.display = "block";
    const btn_save = document.querySelector('.btn_save');
    btn_save.addEventListener("click",sendit);
}

function sendit(){
    const brand = document.getElementById("brand").value; // 브랜드
    const name = document.getElementById("name").value; // 상품명
    const nameKor = document.getElementById("nameKor").value; // 상품명(kor)
    const size = document.getElementById("size").value; // 사이즈
    const img2 = document.getElementById("previewImg").src; // 상품사진
        const img = img2.replaceAll("http://localhost:8889", "");
    const modelNum = document.getElementById("modelNum").value; // 모델번호
    const releaseDate = document.getElementById("releaseDate").value; // 출시일
    const color = document.getElementById("color").value; // 컬러
    const firstPrice = document.getElementById("firstPrice").value; // 발매가
    const category = document.getElementById("category").value; // 카테고리
    const gender = document.getElementById("gender").value; // 성별
    const collection = document.getElementById("collection").value; // 컬렉션

    if(brand === ""){
        alert("브랜드명을 작성해주세요");
        brand.focus();
        return false;
    }
    if(name === ""){
        alert("상품명을 작성해주세요");
        name.focus();
        return false;
    }
    if(nameKor === ""){
        alert("상품명(kor) 작성해주세요");
        nameKor.focus();
        return false;
    }
    if(size === ""){
        alert("사이즈를 작성해주세요");
        size.focus();
        return false;
    }
    if(img === ""){
        alert("상품 이미지를 넣어주세요");
        img.focus();
        return false;
    }

    // fetch("http://localhost:8889/api/admin/products", {
    fetch("http://localhost:8889/api/admin/products", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "brand":`${brand}`,
                "name":`${name}`,
                "nameKor":`${nameKor}`,
                "size":`${size}`,
                "img":`${img}`,
                "modelNum":`${modelNum}`,
                "releaseDate":`${releaseDate}`,
                "color":`${color}`,
                "firstPrice":`${firstPrice}`,
                "category":`${category}`,
                "gender":`${gender}`,
                "collection":`${collection}`
            }
        }),
    })
        .then((res) => {
            alert("상품 등록 성공!")
            location.href="/admin/products";
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
    fetch("http://localhost:8889/api/admin/products/" + idx)
        .then((response) => response.json())
        .then((data) => {
            // console.log(data);
            // console.log(data.data.idx);
            document.querySelector(".idx").innerHTML=data.data.idx;
            document.querySelector(".name").innerHTML=data.data.name;
            document.querySelector(".nameKor").innerHTML=data.data.nameKor;
            document.getElementById("previewImg2").src=data.data.img;
            // console.log(data.data.img);
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
    fetch('http://localhost:8889/api/admin/products/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log("edit🟡" + data);
            document.querySelector("#edit_idx").innerHTML=data.data.idx;
            document.querySelector("#edit_brand").value=data.data.brand;
            document.querySelector("#edit_name").value=data.data.name;
            document.querySelector("#edit_nameKor").value=data.data.nameKor;
            document.getElementsByClassName("edit_img").src=data.data.img;
            // 상품 생성시 받아온 이미지 url을 layer_product_view의 img태그 class명을 찍어 src에 넣어줌
            document.querySelector("#edit_size").value=data.data.size;
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
    // const img2 = document.getElementById('previewImg2').src;
    const modelNum2 = document.getElementById('edit_modelNum').value;
    const releaseDate2 = document.getElementById('edit_releaseDate').value;
    const color2 = document.getElementById('edit_color').value;
    const firstPrice2 = document.getElementById('edit_firstPrice').value;
    let c = document.getElementById('edit_category');
    const category2 = c.options[c.selectedIndex].value;
    let g = document.getElementById('edit_gender');
    const gender2 = g.options[g.selectedIndex].value;
    let co = document.getElementById('edit_collection');
    const collection2 = co.options[co.selectedIndex].value;

    fetch('http://localhost:8889/api/admin/products/' + idx, {
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
            location.href="/admin/products";
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
    fetch('http://localhost:8889/api/admin/products/'+idx, {
        method: "DELETE"
    })
        .then((res) => {
            alert("상품 삭제 성공!")
            location.href="/admin/products";

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






// // 파일첨부
// $(document).ready(function(){
// //파일첨부 이벤트
//     $('.filebox .upload-hidden').on('change', function(){
//         if(window.FileReader){
//             // var filename = $(this)[0].files[0].name;
//             var filename = $(this)[0].files[0].name;
//             if(!validFileType(filename)){
//                 alert("허용하지 않는 확장자 파일입니다.");
//                 return false;
//             }else{
//                 if(!validFileSize($(this)[0].files[0])){
//                     alert("파일 사이즈가 10MB를 초과합니다.");
//                     return false;
//                 }else{
//                     if(!validFileNameSize(filename)){
//                         alert("파일명이 100자를 초과합니다.");
//                         return false;
//                     }
//                 }
//             }
//         } else {
//             var filename = $(this).val().split('/').pop().split('\\').pop();
//         }
//         $(this).prev().val(filename); //input upload-name 에 파일명 설정해주기
//         console.log("🟡" + filename);
//         readImage($(this)[0]); //미리보기
//     });
// });
//
// function validFileType(filename) {
//     const fileTypes = ["png", "jpg", "jpeg"];
//     return fileTypes.indexOf(filename.substring(filename.lastIndexOf(".")+1, filename.length).toLowerCase()) >= 0;
// }
// function validFileSize(file){
//     if(file.size > 10000000){ //10MB
//         return false;
//     }else{
//         return true;
//     }
// }
//
// function validFileNameSize(filename){
//     if(filename.length > 100){ //100자
//         return false;
//     }else{
//         return true;
//     }
// }
//
// //이미지 띄우기
// function readImage(input) {
//     if(input.files && input.files[0]) {
//         var filename = "/img/product/" + input.files[0].name;
//         const reader = new FileReader();
//         reader.onload = function(e){
//             const previewImage = document.getElementById("previewImg"); // create img
//             // previewImage.src = e.target.result;
//             previewImage.src = filename;
//
//         }
//         // reader가 이미지 읽도록 하기
//         reader.readAsDataURL(input.files[0]);
//     }
// }
// // 이미지 원본 팝업 띄우기
// function popImage(url) {
//     var img = new Image();
//     img.src = "/img/product/" + url;
//     var img_width = img.width;
//     var win_width = img.width + 25;
//     var img_height = img.height;
//     var win = img.height + 30;
//     var popup = window.open('', '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto');
//     popup.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
// }


