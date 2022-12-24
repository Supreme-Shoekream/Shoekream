// 체크 개수 따라 구매계속 버튼 색 바뀌기
function getCheck() {
    const query = 'input[class=check]:checked';
    const selectedElements = document.querySelectorAll(query);
    const cnt = selectedElements.length;

    if (cnt == 5) {
        $("#next").css("background-color", "black");
        document.querySelector('.btn').disabled = false;

    } else {
        $("#next").css("background-color", "rgb(214,214,214)");
        document.querySelector('.btn').disabled = true;
    }
}
