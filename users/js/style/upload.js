

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
        const reader = new FileReader();

        reader.onload = e => {
            const previewImage = document.getElementById("previewImage");
            previewImage.src = e.target.result;
        }
        reader.readAsDataURL(input.files[0]);
    }
}






// change 이벤트
const inputImage = document.getElementById('imgUpload');
inputImage.addEventListener("change", e => {
    readImage(e.target);
})

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
