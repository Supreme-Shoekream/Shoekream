(async function() {
    searchStart(0);

    async function searchStart(index) {
        console.log("index : " + index);
        try {
            const response = await fetch(`/api/admin/products?page=${index}`);
            const data = await response.json();
            console.log(data);

            let pagination = data.pagination;
            let totalPages = pagination.totalPages; // 31
            let currentPage = pagination.currentPage + 1; // 1
            let itemList = "";

            data.data.forEach(dto => {
                itemList +=
                    `<tr>
                        <td class="table-plus">${dto.idx}</td>
                        <td>
                            <img
                                src="${dto.img}"
                                width="80"
                                height="80"
                                alt=""
                            />
                        </td>
                        <td>${dto.brand}</td>
                        <td>
                            <h5 class="font-16">${dto.name}</h5>
                            ${dto.nameKor}
                        </td>
                        <td>${dto.modelNum}</td>
                        <td>${dto.releaseDate}</td>
                        <td>${dto.color}</td>
                        <td>${dto.firstPrice}</td>
                        <td>
                            <div class="dropdown">
                                <a
                                        class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle"
                                        href="#"
                                        role="button"
                                        data-toggle="dropdown"
                                >
                                <i class="dw dw-more"></i>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
                                    <a class="dropdown-item" onclick="productview_popup(${dto.idx})"><i class="dw dw-eye"></i>View</a>
                                    <a class="dropdown-item" onclick="productedit_popup(${dto.idx})"><i class="dw dw-edit2"></i>Edit</a>
                                    <a class="dropdown-item" onclick="productdelete_popup(${dto.idx})"><i class="dw dw-delete-3"></i>Delete</a>
                                </div>
                            </div>
                        </td>
                    </tr>`;
            });

            document.querySelector("#itemList").innerHTML = itemList

            let lastPage = data.pagination.totalPages;

            let pageGroup = Math.ceil(currentPage / 10); // 화면에 보여질 페이지 그룹 = 현재 페이지 / 한 화면에 나타낼 페이지 수

            let last = pageGroup * 10;
            if (last > lastPage) last = totalPages;
            let first = last - (10 - 1) <= 0 ? 1 : last - (10 - 1);


            let pageStr = "";
            if(lastPage != 0){
                pageStr += " <li class=\"paginate_button page-item previous disabled\" id=\"DataTables_Table_0_previous\">\n" +
                    "                                        <a href=\"#\" aria-controls=\"DataTables_Table_0\" data-dt-idx=\"0\" tabindex=\"0\" class=\"page-link\">\n" +
                    "                                            <i class=\"ion-chevron-left\"></i>\n" +
                    "                                        </a>\n" +
                    "                                    </li>";
            }
            for(let i = first-1; i < last; i++){
                pageStr += "<li class='page-item '>\n" +
                    "<a aria-controls=\"DataTables_Table_0\" id='" + i + "' class=\"page-link pages\">" + (i+1) + " </a> \n" +
                    "</li>";
            }
            if(lastPage != 0){
                pageStr += "<li class=\"paginate_button page-item next disabled\" id=\"DataTables_Table_0_next\">\n" +
                    "                                        <a href=\"#\" aria-controls=\"DataTables_Table_0\" data-dt-idx=\"2\" tabindex=\"0\" class=\"page-link\">\n" +
                    "                                            <i class=\"ion-chevron-right\"></i>\n" +
                    "                                        </a>\n" +
                    "                                    </li>";
            }
            document.querySelector(".pagination").innerHTML = pageStr;
        }catch (error){
            console.log(error)
        }
    }

    document.addEventListener("click", event => {
        if (event.target.matches(".pages")) {
            let pageId = event.target.id;
            searchStart(pageId);
        }
    });
})();