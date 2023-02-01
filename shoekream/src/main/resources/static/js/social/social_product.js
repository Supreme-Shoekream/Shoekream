addEventListener("scroll", e => {
    const mini = document.getElementById('brand_scroll');
    if(window.scrollY>0){
        mini.setAttribute('style', 'display: flex;');
    }else{
        mini.setAttribute('style', 'display: none;');
    }
})
window.onload = function(){
    const items = document.querySelectorAll('gnb_item');
    items.forEach((it) => {
        it.classList.remove('gnb_on');
    })
    const item = document.getElementById('sh_gnb');
    item.classList.add('gnb_on');

    const productIdx = document.querySelector('.productIdx').value;
    fetch("http://localhost:8889/api/social/products/full/"+productIdx)
        .then((boards) => boards.json())
        .then((d)=>{
            console.log(d)
            let feedList = "";
            for(let i=0;i <d.length;i++){
                feedList +=
                    `
                    <div class="feed_card item vertical" style="padding-top: 10px; position: absolute; left: ${(i%4)*307}px; top: ${Math.floor(i/4)*465}px">
                                    <a class="feed_each">
                                        <div class="card_box">
                                            <div class="social_img_box vertical">
                                                <a href="/social/details#${d[i].idx}" >
                                                <picture class="picture social_img">
                                                    <source type="image/webp"
                                                        srcset="">
                                                    <source
                                                        srcset="">
                                                    <img alt="소셜이미지"
                                                        src="${d[i].img}"
                                                        loading="auto" class="image">
                                                </picture>
                                                </a>
                                            </div>
                                            <div class="card_detail">
                                                <div class="user_box">
                                                    <picture class="picture img_profile">
                                                        <source type="image/webp"
                                                            srcset="">
                                                        <source
                                                            srcset="">
                                                        <img alt="사용자 프로필 이미지"
                                                            src="${d[i].memberDTO.imgUrl}"
                                                            loading="auto" class="image" onclick="open_profile('${d[i].memberDTO.email}', ${d[i].memberDTO.idx})">
                                                    </picture>
                                                    <p class="user_name" onclick="open_profile('${d[i].memberDTO.email}', ${d[i].memberDTO.idx})">${d[i].memberDTO.nickname}</p><span aria-label="좋아요"
                                                        role="button" class="btn like" onclick="like_clicked(${d[i].idx}, ${d[i].lks.length}, this)">
                                                        `
                if(d[i].islike == false){
                    feedList += `<img id="like_icon" src="/img/styleImg/like_icon.png" alt="좋아요 이미지"
                                                            class="icon sprite-icons social-like-gray-sm">`
                }else{
                    feedList += `<img id="like_icon" src="/img/styleImg/like_after_icon.png" alt="좋아요 이미지"
                                                            class="icon sprite-icons social-like-gray-sm">`
                }
                feedList +=  `<span class="like_count">${d[i].lks.length}</span></span>
                                                </div>`
                if(d[i].hashtag != null){
                    feedList += `<p className="text_box">${d[i].content}` + ` #` +
                        `${d[i].hashtag}</p>`
                }else{
                    feedList += `<p className="text_box">${d[i].content}</p>`
                }
                feedList += `</div>
                                        </div>
                                    </a>
                                </div>
                    `
            }

            feedList = `<div class="gutter_item"></div>` + feedList;
            document.querySelector('.masonry_posts').innerHTML = feedList;
            document.querySelector('.footer').style.position= 'absolute';
            document.querySelector('.footer').style.width= '100%';
            document.querySelector('.footer').style.top= (Math.floor(((d.length)/4)+1)*465+300)+'px';
        })
}