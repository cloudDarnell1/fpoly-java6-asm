angular.module('totocoto')
.factory('SizeService', function($http, CONST) {

    const sizeUrl = `${CONST.localUrl}/sizes`

    return {
        getAll: function() {
            return $http.get(sizeUrl).then(resp => resp)
        }
    }
})