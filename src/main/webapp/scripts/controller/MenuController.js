semApp.controller('MenuController', ['$scope', function ($scope) {
    $scope.actionsList = [
        {
          label: 'Home',
          url: '/AngularSpring/#/home',
          show: true
        },
        {
            label: 'Login',
            url: '/AngularSpring/#/login',
            show: true
        },
        {
            label: 'Register',
            url: '/AngularSpring/#/register',
            show: true
        },
        {
            label: 'Submit Project',
            url: '/AngularSpring/#/addproject',
            show: true
        },
        {
            label: 'See projects',
            url: '/AngularSpring/#/projectslist',
            show:true
        }
    ];
}]);
