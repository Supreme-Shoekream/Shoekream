// 기능1. 스크롤시 왼쪽 상품 사진 움직이기
const is_fixed= document.querySelector(".is_fixed");
    // 왼쪽 상품 사진 ㅋ

// const contentTop = is_fixed.getBoundingClientRect().top + window.scrollY; 
    // 컨텐츠 영역부터 브라우저 최상단까지 길이 구함
    // getBoundingClientRect().top: 뷰포트 상의 좌표값을 나타냄
    // window.scrollY: 브라우저 최상단에서 현재까지 스크롤 된 좌표값 구함

window.addEventListener('scroll', function(){
    // console.log(window.scrollY);
    if(window.scrollY >= 1200){ // 좌표값: 1200에서 멈춰야함
        is_fixed.classList.add('is_absolute');
            // css에서 position:absolute , top:1200px 지정해줬음
    }else{
        is_fixed.classList.remove('is_absolute');
    }
});











