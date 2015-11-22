semApp.controller('ProjectsListController', ['$scope', '$http','$location',
    function ($scope, $http, $location) {

        $scope.projects = [];
        
        $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/all', method: 'GET'}).
                success(function (data) {
                    $scope.projects = data;
                });

         $scope.viewProject = function (projectId) {
            $location.url('#/projectview' + projectId);
        };
        
    }]);