angular.module('index.module.rootApp').controller('index.controllers.root',
        [ '$scope', '$location', 'searchService', function($scope, $location, searchService) {
            $scope.searchResult = new Array();
            var result = $location.search();
            $scope.searchResult.push(decodeURIComponent(result.query));
            $scope.searchText = decodeURIComponent(result.query);
            var data = {
                words : $scope.searchText.split(" ")
            };
            searchService.get(data).$promise.then(function(result) {
                $scope.searchResult = result;
            });
            $scope.search = function() {
                $location.path('/result').search('query', encodeURIComponent($scope.searchText));
            };
            $scope.keyPressed = function($event) {
                if ($event.which == 13) {
                    $scope.search();
                };
            };
        } ]);