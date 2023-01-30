function close_search(){
    document.querySelector('.layer_search').style.display="none"
    document.querySelector('body').classList.remove('test');
    //큰애 스느롤 다시 보아가
//.layer_search를 선택하고! 스타일을 줍니다! 안보이도록!

}
function pop_search(){
    document.querySelector('.layer_search').style.display="block"
    document.querySelector('body').classList.add('test');
    //큰애 스크롤 없애기 -
//.layer_search를 선택하고! 스타일을 줍니다! 보이도록!
}