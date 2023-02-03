window.onload = menuSelect();

function menuSelect(){
var link = window.location.href
var menuList = document.getElementsByClassName("menu_link")
for(let i=0;i<menuList.length;i++){
    if(link.includes(menuList[i].getAttribute('name'))){
        menuList[i].classList.add('menu_select')
    }
}
}

