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

                .when("/viewVendas", {templateUrl: "templates/viewVendas.html"})

                .when("/cadUsuarios", {templateUrl: "templates/CadUsuario.html", controller: "cadUsuarioControl"})

                .when("/cadCidade", {templateUrl: "templates/CadCidade.html", controller: "cadCidadeControl"})

                .when("/cadProduto", {templateUrl: "templates/CadProduto.html", controller: "cadProdControl"})

                .when("/cadCliente", {templateUrl: "templates/CadCliente.html", controller: "cadClienteControl"})

                .when("/cadVenda", {templateUrl: "templates/CadVenda.html", controller: "cadVendaControl"})

                .when("/consultaCidade", {templateUrl: "templates/ConsultaCidades.html", controller: "consultaCidadesControl"})

                .when("/consultaUsuario", {templateUrl: "templates/ConsultaUsuario.html", controller: "consultaUsuarioControl"})

                .when("/consultaProduto", {templateUrl: "templates/ConsultaProdutos.html", controller: "consultaProdutosControl"})

                .when("/consultaCliente", {templateUrl: "templates/ConsultaClientes.html", controller: "consultaClientesControl"})

                .when("/consultaVenda", {templateUrl: "templates/ConsultaVenda.html", controller: "consultaVendaControl"})






        // .otherwise({templateUrl: "templates/404.html"});
    }]);


app.controller('consultaVendaControl', function ($scope, $document, $timeout, $http, $route) {

    $scope.AlterVenda = function (venda) {

        var produto = angular.fromJson($('#produto').val());
        var cliente = angular.fromJson($('#cliente').val());

        venda.cliente = {codigo: cliente.codigo};
        venda.produto = {codigo: produto.codigo};
        venda.valor_pago = venda.quantidade * produto.preco;

        debugger;
        $timeout(function () {
            $http({
                url: 'Venda',
                method: 'PUT',
                data: venda
                        
            }).then(
                    function successCallback(response) {
                        Materialize.toast('Venda alterada com sucesso!', 7000);
                        $route.reload();
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao alterar Venda!', 7000);
                    }
            );
        }, 50);
    };

    $scope.atuPreco = function (venda) {
        var produto = angular.fromJson($('#produto').val());
        var cliente = angular.fromJson($('#cliente').val());

        if (produto.estoque < venda.quantidade) {
            $scope.total = "Produto não pode ser vendido por que estoque é menor que a quantidade informada! Quantidade atual em estoque " + produto.estoque;
            $(".altera").addClass("disabled");
        } else {
            $(".altera").removeClass("disabled");
            $scope.total = (venda.quantidade * produto.preco);
        }
    };

    $scope.ShowModalAlterVenda = function (venda) {
        $scope.venda = venda;
        debugger;
        $timeout(
                $http({
                    url: "Clientes",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaClientes = response.data;
        }), 50);
        $timeout(
                $http({
                    url: "Produtos",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaProdutos = response.data;
        }), 50);
        $('#modal_alteraVenda').openModal();
    };


    $scope.excluirVenda = function (venda) {
        debugger;
        $http({
            url: 'Venda',
            method: 'DELETE',
            params: {cod: venda.codigo}
        }).then(
                function successCallback(response) {
                    Materialize.toast('Sucesso: Venda excluida.', 4000);
                    $route.reload();
                },
                function errorCallback(response) {
                    Materialize.toast('Erro: Não foi possível excluir a venda.', 4000);
                }
        );
    };
    $document.ready(function () {
        $timeout(
                $http({
                    url: "Venda",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaVenda = response.data;
            debugger;
        }), 50);
    });
});


app.controller('cadVendaControl', function ($scope, $document, $timeout, $http) {


    $scope.CadVenda = function (venda) {
        var produto = angular.fromJson($('#produto').val());
        var cliente = angular.fromJson($('#cliente').val());

        venda.cliente = {codigo: cliente.codigo};
        venda.produto = {codigo: produto.codigo};
        venda.valor_pago = venda.quantidade * produto.preco;

        debugger;

        $timeout(function () {
            $http({
                url: 'cadVenda',
                method: 'POST',
                data: venda

            }).then(
                    function successCallback(response) {
                        $('#cadastrar .btn').removeClass('disabled');
                        Materialize.toast('Venda Cadastrada!', 7000);
                        $('#qtd').val("");
                        $scope.total = "";
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao cadastrar venda!', 7000);
                    }
            );
        }, 50);

    };


    $scope.atuPreco = function (venda) {
        var produto = angular.fromJson($('#produto').val());
        var cliente = angular.fromJson($('#cliente').val());

        if (produto.estoque < venda.quantidade) {
            $scope.total = "Produto não pode ser vendido por que estoque é menor que a quantidade informada! Quantidade atual em estoque " + produto.estoque;
            $(".cadastrar").addClass("disabled");
        } else {
            $(".cadastrar").removeClass("disabled");
            $scope.total = (venda.quantidade * produto.preco);
        }
    };



    $document.ready(function () {
        $timeout(
                $http({
                    url: "Clientes",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaClientes = response.data;
        }), 50);
        $timeout(
                $http({
                    url: "Produtos",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaProdutos = response.data;
        }), 50);
    });
});

app.controller('consultaClientesControl', function ($scope, $document, $timeout, $http, $route) {


    $scope.ShowModalAlterCli = function (cliente) {
        $scope.cliente = cliente;
        debugger;
        $scope.cliente.nascimento = new Date($scope.cliente.nascimento);
        $timeout(
                $http({
                    url: "Cidades",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaCidades = response.data;
        }), 50);
        $('#modal_alteraCli').openModal();
    };


    $scope.AlterCli = function (cliente) {
        cliente.ativo = $('#ativo').val();
        cliente.sexo = $('#sexo').val();
        cliente.cep = $('#cep').val();
        cliente.nascimento = $('#nasci').val();
        cliente.cidade = {cep: $('#cep').val()};
        $('#cadastrar .btn').addClass('disabled');
        $('#progresso').removeClass('hide');
        debugger;
        $timeout(function () {
            $http({
                url: 'Clientes',
                method: 'PUT',
                data: {'cliente': cliente}

            }).then(
                    function successCallback(response) {
                        Materialize.toast('Cliente alterado com sucesso!', 7000);
                        $route.reload();
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao alterar cliente!', 7000);
                    }
            );
        }, 50);
    };


    $document.ready(function () {
        $scope.listaClientes = [];
        // $('select').material_select();
        $timeout(
                $http({
                    url: "Clientes",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaClientes = response.data;
        }), 50);
    });

    $scope.excluirCli = function (cod) {
        debugger;
        $http({
            url: 'Clientes',
            method: 'DELETE',
            params: {cod: cod}
        }).then(
                function successCallback(response) {
                    Materialize.toast('Sucesso: Cliente excluido.', 4000);
                    $route.reload();
                },
                function errorCallback(response) {
                    Materialize.toast('Erro: Não foi possível excluir o cliente.', 4000);
                }
        );
    };
});


app.controller('consultaUsuarioControl', function ($scope, $document, $timeout, $http, $route) {

    $scope.alterUsuario = function (usuario) {
        usuario.tipo = $('#tipo').val();
        usuario.ativo = $('#ativo').val();
        Materialize.toast('Aguarde alterando usuario!', 1000);
        debugger;
        $timeout(function () {
            $http({
                url: 'Usuarios',
                method: 'PUT',
                data: {'usuario': usuario}

            }).then(
                    function successCallback(response) {
                        $route.reload();
                        Materialize.toast('Usuario alterado com sucesso!', 7000);
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao alterar usuario!', 7000);
                    }
            );
        }, 50);
    };

    $scope.ShowModalAlterUsuario = function (usuario) {
        $scope.usuario = usuario;
        $('#modal_alteraUser').openModal();
    };



    $scope.excluirUser = function (user) {
        debugger;
        $http({
            url: 'Usuarios',
            method: 'DELETE',
            params: {cod: user.codigo}
        }).then(
                function successCallback(response) {
                    $route.reload();
                    Materialize.toast('Sucesso: Usuario excluido.', 4000);
                },
                function errorCallback(response) {
                    Materialize.toast('Erro: Não foi possível excluir o usuario.', 4000);
                }
        );
    };


    $document.ready(function () {
        // $('select').material_select();
        $timeout(
                $http({
                    url: "Usuarios",
                    method: "GET"
                }).then(function successCallback(response) {
            //debugger;
            $scope.listaUsuarios = response.data;
        }), 50);
    });
});
app.controller('consultaProdutosControl', function ($scope, $document, $timeout, $http, $route) {

    $scope.alterProduto = function (produto) {
        $timeout(function () {
            $http({
                url: 'Produtos',
                method: 'PUT',
                data: {'produto': produto}

            }).then(
                    function successCallback(response) {
                        Materialize.toast('Produto alterado com sucesso!', 7000);
                        $route.reload();
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao alterar o produto!', 7000);
                    }
            );
        }, 50);
    };

    $scope.ShowModalAlterProd = function (produto) {
        $scope.produto = produto;
        $('#modal_alteraProd').openModal();
        debugger;
    };



    $scope.excluirProd = function (cod) {
        $http({
            url: 'Produtos',
            method: 'DELETE',
            params: {cod: cod}
        }).then(
                function successCallback(response) {
                    $route.reload();
                    Materialize.toast('Sucesso: Produto excluido.', 4000);
                },
                function errorCallback(response) {
                    Materialize.toast('Erro: Não foi possível excluir o produto.', 4000);
                }
        );
    };

    $document.ready(function () {
        // $('select').material_select();
        $timeout(
                $http({
                    url: "Produtos",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaProdutos = response.data;
        }), 50);
    });
});
app.controller('consultaCidadesControl', function ($scope, $document, $timeout, $http, $route) {

    //$scope.listaCidades = []; //s[{"cep":89190000,"nome":"Taio","estado":"SC"}];


    $scope.alterCid = function (cidade) {
        cidade.estado = $('#estado').val();
        debugger;
        $timeout(function () {
            $http({
                url: 'Cidades',
                method: 'PUT',
                data: {'cidade': cidade}

            }).then(
                    function successCallback(response) {
                        Materialize.toast('Cidade alterada com sucesso!', 7000);
                        $route.reload();
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao alterar a cidade!', 7000);
                    }
            );
        }, 50);
    };

    $scope.excluirCid = function (cidade) {
        debugger;
        $http({
            url: 'Cidades',
            method: 'DELETE',
            params: {cod: cidade.cep}
        }).then(
                function successCallback(response) {
                    $route.reload();
                    Materialize.toast('Sucesso: Cidade excluida.', 4000);
                },
                function errorCallback(response) {
                    Materialize.toast('Erro: Não foi possível excluir a cidade.', 4000);
                }
        );
    };

    $scope.ShowModalAlterCid = function (cidade) {
        $scope.cidade = cidade;
        $('#modal_alteraCidade').openModal();
    };



    $document.ready(function () {
        $timeout(
                $http({
                    url: "Cidades",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaCidades = response.data;
        }), 50);
    });
});
app.controller('cadClienteControl', function ($scope, $document, $timeout, $http) {

    //s[{"cep":89190000,"nome":"Taio","estado":"SC"}];

    $document.ready(function () {
        //$scope.listaCidades = [];
        // $('select').material_select();
        $timeout(
                $http({
                    url: "Cidades",
                    method: "GET"
                }).then(function successCallback(response) {
            $scope.listaCid = response.data;
            debugger;
        }), 100);
    });


    $scope.salvarCliente = function (cliente) {
        cliente.ativo = $('#ativo').val();
        cliente.sexo = $('#sexo').val();
        cliente.cep = $('#cep').val();
        cliente.nascimento = $('#nasci').val();
        cliente.cidade = {cep: $('#cep').val()};
        $('#cadastrar .btn').addClass('disabled');
        $('#progresso').removeClass('hide');
        Materialize.toast('Aguarde cadastrando cliente!', 1000);
        debugger;
        $timeout(function () {
            $http({
                url: 'cadCliente',
                method: 'POST',
                data: {'cliente': cliente}

            }).then(
                    function successCallback(response) {
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                        Materialize.toast('Cliente cadastrado com sucesso!', 7000);
                        $('#cadProd').each(function () {
                            this.reset();
                        });
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao cadastrar cliente!', 7000);
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                    }
            );
        }, 50);
    };
});
//$LISTACID = RESPOSE.CIDADE;



app.controller('cadProdControl', function ($scope, $document, $timeout, $http) {



    $scope.salvarProduto = function (produto) {

        $('#cadastrar .btn').addClass('disabled');
        $('#progresso').removeClass('hide');
        Materialize.toast('Aguarde cadastrando produto!', 1000);
        $timeout(function () {
            $http({
                url: 'cadProd',
                method: 'POST',
                data: {'produto': produto}

            }).then(
                    function successCallback(response) {
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                        Materialize.toast('Produto cadastradO com sucesso!', 7000);
                        $('#cadProd').each(function () {
                            this.reset();
                        });
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao cadastrar produto!', 7000);
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                    }
            );
        }, 50);
    };
});
app.controller('cadCidadeControl', function ($scope, $document, $timeout, $http) {

    $scope.salvarCidade = function (cidade) {
        $('#cadastrar .btn').addClass('disabled');
        $('#progresso').removeClass('hide');
        cidade.estado = $('#estado').val();
        Materialize.toast('Aguarde cadastrando cidade!', 1000);
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
                        $('#cadCidade').each(function () {
                            this.reset();
                        });
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


app.controller('cadUsuarioControl', function ($scope, $document, $timeout, $http) {

    $scope.salvarUsuario = function (usuario) {
        $('#cadastrar .btn').addClass('disabled');
        $('#progresso').removeClass('hide');
        usuario.tipo = $('#tipo').val();
        usuario.ativo = $('#ativo').val();
        Materialize.toast('Aguarde cadastrando usuario!', 1000);
        debugger;
        $timeout(function () {
            $http({
                url: 'cadUser',
                method: 'POST',
                data: {'usuario': usuario}

            }).then(
                    function successCallback(response) {
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                        Materialize.toast('Usuario cadastrada com sucesso!', 7000);
                        $('#cadUser').each(function () {
                            this.reset();
                        });
                    },
                    function errorCallback(response) {
                        Materialize.toast('Erro ao cadastrar usuario!', 7000);
                        $('#cadastrar .btn').removeClass('disabled');
                        $('#progresso').addClass('hide');
                    }
            );
        }, 50);
    };
});