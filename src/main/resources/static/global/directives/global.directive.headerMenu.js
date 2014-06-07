angular.module('global.directives').directive('headerMenu', function() {
    return {
        restrict: 'E',
        templateUrl : '/static/global/templates/header-menu.html',
        replace : true,
        controller: 'headerMenuCtrl'
    };
}).controller('headerMenuCtrl', [ '$scope', '$rootScope', function($scope, $rootScope) {
} ]);