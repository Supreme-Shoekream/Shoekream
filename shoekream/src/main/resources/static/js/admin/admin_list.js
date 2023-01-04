$(function(){

    const { createApp } = Vue

    let showPage = createApp({
        data() {
            return {
                totalElements: {},
                currentPage: {}
            }
        }
    }).mount('#showPage');

    let itemList = createApp({
        data() {
            return {
                itemList: {}
            }
        }
    }).mount('#itemList');

    console.log("user.js 실행!");
    searchStart(0);

    function searchStart(index){
        console.log("index : " + index);
        $.get("/api/admin?page="+index, function(response){

            console.log(response);

            let pagination = response.pagination;
            showPage.totalPages = pagination.totalPages;    //Vue{} 데이터가 없으면 만든다
            showPage.currentPage = pagination.currentPage + 1;

            //response.data url에서 가져온 json의 데이터! 통째로
            itemList.itemList = response.data;


            let lastPage = response.pagination.totalPages;

            let pageStr = "";
            if(lastPage != 0){
                pageStr += "<<";
            }
            for(let i = 0; i < lastPage; i++){
                pageStr += "&nbsp;&nbsp; <span class='pages' id='" + i + "'>" + (i+1) + " </span> &nbsp;&nbsp;";
            }
            if(lastPage != 0){
                pageStr += ">>";
            }
            $('#pageNum').html(pageStr);
            // 선택자 안에 pagestr을 넣어줘라 innerHTML한거랑 같은효과
        });
    }

    $(document).on('click', '.pages', function(){
        let pageId = this.id;
        searchStart(pageId);
    });
});