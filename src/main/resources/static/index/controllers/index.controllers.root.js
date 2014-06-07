angular.module('index.module.rootApp')
.controller('index.controllers.root', [ '$scope', '$location', function($scope, $location) {
    $scope.login = function() {
        alert('login:'+$scope.currentUser.login+"::"+$scope.currentUser.password );
    };
} ]);