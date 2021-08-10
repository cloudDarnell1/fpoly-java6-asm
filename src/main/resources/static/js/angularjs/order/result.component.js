angular.module('totocoto')
    .controller('header', function($scope, $rootScope, ProductSerivce) {

        $scope.keyword = ''

        $scope.search = function() {
            ProductSerivce.findByName($scope.keyword)
                .then(resp => {
                    $rootScope.results = resp.data
                })
        }

        $scope.showResult = function () {
            $rootScope.isShowResult = true
        };

        $scope.hideResult = function() {
            setTimeout(function() {
                $rootScope.isShowResult = false
            }, 1000)
        }
    })

angular.module('totocoto')
.controller('result', function(ProductSerivce, $scope, $rootScope) {

    $rootScope.results = [

    ]

    $rootScope.isShowResult = false

    ProductSerivce.getAll()
        .then(resp => {
            $rootScope.results = resp.data
        })

    $scope.showForm = function(id) {
        ProductSerivce.findById(id)
            .then(resp => {
                console.log(resp)
                $rootScope.product = resp
                $rootScope.isOrder = true
                $rootScope.isShowResult = false
            })
    }
})