angular.module('global.services')
.factory('allianceResource', ['$resource', function($resource) {
    return $resource(
        "/api/alliance/:Id",
        {Id: "@Id" },{
        }
    );
} ]);