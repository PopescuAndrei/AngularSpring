semApp.controller('ProjectViewController', ['$scope', '$http', '$routeParams', '$location', 'projectService',
    function ($scope, $http, $routeParams, $location, projectService) {
        $scope.project = {};


        $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/' + $routeParams.projectId, method: 'GET'}).
                success(function (data) {
                    $scope.project = data;
                });

        $scope.back = function () {
            $location.url('/projectslist');
        };
    }]);