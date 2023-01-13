//// view
function pop_member_view(idx){

    fetch('/api/admin/users/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            console.log(data.data.name)
            document.querySelector(".name").innerHTML=data.data.name;
            document.querySelector(".email").innerHTML=data.data.email;
            document.querySelector(".shoeSize").innerHTML=data.data.shoeSize;
            document.querySelector(".hp").innerHTML=data.data.hp;
        })
    //미리 내용 채우고나서
    document.querySelector(".layer_user_view").style.display = "block";
}
function close_member_view(){
    document.querySelector(".layer_user_view").style.display = "none";
}