const btnDeleteCateogories = document.querySelectorAll('.btn-delete-category')

btnDeleteCateogories.forEach(btn => {
    btn.addEventListener("click", function (event) {
        const products = event.target.dataset.products
        const products2 = event.target.parentNode.dataset.products
        let accept = false;


        if (products > 0 || products2 > 0) {
            accept = confirm("Loại sản phẩm này đang chứa sản phẩm. Bạn có muốn xóa không?")
        } else {
            accept = confirm("Bạn có muốn xóa loại sản phẩm này không?")
        }

        if (!accept) {
            event.preventDefault();
        }
    })
})