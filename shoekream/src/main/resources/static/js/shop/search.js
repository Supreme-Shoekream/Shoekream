/**
 * 🤍 기능1: +버튼 클릭시  ( + ↔ - ) 버튼 변경 및 드롭다운
 */
const nav = document.querySelectorAll(".filter_title");
nav.forEach((tab) => {
    tab.addEventListener('click', () => {
        console.log(tab.childNodes[1].childNodes[3])
        const placeholder = tab.childNodes[1].childNodes[3]
        if(placeholder.style.display=='block'){
            placeholder.style.display='none'
        }else{
            placeholder.style.display='block'
        }
        console.log(tab.childNodes[3].childNodes[1].childNodes[1])
        const img = tab.childNodes[3].childNodes[1].childNodes[1];
        if(img.getAttribute('src')=='/img/shopimg/side_btn1.png'){
            img.setAttribute('src', '/img/shopimg/side_btn0.png');
        }else{
            img.setAttribute('src', '/img/shopimg/side_btn1.png');
        }
        console.log(tab.nextSibling.nextSibling)
        const dropdown = tab.nextElementSibling
        if(dropdown.style.display=='block'){
            dropdown.style.display= 'none';
        }else{
            dropdown.style.display= 'block';
        }

    })
})
/**
 * 🤍 기능2: url parameter 저장 후 체크박스 클릭시 알맞게 url 보내주기
 */
const urlStr = window.location.href.toString();
const url = new URL(urlStr)
const searchParams = url.searchParams
let sort = searchParams.get('sort')
let page = searchParams.get('page')
let psize = searchParams.get('psize')
let brand = searchParams.get('brand')
let category = searchParams.get('category')
let collection = searchParams.get('collection')
let gender = searchParams.get('gender')
let keyword = searchParams.get('keyword')


let sort_str = ''
let page_str =''
let psize_str= ''
let brand_str = ''
let category_str = ''
let collection_str =''
let gender_str = ''
let keyword_str = ''

// 주소창 정리
function psize_check(psize){
    if(psize==null) return ''
    return "&psize="+psize
}

function brand_check(brand){
    if(brand==null) return ''
    return "&brand="+brand
}

function collection_check(collection){
    if(collection==null) return ''
    return "&collection="+collection
}

function gender_check(gender){
    if(gender==null) return ''
    return "&gender="+gender
}

function keyword_check(keyword){
    if(keyword==null) return ''
    return "&keyword="+keyword
}

function category_check(category){
    if(category==null) return ''
    return "&category="+category
}

// 카테고리

const category_menus = document.querySelectorAll('#category_filter .menu')
category_menus.forEach((tab)=>{
    let checkbox = tab.childNodes[1]
    let category_txt= tab.childNodes[3].childNodes[0].innerHTML
    if(checkbox.checked){
        tab.addEventListener('click',()=>{
            category_reload('')
        })
    }else{
        tab.addEventListener('click',()=>{
            category_reload(category_txt)
        })
    }
})

function category_reload(category_txt){

    if(sort==null) sort='wishCount,desc'
    if(page==null) page=0

    psize_str = psize_check(psize)
    brand_str = brand_check(brand)
    collection_str = collection_check(collection)
    gender_str = gender_check(gender)
    keyword_str = keyword_check(keyword)
    category_str = category_check(category)

    location.href="/searchs?sort="+sort+"&page="+page+psize_str+brand_str
        +"&category="+category_txt+collection_str+gender_str+keyword_str
}

// 브랜드

const brand_menus = document.querySelectorAll('#brand_filter .menu')
brand_menus.forEach((tab)=>{
    let checkbox = tab.childNodes[1]
    let brand_txt= tab.childNodes[3].childNodes[0].innerHTML
    if(checkbox.checked){
        tab.addEventListener('click',()=>{
            brand_reload('')
        })
    }else{
        tab.addEventListener('click',()=>{
            brand_reload(brand_txt)
        })
    }
})
function brand_reload(brand_txt){

    if(sort==null) sort='wishCount,desc'
    if(page==null) page=0

    psize_str = psize_check(psize)
    brand_str = brand_check(brand)
    collection_str = collection_check(collection)
    gender_str = gender_check(gender)
    keyword_str = keyword_check(keyword)
    category_str = category_check(category)

    location.href="/searchs?sort="+sort+"&page="+page+psize_str+"&brand="+brand_txt
        +category_str+collection_str+gender_str+keyword_str
}


// 성별

const gender_menus = document.querySelectorAll('#gender_filter .menu')
gender_menus.forEach((tab)=>{
    let checkbox = tab.childNodes[1]
    let gender_txt= tab.childNodes[3].childNodes[0].innerHTML
        if(gender_txt=='남성')gender_txt='M'
        if(gender_txt=='여성')gender_txt='W'
        if(gender_txt=='키즈')gender_txt='Kid'
    if(checkbox.checked){
        tab.addEventListener('click',()=>{
            gender_reload('')
        })
    }else{
        tab.addEventListener('click',()=>{
            gender_reload(gender_txt)
        })
    }
})
function gender_reload(gender_txt){

    if(sort==null) sort='wishCount,desc'
    if(page==null) page=0

    psize_str = psize_check(psize)
    brand_str = brand_check(brand)
    collection_str = collection_check(collection)
    gender_str = gender_check(gender)
    keyword_str = keyword_check(keyword)
    category_str = category_check(category)

    location.href="/searchs?sort="+sort+"&page="+page+psize_str+brand_str
        +category_str+collection_str+"&gender="+gender_txt+keyword_str
}


// 사이즈

const size_menus = document.querySelectorAll('#size_filter .menu')
size_menus.forEach((tab)=>{
    let checkbox = tab.childNodes[1]
    let psize_txt= tab.childNodes[3].childNodes[0].innerHTML
    if(checkbox.checked){
        tab.addEventListener('click',()=>{
            size_reload('')
        })
    }else{
        tab.addEventListener('click',()=>{
            size_reload(psize_txt)
        })
    }
})
function size_reload(psize_txt){

    if(sort==null) sort='wishCount,desc'
    if(page==null) page=0

    psize_str = psize_check(psize)
    brand_str = brand_check(brand)
    collection_str = collection_check(collection)
    gender_str = gender_check(gender)
    keyword_str = keyword_check(keyword)
    category_str = category_check(category)

    location.href="/searchs?sort="+sort+"&page="+page+"&psize="+psize_txt+brand_str
        +category_str+collection_str+gender_str+keyword_str
}

/**
 * 🤍 기능3: sort클릭시 드롭다운
 */
function sort_list() {
    let click = document.getElementById("sort_list");
    if (click.style.display === "none") {
        click.style.display = "block";
    } else {
        click.style.display = "none";
    }
}

// shop 클릭시 폰트변경
const item = document.getElementById('sh_gnb');
item.classList.add('gnb_on');




/**
 * 🤍 기능4: 관심상품
 */
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

function tag_href(product_idx){
    location.href = '/social/social_product/'+product_idx;
}