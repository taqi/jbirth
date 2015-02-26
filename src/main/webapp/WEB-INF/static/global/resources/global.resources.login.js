angular.module('global.resources')
.factory('loginResource', [ '$resource', function($resource) {
    return $resource("/api/auth/:action", {}, {
        login : {
            method : "POST",
            params : {
                action : "login"
            }
        }
    });
} ]);