semApp.controller('UsersListController', ['$scope', '$http',
    function ($scope, $http) {

        $scope.users = [];
       

        $http({url: 'http://localhost:8080/AngularSpring/mvc/users', method: 'GET'}).
                success(function (data) {
                    $scope.users = data;
                });
                
                
    }]);