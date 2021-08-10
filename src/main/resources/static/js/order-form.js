const order = document.querySelector('.order');


const btnIncrease = document.querySelector('#increase');
const btnDecrease = document.querySelector('#decrease');
const textQuantity = document.querySelector('#quantity');

const btnCartIncrease = document.querySelector('#cart__increase');
const btnCartDecrease = document.querySelector('#cart__decrease');
const textCartQuantity = document.querySelector('#cart__quantity');

const infoCal = document.querySelector('.info__cal')

const itemsCart = document.querySelector('.cart__items')

const loader = document.querySelector('.loader')

const url = window.location.origin;

(function closeForm() {

    if (!order) return

    const orderBackground = order.querySelector('.order__background');

    if (orderBackground) {
        orderBackground.addEventListener('click', function (event) {
            order.style.display = 'none'
        })
    }
})();

(function changeQuantity() {

    if (!btnIncrease || !btnDecrease || !textQuantity) return

    btnIncrease.addEventListener('click', function(event) {
        const currentQuantity = parseInt(textQuantity.value)
        textQuantity.value = currentQuantity + 1
    })

    btnDecrease.addEventListener('click', function(event) {
        const currentQuantity = parseInt(textQuantity.value)

        if (currentQuantity <= 1) {
            return;
        }

        textQuantity.value = currentQuantity - 1
    })
})();

(function changeAfterStoreToSession() {

    if (!btnCartIncrease || !btnCartDecrease || !textCartQuantity) return

    btnCartIncrease.addEventListener('click', function (event) {

        loader.style.display = 'block'
        const productId = event.target.dataset.id
        const productPrice =event.target.dataset.price



        fetch(url+"/cart/increase/"+productId)
            .then(resp => loader.style.display = 'none')
            .catch(console.log)
        const currentQuantity = parseInt(textCartQuantity.textContent)
        textCartQuantity.textContent = currentQuantity + 1 + ''
        calculate(productPrice)
    });

    btnCartDecrease.addEventListener('click', function (event) {
        loader.style.display = 'block'
        const productId = event.target.dataset.id
        const productPrice = event.target.dataset.price

        const currentQuantity = parseInt(textCartQuantity.textContent)

        fetch(url+"/cart/decrease/"+productId)
            .then(resp => loader.style.display = 'none')
            .catch(console.log)

        textCartQuantity.textContent = currentQuantity - 1 +''

        if (currentQuantity <= 1) {
            itemsCart.removeChild(event.target.parentNode.parentNode)

            if (itemsCart.childElementCount <= 0) {
                const p = document.createElement("p")
                p.className = 'items__status'
                p.textContent = 'Bạn chưa chọn sản phẩm nào!';
                itemsCart.appendChild(p);
            }
        }


        calculate(productPrice)
    })


})();

function calculate(price) {
    infoCal.textContent = `x ${textCartQuantity.textContent} = ${textCartQuantity.textContent * price}`
}