angular.module('app', []).controller('indexController', function($scope, $http){
    const contextPath = 'http://localhost:8089/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then (function (response) {
                $scope.products = response.data;
            });
    };

     $scope.createNewProduct = function () {
            $http.post(contextPath + '/api/v1/products', $scope.newProduct)
                .then (function successCallback(response) {
                    $scope.init();
                    $scope.newProduct = null;
                }, function errorCallback(response){
                    console.log(response.data);
                    alert('Error: ' + response.data.messages);
                });
        };

    $scope.clickOnProduct = function (p){
        console.log(p);
    };

    $scope.addProductToCart = function (productId){
        console.log(productId);
        $http({
             url: contextPath + '/api/v1/cart/add',
             method: 'GET',
             params: {
                 id: productId
             }
        }).then(function (response){
             console.log("OK");
             $scope.cartProducts = response.data;
        });
    };

    $scope.deleteAllFromCart = function(){
        $http({
             url: contextPath + '/api/v1/cart/clean',
             method: 'GET',
             params: {
                  cartCleanStatus: 1
             }
        }).then(function (response){
        $scope.cartProducts = null;
        });
    };

    $scope.init();
});