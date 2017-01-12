var userApp=angular.module('userApp',[]);

userApp.controller('userCtrl', function($scope,$http){
	
	$scope.refreshUser=function(){
		$http.get('http://localhost:10080/restexample-rest/user').success(function(data){
			$scope.users=data;
		});
	};
	
	$scope.deleteUser=function(username){
		alert('delete function called')
		$http['delete']('http://localhost:10080/restexample-rest/user/'+username).success(function(){
			$scope.refreshUser();
		});
	};
	
	
	
});