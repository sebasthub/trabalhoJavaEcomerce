let exist = true;
let existUser = true;
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

function usuario() {
    const menuUsuario = document.querySelector('.menu-usuario')
    if (existUser === false) {
        menuUsuario.style.left = '-95%';
        existUser = true;
    }else {
        menuUsuario.style.left = '71%';
        existUser = false;
    }
}