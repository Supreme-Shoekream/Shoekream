
/* 팝업창 */

function follower_up() {
    const popup = document.getElementById('follower_pop');
    popup.style.display = "block";
    const body = document.querySelector('body');
    body.style.overflow = 'hidden'
}

function follower_down() {
    const popdown = document.getElementById('follower_pop');
    popdown.style.display = "none"
    const body = document.querySelector('body');
    body.style.overflow = 'unset'
}

function following_up() {
    const popup = document.getElementById('following_pop');
    popup.style.display = "block";
    const body = document.querySelector('body');
    body.style.overflow = 'hidden'
}

function following_down() {
    const popdown = document.getElementById('following_pop');
    popdown.style.display = "none"
    const body = document.querySelector('body');
    body.style.overflow = 'unset'
}


function more_up(){
    const popup = document.getElementById('more_pop');
    popup.style.display = "block";
    const body = document.querySelector('body');
    body.style.overflow = 'hidden'
}

function more_down() {
    const popdown = document.getElementById('more_pop');
    popdown.style.display = "none"
    const body = document.querySelector('body');
    body.style.overflow = 'unset'
}