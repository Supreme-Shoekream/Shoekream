// window.onload = async function(){

(async function() {
    searchStart(0);

    async function searchStart(index) {
    try {
        const response = await fetch(`/api/buying`);
        console.log(response);
        const data = await response.json();
        console.log(data);

        let pagination = data.pagination;
        let totalPages = pagination.totalPages;
        let currentPage = pagination.currentPage + 1;
        let buyList = "";

        data.forEach(dto => {
            buyList += `
                    <div class="purchase_list_display_item" style="background-color:#FFFFFF;">
                        <div class="purchase_list_product">
                            <div class="list_item_img_wrap">
                                <img alt="product_image" src="${dto.productImg}" class="list_item_img" style="background-color:#f4f4f4;">
                            </div>
                            <div class="list_item_title_wrap">
                                <p class="list_item_title">${dto.productName}</p>
                                <p class="list_item_description">${dto.productSize}</p>
                            </div>
                        </div>
                        <div class="list_item_status">
                            <div class="list_item_column column_secondary">
                                <p class="secondary_title display_paragraph" style="color:#222222;">${dto.price}</p>
                            </div>
                            <div class="list_item_column column_last">
                                <p class="last_title display_paragraph" style="color:#222222;">${dto.createdAt}</p>
                                <p class="last_description display_paragraph"></p>
                            </div>
                        </div>
                    </div>
                `;
        });
        document.querySelector("#buyList").innerHTML = buyList

        let lastPage = data.pagination.totalPages;

        let pageStr = "";
        if (lastPage != 0) {
            pageStr += "<<";
        }
        for (let i = 0; i < lastPage; i++) {
            pageStr +=
                "&nbsp;&nbsp; <span class='pages' id='" +
                i +
                "'>" +
                (i + 1) +
                " </span> &nbsp;&nbsp;";
        }
        if (lastPage != 0) {
            pageStr += ">>";
        }
        document.querySelector("#showPage").innerHTML = "총 "+ totalPages+" 페이지 중 " +currentPage+" 페이지"
        document.querySelector("#pageNum").innerHTML = pageStr;
    }catch (error){
        console.log(error)
    }
}




// (async function() {
//     searchStart(0);
//
//     async function searchStart() {
//
//     }
    document.addEventListener("click", event => {
        if (event.target.matches(".pages")) {
            let pageId = event.target.id;
            searchStart(pageId);
        }
    });
})();