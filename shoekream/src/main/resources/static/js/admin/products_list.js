
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

    console.log("products_list.js 실행!");
    searchStart(0);

    function searchStart(index){
        console.log("index : " + index);
        $.get("/api/admin/products?page="+index, function(response){

            console.log(response);

            let pagination = response.pagination;
            showPage.totalPages = pagination.totalPages;
            showPage.currentPage = pagination.currentPage + 1;

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
        });
    }

    $(document).on('click', '.pages', function(){
        let pageId = this.id;
        searchStart(pageId);
    });
});