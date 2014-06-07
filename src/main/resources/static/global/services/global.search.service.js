angular.module('global.services').factory('searchService', [ '$resource', function($resource) {
    return $resource("/api/search", {}, {
        "get" : {
            method : "GET",
            isArray : false
        }
    });
} ]);
