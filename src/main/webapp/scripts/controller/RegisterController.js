semApp.controller('RegisterController', ['$scope', '$http','$location',
    function ($scope, $http, $location) {
        $scope.user = {
            id:"",
            role:"proposer",
            username:"",
            password:"",
            firstName:"",
            lastName:"",
            mail:""
            
        };
        $scope.requiredErrorMessage = "Please fill out this form";

        $scope.reset = function () {
            console.log(user);
            this.user = {};
            this.user.role="proposer";
            console.log(user);
        };

        $scope.create = function (addUser) {
            $http({url: 'http://localhost:8080/AngularSpring/mvc/users/', method: 'POST', data: addUser, headers: {'Content-Type': 'application/json'}}).
                    success(function (data) {
                        $scope.user = data;
                        $location.url('/AngularSpring/#/home');
                    });
        }
    }]);