var learningApi = angular.module('learningApi', ['angular-google-gapi'])
.run(['GApi', 'GAuth', '$window',
 function(GApi, GAuth,$window) {
     var BASE;
     if($window.location.hostname == 'localhost') {
        BASE = '//localhost:8080/_ah/api';
     } else {
        BASE = 'https://learning-api-project.appspot.com/_ah/api';
     }
     GApi.load('storyApi','v1',BASE);
     GAuth.setScope("https://www.googleapis.com/auth/userinfo.email"); // default scope is only https://www.googleapis.com/auth/userinfo.email
 }
]);