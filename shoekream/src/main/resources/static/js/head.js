function auth_policy(){
    const pop = document.getElementById('layer_auth_policy');
    console.log(pop);
    pop.style.display="block";
}

function close_auth_policy(){
    document.layer_auth_policy('.layer_auth_policy').style.display="none"
}