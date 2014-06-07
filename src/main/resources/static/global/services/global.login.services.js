angular.module('global.services').service('loginModalService', [ '$modal', function($modal) {
    return {
        openModalWindow : function(modalScope) {
            var myModal = $modal({
                controller : 'loginCtrl',
                template : '/static/global/templates/login.html',
                show : true,
                placement : 'center',
                animation: 'none',
                scope : modalScope
            });
            return myModal;
        }
    };
} ])

.service('authService', [ '$modal', '$http', '$rootScope', function($modal, $http, $rootScope) {
  return {
    whoAmI: function(callback, errorCallback) {
      $http.get("/api/auth/whoami")
      .success(function(resp){
        $rootScope.currentUser = resp; 
        callback(resp);
      })
      .error(function(resp){
        errorCallback(resp);
      });
    },
    logout: function(callback, errorCallback) {
      $http.get("/api/auth/logout")
      .success(function(resp){
        callback(resp);
      })
      .error(function(resp){
        errorCallback(resp);
      });
    }
  };
} ])

.service('oauthLoginService', [ '$window', '$http', function($window, $http) {
    return {
        openModalWindow : function(apiCall, modalScope) {
            $http.get(apiCall).success(function(data) {
                var modalWindow = $window.open(data, '', 'width=400, height=400');
                var timer = setInterval(function() {
                    if (modalWindow.closed) {
                        clearInterval(timer);
                        modalScope.onClose({});
                    }
                }, 3000);
            }).error(function(data) {
                alert("connection error\n" + data);
            });
        }
    };
} ])

.controller('loginCtrl', [ '$scope', '$http', function($scope, $http) {
  $scope.keyPressed = function(event) {
    if (event.which==13)
      $scope.ok();
  };
  $scope.ok = function() {
    $scope.loginError = false;
    var data = {
      login : $scope.loginData.login,
      password : $scope.loginData.password
    };
    $http.post('/api/auth/login', data).success(function(data, status, headers, config) {
      $scope.$hide();
      $scope.onClose(data);
    }).error(function(data, status, headers, config) {
      $scope.loginError = true;
      $scope.loginErrorMessage = data;
    });
  };
} ]);