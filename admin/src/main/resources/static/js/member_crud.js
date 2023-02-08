//// view
function pop_member_view(idx){

    fetch('http://localhost:8899/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            // console.log(data)
            // console.log(data.data.name)
            // console.log(data.data.imgUrl)
            let img = data.data.imgUrl;
            if(img==null) img='/img/styleImg/empty_profile_img.png'
            document.querySelector(".layer_user_view .name").innerHTML=data.data.name;
            document.querySelector(".layer_user_view .nickname").innerHTML=data.data.nickname;
            document.querySelector(".layer_user_view .shoeSize").innerHTML=data.data.shoeSize;
            document.querySelector(".layer_user_view .userImg").src=img;
            document.querySelector(".layer_user_view .email").innerHTML=data.data.email;
            document.querySelector(".layer_user_view .point").innerHTML=data.data.point;
            document.querySelector(".layer_user_view .hp").innerHTML=data.data.hp;
            document.querySelector(".layer_user_view .bank").innerHTML=data.data.bank;
            document.querySelector(".layer_user_view .accountNumber").innerHTML=data.data.accountNumber;
        })
    //미리 내용 채우고나서
    document.querySelector(".layer_user_view").style.display = "block";
}
function close_member_view(){
    document.querySelector(".layer_user_view").style.display = "none";
}
function href_penalty(idx) {
    fetch('http://localhost:8899/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            let email = data.data.email
            location.href="/penalty?page=0&searchKeyword="+email;
        })
}
function href_buyList(idx) {
    fetch('http://localhost:8899/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            let email = data.data.email
            location.href="/buy?page=0&searchKeyword="+email;
        })

}
function href_sellList(idx) {
    fetch('http://localhost:8899/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            let email = data.data.email
            location.href="/sell?page=0&searchKeyword="+email;
        })

}
function href_styleList(idx) {
    fetch('http://localhost:8899/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            let email = data.data.email
            location.href="/style?page=0&searchKeyword="+email;
        })

}
const searchInput = document.getElementById('search_box')
searchInput.addEventListener('blur',search_users)
function search_users(){
    location.href="/users?page=0&searchKeyword="+searchInput.value;
}