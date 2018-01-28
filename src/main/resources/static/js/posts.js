angular.module('posts', [])
    .controller('Hello', function ($scope, $http) {
        $http.get('localhost:8080/posts').then(function (response) {
            $scope.posts = response.data;
        });
    });