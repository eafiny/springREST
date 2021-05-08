angular.module('app', []).controller('indexController', function($scope, $http){
    const contextPath = 'http://localhost:8089/market';

    $scope.loadPage = function (page) {
        $http({
            url:contextPath + '/api/v1/products',
            method: 'GET',
            params: {
                p: page
            }
        }).then (function (response) {
                $scope.productsPage = response.data;

                let minPageIndex = page - 2;
                if (minPageIndex < 1) {
                    minPageIndex = 1;
                }
                let maxPageIndex = page + 2;
                if(maxPageIndex > $scope.productsPage.totalPages){
                    maxPageIndex = $scope.productsPage.totalPages;
                    console.log($scope.productsPage.totalPages);
                }

                $scope.paginationArray = $scope.generatePagesIndexes(minPageIndex, maxPageIndex);
            });
    };

     $scope.createNewProduct = function () {
            $http.post(contextPath + '/api/v1/products', $scope.newProduct)
                .then (function successCallback(response) {
                    $scope.loadPage(1);
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

    $scope.deleteProduct = function(productId){
        $http({
            url: contextPath + '/api/v1/products',
            method: 'DELETE',
            params:{
                id: productId
            }
        }).then(function(response){
            $scope.loadPage(1);
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

    $scope.generatePagesIndexes = function(startPage, endPage){
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++){
            arr.push(i);
        }
        return arr;
    }

    $scope.loadPage(1);
});