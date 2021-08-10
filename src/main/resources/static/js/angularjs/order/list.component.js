angular.module('totocoto')
.controller('list', function($rootScope, $scope, ProductSerivce) {
    $scope.showForm = function (id) {
        ProductSerivce.findById(id).then(data => {
            console.log($rootScope.carts)
            $rootScope.product = data
        })
        $rootScope.isOrder = true
    };
})
