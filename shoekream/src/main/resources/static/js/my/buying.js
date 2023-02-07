// 상태 팝업창
function status_layer(){
    document.querySelector('.status_layer').style.display="block"
}

function closeLayer(){
    document.querySelector('.status_layer').style.display="none"
}

// 팝업창 상태 선택
$(".status_item").click(function(){
    $(".status_item").removeClass("active");
    $(this).addClass("active");
    $(".status_item").removeClass("status_on");
    });

// 진행중 상태일경우
const tab_items = document.querySelectorAll('.tab_item');
tab_items.forEach((item) => {
    item.addEventListener('click', () => {
        tab_items.forEach((e) => {
            e.classList.remove("active")
            e.classList.remove("tab_on")
        })
        item.classList.add("active")
        console.log(item)
        if (item.classList.contains('end')){ // 종료 상태일경우
            document.querySelector('.status_box.field_price').style.display='none';    
            document.querySelector('.status_box.field_expires_at').style.display='none';
            document.querySelector('.status_box.field_date_purchased').style.display='none';
            document.querySelector('.status_box.field_date_paid').style.display='none';
            document.querySelector('.status_box.field_status').style.display='block';
            document.querySelector('#bidding').style.display='none';
            document.querySelector('#progressing').style.display='none';
            document.querySelector('#ends').style.display='block';
        }else if(item.classList.contains('inProgress')){ // 진행중 상태일경우
            document.querySelector('.status_box.field_price').style.display='none';    
            document.querySelector('.status_box.field_expires_at').style.display='none';
            document.querySelector('.status_box.field_date_paid').style.display='none';
            document.querySelector('.status_box.field_date_purchased').style.display='none';
            document.querySelector('.status_box.field_status').style.display='block';
            document.querySelector('#bidding').style.display='none';
            document.querySelector('#progressing').style.display='block';
            document.querySelector('#ends').style.display='none';
        }else { // 구매입찰 상태일경우
            document.querySelector('.status_box.field_date_paid').style.display='none';
            document.querySelector('.status_box.field_date_purchased').style.display='none';
            document.querySelector('.status_box.field_status').style.display='none';
            document.querySelector('.status_box.field_price').style.display='block';    
            document.querySelector('.status_box.field_expires_at').style.display='block';
            document.querySelector('#bidding').style.display='block';
            document.querySelector('#progressing').style.display='none';
            document.querySelector('#ends').style.display='none';
        }
        })
})

// // 버튼에 상태 value 값으로 전달
// $(document).on('click', '.status_item', function(){
//     $(".status_layer").css('display', 'none'); // 팝업 닫고
//     $(".status").val(document.querySelector('.status_item.active .btn').text.trim());
// });

// 상세페이지
function buyDetail(idx){
    location.href='/my/buying/'+ idx
}
