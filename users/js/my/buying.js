// 테이블 선택
// $(".tab_item").click(function(){
//     $(".tab_item").removeClass("active");
//     $(this).addClass("active");
//     $(".tab_item").removeClass("tab_on")
//     }); 

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
        if (item.classList.contains('buy')){
            console.log("hi")
        }
        })
})





// const tab_item = document.querySelectorAll('.tab_item')
// document.querySelectorAll('.tab_item').addEventListener('click', function(){
//     if(document.querySelector('.title').innerHTML == '진행중'){
//         document.querySelector('.status_box.field_price').style.display='none';    
//         document.querySelector('.status_box.field_expires_at').style.display='none';
//         document.querySelector('.status_box.field_date_purchased').style.display='none';
//         document.querySelector('.status_box.field_date_paid').style.display='none';
//         document.querySelector('.status_box.field_status').style.display='block';
//     }else if(document.querySelector('.title').innerHTML == '종료'){
//         document.querySelector('.status_box.field_price').style.display='none';    
//         document.querySelector('.status_box.field_expires_at').style.display='none';
//         document.querySelector('.status_box.field_date_paid').style.display='none';
//         document.querySelector('.status_box.field_date_purchased').style.display='block';
//         document.querySelector('.status_box.field_status').style.display='block';
//     }else{
//         document.querySelector('.status_box.field_date_paid').style.display='none';
//         document.querySelector('.status_box.field_date_purchased').style.display='none';
//         document.querySelector('.status_box.field_status').style.display='none';
//         document.querySelector('.status_box.field_price').style.display='block';    
//         document.querySelector('.status_box.field_expires_at').style.display='block';
//     }
// });

// $(".tab_item").click(function(){
//     $(".tab_item").removeClass("active");
//     $(this).addClass("active");
//     $(".tab_item").removeClass("tab_on")
//     let str = this.val();
//     console.log(str);
//     // if($(this).val() == 'inProgress'){
//     if($(this).val() == 'inProgress'){
//         $(".status_box.field_price").css({display: 'none'});
//         $('.status_box.field_expires_at').css({display: 'none'});
//         $('.status_box.field_date_purchased').css({display: 'none'});
//         $('.status_box.field_date_paid').css({display: 'none'});
//         $('.status_box.field_status').css({display: 'block'});
//     }else if($(".tab_item").has('end')){
//         $(".status_box.field_price").css({display: 'none'});
//         $('.status_box.field_expires_at').css({display: 'none'});
//         $('.status_box.field_date_paid').css({display: 'none'});
//         $('.status_box.field_date_purchased').css({display: 'block'});
//         $('.status_box.field_status').css({display: 'block'});
//     }else{
//         $(".status_box.field_date_paid").css({display: 'none'});
//         $('.status_box.field_date_purchased').css({display: 'none'});
//         $('.status_box.field_status').css({display: 'none'});
//         $('.status_box.field_price').css({display: 'block'});
//         $('.status_box.field_expires_at').css({display: 'block'});
//     }
//     }); 



// $(document).on('click', '.tab_item', function(){
//     if($(".tab_item").has('.active')){  
//     }
//     this.className+="active";
//     $(".status_box.field_price").css({display: 'none'});
//     $('.status_box.field_expires_at').css({display: 'none'});
//     $('.status_box.field_date_purchased').css({display: 'none'});
//     $('.status_box.field_date_paid').css({display: 'none'});
//     $('.status_box.field_status').css({display: 'block'});
// });



// if(document.querySelector('.title').innerHTML == '진행중'){
//     document.querySelector('.status_box.field_price').style.display='none';    
//     document.querySelector('.status_box.field_expires_at').style.display='none';
//     document.querySelector('.status_box.field_date_purchased').style.display='none';
//     document.querySelector('.status_box.field_date_paid').style.display='none';
//     document.querySelector('status_box.field_status').style.display='block';
// };
// // 종료 상태일경우
// if(document.querySelector('.title').innerHTML == '종료'){
//     document.querySelector('.status_box.field_price').style.display='none';    
//     document.querySelector('.status_box.field_expires_at').style.display='none';
//     document.querySelector('.status_box.field_date_paid').style.display='none';
//     document.querySelector('status_box.field_date_purchased').style.display='block';
//     document.querySelector('status_box.field_status').style.display='block';
// };

// // 구매입찰 상태일경우
// if(document.querySelector('.title').innerHTML == '구매입찰'){
//     document.querySelector('.status_box.field_date_paid').style.display='none';
//     document.querySelector('status_box.field_date_purchased').style.display='none';
//     document.querySelector('status_box.field_status').style.display='none';
//     document.querySelector('.status_box.field_price').style.display='block';    
//     document.querySelector('.status_box.field_expires_at').style.display='block';
// };








// $(".tab_item").click(function(){
//     $("tab_item").removeClass("active");
//     $(this).addClass("active");
//     $("tab_item").removeClass("status_on");
//     // $(".status_box.field_price").css({display: 'block'});
//     // $('.status_box.field_price').removeAttr('display');
//     // $('.status_box.field_date_purchased').css({display: ''});
//     // $('status_box.field_status').css({display: ''});
    
//     // $(".status_box.field_date_purchased").css("display","block");
//     }); 





// $("#inProgress").click(function(){
//     $('.status_box.field_price').removeAttr('display');
//     $('.status_box.field_expires_at').removeAttr('display');
//     $('status_box.field_status').css({display: 'block'});
//     }); 


// 






// // 버튼에 상태 value 값으로 전달
// $(document).on('click', '.status_item', function(){
//     $(".status_layer").css('display', 'none'); // 팝업 닫고
//     $(".status").val(document.querySelector('.status_item.active .btn').text.trim());
// });



// $(document).on('click', '.tab_item', function(){
//     if($(".tab_item").has('.active')){  
//         // $(".tab_item").removeClass("active");
//     }
//     this.className+="active";
//     $(".status_box.field_price").css({display: 'none'});
//     $('.status_box.field_expires_at').css({display: 'none'});
//     $('.status_box.field_date_purchased').css({display: 'none'});
//     $('.status_box.field_date_paid').css({display: 'none'});
//     $('.status_box.field_status').css({display: 'block'});
//     // $(".status_box").find("span").text($(this).children().text());
//     // layer.forEach(element=>{
//     //     element.style.display = 'none';
//     // });
//     bindAxios();
// });


// $(".tab_item").click(function(){
//     $(".tab_item").removeClass("active");
//     $(this).addClass("active");
//     $(".tab_item").removeClass("tab_on")
//     if($(this).val() == 'inProgress'){
//         $(".status_box.field_price").css({display: 'none'});
//         $('.status_box.field_expires_at').css({display: 'none'});
//         $('.status_box.field_date_purchased').css({display: 'none'});
//         $('.status_box.field_date_paid').css({display: 'none'});
//         $('.status_box.field_status').css({display: 'block'});
//     }else if($(".tab_item").has('end')){
//         $(".status_box.field_price").css({display: 'none'});
//         $('.status_box.field_expires_at').css({display: 'none'});
//         $('.status_box.field_date_paid').css({display: 'none'});
//         $('.status_box.field_date_purchased').css({display: 'block'});
//         $('.status_box.field_status').css({display: 'block'});
//     }else{
//         $(".status_box.field_date_paid").css({display: 'none'});
//         $('.status_box.field_date_purchased').css({display: 'none'});
//         $('.status_box.field_status').css({display: 'none'});
//         $('.status_box.field_price').css({display: 'block'});
//         $('.status_box.field_expires_at').css({display: 'block'});
//     }
//     }); 