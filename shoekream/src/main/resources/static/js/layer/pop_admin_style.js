function pop_style_view(idx){
    fetch("http://localhost:8889/api/social/" +idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            document.querySelector(".social_img").src=data.img;
            if(data.memberDTO.imgUrl == null){
                document.querySelector(".img_profile").src="/img/kream_empty_img.png";
            }else{
                document.querySelector(".img_profile").src=data.memberDTO.imgUrl;
            }
            document.querySelector(".user_name").innerHTML=data.memberDTO.nickname;
            document.querySelector(".text_box").innerHTML=data.content;
            document.querySelector(".like_count").innerHTML=data.lks.length;

            document.querySelector(".comment_count").innerHTML=data.replies.length;

            // 상품태그 추가‼‼‼‼
            document.getElementById("info_info_id").innerHTML=data.memberDTO.nickname;
            document.getElementById("info_info_hashtag").innerHTML=data.hashtag;
            document.getElementById("info_info_created").innerHTML=data.createdAt;
            // document.getElementById("info_info_modified").innerHTML=data.modifiedAt;
        })


    document.querySelector('.pop_style_view').style.display="block";
}

function pop_style_view_down(){
    document.querySelector('.pop_style_view').style.display="none";
}

function pop_style_delete(idx){
    document.querySelector('.pop_style_delete').style.display="block"
    const del = document.getElementById('delete_btn_selected');
    del.addEventListener('click', ()=>{
        delete_board(idx);
    })
}

function pop_style_delete_down(){
    document.querySelector('.pop_style_delete').style.display="none";
}

function delete_board(idx){
    fetch("http://localhost:8889/api/social/" +idx, {
        method:"DELETE"
    }).then((res)=>{
            alert('삭제 완료');
            location.href='/admin/style';
            return;
        })
        .catch((err) => {
            alert(err);
        })
}
