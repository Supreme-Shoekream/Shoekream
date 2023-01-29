window.onload = function(){
    const items = document.querySelectorAll('gnb_item');
    items.forEach((it) => {
        it.classList.remove('gnb_on');
    })
    const item = document.getElementById('st_gnb');
    item.classList.add('gnb_on');

    const memberIdx = document.getElementById("memberIdx").value;
    console.log(memberIdx)
    fetch("http://localhost:8889/api/social/profile/"+memberIdx )
        .then((response) => response.json())
        .then((profile) => {
            console.log(profile);
            if(profile.imgUrl != null){
                document.getElementById('profile_img_main').src =profile.imgUrl;
                document.getElementById('profile_img_mini').src =profile.imgUrl;
            }else{
                document.getElementById('profile_img_main').src ='/img/kream_empty_img.png';
                document.getElementById('profile_img_mini').src ='/img/kream_empty_img.png';
            }

            document.getElementById('user_name_mini').innerHTML = profile.nickname;
            document.getElementById('user_name_main').innerHTML = profile.nickname;

            document.querySelector('.main_txt').innerText = profile.email.split('@')[0];

            //     프로필 클릭 => idx로 받아서 kreamprincipal과 같으면 글쓰기/수정 보이게

            const memberIdx = profile.idx;

            fetch("http://localhost:8889/api/social/isBoardExist/"+ memberIdx)
                .then((res) => res.json())
                .then((dat) => {
                    // console.log(res)
                    console.log(dat)
                    document.querySelector('.social_feeds_empty').style.display = "none";
                    document.querySelector('.social_feeds').style.display = "block";
                    document.getElementById('count_boards').innerHTML = dat.length;

                    let feedList="<div class=\"gutter_item\"></div>";
                    for(let i=0;i<dat.length;i++){
                        feedList +=
                            `
                            <div class="feed_card item vertical" style="padding-top: 10px; position: absolute; left: ${Math.floor(i/5)*307}px; top: ${(i%5)*465}px">
                                <a href="#">
                                    <div class="card_box">
                                        <div class="social_img_box vertical">
                                            <picture class="picture social_img">
                                                <source type="image/webp"
                                                    srcset="">
                                                <source
                                                    srcset="">
                                                <img alt="소셜이미지"
                                                    src="${dat[i].img}"
                                                    loading="auto" class="image">
                                            </picture>
                                        </div>
                                        <div class="card_detail">
                                            <div class="user_box">
                                                <picture class="picture img_profile">
                                                    <source type="image/webp"
                                                        srcset="">
                                                    <source
                                                        srcset="">
                                                    <img alt="사용자 프로필 이미지"
                                                        src="${dat[i].memberDTO.imgUrl}"
                                                        loading="auto" class="image">
                                                </picture>
                                                <p class="feed_user_name">${dat[i].memberDTO.nickname}</p>
                                                <span aria-label="좋아요"  class="btn like" >
                                                    <img src="/img/styleImg/like_icon.png" alt="좋아요 이미지" id="like_icon"
                                                        class="icon sprite-icons social-like-gray-sm">
                                                    <span class="like_count">${dat[i].lks.length}</span>
                                                </span>
                                            </div>
                                            <p class="text_box">${dat[i].content}` + `  #` + `
                                                ${dat[i].hashtag}</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                                `
                    }
                    document.getElementById('masonry_posts').innerHTML = feedList;
                    // }
                })
                .catch((err)=>{
                    console.log(err)
                    document.querySelector('.social_feeds').style.display = "none";
                    document.querySelector('.social_feeds_empty').style.display = "block";
                })

        })

}