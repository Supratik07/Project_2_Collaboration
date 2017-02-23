(function(){
	'use strict';
	console.log("inside friend controller");
	angular.module('app')
	.controller('FriendController',FriendController);
	
	FriendController.$inject=['UserService','$scope','FriendService','$location','$rootScope']
	
	function FriendController(UserService,$scope,FriendService,$location,$rootScope){
		var vm=this;
		vm.friend=null;
		vm.friends=[];
		vm.user=null;
		vm.users=[];
		vm.friendRequests=[];
		
		vm.sendFriendRequest=sendFriendRequest;
		vm.getMyFriends=getMyFriends;
		vm.updateFriendRequest=updateFriendRequest;
		vm.deleteFriend=deleteFriend;
		vm.fetchAllUsers=fetchAllUers;
		vm.acceptFriendRequest=acceptFriendRequest;
		vm.rejectFriendRequest=rejecttFriendRequest;
		
		fetchAllUsers();
		getMyFriends();
		getMyFriendrequests();
		
		function sendFriendRequsest(friendId){
			console.log("->sendFriendRequest: "+friendId)
			FriendService.sendFriendRequest(friendId)
			.then(
					function(d){
						vm.friend=d;
						alert("friend Request sent")
					},
					function(errResponse){
						console.error('Error while sending friend request')
					}
						
					);
		}
		
		function acceptFriendRequest(friendID){
			console.log("->AcceptFriendRequest: "+friendId)
			FriendService.acceptFriendrequest(friendId)
			.then(
					function(d){
						vm.friend=d;
						getMyFriendRequests();
						alert("Friend request accepted");
						
					},
					function(errResponse){
						console.error('Error while sending friend requests');
					});
		}
		
		function rejectFriendRequest(friendID){
			console.log("->RejecttFriendRequest: "+friendId)
			FriendService.rejecttFriendrequest(friendId)
			.then(
					function(d){
						vm.friend=d;
						getMyFriendRequests();
						alert("Friend request rejected");
						
					},
					function(errResponse){
						console.error('Error while rejecting friend requests');
					});
			
			
		}
		function getMyFriends(){
			console.log("Getting My friends")
			FriendService.getMyFriends()
			.then(
					function(d){
						vm.friends=d;
						console.log("Got the friend list")
					},
					function(errResponse){
						console.error('Error while fetching friends');
					});
		}
		function getMyFriendRequets(){
			console.log("Getting my friendrequests")
			FriendService.getMyFriendRequests()
			.then(
					function(d){
						vm.friends=d;
						console.log("Got the friend list")
					},
					function(errResponse){
						console.error('Error while fetching friends');
					});
		}
	
	}
})();