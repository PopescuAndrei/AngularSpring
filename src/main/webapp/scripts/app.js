var semApp = angular.module('semApp', ['ngRoute']);
semApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider

                .when('/projectview/:id', {
                    templateUrl: 'views/projectview.html',
                    controller: 'ProjectViewController'
                })
                .when('/register', {
                    templateUrl: 'views/register.html',
                    controller: 'RegisterController'
                })
                .when('/projectslist', {
                    templateUrl: 'views/projects.html',
                    controller: 'ProjectsListController'
                })
                .when('/completeprojects', {
                    templateUrl: 'views/projects.html',
                    controller: 'CompleteProjectsController'
                })
                .when('/projectview/:projectId', {
                    templateUrl: 'views/projectview.html',
                    controller: 'ProjectViewController'
                })
                .otherwise({
                    templateUrl: 'views/home.html',
                    controller: 'HomeController'
                });
    }])
        .
        run(['$rootScope',
            function ($rootScope) {

            }
        ]);