semApp.service('projectService', ['$http', function ($http) {
        return {
            findById: function (id) {
                $http.get('http://localhost:8080/AngularSpring/mvc/projects/' + id)
                    .success(function (data) {
                        return data;
                    })
                    .error(function (data) {
                        return {
                            "id": 100,
                            "title": "Steven",
                            "budget": "100",
                            "duration": "200"
                        };
                    });
            }
        };
    }]
);