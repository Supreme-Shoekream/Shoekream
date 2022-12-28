
// 기능1. 스크롤시 왼쪽 상품 사진 이동후 멈춤
const is_fixed= document.querySelector(".is_fixed");
    // 왼쪽 상품 사진 

window.addEventListener('scroll', function(){
    // console.log(window.scrollY);
    if(window.scrollY >= 1250){ 
        // window.scrollY: 브라우저 최상단에서 현재까지 스크롤 된 좌표값 구함
        // 좌표값이 1250인 곳에서 멈춰야함
        is_fixed.classList.add('is_absolute');
            // css에서 position:absolute , top:1250px으로 지정해줬음
    }else{
        is_fixed.classList.remove('is_absolute');
    }
});


// 기능2. 모든 사이즈 옆 버튼 클릭시 팝업 레이어창 
function everysizePopup() {
    const layer = document.querySelector('.lg.layer_detail_size_select.select_only_size');
    layer.classList.add('layer');
    const body = document.querySelector('body');
    body.style.overflow = 'hidden';
}

function everysizePopdown() {
    const layer = document.querySelector('.lg.layer_detail_size_select.select_only_size');
    layer.classList.remove('layer');
    const body = document.querySelector('body');
    body.style.overflow = '';

}

// 기능3. 
function wishPopup() {
    const layer = document.querySelector('.layer_interest.mo');
    layer.classList.add('layer');
    const body = document.querySelector('body');
    body.style.overflow = 'hidden';
}

function wishPopdown() {
    const layer = document.querySelector('.layer_interest.mo');
    layer.classList.remove('layer');
    const body = document.querySelector('body');
    body.style.overflow = '';

}









