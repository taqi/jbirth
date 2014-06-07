angular.module('global.services').factory('biographyResource', [ '$resource', function($resource) {
    return $resource("/api/biography/:relativeId/", {
        relativeId : "@targetRelativeId"
    }, {
        "getFacts" : {
            method : "GET",
            isArray : true,
            params: {
                relativeId:"@targetRelativeId" 
            },
        },
        "saveFact" : {
            method : "POST"
        }
    });
} ]);
