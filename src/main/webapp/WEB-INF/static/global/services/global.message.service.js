angular.module('global.services').service('modalMessageService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'modalMessageCtrl',
                template : '/static/global/templates/modalMessage.html',
                show : true,
                placement : 'center',
                animation: 'none',
                scope: modalScope
            });
            return myModal;
        }
    };
} ]).controller('modalMessageCtrl', [ '$scope', function($scope) {
    $scope.ok = function() {
        $scope.$hide();
    };
} ]);