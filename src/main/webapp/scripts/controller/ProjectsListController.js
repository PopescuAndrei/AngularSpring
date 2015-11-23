semApp.controller('ProjectsListController', ['$scope', '$http', '$location', '$routeParams',
    function ($scope, $http, $location, $routeParams) {

        $scope.projects = [];
        $scope.domain = '';
        $scope.budget = undefined;
        $scope.duration = '';
        $scope.sum = undefined;
        $scope.excess = undefined;
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

        $scope.getAllProjectsWithBudget = function () {
            if ($scope.checkbox === 'Greater') {
                return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getWithBudget', method: 'GET', params: {'budget': $scope.budget, 'operator': 'Greater'}})
                        .success(function (data) {
                            $scope.projects = data;
                        });
            } else {
                return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getWithBudget', method: 'GET', params: {'budget': $scope.budget, 'operator': 'Lower'}})
                        .success(function (data) {
                            $scope.projects = data;
                        });
            }
        };

        $scope.getAllProjectsWithDuration = function () {
            if ($scope.checkbox === 'Greater') {
                return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getWithDuration', method: 'GET', params: {'duration': $scope.budget, 'operator': 'Greater'}})
                        .success(function (data) {
                            $scope.projects = data;
                        });
            } else {
                return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getWithDuration', method: 'GET', params: {'duration': $scope.budget, 'operator': 'Lower'}})
                        .success(function (data) {
                            $scope.projects = data;
                        });
            }
        };

        $scope.getProjectsBudgetDuration = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getWithBudgetDuration', method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };

        $scope.getProjectsMorePartners = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getProjectsMorePartners', method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };

        $scope.getProjectsWorkingPackages = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getProjectsWorkingPackages', method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };

        $scope.getSumProjects = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getSumProjects', method: 'GET'}).
                    success(function (data) {
                        $scope.sum = data;
                    });
        };

        $scope.getExcess = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getExcess', method: 'GET'}).
                    success(function (data) {
                        $scope.excess = data;
                    });
        };

        $scope.getNoPmProjects = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/getNoPmProjects', method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };

        $scope.getAllProjectsWithDomain = function () {
            return $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/domain?domain=' + $scope.domain, method: 'GET'}).
                    success(function (data) {
                        $scope.projects = data;
                    });
        };
    }]);