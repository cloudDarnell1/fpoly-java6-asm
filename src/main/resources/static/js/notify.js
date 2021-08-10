const notifyBlock = document.querySelector('.notify-block')

const btnClose = document.querySelector('.btn-notify')

btnClose.addEventListener('click', function (event) {
    notifyBlock.style.top = '-110px'
})

if (notifyBlock) {
    const isShow = notifyBlock.dataset.show
    if (isShow) {
        notifyBlock.style.top = '0'
        setTimeout(function() {
            notifyBlock.style.top = '-110px'
        }, 2500)
    }
}