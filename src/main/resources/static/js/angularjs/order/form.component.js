angular.module('totocoto')
.controller('form', function($scope, ProductSerivce, NotiService ,$rootScope, IceService, SugarService, SizeService, ToppingService) {

    $rootScope.isOrder = false
    $rootScope.total = 0
    $rootScope.totalQuantity = 0

    // call when url includes 'product_id'
    ;(function order() {
        if (location.search.includes('product_id')) {
            const [p] = location.search.slice(1).split('&')
            const [key, value] = p.split('=')

            if (key !== 'product_id') return

            ProductSerivce.findById(value)
                .then(resp => {
                    $rootScope.product = resp
                    $rootScope.isOrder = true
                })
        }
    })()

    $rootScope.product = {

    }

    $rootScope.total = 0

    $scope.item = {
        id: -1,
        product: {},
        size: {},
        sugar: {},
        ice: {},
        quantity: 1,
        price: 0,
        topping: []
    }

    IceService.getAll().then(resp => {
        $scope.ices = resp.data
        $scope.setIce($scope.ices[0])
    })

    ToppingService.getAll().then(resp => {
        $scope.toppings = resp.data
    })

    SugarService.getAll().then(resp => {
        $scope.sugars = resp.data
        $scope.setSugar($scope.sugars[0])
    })

    SizeService.getAll().then(resp => {
        $scope.sizes = resp.data
        $scope.setSize($scope.sizes[0])
    })


    $scope.hideForm = function() {
        $rootScope.isOrder = false
    }

    $scope.increaseQuantity = function() {
        $scope.item.quantity++
    }

    $scope.decreaseQuantity = function() {
        if ($scope.item.quantity === 1) return
        $scope.item.quantity--
    }

    $scope.order = function() {
        $scope.item.id = $rootScope.product.id
        $scope.item.product = $rootScope.product
        $scope.item.price += $rootScope.product.price

        const i = {...$scope.item}
        i.topping = [...$scope.item.topping]

        $rootScope.carts.push(i)
        $rootScope.total += $scope.item.price
        $rootScope.totalQuantity += $scope.item.quantity

        NotiService.success('add to cart successfully!')
        $scope.item.id = -1
        $scope.item.quantity = 1
        $scope.item.price = 0
        $rootScope.isOrder = false
    }

    $scope.setSize = function(size) {
        $scope.item.size = size
    }

    $scope.setSugar = function(sugar) {
        $scope.item.sugar = sugar
    }

    $scope.setIce = function(ice) {
        $scope.item.ice = ice
    }

    $scope.addTopping = function(topping, event) {
        const {checked} = event.target

        // add topping to item
        if (checked) {
            $scope.item.topping.push(topping)
            $scope.item.price += topping.price
            return
        }

        // remove topping in item
        $scope.item.topping = $scope.item.topping.filter( t => t.id !== topping.id)

    }
})