(async function() {
    searchStart(0);

    async function searchStart(index) {
        console.log("index : " + index);
        try {
            const response = await fetch(`/api/admin?page=${index}`);
            const data = await response.json();
            console.log(data);

            let pagination = data.pagination;
            let totalPages = pagination.totalPages;
            let currentPage = pagination.currentPage + 1;
            let itemList = "";
            data.data.forEach(dto => {
                itemList += `
          <tr class="bg-pink">
            <td >${dto.idx}</td>
            <td>${dto.adminid}</td>
            <td>${dto.name}</td>
            <td>${dto.hp}</td>
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
                <div
                  class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list"
                >
                  <a class="dropdown-item view" href="#" onclick="pop_admin_view(${dto.idx})"
                  ><i class="dw dw-eye"></i> View</a
                  >
                  <a class="dropdown-item edit" href="#" onclick="pop_admin_edit(${dto.idx})"
                  ><i class="dw dw-edit2"></i> Edit</a
                  >
                  <a class="dropdown-item delete" href="#" onclick="pop_admin_delete(${dto.idx})"
                  ><i class="dw dw-delete-3"></i> Delete</a
                  >
                </div>
              </div>
            </td>
          </tr>
        `;
            });
            document.querySelector("#itemList").innerHTML = itemList

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
    document.addEventListener("click", event => {
        if (event.target.matches(".pages")) {
            let pageId = event.target.id;
            searchStart(pageId);
        }
    });
})();