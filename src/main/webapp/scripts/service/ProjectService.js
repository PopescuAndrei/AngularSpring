semApp.service('projectService', ['$http', function ($http) {
        return {
            findById: function (id) {
                $http.get('http://localhost:8080/AngularSpring/mvc/projects/' + id)
                        .success(function (data) {
                            return data;
                        });
            }
        };
    }]
        );