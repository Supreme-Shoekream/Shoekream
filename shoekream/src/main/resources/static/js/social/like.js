

$('span.like').click(function(e){
    e.preventDefault();
});

// const items = document.querySelectorAll(".like");
//     //배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
//     items.forEach((item)=>{
//
//         item.addEventListener('click',()=>{
//
//             const ch=item.childNodes;
//             console.log(ch)
//
//             if(ch[1].getAttribute('src')=='../../img/styleImg/like_icon.png'){
//                 ch[1].setAttribute('src','../../img/styleImg/like_after_icon.png');
//                 // 숫자 ++1
//             }else{
//                 ch[1].setAttribute('src','../../img/styleImg/like_icon.png');
//             }
//
//         })
//     })

function like_clicked(boardIdx, size, lk){
        console.log(boardIdx)
    if(document.getElementById('username') == null){
        location.href='/login';
    }
    const items = document.querySelectorAll(".like");
    let likecnt = Number(document.getElementById('likecnt'+boardIdx).innerHTML);
    console.log(likecnt)
    $('span.like').click(function(e){
        e.preventDefault();
    });
            const ch=lk.childNodes;
            // console.log(lk)
            console.log(ch)
            // console.log(size)

            if(ch[1].getAttribute('src')=='/img/styleImg/like_icon.png'){
                fetch("http://localhost:8889/api/social/like/" + boardIdx)
                    .then(()=>{
                        ch[1].setAttribute('src','/img/styleImg/like_after_icon.png');
                        // ch[2].innerHTML= Number(size) + 1;
                        ch[2].innerHTML= likecnt + 1;
                    })
            }else{
                fetch("http://localhost:8889/api/social/unlike/" + boardIdx)
                    .then(()=>{
                        ch[1].setAttribute('src','/img/styleImg/like_icon.png');
                        if(ch[2].innerHTML= Number(size) == 0){
                            console.log('0일때')
                            // ch[2].innerHTML= Number(size);
                            ch[2].innerHTML= likecnt-1;
                        }else{
                            console.log('0 아닐 때')
                            // ch[2].innerHTML= Number(size)-1;
                            ch[2].innerHTML= likecnt -1;
                        }
                    })
            }


}

function like_clicked_follow(boardIdx, size, lk){

    if(document.getElementById('username') == null){
        location.href='/login';
    }
    const items = document.querySelectorAll(".like");
    $('span.like').click(function(e){
        e.preventDefault();
    });
    const ch=lk.childNodes;

    if(ch[1].getAttribute('src')=='/img/styleImg/like_icon.png'){
        fetch("http://localhost:8889/api/social/like/" + boardIdx)
            .then(()=>{
                ch[1].setAttribute('src','/img/styleImg/like_after_icon.png');
                // ch[2].innerHTML= Number(size) + 1;
                document.getElementById('likeid_'+boardIdx).innerHTML = Number(size) + 1;
            })
    }else{
        fetch("http://localhost:8889/api/social/unlike/" + boardIdx)
            .then(()=>{
                ch[1].setAttribute('src','/img/styleImg/like_icon.png');
                // ch[2].innerHTML= Number(size);
                window.location.reload();


        })
    }

}

function open_profile(memberEmail, memberIdx){
    console.log(memberEmail)
    if(document.getElementById('username') == null){
        location.href='/login';
    }
    console.log(document.getElementById('username').innerHTML.trim())
    console.log(document.getElementById('username').innerHTML.trim() == memberEmail.trim())

    if(document.getElementById('username').innerHTML.trim() == memberEmail.trim()){
        location.href = '/social/myprofile';
    }else{
        location.href = '/social/profile/'+memberIdx
    }
}

function profileCheck(memberIdx){
    fetch("http://localhost:8889/api/social/profileCheck/"+memberIdx)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
            console.log(data.goToProfile);
            location.href= data.goToProfile;
        })
}

function hashtag(hashtag){
    const hash = hashtag.innerHTML.trim().substring(1);
    console.log(hash)
    location.href='/social/hashtag/'+hash;
}
