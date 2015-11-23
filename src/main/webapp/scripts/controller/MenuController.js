semApp.controller('MenuController', ['$scope', '$rootScope', '$location', function ($scope, $rootScope, $location) {
        $rootScope.actionsList = [
            {
                label: 'Home',
                url: '/AngularSpring/#/home',
                show: true
            },
            {
                label: 'Login',
                url: '/AngularSpring/#/login',
                show: true
            },
            {
                label: 'Register',
                url: '/AngularSpring/#/register',
                show: true
            },
            {
                label: 'Add Project',
                url: '/AngularSpring/#/addProject',
                show: false
            },
            {
                label: 'See projects',
                url: '/AngularSpring/#/projectslist',
                show: false
            }

        ];

        $scope.logout = function () {
            $rootScope.loggedInUser = {};
            $rootScope.actionsList[0].show = true;
            $rootScope.actionsList[1].show = true;
            $rootScope.actionsList[2].show = true;
            $rootScope.actionsList[4].show = false;
            $rootScope.actionsList[3].show = false;
            $location.url('/AngularSpring/#/home');
        };
    }]);
