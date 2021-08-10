angular.module('totocoto')
.factory('IceService', function($http, CONST) {

    const productUrl = `${CONST.localUrl}/ices`

    return {
        getAll: function() {
            return $http.get(productUrl).then(resp => resp)
        }
    }
})