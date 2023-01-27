window.onload = function(){
    const items = document.querySelectorAll('gnb_item');
    items.forEach((it) => {
        it.classList.remove('gnb_on');
    })
    const item = document.getElementById('st_gnb');
    item.classList.add('gnb_on');


    fetch("http://localhost:8889/api/social/myprofile")
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
            document.querySelectorAll('.user_name').innerHTML = profile.nickname;

            document.querySelector('.main_txt').innerHTML = profile.email.split('@')[0];
            
        //     프로필 클릭 => idx로 받아서 kreamprincipal과 같으면 글쓰기/수정 보이게
        //     api에서 해당 사용자 board 개수 => if문으로 0일 때 / 0보다 클 때 나눠서
            const memberIdx = profile.idx;

            fetch("http://localhost:8889/api/social/isBoardExist/"+ memberIdx)
                .then((res) => {
                    if(res.json.length == 0){
                        document.querySelector('.social_feeds_empty').style.display = "block";
                    }
                })

        })

}