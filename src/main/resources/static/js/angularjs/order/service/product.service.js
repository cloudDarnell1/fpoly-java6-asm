angular.module('totocoto')
.factory('ProductSerivce', function($http, CONST) {

    const productUrl = `${CONST.localUrl}/products`

    return {
        getAll: function() {
            return $http.get(productUrl).then(resp => resp)
        },
        findById: function(id) {
            return $http.get(`${productUrl}/${id}`).then(resp => resp.data)
        },
        findByName: function(name) {
            return $http.post(productUrl, name).then(resp =>resp)
        }
    }
})