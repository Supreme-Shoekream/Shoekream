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

// ===============================
//  검색창 검색 구현
// ===============================

let bef_timeout ;

document.addEventListener("DOMContentLoaded", function() {
    document.querySelector(".search1 .input_search").addEventListener("keyup" , (e) => {
        let _text = document.querySelector(".search1 .input_search").value.trim();
        if(_text) {
            clearTimeout(bef_timeout);
            bef_timeout = setTimeout(show_search_layer, 500, _text)
        }else reset_search_layer();

    })
})


// 검색어 입력시
// async function 함수명() {
//     await 비동기_처리_메서드_명();
//      promise > 비동기 처리 객체
//      new Promise -> 대기상태

async function show_search_layer(_keyword){
    console.log(_keyword);
    // 검색 리스트
    return new Promise(async function (resolve, reject) {
        document.querySelector(".search_content_wrap").classList.remove("on");

        const _result = await fetch(`/api/shop/searchWord?keyword=${_keyword}`).then((response) => response.json());
        console.log(_result);

        const _html = _result.data.map(_val =>

            `<div  class="suggest_item">
                  <a  href="/product/${_val.idx}" class="suggest_link">
                    <div  class="suggest_thumb" style="background-color: rgb(235, 240, 245);">
                      <picture class="picture thumb_img">
                        <img src="${_val.img}" loading="lazy" class="image">
                      </picture>
                    </div>
                    <div  class="suggest_info">
                      <p  class="model_title">${_val.name}</p>
                      <p  class="model_sub_info">${_val.nameKor}</p>
                    </div>
                  </a>
            </div>`
        );
        console.log(_html);
        let str = _html.toString().replaceAll(',','');
        document.querySelector(".suggest_list").innerHTML = _html.length > 0  ? str : '<div><a href="#">검색결과가 없습니다.</a></div>';
        document.querySelector(".search_content_wrap").classList.add("on");
        resolve();
    });
}

//레이어 검색 영역 reset
function reset_search_layer(){
    document.querySelector(".search_content_wrap").classList.remove("on");
    document.querySelector(".suggest_list").innerHTML = "";

}


