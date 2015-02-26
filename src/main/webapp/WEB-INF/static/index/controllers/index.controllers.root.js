angular.module('index.module.rootApp')
.controller('index.controllers.root', [ '$scope', '$location', 'loginResource', function($scope, $location, LoginResource) {
    $scope.login = function() {
        var loginResource = new LoginResource({login:$scope.currentUser.login, password: $scope.currentUser.password});
        var promise = loginResource.$login();
        $scope.loading = true;
        promise.then(function(){
        	
        })
        .catch(function(){
        	
        })
        .finally(function(){
        	$scope.loading = false;
        });
    };
} ]);