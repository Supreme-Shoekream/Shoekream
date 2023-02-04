

$('span.like').click(function(e){
    e.preventDefault();
});

const items = document.querySelectorAll(".like");
    //배열로 저장되기 때문에 forEach로 하나씩 이벤트를 등록해준다.
    items.forEach((item)=>{

        item.addEventListener('click',()=>{

            const ch=item.childNodes;

            if(ch[1].getAttribute('src')=='../../img/styleImg/like_icon.png'){
                ch[1].setAttribute('src','../../img/styleImg/like_after_icon.png');
                // 숫자 ++1
            }else{
                ch[1].setAttribute('src','../../img/styleImg/like_icon.png');
            }
            
        })
    })

