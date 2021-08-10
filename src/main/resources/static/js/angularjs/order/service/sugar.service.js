angular.module('totocoto')
.factory('SugarService', function($http, CONST) {

    const sugarurl = `${CONST.localUrl}/sugars`

    return {
        getAll: function() {
            return $http.get(sugarurl).then(resp => resp)
        }
    }
})