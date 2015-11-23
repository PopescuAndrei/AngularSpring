semApp.controller('ProjectAddController', ['$scope', '$http','$location',
    function ($scope, $http, $location) {
        $scope.project = {
            id:"",
            title:"",
            budget:undefined,
            duration:undefined,
            partners:[],
            domain:"",
            objective:"",
            stages:[]
        };
        
        $scope.message = '';
        
        $scope.partner = {
            name:"",
            leader:undefined
        };
        
        $scope.stage = {
            name:"",
            duration:undefined,
            description:""
        };
        
        $scope.requiredErrorMessage = "Please fill out this form";


        $scope.addPartner = function(){
            $scope.project.partners.push($scope.partner);
        };
        
        $scope.addStage = function(){
            $scope.project.stages.push($scope.stage);
        };
        
        $scope.reset = function () {
            $scope.user = {};
        };

        $scope.create = function (addProject) {
            $http({url: 'http://localhost:8080/AngularSpring/mvc/projects/', method: 'POST', data: addProject, headers: {'Content-Type': 'application/json'}}).
                    success(function (data) {
                        $scope.project = data;
                        $scope.message = "Saved Succesfull";
                    });
        };
        
    }]);