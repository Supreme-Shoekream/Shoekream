function wishCreate(product_idx) {
    // console.log(proIdx);
    if(document.querySelector('#username') !=null){
        fetch("http://localhost:8889/api/product", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                "productIdx": product_idx
            }),
        })
            .then((res) => {
                let wish_on_img = document.getElementById(product_idx).childNodes[1].childNodes[1].childNodes[1]
                wish_on_img.style.display='block'
                let wish_off_img = document.getElementById(product_idx).childNodes[1].childNodes[1].childNodes[3]
                wish_off_img.style.display='none'
                let wish_count =document.getElementById(product_idx).childNodes[1].childNodes[3]
                wish_count.innerHTML =Number(wish_count.innerHTML) + 1
            })
            .then((data) => {
                console.log(data);
            })
            .catch((err) => {
                console.log(err);
            })
    }else{
        location.href="/login"
    }

}

function wishDelete(product_idx) {
    if(document.querySelector('#username') !=null) {
        fetch('http://localhost:8889/api/product/' + product_idx, {
            method: "DELETE"
        })
            .then((res) => {
                let wish_on_img = document.getElementById(product_idx).childNodes[1].childNodes[1].childNodes[1]
                wish_on_img.style.display = 'none'
                let wish_off_img = document.getElementById(product_idx).childNodes[1].childNodes[1].childNodes[3]
                wish_off_img.style.display = 'block'
                let wish_count = document.getElementById(product_idx).childNodes[1].childNodes[3]
                wish_count.innerHTML = Number(wish_count.innerHTML) - 1
            })
            .then((data) => {
                console.log(data);
            })
            .catch((err) => {
                console.log(err);
            })
    }else{
        location.href="/login"
    }
}