(function(){
	'use strict';
	angualr
	.module('app')
	.factory('FriendService',FriendService);
	FriendService.$inject=['$http','$q','$rootScope'];
	function FriendService($http,$q,$rootscope){
		var BASE_URL='http://localhost:11026/angularauth-rest/user/'
			var service={};
		service.getMyFriends=getMyFriends;
		service.acceptFriendRequest=acceptFriendRequest;
		service.updateFriendRequest=updateFriendRequest;
		service.rejectFriendRequest=rejecttFriendRequest;
		service.deleteFriend=deleFriend;
		return service;
		
		function sendFriendRequest(friendId){
			return $http.get(BASE_URL+'/addFriend'/+friendID)
			.then(
					function(response){
						return response.data;
					},
					function (errResponse){
						console.error('Error while creating friend');
						return $q.reject(errResponse);
					}
			);
		}
	
	function getMyFriendRequests(){
		return $http.get(BASE_URL+'/getMyFriendRequests/')
		.then(
				function(response){
					return response.data;
				},
				null);
	}
	function acceptFriendRequests(){
		return $http.get(BASE_URL+'/acceptFriend/'+friendID)
		.then(
				function(response){
						return response.data;
					},
					function (errResponse){
						console.error('Error while accepting friend');
						return $q.reject(errResponse);
					});
	}
	function rejectFriendRequests(){
		return $http.get(BASE_URL+'/rejectFriend/'+friendID)
		.then(
				function(response){
						return response.data;
					},
					function (errResponse){
						console.error('Error while rejecting friend');
						return $q.reject(errResponse);
					});
	}
	function getMyFriends(){
		return $http.get(BASE_URL+'/myFriends')
		.then(
				function (response){
					return response.data;
				},
				null);
	}
	
	}
})