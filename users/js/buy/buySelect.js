

// 방법1: jquery로 
// $(".select_item").click(function(){
// $(".select_item").removeClass("active");
// $(this).addClass("active");
// $("#next").css("display","block")
// }); 



// 방법2: 화살표 함수
// https://gahyun-web-diary.tistory.com/302
const items = document.querySelectorAll(".select_item");

items.forEach((item)=>{
    item.addEventListener('click',()=>{
        items.forEach((e)=>{
            e.classList.remove('active');
        })
        item.classList.add('active');
        document.getElementById('next').style.display='block';
    })
})


