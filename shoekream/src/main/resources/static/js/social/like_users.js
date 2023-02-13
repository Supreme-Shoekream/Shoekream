function like_users_pop_down(){
    const div = document.getElementById('like_users');
    div.style.display = 'none';
    const body = document.querySelector('body');
    body.style.overflow = 'unset'
}

function pop_like(idx){
    console.log('in')
    fetch("http://localhost:8889/api/social/likes/" + idx)
        .then((response) => response.json())
        .then((members) => {
            console.log(members)
            document.getElementById('layer_like_cnt').innerHTML = members.length;
            memberList = "";
            for(let i=0; i<members.length;i++){
                memberList +=
                    `<li class="user_item">
                    <div class="user_box">
                        <a class="user_link" onclick="profileCheck(${members[i].idx})">
                            <div class="img_box">
                                <img src="${members[i].imgUrl}"
                                     alt="KREAM 프로필 이미지" class="profile_img">
                            </div>
                            <div class="info_box">
                                                        <span class="user_name">
                                                            <span class="user_name_text">${members[i].nickname}</span>
                                                        </span>
                                <span class="user_subname">${members[i].profileMemo}</span>
                            </div>
                        </a>
<!--                        <div class="user_box_follow">-->
<!--                            <button type="button" class="btn solid small btn_follow small"> 팔로우-->
<!--                            </button>-->
<!--                        </div>-->
                    </div>
                </li>
            `
            }
            document.getElementById('layer_like_user_list').innerHTML = memberList;
        })
    const popup = document.getElementById('like_users');
    popup.style.display = "block";
    const body = document.querySelector('body')
    body.style.overflow = "hidden";
}