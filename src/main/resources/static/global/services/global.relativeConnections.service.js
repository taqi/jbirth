angular.module('global.services').factory('relativeConnectionsResource', [ '$resource', function($resource) {
    return $resource("/api/connections/:action/:id:genderLeft/:genderRight", {
        id : "@id"
    }, {
        "available" : {
            isArray: true,
            method : "GET",
            params : {
                action: "types"
            }
        }
    });
} ]);