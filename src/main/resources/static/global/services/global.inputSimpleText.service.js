angular.module('global.services')
.service('inputSimpleTextService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'inputSimpleTextCtrl',
                template : '/static/global/templates/input-simple-text.html',
                show : true,
                placement : 'center',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])
.controller('inputSimpleTextCtrl', [ '$scope', function($scope) {
  $scope.ok = function() {
    $scope.$hide();
    $scope.onClose($scope.inputText);
  };
} ]);