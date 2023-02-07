
window.onload = function (){
    $('span.like').click(function(e){
        e.preventDefault();
    });
    searchStart(0);
    const items = document.querySelectorAll('gnb_item');
    items.forEach((it) => {
        it.classList.remove('gnb_on');
    })
    const item = document.getElementById('st_gnb');
    item.classList.add('gnb_on');
    let pageNum = Number(document.querySelector('.btn_page').innerHTML)-1;



    async function searchStart(pageNum) {
        fetch("http://localhost:8889/api/social/trend?page="+pageNum)
            .then((response) => response.json())
            .then((data) => {
                data = data.content
                console.log(data)
                let feedList = "";
                for(let i=0;i <data.length;i++){
                    feedList +=
                        `
                        <div class="feed_card item vertical" style="padding-top: 10px; position: absolute; left: ${(i%4)*307}px; top: ${Math.floor(i/4)*465}px">
                                        <a class="feed_each">
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
                    if(data[i].islike == false){
                        feedList += `<img id="like_icon" src="/img/styleImg/like_icon.png" alt="좋아요 이미지"
                                                                class="icon sprite-icons social-like-gray-sm">`
                    }else{
                        feedList += `<img id="like_icon" src="/img/styleImg/like_after_icon.png" alt="좋아요 이미지"
                                                                class="icon sprite-icons social-like-gray-sm">`
                    }
                    feedList +=  `<span class="like_count">${data[i].lks.length}</span></span>
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

                // feedList = `<div class="gutter_item"></div>` + feedList;
                let newList = document.createElement('span');
                newList.innerHTML = feedList;
                document.getElementById('masonry_posts').appendChild(newList);
                document.querySelector('.footer').style.position= 'absolute';
                document.querySelector('.footer').style.width= '100%';
                document.querySelector('.footer').style.top= (Math.floor(((data.length)/4)+1)*465+200)+'px';
            })
        scroll();
    }
    function scroll() {

        const fullContent = document.querySelector('.masonry_posts');
        const screenHeight = screen.height;
        let flag = false;
        document.addEventListener('scroll', () => {
            const fullHeight = fullContent.clientHeight;
            const scrollPosition = pageYOffset;
            if (fullHeight - screenHeight / 2 <= scrollPosition && !flag) {
                flag = true;
                pageNum++;
                searchStart(pageNum);
            }
        })
}

    function addContent(){
        const nextLink = document.querySelector('.next_page');
        const nextURL = nextLink.getAttribute('href');

        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function(){
            if(xhr.readyState == xhr.DONE){
                if(xhr.status == 200 || xhr.status == 201){
                    const newContent = xhr.response;
                    const addList = newContent.querySelector('.masonry_posts');
                    console.log(addList)
                    fullContent.appendChild(addList);
                    flag = false;
                }else{
                    console.log(xhr.response);
                }
            }
        };
        xhr.open('GET', nextURL);
        xhr.send();
        xhr.responseType = "document";
    }
}


