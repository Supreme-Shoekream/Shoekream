function pop_event_register(){
    document.querySelector(".layer_event_create").style.display = "block";
    const btn_save = document.querySelector('#create_btn');
    btn_save.addEventListener('click',sendit);
}
function sendit() {
    // create
    //request로 필요한 DOM 객체 선택
    const title = document.querySelector('#c_event_input');
    const product = document.querySelector('#c_pro_input');
    const img = document.querySelector('#c_file1');
    const startTime = document.querySelector('#c_startTime_input');
    const endTime = document.querySelector('#c_endTime_input');



    fetch('http://localhost:8889/api/admin/event', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time":`${new Date()}`,
            "resultCode":"ok",
            "description":"정상",
            "data":{
                "productIdx":`${product.value}`,
                "title":`${title.value}`,
                "img":`${img.value}`,
                "startTime":`${startTime.value}`,
                "endTime":`${endTime.value}`
            }
        }),
    })
        .then((res) => {
            alert('등록성공')
            location.href='/admin/event';
            return; //리턴을 걸어서 진행하는 것을 막는다!
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err)=>{
            alert(err);
        })
}

function pop_event_edit(idx){
    document.querySelector(".layer_event_edit").style.display = "block";
    fetch('http://localhost:8889/api/admin/event/'+idx)
        .then((response) => response.json())
        .then((data) => {
            console.log("edit🟡" + data);
            document.querySelector("#e_event_input").value = data.title;
            document.querySelector("#e_pro_input").value = data.productIdx;
            document.querySelector("#e_file1").value = data.img;
            document.querySelector("#e_startTime_input").value = data.startTime;
            document.querySelector("#e_endTime_input").value = data.endTime;
        })

    const btn_edit = document.querySelector('#edit_btn');
    btn_edit.addEventListener('click',() =>{
        sendedit(idx)
    });
}
function sendedit(idx) {
// edit
    const title1 = document.querySelector('#e_event_input');
    const product1 = document.querySelector('#e_pro_input');
    const img1 = document.querySelector('#e_file1');
    const startTime1 = document.querySelector('#e_startTime_input');
    const endTime1 = document.querySelector('#e_endTime_input');

    fetch('http://localhost:8889/api/admin/event/'+idx, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            //우리가 만든데이터
            "transaction_time": `${new Date()}`,
            "resultCode": "ok",
            "description": "정상",
            "data": {
                "productIdx": `${product1.value}`,
                "title": `${title1.value}`,
                "img": `${img1.value}`,
                "startTime": `${startTime1.value}`,
                "endTime": `${endTime1.value}`
            }
        }),
    })
        .then((res) => {
            alert('수정성공')
            location.href = '/admin/event';
            return; //리턴을 걸어서 진행하는 것을 막는다!
        })
        .then((data) => {
            console.log(data);
            return;
        })
        .catch((err) => {
            alert(err);
        })
}

//view
function pop_event_view(idx) {
    document.querySelector(".layer_event_view").style.display = "block";
    fetch('http://localhost:8889/api/admin/event/' + idx)
        .then((response) => response.json())
        .then((data) => {
            document.querySelector("#v_event_input").innerHTML = data.title;
            document.querySelector("#v_pro_input").innerHTML = data.productIdx;
            document.querySelector("#v_file1").src = data.img;
            document.querySelector("#v_startTime_input").innerHTML = data.startTime;
            document.querySelector("#v_endTime_input").innerHTML = data.endTime;
        })
}
    $(document).ready(function () {
        //파일첨부 이벤트
        $('.filebox .upload-hidden').on('change', function () {
            if (window.FileReader) {
                var filename = $(this)[0].files[0].name;
                if (!validFileType(filename)) {
                    alert("허용하지 않는 확장자 파일입니다.");
                    return false;
                } else {
                    if (!validFileSize($(this)[0].files[0])) {
                        alert("파일 사이즈가 10MB를 초과합니다.");
                        return false;
                    } else {
                        if (!validFileNameSize(filename)) {
                            alert("파일명이 30자를 초과합니다.");
                            return false;
                        }
                    }
                }
            } else {
                var filename = $(this).val().split('/').pop().split('\\').pop();
            }
            $(this).prev().val(filename); //input upload-name 에 파일명 설정해주기

            readImage($(this)[0]); //미리보기
        });
    });

    function validFileType(filename) {
        const fileTypes = ["png", "jpg", "jpeg", "gif"];
        return fileTypes.indexOf(filename.substring(filename.lastIndexOf(".") + 1, filename.length).toLowerCase()) >= 0;
    }

    function validFileSize(file) {
        if (file.size > 100000000) { //10MB
            return false;
        } else {
            return true;
        }
    }

    function validFileNameSize(filename) {
        if (filename.length > 30) { //30자
            return false;
        } else {
            return true;
        }
    }

//이미지 띄우기
    function readImage(input) {
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const previewImage = document.getElementById("c_file1");
                previewImage.src = e.target.result;
            }
            // reader가 이미지 읽도록 하기
            reader.readAsDataURL(input.files[0]);
        }
    }

//이미지 원본 팝업 띄우기
    function popImage(url) {
        var img = new Image();
        img.src = url;
        var img_width = img.width;
        var win_width = img.width + 25;
        var img_height = img.height;
        var win = img.height + 30;
        var popup = window.open('', '_blank', 'width=' + img_width + ', height=' + img_height + ', menubars=no, scrollbars=auto');
        popup.document.write("<style>body{margin:0px;}</style><img src='" + url + "' width='" + win_width + "'>");
    }
