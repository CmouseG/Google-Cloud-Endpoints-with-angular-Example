var learningApi = angular.module('learningApi', ['ui.router', 'angular-google-gapi', 'angular-google-api-example.router'])
.run(['GAuth', 'GApi', 'GData', '$state', '$rootScope', '$window',
    function(GAuth, GApi, GData, $state, $rootScope, $window) {

        $rootScope.gdata = GData;

        var CLIENT = '938739565497-i4njb8kpphdt3kqrn6ruuc2n480ik0q5.apps.googleusercontent.com';
        var BASE;
        if($window.location.hostname == 'localhost') {
            BASE = 'http://localhost:8080/_ah/api';
        } else {
            BASE = 'https://learning-api-project.appspot.com/_ah/api';
        }

        GApi.load('storyApi', 'v1', BASE);
        GAuth.setClient(CLIENT);
        GAuth.setScope('https://www.googleapis.com/auth/userinfo.email');
        GAuth.checkAuth().then(
            function () {
                if($state.includes('login'))
                    $state.go('home');
            }, function() {
                $state.go('login');
            }
        );

        $rootScope.logout = function() {
            GAuth.logout().then(
            function () {
                $state.go('login');
            });
        };
    }
]);