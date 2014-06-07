angular.module('index.module.rootApp').controller('index.controllers.search', [ '$scope', '$location', function($scope, $location) {
    $scope.searchResult = new Array();
    $scope.search = function() {
        $scope.searchResult.push($scope.searchText);
        $location.path('/result').search('query', encodeURIComponent($scope.searchText));
    };
    $scope.keyPressed = function($event) {
        if ($event.which == 13) {
            $scope.search();
        };
    };
} ]);