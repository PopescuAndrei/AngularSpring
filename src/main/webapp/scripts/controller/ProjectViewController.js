semApp.controller('ProjectViewController', ['$scope', '$http', '$routeParams', '$location',
    function ($scope, $http, $routeParams, $location) {
        $scope.project = {};


        $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/' + $routeParams.projectId, method: 'GET'}).
                success(function (data) {
                    $scope.project = data;
                    console.log($scope.project);
                });

        $scope.back = function () {
            $location.url('/projectslist');
        };
    }]);