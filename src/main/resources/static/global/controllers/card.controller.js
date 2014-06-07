var module = angular.module('global.cardController', [ 'relative.services', 'global.services' ]);

module.controller('cardController', [ '$scope', '$rootScope', 'relativeResource', 'editCardService',
        function($scope, $rootScope, relativeResource, editCardService) {
            $scope.editCard = function(id, isUpdate) {
                var relative = relativeResource.get({}, {
                    'id' : id
                });
                var editScope = $scope.$new(true);
                editScope.isUpdate = isUpdate;
                editScope.editedHuman = relative;
                editScope.onClose = function(updatedCard) {
                    relative.firstname = updatedCard.firstname;
                    relative.lastname = updatedCard.lastname;
                    relative.surname = updatedCard.surname;
                    relative.$update(function(result) {
                        console.log($rootScope.alliance);
                    });
                };
                editCardService.openModalWindow(editScope);
            };
        } ]);
