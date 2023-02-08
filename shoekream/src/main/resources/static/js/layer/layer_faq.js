// 팝업창 닫기
function closeLayer(){
    document.querySelector('.layer_faq').style.display="none"
    document.querySelector('.layer_penalty').style.display="none"
}

// 드롭다운
const dropdowns = document.querySelectorAll(".dropdown");
dropdowns.forEach((dropdown) => {

    dropdown.addEventListener("click",() => {

        const ch = dropdown.childNodes;
        console.log(ch);

        if(ch[3].style.display=="none"){
            ch[3].style.display="block";
        }else{
            ch[3].style.display="none";
        }

    })
});