function following_down(){
    const div = document.getElementById('like_users');
    div.style.display = 'none';
}

function pop_like(idx){
    fetch("http://localhost:8889/api/social/likes/" + idx)

}