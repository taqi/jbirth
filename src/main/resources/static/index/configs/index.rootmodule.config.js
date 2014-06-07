angular.module('index.module.rootApp').config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/result', {
        templateUrl : '/static/index/templates/result.html'
    }).otherwise({
        templateUrl : '/static/index/templates/search.html'
    });
} ]);