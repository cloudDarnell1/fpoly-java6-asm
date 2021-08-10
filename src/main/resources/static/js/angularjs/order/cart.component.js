angular.module('totocoto')
.controller('cart', function($rootScope, $scope, NotiService, PaymentService) {

    $rootScope.carts = []

    $scope.increaseQuantity = function(item) {
        item.quantity++
        $rootScope.totalQuantity++
        $rootScope.total += item.product.price
        item.price += item.product.price
    }

    $scope.decreaseQuantity = function(item) {
        $rootScope.total -= item.product.price
        $rootScope.totalQuantity--
        if (item.quantity === 1) {
            $rootScope.carts = $rootScope.carts.filter(cart => {
                return cart.id !== item.id
            })
            NotiService.success("remove item successfully")
            return
        }
        item.price -= item.product.price
        item.quantity--;
    }

    $scope.payment = function() {

        PaymentService.pay($rootScope.carts)
            .then(resp => {

                if (resp.data.status === '2001') {
                    NotiService.error(resp.data.message)
                    setTimeout(function() {
                        location.href = '/login'
                    }, 1000)
                    return
                }

                if (resp.data.error === 'true') {
                    NotiService.error(resp.data.message)
                    return
                }

                NotiService.success('Thanh Toán thành công')
                $scope.clear()
            })
            .catch(resp => {
                NotiService.error(resp)
            })

    }

    $scope.clear = function() {
        localStorage.setItem("carts", "")
        $rootScope.carts = []
        $rootScope.total = 0
        $rootScope.totalQuantity = 0
    }

})