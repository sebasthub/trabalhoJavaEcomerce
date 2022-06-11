let exist = true;
const menu = document.querySelector('.menu-interno');
function coisa() {
    if (exist === false) {
        menu.classList.remove('animacao');
        menu.classList.add('animacao-reverso');
        menu.style.left = '-93%';
        exist = true;
    }else {
        menu.classList.remove('animacao-reverso');
        menu.classList.add('animacao');
        menu.style.left = '0%';
        exist = false;
    }
    
}

function volta(){
    history.back();
}