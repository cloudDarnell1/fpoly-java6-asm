angular.module('totocoto')
.factory("PaymentService", function ($http, CONST) {
    paymentUrl = `${CONST.localUrl}/payment`

    return {
        pay: function(items) {
            return $http.post(paymentUrl, items).then(resp => resp)
        }
    }

})