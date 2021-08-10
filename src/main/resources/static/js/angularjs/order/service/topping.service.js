angular.module('totocoto')
.factory('ToppingService', function($http, CONST) {

    const toppingUrl = `${CONST.localUrl}/toppings`

    return {
        getAll: function() {
            return $http.get(toppingUrl).then(resp => resp)
        }
    }
})