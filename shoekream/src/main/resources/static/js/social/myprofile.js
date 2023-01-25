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
            document.querySelector('user_name_mini').innerHTML = profile.nickname;


        })

}