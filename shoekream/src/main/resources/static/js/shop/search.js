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
let size = searchParams.get('size')
let brand = searchParams.get('brand')
let category = searchParams.get('category')
let collection = searchParams.get('collection')
let gender = searchParams.get('gender')
let keyword = searchParams.get('keyword')

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
    if(sort==null) sort='idx'
    if(page==null) page=0
    if(size==null) size=''
    if(brand==null) brand=''
    if(collection==null) collection=''
    if(gender==null) gender=''
    if(keyword==null) keyword=''
    location.href="/searchs?sort="+sort+"&page="+page+"&size="+size+"&brand="+brand
        +"&category="+category_txt+"&collection="+collection+"&gender="+gender+"&keyword="+keyword
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
    if(sort==null) sort='idx'
    if(page==null) page=0
    if(size==null) size=''
    if(category==null) category=''
    if(collection==null) collection=''
    if(gender==null) gender=''
    if(keyword==null) keyword=''
    location.href="/searchs?sort="+sort+"&page="+page+"&size="+size+
        "&brand="+brand_txt+"&category="+category+"&collection="+collection+"&gender="+gender+"&keyword="+keyword
}

// 성별

const gender_menus = document.querySelectorAll('#gender_filter .menu')
gender_menus.forEach((tab)=>{
    let checkbox = tab.childNodes[1]
    let gender_txt= tab.childNodes[3].childNodes[0].innerHTML
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
    if(sort==null) sort='idx'
    if(page==null) page=0
    if(size==null) size=''
    if(category==null) category=''
    if(collection==null) collection=''
    if(gender==null) gender=''
    if(keyword==null) keyword=''
    location.href="/searchs?sort="+sort+"&page="+page+"&size="+size+
        "&brand="+brand+"&category="+category+"&collection="+collection+"&gender="+gender_txt+"&keyword="+keyword
}

// 컬렉션

const collection_menus = document.querySelectorAll('#collection_filter .menu')
collection_menus.forEach((tab)=>{
    let checkbox = tab.childNodes[1]
    let collection_txt= tab.childNodes[3].childNodes[0].innerHTML
    if(checkbox.checked){
        tab.addEventListener('click',()=>{
            collection_reload('')
        })
    }else{
        tab.addEventListener('click',()=>{
            collection_reload(collection_txt)
        })
    }
})
function collection_reload(collection_txt){
    if(sort==null) sort='idx'
    if(page==null) page=0
    if(size==null) size=''
    if(category==null) category=''
    if(collection==null) collection=''
    if(gender==null) gender=''
    if(keyword==null) keyword=''
    location.href="/searchs?sort="+sort+"&page="+page+"&size="+size+
        "&brand="+brand+"&category="+category+"&collection="+collection_txt+"&gender="+gender+"&keyword="+keyword
}

// 사이즈

const size_menus = document.querySelectorAll('#size_filter .menu')
size_menus.forEach((tab)=>{
    let checkbox = tab.childNodes[1]
    let size_txt= tab.childNodes[3].childNodes[0].innerHTML
    if(checkbox.checked){
        tab.addEventListener('click',()=>{
            size_reload('')
        })
    }else{
        tab.addEventListener('click',()=>{
            size_reload(size_txt)
        })
    }
})
function size_reload(size_txt){
    if(sort==null) sort='idx'
    if(page==null) page=0
    if(size==null) size=''
    if(category==null) category=''
    if(collection==null) collection=''
    if(gender==null) gender=''
    if(keyword==null) keyword=''
    location.href="/searchs?sort="+sort+"&page="+page+"&size="+size_txt+
        "&brand="+brand+"&category="+category+"&collection="+collection+"&gender="+gender+"&keyword="+keyword
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
