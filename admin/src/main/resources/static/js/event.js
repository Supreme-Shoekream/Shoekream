// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// 파일첨부
// 이미지 업로드
$("input[type='file']").on("change", function(e){
    let isExist = document.getElementById("previewImg").getAttribute("create");
    if(isExist == "true"){
        let formData = new FormData();

        let fileInput = $('input[name="uploadFile"]');
        let fileList = fileInput[0].files;

        let fileObj = fileList[0];

        if(!fileCheck(fileObj.name, fileObj.size)){
            return false;
        }
        alert("사진 파일 이름, 크기 통과");

        formData.append("uploadFile", fileObj);

        // ajax를 사용하여 서버로 전송
        $.ajax({
            url: '/api/admin/event/uploadFile',
            processData : false,
            contentType : false,
            data : formData,
            type : 'POST',
            dataType : 'text',
            success : function (data){
                alert(data);
                document.getElementById("previewImg").value = "/images/" + data;
                alert("create success");
            },
            error: function(e) {
                alert("값을 가져오지 못했습니다.");
            }
        });
    }
    else if(isExist == null) {
        let formData2 = new FormData();

        let fileInput2 = $('input[name="uploadFile2"]');
        let fileList2 = fileInput2[0].files;

        let fileObj2 = fileList2[0];

        if(!fileCheck(fileObj2.name, fileObj2.size)){
            return false;
        }
        alert("사진 파일 이름, 크기 통과");

        formData2.append("uploadFile", fileObj2);


        // ajax를 사용하여 서버로 전송
        $.ajax({
            url: '/api/admin/event/uploadFile',
            processData : false,
            contentType : false,
            data : formData2,
            type : 'POST',
            dataType : 'text',
            success : function (data){
                alert(data);
                document.getElementById("previewImgEdit").value = "/images/" + data;
                alert("edit success");
            },
            error: function(e) {
                alert("값을 가져오지 못했습니다.");
            }
        });
    }
});


function load_event_img_create(input){
    if(input.files && input.files[0]){
        const file = new FileReader();
        file.onload = function (e) {
            document.getElementById("previewImg").src = e.target.result;
        }
        file.readAsDataURL(input.files[0]);
    }
    else{
        document.getElementById("previewImg").src = "";
    }
}

function load_event_img_edit(input){
    if(input.files && input.files[0]){
        const file = new FileReader();
        file.onload = function (e) {
            document.getElementById("previewImgEdit").src = e.target.result;
        }
        file.readAsDataURL((input.files[0]));
    }
    else{
        document.getElementById("previewImgEdit").src = "";
    }
}

// 파일 타입, 크기 제한
let regex = new RegExp("(.*?)\.(jpg|jpeg|png|gif)$"); // 이미지 파일 타입 png, jpg, jpeg만 허용
let maxSize = 100 * 1024 * 1024;
let blankPattern = /[\s]/g;
function fileCheck(fileName, fileSize){
    if(fileSize >= maxSize){
        alert("파일 업로드 가능한 사이즈를 초과하였습니다.");
        return false;
    }
    if(!regex.test(fileName)){
        alert("해당 종류의 파일은 업로드할 수 없습니다.");
        return false;
    }
    if(blankPattern.test(fileName)){
        alert("파일명에는 공백이 포함될 수 없습니다.");
        return false;
    }
    return true;
}



// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// create
function pop_event_register(){
    document.querySelector(".layer_event_create").style.display = "block";
    document.getElementById("previewImg").setAttribute("create", "true");
    document.getElementById('create_btn').onclick = function(){sendit()};
}

function sendit(){
    const title = document.getElementById('c_event_input').value;
    const product = document.getElementById('c_pro_input').value;
    const img = document.getElementById('previewImg').value;
    const startTime = document.getElementById('c_startTime_input').value;
    const endTime = document.getElementById('c_endTime_input').value;


    if(title === ""){
        alert("이벤트명을 작성해주세요");
        title.focus();
        return false;
    }
    if(product === ""){
        alert("상품번호를 작성해주세요");
        product.focus();
        return false;
    }
    if(img === ""){
        alert("이벤트 이미지를 넣어주세요");
        img.focus();
        return false;
    }
    if(startTime === ""){
        alert("시작시간을 설정해주세요");
        startTime.focus();
        return false;
    }
    if(endTime === ""){
        alert("마감시간을 설정해주세요");
        endTime.focus();
        return false;
    }


    fetch("http://localhost:8899/api/admin/event", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "title":`${title}`,
                "productIdx":`${product}`,
                "img":`${img}`,
                "startTime":`${startTime}`,
                "endTime":`${endTime}`
            }
        }),
    })
        .then((res) => {
            alert("이벤트 등록 성공!")
            location.reload();
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 이벤트 등록 실패!");
            location.reload();
            return;
        })
}

function close_event_create(){
    document.querySelector(".layer_event_create").style.display = "none";
    document.getElementById("previewImg").removeAttribute("create");
    location.reload();
}


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// view
function pop_event_view(idx){
    document.querySelector(".layer_event_view").style.display = "block";

    fetch("http://localhost:8899/api/admin/event/" + idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            document.querySelector("#v_event_input").innerHTML = data.title;
            document.querySelector("#v_pro_input").innerHTML = data.productIdx;
            document.querySelector("#v_file1").src = data.img;
            document.querySelector("#v_startTime_input").innerHTML = data.startTime;
            document.querySelector("#v_endTime_input").innerHTML = data.endTime;
        })

}

function close_event_view() {
    document.querySelector(".layer_event_view").style.display = "none";
}


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// edit
function pop_event_edit(idx){
    document.querySelector(".layer_event_edit").style.display = "block";

    fetch('http://localhost:8899/api/admin/event/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            document.querySelector("#e_event_input").value = data.title;
            document.querySelector("#e_pro_input").value = data.productIdx;
            document.querySelector("#previewImgEdit").src = data.img;
            document.querySelector("#e_startTime_input").value = data.startTime;
            document.querySelector("#e_endTime_input").value = data.endTime;
        })

    document.getElementById("edit_btn").onclick = function(){sendedit(idx)};
}

function sendedit(idx) {
    const title1 = document.querySelector('#e_event_input').value;
    const product1 = document.querySelector('#e_pro_input').value;
    let img1 = "";
    if(document.getElementById('previewImgEdit').value == null){
        const img = document.getElementById('previewImgEdit').src;
        img1 = img.replaceAll("http://localhost:8899", "");
    }else{
        const img = document.getElementById('previewImgEdit').value;
        img1 = img.replaceAll("http://localhost:8899", "");
    }
    const startTime1 = document.querySelector('#e_startTime_input').value;
    const endTime1 = document.querySelector('#e_endTime_input').value;


    fetch('http://localhost:8899/api/admin/event/' + idx, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            "transaction_time": `${new Date()}`,
            "resultCode": "ok",
            "description": "정상",
            "data": {
                "productIdx": `${product1}`,
                "title": `${title1}`,
                "img": `${img1}`,
                "startTime": `${startTime1}`,
                "endTime": `${endTime1}`
            }
        }),
    })
        .then((res) => {
            alert("이벤트 수정 성공!")
            location.reload();
            return;
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 이벤트 수정 실패!");
            location.reload();
            return;
        })
}

function close_event_edit() {
    document.querySelector(".layer_event_edit").style.display = "none";
    location.reload();
}


// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
// delete
function pop_event_delete(idx){
    document.querySelector(".event_delete").style.display = "block";
    const btn_delete = document.querySelector('.btn_delete');
    btn_delete.addEventListener('click',()=>{
        eventdelete(idx);
    });
}


function eventdelete(idx){
    fetch('http://localhost:8899/api/admin/event/'+idx, {
        method: "DELETE"
    })
        .then((res) => {
            alert("이벤트 삭제 성공!")
            location.reload();

        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert("에러! 에러! 이벤트 삭제 실패!");
            location.reload();
            return;
        })
}

function close_event_delete() {
    document.querySelector(".event_delete").style.display = "none";
}

// ⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️⚠️
let eventIdx
function addPointEvent(){
    eventdraw(eventIdx)
}
function pop_event_draw(idx){
    eventIdx =idx
    document.querySelector(".event_draw").style.display = "block";
    const btn_draw = document.querySelector('.btn_draw');
    btn_draw.addEventListener('click', addPointEvent)
}
function eventdraw(idx){
    fetch('http://localhost:8899/api/admin/event/'+idx, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            "transaction_time": `${new Date()}`,
            "resultCode": "ok",
            "description": "정상",
            "data": {
                "idx": `${idx}`
            }
        }),})
            .then((res) => {
                alert(" 성공!")
                location.reload();
            })
            .catch((err) => {
                alert("에러! 에러!!");
                location.reload();
                return;
            })
}
function close_event_draw() {
    document.querySelector(".event_draw").style.display = "none";
    document.querySelector('.btn_draw').removeEventListener('click', addPointEvent)
}