semApp.controller('LoginController', ['$scope', '$http', '$location', '$rootScope',
    function ($scope, $http, $location, $rootScope) {
        $rootScope.loggedInUser = {
            id: "",
            role: "",
            username: "",
            password: "",
            firstName: "",
            lastName: "",
            mail: ""

        };


        $scope.password = '';
        $scope.mail = '';
        $scope.requiredErrorMessage = "Please fill out this form";

        $scope.reset = function () {
            this.mail = {};
            this.passsword = {};
        };

        $scope.create = function (mail, password) {
            $http({url: 'http://localhost:8080/AngularSpring/mvc/users/login', method: 'GET', params: {'mail': mail, 'password': password}}).
                    success(function (data) {
                        $rootScope.loggedInUser = data;
                        if ($rootScope.loggedInUser.role === 'director') {
                            $rootScope.actionsList[1].show = false;
                            $rootScope.actionsList[2].show = false;
                            $rootScope.actionsList[3].show = false;
                            $rootScope.actionsList[4].show = true;
                        } else if ($rootScope.loggedInUser.role === 'proposer'){
                            $rootScope.actionsList[1].show = false;
                            $rootScope.actionsList[2].show = false;
                            $rootScope.actionsList[3].show = true;
                            $rootScope.actionsList[4].show = false;
                            
                        }
                        $location.url('/AngularSpring/#/home');
                    });
        };
    }]);