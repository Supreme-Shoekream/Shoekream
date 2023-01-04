function delete_img(){
    const pi = document.getElementById('profile_img');
    console.log(pi.src);
    pi.setAttribute('src', '../../img/kream_empty_img.png');
    console.log(pi.src);
}


// 프로필 이미지 변경
function readImage(input){
    // console.log(input);
    if(input.files && input.files[0]){
        const reader = new FileReader();

        reader.onload = e => {
            const newImg = document.getElementById('profile_img');
            newImg.src=e.target.result;
        }
        reader.readAsDataURL(input.files[0]);
    }
}

const modifyImg = document.getElementById('modify_img');
modifyImg.addEventListener("change", e => {
    // console.log(e.target);
    readImage(e.target);
})