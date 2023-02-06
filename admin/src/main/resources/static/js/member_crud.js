//// view
function pop_member_view(idx){

    fetch('http://localhost:8899/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            console.log(data.data.name)
            console.log(data.data.imgURL)
            document.querySelector(".layer_user_view .name").innerHTML=data.data.name;
            document.querySelector(".layer_user_view .userImg").src=data.data.imgURL;
            document.querySelector(".layer_user_view .email").innerHTML=data.data.email;
            document.querySelector(".layer_user_view .shoeSize").innerHTML=data.data.shoeSize;
            document.querySelector(".layer_user_view .hp").innerHTML=data.data.hp;
        })
    //미리 내용 채우고나서
    document.querySelector(".layer_user_view").style.display = "block";
}
function close_member_view(){
    document.querySelector(".layer_user_view").style.display = "none";
}
function pop_penalty(idx){
    document.querySelector(".layer_penalty").style.display = "block";
    const btn_save = document.querySelector('.btn_save_penalty');
    btn_save.addEventListener('click',sendit);
}
function close_penalty(){
    document.querySelector(".layer_penalty").style.display = "none";
}