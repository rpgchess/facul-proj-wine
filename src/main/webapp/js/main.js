(function (angular){
	'use strict';
	
var app = angular.module('wine', ['ngRoute']);

// Definindo Rotas
app.config(function($routeProvider, $locationProvider){
	// Utilizando o HTML5 History API (TRUE)
	$locationProvider.html5Mode(true);
    //$locationProvider.hashPrefix("!");
	
	$routeProvider
		.when("/", {controller: "listController", templateUrl: "pages/list.html"})
		.when("/about", {controller: "aboutController", templateUrl: "pages/about.html"})
		.when("/edit/:obj", {controller: "editController", templateUrl: "pages/form.html"})
		.when("/del/:obj", {controller: "delController", templateUrl: "pages/list.html"})
		.when("/new", {controller: "newController", templateUrl: "pages/form.html"})
		.otherwise({redirectTo: "/"});
});

app.run(function($rootScope, $location, $http) {
	$rootScope.baseUrl = "http://localhost:9999";
	$rootScope.wines = [];
	$rootScope.wine = [];
	$rootScope.home = function (){
		$location.path('/');
	};
	$rootScope.update = function (){
		var link = $rootScope.baseUrl + "/listar";
		$http({
			method: 'POST',
			url: link
		}).then(function successCallback(response) {
			$rootScope.wines = response.data;
			console.log(response.data);
		});
	};
	$rootScope.alert_reset = function (){
		$("#toggleCSS").attr("href", "css/alertify.default.css");
		alertify.set({
			labels : {
				ok     : "OK",
				cancel : "Cancel"
			},
			delay : 5000,
			buttonReverse : false,
			buttonFocus   : "ok"
		});
	};
});

app.controller('aboutController', ['$scope', '$http', '$routeParams', '$rootScope', '$route', '$location', function aboutController($scope, $http, $routeParams, $routeScope, $route, $location) {
	
}]);

app.controller('delController', ['$scope', '$http', '$routeParams', '$rootScope', '$route', '$location', function delController($scope, $http, $routeParams, $routeScope, $route, $location) {
	$scope.alert_reset();
	alertify.confirm("Deseja remover este item?", function (e) {
		if (e) {
			$scope.update();
			var link = $scope.baseUrl + "/deletar";
			$scope.wine = angular.fromJson($routeParams.obj);
			$scope.id = $scope.wine.id;
		    $scope.name = $scope.wine.nome;
		    $scope.year = $scope.wine.ano;
		    $scope.type = $scope.wine.tipo;
		    $scope.price = $scope.wine.valor;
			var wine = { id : $scope.id, nome : $scope.name, valor : $scope.price, ano : $scope.year, tipo : $scope.type };
			$http({
		        method: "POST",
		        url: link,
		        data: wine,
		        headers: {'Content-Type': 'application/json; charset=utf-8'}
			});
			alertify.success("Removido com sucesso");
			$scope.home();
		};
	});
	//$scope.home();
}]);

app.controller('listController', ['$scope', '$http', '$routeParams', '$rootScope', '$route', '$location', function listController($scope, $http, $routeParams, $routeScope, $route, $location) {
	$scope.update();
}]);

app.controller('editController', ['$scope', '$http', '$routeParams', '$rootScope', '$route', '$location', function editController($scope, $http, $routeParams, $routeScope, $route, $location) {
	$scope.wine = angular.fromJson($routeParams.obj);
	$scope.id = $scope.wine.id;
    $scope.name = $scope.wine.nome;
    $scope.year = $scope.wine.ano;
    $scope.type = $scope.wine.tipo;
    $scope.price = $scope.wine.valor;
	$scope.salvar = function (){
		var link = $scope.baseUrl + "/editar";
		var wine = { id : $scope.id, nome : $scope.name, valor : $scope.price, ano : $scope.year, tipo : $scope.type };
		$http({
	        method: "POST",
	        url: link,
	        data: wine,
	        headers: {'Content-Type': 'application/json; charset=utf-8'}
		});
		alertify.success("Editado com sucesso!");
		$scope.home();
	};
}]);

app.controller('newController', ['$scope', '$http', '$routeParams', '$rootScope', '$route', '$location', function newController($scope, $http, $routeParams, $routeScope, $route, $location) {
	$scope.salvar = function (){
		var link = $scope.baseUrl + "/novo";
		$scope.wine = { id : 0, nome : $scope.name, valor : $scope.price, ano : $scope.year, tipo : $scope.type };
		$http({
	        method: "POST",
	        url: link,
	        data: $scope.wine,
	        headers: {'Content-Type': 'application/json; charset=utf-8'}
		});
		$scope.name = "";
		$scope.year = "";
		$scope.type = "";
		$scope.price = "";
		$scope.alert_reset();
		alertify.success("Adicionado com sucesso!");
		//alertify.success("Todos os campos limpos!");
		//alertify.success("Pronto para adicionar um novo item!");
		$scope.home();
	};
}]);

})(window.angular);