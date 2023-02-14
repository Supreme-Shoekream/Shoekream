

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


// 첨부파일
$("input[type='file']").on("change", function(e){
        let formData = new FormData();
        let fileInput = $('input[name="imgUpload"]');
        let fileList = fileInput[0].files;
        let fileObj = fileList[0];
        formData.append("imgUpload", fileObj);

        // ajax를 사용하여 서버로 전송
        $.ajax({
            url: "/api/social/imgUpload", // 서버로 요청을 보낼 url
            processData : false, // 서버로 전송할 데이터를 queryStirng 형태로 변환할지 여부
            contentType : false, // 서버로 전송되는 데이터의 content-type
            data : formData, // 서버로 전송할 데이터
            type : 'POST', // 서버 요청 타입(GET, POST)
            dataType : 'text', // 서버로부터 반환받을 데이터 타입
            success : function (data){
                //alert(data);
                document.getElementById("previewImage").value = "/images/" + data;
            },
            error: function(e) {
                alert("값을 가져오지 못했습니다.");
            }
        });
    });

// 이미지 미리보기
function readImage(input){
    if(input.files && input.files[0]){
        const fileReader = new FileReader();
        fileReader.onload = function (e) {
            document.getElementById("previewImage").src = e.target.result;
        }
        fileReader.readAsDataURL(input.files[0]);
    }
    else{
        document.getElementById("previewImage").src = "";
    }
}


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// create
document.getElementById('submit').onclick = function(){styleBoardSend()};
function styleBoardSend(){
    const content = document.getElementById("content_input").value; // 게시글 내용
    const hashtag = document.getElementById("hashtag_input").value; // 해시태그 내용
    const img2 = document.getElementById("previewImage").value; // 게시글 사진
        const img = img2.replaceAll("http://localhost:8889", "");
    let tags = document.getElementById('selected_product_list').childNodes; // 상품 태그
    let tagIds = '';// 상품태그들의 productIdx 목록. ','로 구분해 보낸 뒤 split해서 사용함

    for(let i=0;i<tags.length;i++){
        tagIds += (tags[i].id+',')
    }

    console.log(tagIds)

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
                "hashtag":`${hashtag}`,
                "tag":`${tagIds}`
            }
        }),
    })
        .then((res) => {
            //alert("게시글 등록 성공!")
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



// oninput
// let bef_timeout;
function search(){
    // const lists=document.getElementById('lists');
    // lists.style.display='block';


    let txt = document.querySelector('.tag_search').value.trim();
    if(txt){
        clearTimeout(bef_timeout);
        bef_timeout = setTimeout(show_tag_search_layer, 500, txt);
    }else reset_tag_search();

}

async function show_tag_search_layer(keyword){
    console.log(keyword);
    return new Promise(async function(resolve, reject){
        const result = await fetch(`/api/shop/searchWord?keyword=${keyword}`).then((response)=>response.json());
        const res = result.data;
        console.log(res);

        let tagList = '';
        for(let i=0;i<res.length;i++){
            tagList +=`<li class="product_item" id="before ${res[i].idx}" onclick="tagThis(this)"><a
                     class="product_link">
                     <div  class="product" style="background-color: rgb(244, 244, 244);">
                       <picture class="picture product_img">
                         <source type="image/webp"
                           srcset="">
                         <source
                           srcset="">
                         <img alt="Arc'teryx Zeta SL Jacket Black"
                           src="${res[i].img}"
                           loading="lazy" class="image">
                       </picture>
                     </div>
                     <div  class="product_desc">
                       <p  class="product_name">${res[i].name}</p>
                       <div  class="price_box">
                        <span class="amount">${res[i].firstPrice}</span><span  class="unit">원</span>
                        <span class="tag_size">${res[i].size}</span>
                        </div>
                     </div>
                   </a></li>`
        }

        document.getElementById('search_tag_list').innerHTML = tagList;
        document.getElementById('lists').style.display = 'block';
    })
}

function isNull(input){
    let lists = document.getElementById('lists');

    if(input.value=="" || input.value==" "){
        lists.style.display='none';
        document.getElementById('search_tag_list').innerHTML = "";
    }
}
function reset_tag_search(){
    document.getElementById('lists').style.display='none';
    document.getElementById('.search_tag_list').innerHTML='';
}


function tagThis(th){
    const productIdx = Number(th.id.split(' ')[1].trim());
    let inner = `<li class="product_item" id="${productIdx}" onclick="removeTag(this)">`
        +th.innerHTML + `</li>`
    document.getElementById('selected_product_list').innerHTML =  document.getElementById('selected_product_list').innerHTML+ inner;
}

function removeTag(th){
    let list = document.getElementById('selected_product_list');
    list.removeChild(th)
}