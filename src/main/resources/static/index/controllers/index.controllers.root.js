angular.module('index.module.rootApp')
.controller('index.controllers.root', [ '$scope', '$location', 'loginResource', function($scope, $location, LoginResource) {
    $scope.login = function() {
        var loginResource = new LoginResource({login:$scope.currentUser.login, password: $scope.currentUser.password});
        loginResource.$login();
    };
} ]);