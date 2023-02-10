let flag= false;
window.onload = function () {
    $('span.like').click(function (e) {
        e.preventDefault();
    });
    searchStart(0);
    const items = document.querySelectorAll('gnb_item');
    items.forEach((it) => {
        it.classList.remove('gnb_on');
    })
    const item = document.getElementById('st_gnb');
    item.classList.add('gnb_on');

    let pageNum = Number(document.querySelector('.btn_page').innerHTML) - 1;
    let totPages = Number(document.getElementById('totPages').value)


    async function searchStart(pageNum) {
        flag = false;
        fetch("http://localhost:8889/api/social/trend?page=" + pageNum)
            .then((response) => response.json())
            .then((data) => {
                data = data.content
                let feedList = "";
                for (let i = 0; i < data.length; i++) {
                    feedList +=
                        `
                        <div class="feed_card item vertical" style="padding-top: 10px; position: absolute; left: ${(i % 4) * 307}px; top: ${Math.floor(i / 4) * 465 + pageNum * 1400}px">
<!--                        <div class="feed_card item" >-->
                                        <a href="/social/details#${data[i].idx}" class="feed_each">
                                            <div class="card_box">
                                                <div class="social_img_box vertical">
                                                    <a href="/social/details#${data[i].idx}" >
                                                    <picture class="picture social_img">
                                                        <source type="image/webp"
                                                            srcset="">
                                                        <source
                                                            srcset="">
                                                        <img alt="소셜이미지"
                                                            src="${data[i].img}"
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
                                                                src="${data[i].memberDTO.imgUrl}"
                                                                loading="auto" class="image" onclick="open_profile('${data[i].memberDTO.email}', ${data[i].memberDTO.idx})">
                                                        </picture>
                                                        <p class="user_name" onclick="open_profile('${data[i].memberDTO.email}',
                                                         ${data[i].memberDTO.idx})">${data[i].memberDTO.nickname}</p><span aria-label="좋아요"
                                                            role="button" class="btn like" onclick="like_clicked(${data[i].idx}, ${data[i].lks.length}, this)">
                                                            `
                    if (data[i].islike == false) {
                        feedList += `<img id="like_icon" src="/img/styleImg/like_icon.png" alt="좋아요 이미지"
                                                                class="icon sprite-icons social-like-gray-sm">`
                    } else {
                        feedList += `<img id="like_icon" src="/img/styleImg/like_after_icon.png" alt="좋아요 이미지"
                                                                class="icon sprite-icons social-like-gray-sm">`
                    }
                    feedList += `<span class="like_count" id="likecnt${data[i].idx}">${data[i].lks.length}</span></span>
                                                    </div>`
                    if (data[i].hashtag != null) {
                        feedList += `<p className="text_box" style="padding: 5px">${data[i].content}` + ` #` +
                            `${data[i].hashtag}</p>`
                    } else {
                        feedList += `<p className="text_box" style="padding: 5px">${data[i].content}</p>`
                    }
                    feedList += `</div>
                                            </div>
                                        </a>
                                    </div>
                        `
                }

                // feedList = `<div class="gutter_item"></div>` + feedList;
                let newList = document.createElement('div');
                newList.innerHTML = feedList;
                // newList.style.display='flex';
                document.getElementById('masonry_posts').appendChild(newList);
                document.querySelector('.footer').style.position = 'absolute';
                document.querySelector('.footer').style.width = '100%';
                document.querySelector('.footer').style.top = (Math.floor(((data.length) / 4) + 1) * 465 + 200 + pageNum * 1300) + 'px';
                // document.getElementById('loading').style.top = (Math.floor(((data.length) / 4) + 1) * 465 + pageNum * 1300) + 'px';
                if (pageNum == totPages - 2) {
                    document.getElementById('loading').style.display = 'none';
                }
            })
        // document.getElementById('loading').style.display = 'none';
        loading()
    }

    if (pageNum < totPages - 1) {
        scroll();
    }

    function scroll() {
        const screenHeight = screen.height;
        document.addEventListener('scroll', () => {
            flag = false;
            const scrollPosition = pageYOffset;
            if (160 + (pageNum + 1) * 1300 - screenHeight / 2 <= scrollPosition && !flag) {
                flag = true;
                pageNum++;
                if (pageNum < totPages - 1) {
                    loading();
                    wait(1);
                    searchStart(pageNum);
                }
            }
        })
    }


    function wait(sec) {
        let start = Date.now(), now = start;
        while (now - start < sec * 1000) {
            now = Date.now();
        }
    }
}

function loading(){
    loadimgImg = document.getElementById('loading');
    if(loadimgImg.style.display == 'block'){
        loadimgImg.style.display = 'none';
    }else{
        loadimgImg.style.display = 'block';
    }
}
