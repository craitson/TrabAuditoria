var app = angular.module('trab_auditoria', ['ngRoute', 'ngSanitize']);

/** Configuração das rotas */
app.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
                //  .when("/", {templateUrl: "index.html"})

                .when("/viewClientes", {templateUrl: "templates/viewClientes.html"})

                .when("/viewCidades", {templateUrl: "templates/viewCidades.html"})

                .when("/viewProdutos", {templateUrl: "templates/viewProduto.html"})

                .when("/viewClientes", {templateUrl: "templates/viewClientes.html"})

                .when("/viewUsuarios", {templateUrl: "templates/viewUsuarios.html"})

                .when("/cadUsuarios", {templateUrl: "templates/CadUsuario.html"})

                .when("/cadCidade", {templateUrl: "templates/CadCidade.html", controller: "cadCidadeControl"})

                .when("/cadProduto", {templateUrl: "templates/CadProduto.html"})

                .when("/cadCliente", {templateUrl: "templates/CadCliente.html"})


                .otherwise({templateUrl: "templates/404.html"});
    }]);

app.controller('cadCidadeControl', function ($scope, $document, $timeout, $http) {

    $scope.salvarCidade = function (cidade) {
        $('#cadastrar .btn').addClass('disabled');
        $('#progresso').removeClass('hide');
        Materialize.toast('Aguarde cadastrando cidade!', 1000);
        debugger;
        $timeout(function () {
            $http({
                url: 'cadCid',
                method: 'POST',
                data: {'cidade': cidade}
                
            }).then(
                    function successCallback(response) {
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                        Materialize.toast('Cidade cadastrada com sucesso!', 7000);
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao cadastrar cidade!', 7000);
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                    }
            );
        }, 50);
    };


});


