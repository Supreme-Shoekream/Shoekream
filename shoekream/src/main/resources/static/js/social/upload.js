

// textarea 크기 자동조정
function resize(obj){
    obj.style.height = "1px";
    obj.style.height = (12+obj.scrollHeight)+"px";
}


//popup
function addProducts() {
    const popup = document.getElementById('follower_pop');
    popup.style.display = "block";
    const body = document.querySelector('body');
    body.style.overflow = 'hidden'
}

function closeSearch() {
    const popdown = document.getElementById('follower_pop');
    popdown.style.display = "none"
    const body = document.querySelector('body');
    body.style.overflow = 'unset'
}






// 이미지 미리보기
function readImage(input){

    // 인풋 태그에 파일이 있는 경우
    if(input.files && input.files[0]){
        const fileReader = new FileReader();

        fileReader.onload = () => {
            const previewImage = document.getElementById("previewImage");
            previewImage.src = "/img/styleImg/" + input.files[0].name;
        }
        fileReader.readAsDataURL(input.files[0]);
        //fileReader.readAsText(input.files[0]);
    }
}

// change 이벤트
// const inputImage = document.getElementById('imgUpload');
// inputImage.addEventListener("change", e => {
$("input[type='file']").on("change", function(e){
        let formData = new FormData();
        let fileInput = $('input[name="imgUpload"]');
        let fileList = fileInput[0].files;
        let fileObj = fileList[0];
        console.log("fileName : " + fileObj.name); // 파일 이름 확인
        console.log("fileSize : " + fileObj.size); // 파일 사이즈 확인
        formData.append("imgUpload", fileObj);

    // const previewImage = document.getElementById("previewImage");
    // previewImage.src = "/img/styleImg/" + fileObj.name;

        // ajax를 사용하여 서버로 전송
        $.ajax({
            url: "/api/social/imgUpload", // 서버로 요청을 보낼 url
            processData : false, // 서버로 전송할 데이터를 queryStirng 형태로 변환할지 여부
            contentType : false, // 서버로 전송되는 데이터의 content-type
            data : formData, // 서버로 전송할 데이터
            type : 'POST', // 서버 요청 타입(GET, POST)
            enctype:'multipart/form-data',
            dataType : 'text', // 서버로부터 반환받을 데이터 타입
            async : false,
            success : function (data){
                alert(data);
            },
            error: function(e) {
                alert("값을 가져오지 못했습니다.");
            }
        });

        readImage(e.target);
    });



document.getElementById('submit').onclick = function(){styleBoardSend()};

// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// create
function styleBoardSend(){
    const content = document.getElementById("content_input").value; // 게시글 내용
    const hashtag = document.getElementById("hashtag_input").value; // 해시태그 내용
    const img2 = document.getElementById("previewImage").src; // 게시글 사진
        const img = img2.replaceAll("http://localhost:8889", "");
    // const hashtag = document.getElementById("brand").value; // 해시태그
    // const tag = document.getElementById("brand").value; // 상품 태그

    if(img === ""){
        alert("게시글 이미지를 넣어주세요");
        img.focus();
        return false;
    }

    fetch("http://localhost:8889/api/social/boardcreate", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "content":`${content}`,
                "img":`${img}`,
                "hashtag":`${hashtag}`
                // "tag":`${tag}`
            }
        }),
    })
        .then((res) => {
            alert("게시글 등록 성공!")
            location.href="myprofile";
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 게시글 등록 실패!");
            location.reload();
            return;
        })
}



// onintut
function search(){
    const lists=document.getElementById('lists');
    lists.style.display='block';

}

function isNull(input){
    const lists = document.getElementById('lists');
    if(input.value==""){
        lists.style.display='none';
    }
}


// 선택된 태그 추가
// $('li.product_item').click(function(e){
//     e.preventDefault();
// });

const items = document.querySelectorAll(".product_item");
    //배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
    items.forEach((item)=>{


        item.addEventListener('click',()=>{
            // const ch=item.childNodes;

            console.log(item);
            const parent = document.getElementById('selected_product_list');
            parent.appendChild(item);
            
        })
    })
