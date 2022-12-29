
// 기능1. 스크롤시 왼쪽 상품 사진 이동후 멈춤
const is_fixed= document.querySelector(".is_fixed");
    // 왼쪽 상품 사진 

window.addEventListener('scroll', function(){
    // console.log(window.scrollY);
    if(window.scrollY >= 1250){ 
        // window.scrollY: 브라우저 최상단에서 현재까지 스크롤 된 좌표값 구함
        // 좌표값이 1250인 곳에서 멈춰야함
        is_fixed.classList.add('is_absolute');
            // css에서 position:absolute , top:1250px으로 지정해줬음
    }else{
        is_fixed.classList.remove('is_absolute');
    }
});


// 기능2. 모든 사이즈 클릭시 팝업 레이어창 
function everysizePopup() {
    const layer = document.querySelector('.lg.layer_detail_size_select.select_only_size');
    layer.classList.add('layer');
        // class="lg layer_detail_size_select select_only_size"에 layer 추가
    
    const body = document.querySelector('body');
    body.style.overflow = 'hidden';

    layer.style.visibility ='visible';
        // popup시 요소 뜨게함
}

function everysizePopdown() {
    const layer = document.querySelector('.lg.layer_detail_size_select.select_only_size');
    layer.classList.remove('layer');
        // class="lg layer_detail_size_select select_only_size layer"에서 layer 제거
    
    const body = document.querySelector('body');
    body.style.overflow = '';

    layer.style.visibility ='hidden';
        // popdown시 요소 숨김(style은 유지=자리값은 유지됨)
}


// 기능3. 관심 상품 클릭시 팝업 레이어창 
function wishPopup() {
    const layer = document.querySelector('.layer_interest.lg');
    layer.classList.add('layer');
    
    const body = document.querySelector('body');
    body.style.overflow = 'hidden';

    layer.style.visibility ='visible';
}

function wishPopdown() {
    const layer = document.querySelector('.layer_interest.lg');
    layer.classList.remove('layer');
    
    const body = document.querySelector('body');
    body.style.overflow = '';

    layer.style.visibility ='hidden';
}


// 기능4. 시세 그래프
function mm1Click() {
    const item = document.getElementsByClassName('item');
    // const selected = document.getAttribute('aria-selected');
    if(item.getAttribute('aria-selected')=='false'){
        item.setAttribute('aria-selected','true');

        const tabContent = document.getElementsByClassName('tab_content');
        tabContent.classList.add('show');
    }

}

const sales_panel1 = document.getElementById("graph1m").getContext("2d");
const graph1m = new Chart(sales_panel1, {
    type: "line",
    data: {
        labels: [" ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        datasets: [{
            label: "시세",
            data: [784000, 784000, 789000, 858000, 792000, 790000, 100000, 200000, 320000, 9000],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        scales: {
            y: {
                beginAtZero: false
            }
        }
    },
    scales: {
        y: {
            position: 'right' // 축이 왼쪽에 표시될지, 오른쪽에 표시될지 정할 수 있습니다.
        }
    }
    
});

const sales_panel2 = document.getElementById("graph3m").getContext("2d");
const graph3m = new Chart(sales_panel2, {
    type: "line",
    data: {
        labels: [" ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        datasets: [{
            label: "시세",
            data: [784000, 784000, 789000, 858000, 792000, 790000, 100000, 200000, 320000, 9000],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        scales: {
            y: {
                beginAtZero: false
            }
        }
    },
    scales: {
        y: {
            position: 'right' // 축이 왼쪽에 표시될지, 오른쪽에 표시될지 정할 수 있습니다.
        }
    }
    
});

const sales_panel3 = document.getElementById("graph6m").getContext("2d");
const graph6m = new Chart(sales_panel3, {
    type: "line",
    data: {
        labels: [" ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        datasets: [{
            label: "시세",
            data: [784000, 784000, 789000, 858000, 792000, 790000, 100000, 200000, 320000, 9000],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        scales: {
            y: {
                beginAtZero: false
            }
        }
    },
    scales: {
        y: {
            position: 'right' // 축이 왼쪽에 표시될지, 오른쪽에 표시될지 정할 수 있습니다.
        }
    }
    
});

const sales_panel4 = document.getElementById("graph1y").getContext("2d");
const graph1y = new Chart(sales_panel4, {
    type: "line",
    data: {
        labels: [" ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        datasets: [{
            label: "시세",
            data: [784000, 784000, 789000, 858000, 792000, 790000, 100000, 200000, 320000, 9000],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        scales: {
            y: {
                beginAtZero: false
            }
        }
    },
    scales: {
        y: {
            position: 'right' // 축이 왼쪽에 표시될지, 오른쪽에 표시될지 정할 수 있습니다.
        }
    }
    
});

const sales_panel5 = document.getElementById("graphall").getContext("2d");
const graphall = new Chart(sales_panel5, {
    type: "line",
    data: {
        labels: [" ", " ", " ", " ", " ", " ", " ", " ", " ", " "],
        datasets: [{
            label: "시세",
            data: [784000, 784000, 789000, 858000, 792000, 790000, 100000, 200000, 320000, 9000],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        scales: {
            y: {
                beginAtZero: false
            }
        }
    },
    scales: {
        y: {
            position: 'right' // 축이 왼쪽에 표시될지, 오른쪽에 표시될지 정할 수 있습니다.
        }
    }
    
});







