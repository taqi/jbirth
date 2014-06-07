angular.module('global.services')
.service('inputTextWithTimeService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'inputTextTimeCtrl',
                template : '/static/global/templates/input-text-and-time.html',
                show : true,
                placement : 'center',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])
.controller('inputTextTimeCtrl', [ '$scope', function($scope) {
  $scope.ok = function() {
    $scope.$hide();
    var date = $('#dateTimePickerForBiographyFact').data("DateTimePicker").getDate();
    $scope.onClose(date, $scope.inputText);
  };
} ]);