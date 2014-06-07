angular.module('global.services')
.service('yesNoService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'yesNoCtrl',
                template : '/static/global/templates/yes-no.html',
                show : true,
                placement : 'center',
                animation: 'none',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])
.controller('yesNoCtrl', ['$scope', function($scope){
    $scope.yes = function() {
        $scope.$hide();
        $scope.onClose(true);
      };
     $scope.no = function() {
          $scope.$hide();
          $scope.onClose(false);
        };
}]);