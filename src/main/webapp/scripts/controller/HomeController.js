semApp.controller('HomeController', ['$scope','$rootScope', function($scope,$rootScope) {
    $scope.title = 'Semantic Web Application';
    $scope.description = "Application for managagin submiting project proposals and managing the respective proposals";
    $scope.user = $rootScope.loggedInUser;
}]);