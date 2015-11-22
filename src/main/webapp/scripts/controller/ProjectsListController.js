semApp.controller('ProjectsListController', ['$scope', '$http', '$location', '$routeParams',
    function ($scope, $http, $location, $routeParams) {

        $scope.projects = [];
        $scope.domain = '';
        $scope.budget = '';
        $scope.duration = '';
        $scope.checkbox = undefined;
        $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/all', method: 'GET'}).
                success(function (data) {
                    $scope.projects = data;
                });
        $scope.viewProject = function (projectId) {
            $location.url('/projectslist/' + projectId);
        };
        $scope.getAllProjects = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/all', method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };
        $scope.getCompleteProjects = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/complete', method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };
        $scope.getAllProjects = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/all', method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };
        $scope.getAllProjectsWithBudget = function () {
            if ($scope.checkbox === 'Greater') {
                console.log($scope.checkbox.value());
                return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getWithBudget', method: 'GET', data: {'budgetValue': $scope.budget, 'operator': 'Greater'}})
                        .success(function (data) {
                            $scope.projects = data;
                        });
            } else {
                console.log($scope.checkbox);
                console.log($scope.budgetValue);
                return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getWithBudget', method: 'GET', data: {'budgetValue': $scope.budget, 'operator': 'Lower'}})
                        .success(function (data) {
                            $scope.projects = data;
                        });
            }
        };
    }]);