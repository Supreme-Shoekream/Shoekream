window.onload=function(){
    const productIdx = document.getElementById('wish_proIdx').getAttribute('value');
    // console.log(productIdx);
    fetch("http://localhost:8889/api/social/products/mini/"+productIdx)
        .then((res)=>res.json())
        .then((data)=>{
            console.log(data)
            let feedList="<div class=\"gutter_item\"></div>";
            document.getElementById('style_cnt').innerHTML = data.length;
            if(data.length<=8){
                document.querySelector('.more_btn_box').style.display='none';
                for(let i=0;i<data.length;i++){
                    feedList += `
                    <div class="feed_card item square" style="padding-top: 10px; position: absolute; left: ${(i%4)*307}px; top: ${Math.floor(i/4)*400}px">
                                            <a>
                                                <div class="card_box">
                                                    <div class="social_img_box square">
                                                        <a href="/social/details#${data[i].idx}">
                                                            <picture class="picture social_img">
                                                                <source type="image/webp" srcset="">
                                                                <source srcset="">
                                                                <img alt="소셜이미지" src="${data[i].img}" loading="lazy" class="image">
                                                            </picture>
                                                        </a>
                                                    </div>
                                                    <div class="card_detail">
                                                        <div class="user_box">
                                                            <picture class="picture img_profile">
                                                                <source type="image/webp" srcset="">
                                                                <source srcset="">
                                                                <img alt="사용자 프로필 이미지" src="${data[i].memberDTO.imgUrl}" loading="lazy" class="image">
                                                            </picture>
                                                            <p class="user_name">${data[i].memberDTO.nickname}</p>
                                                            <span aria-label="좋아요" role="button" class="btn like" onclick="like_clicked(${data[i].idx}, ${data[i].lks.length}, this)">
                                                                `
                        if(data[i].islike == false){
                            feedList += `<img id="like_icon" src="/img/styleImg/like_icon.png" alt="좋아요 이미지"
                                                            class="icon sprite-icons social-like-gray-sm">`
                        }else{
                            feedList += `<img id="like_icon" src="/img/styleImg/like_after_icon.png" alt="좋아요 이미지"
                                                            class="icon sprite-icons social-like-gray-sm">`
                        }
                                                      feedList +=  `<span class="like_count">${data[i].lks.length}</span>
                                                            </span>
                                                        </div>
                                                        <p class="text_box">${data[i].content}` + `  #` + `
                                                ${data[i].hashtag}</p>
                                                    </div>
                                                </div>
                                            </a>
                                        </div>
                    `
                }
            }else{
                for(let i=0;i<8;i++){
                    feedList += `
                    <div class="feed_card item square" style="padding-top: 10px; position: absolute; left: ${(i%4)*307}px; top: ${Math.floor(i/4)*400}px">
                                            <a>
                                                <div class="card_box">
                                                    <div class="social_img_box square">
                                                        <a href="/social/details#${data[i].idx}">
                                                            <picture class="picture social_img">
                                                                <source type="image/webp" srcset="">
                                                                <source srcset="">
                                                                <img alt="소셜이미지" src="${data[i].img}" loading="lazy" class="image">
                                                            </picture>
                                                        </a>
                                                    </div>
                                                    <div class="card_detail">
                                                        <div class="user_box">
                                                            <picture class="picture img_profile">
                                                                <source type="image/webp" srcset="">
                                                                <source srcset="">
                                                                <img alt="사용자 프로필 이미지" src="${data[i].memberDTO.imgUrl}" loading="lazy" class="image">
                                                            </picture>
                                                            <p class="user_name">${data[i].memberDTO.nickname}</p>
                                                            <span aria-label="좋아요" role="button" class="btn like" onclick="like_clicked(${data[i].idx}, ${data[i].lks.length}, this)">
                                                                `
                    if(data[i].islike == false){
                        feedList += `<img id="like_icon" src="/img/styleImg/like_icon.png" alt="좋아요 이미지"
                                                            class="icon sprite-icons social-like-gray-sm">`
                    }else{
                        feedList += `<img id="like_icon" src="/img/styleImg/like_after_icon.png" alt="좋아요 이미지"
                                                            class="icon sprite-icons social-like-gray-sm">`
                    }
                    feedList +=  `<span class="like_count">${data[i].lks.length}</span>
                                                            </span>
                                                        </div>`
                    if(data[i].hashtag != null){
                        feedList += `<p className="text_box">${data[i].content}` + ` #` +
                            `${data[i].hashtag}</p>`
                    }else{
                        feedList += `<p className="text_box">${data[i].content}</p>`
                    }
                    feedList += `</div>
                                                </div>
                                            </a>
                                        </div>
                    `
                }


            }
            document.querySelector('.masonry_posts').innerHTML = feedList;
        })
        .catch((err)=>{
            console.log(err)
                document.getElementById('style_cnt').innerHTML = '0';

            const empty = `<div class="empty_box">
                <img src="/img/styleImg/empty_feed_product.png" style="width: 70px">
                <p class="empty_txt">앱에서 상품 태그를 등록하여 사진을 올리세요</p>
            </div>`

            document.querySelector('.masonry_posts').style.height='300px'
            document.querySelector('.masonry_posts').innerHTML = empty;
            document.querySelector('.more_btn_box').style.display='none';
        })
}

function tagPage(){
    const productIdx = document.getElementById('wish_proIdx').getAttribute('value');
    console.log(productIdx)
    location.href='/social/social_product/'+productIdx;
}