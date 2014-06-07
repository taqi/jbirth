angular.module('global.services')
.service('editCardService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'editCardCtrl',
                template : '/static/global/templates/edit-card.html',
                show : true,
                placement : 'center',
                animation: 'none',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])

.service('createCardService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'createCardCtrl',
                template : '/static/global/templates/create-card.html',
                show : true,
                animation: 'none',
                placement : 'center',
                scope : modalScope
            });
            return myModal;
        }
    };
} ]).service('selectRelativeService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'selectRelativeCtrl',
                template : '/static/global/templates/select-relative.html',
                show : true,
                placement : 'center',
                animation: 'none',
                backdropAnimation: 'am-fade',
                backdrop:'static',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])

.service('selectConnectionTypeService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'selectConnectionTypeCtrl',
                template : '/static/global/templates/select-connection-type.html',
                show : true,
                placement : 'center',
                animation: 'none',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])
.service('selectLikenessService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'selectLikenessCtrl',
                template : '/static/global/templates/select-likeness.html',
                show : true,
                placement : 'center',
                animation: 'none',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])

/**
 * ===================== CONTROLLERS =====================
 */
.controller('createCardCtrl', [ '$scope', function($scope) {
    $scope.ok = function() {
        alert('OK clicked');
    };
} ]).controller('editCardCtrl', [ '$scope', '$rootScope', function($controllerScope, $rootScope) {
    $controllerScope.genders = [ {
        id : 0,
        name : 'женщина'
    }, {
        id : 1,
        name : 'мужчина'
    } ];
    if ($controllerScope.editedHuman == undefined) {
        $controllerScope.editedHuman = {
            fatherName : '',
            gender : 0,
            firstName : '',
            surName : '',
            imageName: 'no_image.jpg'
        };
    }
    $controllerScope.currentGender = $controllerScope.genders[$controllerScope.editedHuman.gender];
    $controllerScope.editGender = function(gender) {
        $controllerScope.editedHuman.gender = gender.id;
        $controllerScope.currentGender = gender;
    };
    $controllerScope.$on('flow::fileSuccess', function($scope, $flow, file, response){
        if(response != null) {
            $controllerScope.editedHuman.imageName = response;
            $controllerScope.onClose($controllerScope.editedHuman);
            $controllerScope.$hide();
        } else {
            $controllerScope.errorMessage = 'Фото не было загружено. Выберите другое фото';
        }
    });
    
    $controllerScope.ok = function() {
        $controllerScope.errorMessage = null;
        var fileIndex = $controllerScope.$flow.files.length  - 1;
        if(fileIndex >= 0) {
            var fileType = $controllerScope.$flow.files[fileIndex].file.type;
            $controllerScope.$flow.opts.headers.fileType = fileType;
            $controllerScope.$flow.upload();
        } else {
            $controllerScope.onClose($controllerScope.editedHuman);
            $controllerScope.$hide();
        }
    };
} ]).controller('selectRelativeCtrl', [ '$scope', function($scope) {
    $scope.selected = function(human) {
        $scope.selectedCard = human;
        $scope.$hide();
        $scope.onClose($scope.selectedCard);
    };
} ]).controller('selectConnectionTypeCtrl', [ '$scope', function($scope) {
    $scope.currentConnectionType = $scope.connectionTypes[0];
    $scope.setCurrentConnectionType = function(connectionType) {
        $scope.currentConnectionType = connectionType;
    };
    $scope.ok = function() {
        $scope.$hide();
        $scope.onClose($scope.leftRelative, $scope.rightRelative, $scope.currentConnectionType);
    };
} ]).controller('selectLikenessCtrl', [ '$scope', function($scope) {
    $scope.ok = function() {
        $scope.$hide();
        $scope.onClose(true);
    };
    $scope.cancel = function() {
        $scope.$hide();
        $scope.onClose(false);
    };
} ]);