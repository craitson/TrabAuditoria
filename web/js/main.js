var app = angular.module('teste',['ngRoute', 'ngSanitize']);

/** Configuração das rotas */
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/", {templateUrl: "templates/viewCidades.html"})

        .otherwise({templateUrl: "templates/404.html"});
}]);
