const header = document.querySelector('.header')

const headerMenu = header.querySelector('.header__status-bar')

const productMenu = document.querySelector('.products__menu')


window.addEventListener('scroll', function (event) {
    const rect = header.getBoundingClientRect()
    if (rect.bottom < headerMenu.clientHeight) {
        if (productMenu) {
            productMenu.style.top = `${headerMenu.clientHeight}px`
        }
        headerMenu.style.backgroundColor = 'black'
        return
    }

    if (productMenu) {
        productMenu.style.top = `${rect.bottom}px`
    }

    headerMenu.style.backgroundColor = 'transparent'

})